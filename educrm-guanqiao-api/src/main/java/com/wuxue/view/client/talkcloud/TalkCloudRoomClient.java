package com.wuxue.view.client.talkcloud;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.wuxue.model.Product;
import com.wuxue.model.QuestionsItem;
import com.wuxue.model.Schedule;
import com.wuxue.model.TalkCloudRoom;
import com.wuxue.utils.common.AESUtil;
import com.wuxue.utils.common.MD5Util;
import com.wuxue.utils.contract.Response;
import com.wuxue.utils.https.ParamsBean;
import com.wuxue.utils.https.httpRequestSend;
import com.wuxue.view.client.TalkCloudBaseClient;
import com.wuxue.view.enums.ActionEnum;
import com.wuxue.view.interfaces.IFindByPrimaryKeyClient;
import com.wuxue.view.interfaces.IFindClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


/**
 *
 * description:
 * @auther: wh
 * @date: 2018/6/7 11:17
 */
@Service
public class TalkCloudRoomClient extends TalkCloudBaseClient implements
		IFindByPrimaryKeyClient<TalkCloudRoom,Response<TalkCloudRoom>>, IFindClient<TalkCloudRoom, Response<List<TalkCloudRoom>>> {


	@Value("${talk.cloud_url}")
    protected String cloud_url;
	
	@Value("${talk.key}")
    protected String key;

	@Value("${talk.AES.key}")
	protected String AES_key;

	/**
	 * 所属页面名称
	 * @return
	 */
	@Override
	protected String getPageName() {
		return "/room";
	}


	/**
	 * 根据主键查询
	 * @param talkCloudRoom
	 * @return
	 */
	@Override
	public Response<TalkCloudRoom> findByPrimaryKey(TalkCloudRoom talkCloudRoom) {
		String responseXml = POST(getSendUrl(ActionEnum.FIND),talkCloudRoom);
		Response<TalkCloudRoom> response = JSON.parseObject(responseXml,new TypeReference<Response<TalkCloudRoom>>(){});
		return response;
	}


	/**
	 * save talkCloudRoom
	 * @param questions
	 * @return
	 */
	public Response<Object> saveTalkClourRoom(TalkCloudRoom questions ) {
		String responseXml = POST(getSendUrl(ActionEnum.SAVE),questions);
		Response<Object> response = JSON.parseObject(responseXml,new TypeReference<Response<Object>>(){});
		return response;
	}

	/**
	 * 查询列表
	 * @param talkCloudRoom
	 * @return
	 */
	@Override
	public Response<List<TalkCloudRoom>> find(TalkCloudRoom talkCloudRoom) {
		String responseXml = POST(getSendUrl(ActionEnum.FIND), talkCloudRoom);
		Response<List<TalkCloudRoom>> response = JSON.parseObject(responseXml, new TypeReference<Response<List<TalkCloudRoom>>>() {});
		return response;
	}

    /**
     *保存/修改教室
     * @param talkCloudRoom
     * @return
     */
    public String save(TalkCloudRoom talkCloudRoom){
    	//输入中文和特殊字符加密
    	String roomName = MD5Util.encode(talkCloudRoom.getRoomname());
    	
    	List<ParamsBean> list = new ArrayList<ParamsBean>();
		String url = cloud_url+"/WebAPI/roomcreate";
		list.add(new ParamsBean("key",key)); //必填 企业id  authkey
		list.add(new ParamsBean("roomname",roomName)); //必填 房间名称必填；如果有中文请使用UTF8编码，特殊字符需使用urlencode转义
		list.add(new ParamsBean("roomtype", talkCloudRoom.getRoomtype()));//教室类型  0:1对1   3：1对多
		list.add(new ParamsBean("starttime",talkCloudRoom.getStarttime())); //必填 房间开始时间戳(精确到秒)
		list.add(new ParamsBean("endtime",talkCloudRoom.getEndtime())); //必填 房间结束时间(精确到秒)
		list.add(new ParamsBean("chairmanpwd",talkCloudRoom.getChairmanpwd())); //必填 老师密码	必填，4=<长度<=16
		list.add(new ParamsBean("assistantpwd", talkCloudRoom.getAssistantpwd()));//必填，助教密码4=<长度<=16
		list.add(new ParamsBean("patrolpwd", talkCloudRoom.getPatrolpwd()));//必填，巡课密码4=<长度<=16
		//list.add(new ParamsBean("passwordrequired",talkCloudRoom.getPasswordrequired())); //选填 学生进入房间是否需要密码  0:否、1:是
		
		//if(talkCloudRoom.getPasswordrequired().equals("1") && talkCloudRoom.getConfuserpwd()!=null && !("").equals(talkCloudRoom.getConfuserpwd())){
		//	list.add(new ParamsBean("confuserpwd",talkCloudRoom.getConfuserpwd()));  //学生密码 passwordrequired = 1 时必填(4=<长度<=16)或者allowsidelineuser = 1 时必填
		//}
		
		//list.add(new ParamsBean("videotype",talkCloudRoom.getVideotype()));  //选填 视频分辨率 0：176x144   1：320x240   2：640x480   3：1280x720   4：1920x1080
		//list.add(new ParamsBean("videoframerate",talkCloudRoom.getVideoframerate()));  //帧率10,15,20,25,30
		//list.add(new ParamsBean("autoopenav",talkCloudRoom.getAutoopenav()));//0: 不自动开启  1：自动开启
    	
    	if(talkCloudRoom.getSerial()!=null&&!"".equals(talkCloudRoom.getSerial())&&talkCloudRoom.getPkTalkCloudRoom() != null && !"".equals(talkCloudRoom.getPkTalkCloudRoom())){
        	//更新
        	list.add(new ParamsBean("serial", talkCloudRoom.getSerial())); //房间号
        	url = cloud_url+"/WebAPI/roommodify";
        }
		String result = httpRequestSend.httpSend(url, list); //发送请求
		String responseStr = "";
		JSONObject json = JSONObject.parseObject(result);

		if(json.getString("result").equals("0")){
			//保存或更新教室成功,开始调用api数据层
			String teacherUrl = cloud_url+"/"+json.getString("serial")+"/10751/1/0";
			String studentUrl = cloud_url+"/"+json.getString("serial")+"/10751/0/2";
			talkCloudRoom.setTeacherUrl(teacherUrl);
			talkCloudRoom.setStudentUrl(studentUrl);
			talkCloudRoom.setSerial(json.getString("serial"));

			String responseXml = POST(getSendUrl(ActionEnum.SAVE),talkCloudRoom);
			return responseXml;
		}else{
			String code=json.getString("result");
			responseStr="{'code'：'"+code+"'}";
		}

        return responseStr;
    }

	/**
	 * 删除教室
	 */
	public String delete(TalkCloudRoom talkCloudRoom) {

		List<ParamsBean> list = new ArrayList<ParamsBean>();
		String url = cloud_url+"/WebAPI/roomdelete";
		list.add(new ParamsBean("key",key)); //必填 企业id authkey
		list.add(new ParamsBean("serial",talkCloudRoom.getSerial()));//必填 房间号
		String result = httpRequestSend.httpSend(url, list); //发送请求
		JSONObject json = JSONObject.parseObject(result);

		String responseStr="";
		if(json.getString("result").equals("0")){
			//删除成功,开始调用api数据层
			String responseXml = POST(getSendUrl(ActionEnum.DELETE),talkCloudRoom.getPkTalkCloudRoom());
			return responseXml;
		}else{
			responseStr=json.toString();
		}
		return responseStr;
	}
	
	
	/**
	 * 获取教室在线用户数
	 * @param talkCloudRoom
	 * @return
	 */
	public String roomonlinenum(TalkCloudRoom talkCloudRoom) {
		
		String url = cloud_url +"/WebAPI/roomonlinenum";           
		List<ParamsBean> list = new ArrayList<ParamsBean>();
		
		list.add(new ParamsBean("key",key)); //必填 企业id authkey
		list.add(new ParamsBean("serial",talkCloudRoom.getSerial())); //必填 房间号
		list.add(new ParamsBean("type",talkCloudRoom.getType())); //0: 当前在线人数 1：登录人数
		
		String result = httpRequestSend.httpSend(url, list);
		//JSONObject json = JSONObject.parseObject(result);
		return result;
	}

//
	
	/**
	 * 获取教室录制件列表
	 * @param talkCloudRoom
	 * @return
	 */
	public String getrecordlist(TalkCloudRoom talkCloudRoom) {
		
		String url = cloud_url +"/WebAPI/getrecordlist";           
		List<ParamsBean> list = new ArrayList<ParamsBean>();
		
		list.add(new ParamsBean("key",key)); //必填 企业id authkey
		list.add(new ParamsBean("serial",talkCloudRoom.getSerial())); //必填 房间号
		
		String result = httpRequestSend.httpSend(url, list);
		
//		JSONObject json = JSONObject.parseObject(result);
//		if(json.getString("result").equals("0")){
//			String  str=json.getString("recordlist");
//
//			JSONObject jsonObject=JSONObject.parseObject(str.substring(1,str.length()-1));
//			result=jsonObject.getString("playpath");
//		}
		return result;
	}
	
	
	
	/**
	 * 获取教室用户奖章或礼物
	 * @param talkCloudRoom
	 * @return
	 */
	public String getusergift(TalkCloudRoom talkCloudRoom) {
			
		String url = cloud_url +"/WebAPI/getusergift";
		List<ParamsBean> list = new ArrayList<ParamsBean>();
			
		list.add(new ParamsBean("key",key)); //必填 企业id authkey
		list.add(new ParamsBean("serial",talkCloudRoom.getSerial())); //必填 房间号
//		list.add(new ParamsBean("userid","123")); //第三方用户id
			
		String result = httpRequestSend.httpSend(url, list);
			
		JSONObject json = JSONObject.parseObject(result);
			
		return result;
		}
	
	
	/**
	 * 获取教室聊天信息
	 * @param talkCloudRoom
	 * @return
	 */
	public String getroomchat(TalkCloudRoom talkCloudRoom) {
		
		String url = cloud_url +"/WebAPI/getroomchat";           
		List<ParamsBean> list = new ArrayList<ParamsBean>();
		
		list.add(new ParamsBean("key",key)); //必填 企业id authkey
		list.add(new ParamsBean("serial",talkCloudRoom.getSerial())); //必填 房间号
		list.add(new ParamsBean("chattype",talkCloudRoom.getChattype())); //类型 0:公聊; 2:私聊（目前只有 0）
		
		String result = httpRequestSend.httpSend(url, list);
		
		JSONObject json = JSONObject.parseObject(result);
		
		return result;
	}

	
	/**
	 * 获取教室用户登入登出情况
	 * @param talkCloudRoom
	 * @return
	 */
	public String getlogininfo(TalkCloudRoom talkCloudRoom) {
			
		String url = cloud_url +"/WebAPI/getlogininfo";
		List<ParamsBean> list = new ArrayList<ParamsBean>();
			
		list.add(new ParamsBean("key",key)); //必填 企业id authkey
		list.add(new ParamsBean("serial",talkCloudRoom.getSerial())); //必填 房间号
			
		String result = httpRequestSend.httpSend(url, list);
			
		JSONObject json = JSONObject.parseObject(result);

		return result;
	}


	/**
	 * 获取教室列表
	 * @param talkCloudRoom
	 * @return
	 */
	public String getroomlist(TalkCloudRoom talkCloudRoom) {

		String url = cloud_url +"/WebAPI/getroomlist";
		List<ParamsBean> list = new ArrayList<ParamsBean>();

		list.add(new ParamsBean("key",key)); //必填 企业id authkey
		list.add(new ParamsBean("timetype",talkCloudRoom.getRoomtype())); // 类型 0全部 1当前 2未来 3历史

		String result = httpRequestSend.httpSend(url, list);

		JSONObject json = JSONObject.parseObject(result);

		String str="";
		if(json.getString("result").equals("0")){
			 str=json.get("room").toString();
		}else{
             str=json.toString();
		}
		return str;
	}


	/**
	 * 教室关联文档
	 * @param filedIds
	 * @param talkCloudFile
	 * @return
	 */
//	public String roombindfile(String[] filedIds,TalkCloudFile talkCloudFile) {
//
//		String url = cloud_url +"/WebAPI/roombindfile";
//		List<ParamsBean> list = new ArrayList<ParamsBean>();
//
//		list.add(new ParamsBean("key",key)); //必填 企业id authkey
//		list.add(new ParamsBean("serial",talkCloudFile.getSerial())); //必填 房间号
//		list.add(new ParamsBean("fileid", talkCloudFile.getFileid()));//选填，指定缺省显示文件的id .如果以前指定过，那么会重新指定为这个fileid.
//
//		for (String filedId : filedIds) {
//			list.add(new ParamsBean("fileidarr[]",filedId));//必填 文件id组，文件id数组
//		}
//
//		String result = httpRequestSend.httpSend(url, list);
//
//		JSONObject json = JSONObject.parseObject(result);
//		if(json.getString("result").equals("0")){
//			//教室关联文件成功
//			//开始调用api数据层
//		}
//
//		return result;
//	}


	/**
	 * 取消教室关联文档
	 * @param filedIds
	 * @param talkCloudFile
	 * @return
	 */
//	public String roomdeletefile(String[] filedIds,TalkCloudFile talkCloudFile) {
//
//			String url = cloud_url +"/WebAPI/roomdeletefile";
//			List<ParamsBean> list = new ArrayList<ParamsBean>();
//
//			list.add(new ParamsBean("key",key)); //必填 企业id authkey
//			list.add(new ParamsBean("serial",talkCloudFile.getSerial())); //必填 房间号
//
//			for (String filedId : filedIds) {
//				list.add(new ParamsBean("fileidarr[]",filedId));//必填 文件id组，文件id数组
//			}
//
//			String result = httpRequestSend.httpSend(url, list);
//
//			JSONObject json = JSONObject.parseObject(result);
////			if(json.getString("result").equals("0")){
////				//教室关联文件成功
////				//开始调用api数据层
////			}
//
//			return result;
//		}





	/**
	 //	 * 进入教室
	 //	 * @param JsonStr
	 //	 * @return
	 //	 */
//	public void entry(JSONObject JsonStr) throws Exception {
//
//		String url = cloud_url +"/WebAPI/entry";
//		List<ParamsBean> list = new ArrayList<ParamsBean>();
//
//		list.add(new ParamsBean("domain","szgq")); //必填 企业域名 szgq
//		list.add(new ParamsBean("serial",JsonStr.getString("serial"))); //必填 房间号
//		list.add(new ParamsBean("usertype",JsonStr.getString("usertype"))); //必填 用户类型
//
//		int a = (int) (System.currentTimeMillis() / 1000.0);
//        list.add(new ParamsBean("ts",a));// 必填  当前时间戳
//
//		String Stringmd5=MD5Util.string2MD5(AES_key+a+JsonStr.getString("serial")+JsonStr.getString("usertype"));// 必填 key+ts+serial+usertype
//		list.add(new ParamsBean("auth",Stringmd5));// 必填  验证码
//
//		if(JsonStr.getString("userpassword")!=null||!JsonStr.getString("userpassword").equals("")){
//			String s=JsonStr.getString("userpassword");
//			list.add(new ParamsBean("userpassword",AESUtil.Decrypt(s,AES_key)));// 密码
//		}
//
//
//		httpRequestSend.httpSend(url, list);
//	}
//








}
