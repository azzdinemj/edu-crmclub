package com.wuxue.view.controllers.talkcloud;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.sun.org.apache.regexp.internal.RE;
import com.wuxue.model.Schedule;
import com.wuxue.model.TalkCloudRoom;
import com.wuxue.model.TkLearnRecords;
import com.wuxue.model.TkPayRecords;
import com.wuxue.utils.contract.Light;
import com.wuxue.utils.contract.Response;
import com.wuxue.view.client.product.ProductClient;
import com.wuxue.view.client.product.ScheduleClient;
import com.wuxue.view.client.product.TkLearnRecordsClient;
import com.wuxue.view.client.system.TkPayRecordsClient;
import com.wuxue.view.client.talkcloud.TalkCloudRoomClient;
import com.wuxue.view.controllers.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
* @Title: TalkCloudRoomController.java  - 拓课云教室 
* @Package com.wuxue.view.controllers.talkcloud   
* @author admin  
* @date 2018年5月30日
 */
@Controller
@RequestMapping(value = "/talkCloud/room")
public class TalkCloudRoomController extends BaseController {

    @Autowired
    private TalkCloudRoomClient talkCloudRoomClient;

    @Autowired
	private ProductClient productClient;




//	{
//
//		"talkCloud": [{
//		"roomname": "测试教室New",
//				"roomtype": "0",
//				"starttime": "1529371741",
//				"endtime": "1529458141",
//				"chairmanpwd": "111111",
//				"assistantpwd":"222222",
//				"patrolpwd": "333333",
//				"passwordrequired":"0",
//				//"serial":"2067951565",
//				//"pkTalkCloudRoom":"201829377529193571"  修改
//
//	}],
//		"product": [{
//		"caption": "测试教室New",
//				"pkDomain": "1",
//				"productNum":"",
//				"productType":"201818897644545644",
//				"productInclass":"1",
//				"productTotalclass":"10",
//				"productTypetalkcloudroom":"0",
//				//"pkProduct":"201829377549640517" 修改
//	}]
//
//	}

	/**
	 * 保存/修改talkCloudRoom,  talkCloudRoom关联到product
	 *
	 * @param request
	 * @param model
	 * @param str
	 * @return
	 */
//	@RequestMapping("/save")
//	@ResponseBody
//	public String save(@RequestBody String str,HttpServletRequest request, Model model) {
//
//		JSONObject json = JSONObject.parseObject(str);
//
//        Object talkCloud=json.get("talkCloud");
//        Object  product=json.get("product");
//
//		//TalkCloudRoom
//		String tcr=talkCloud.toString();
//		String t=tcr.substring(1,tcr.length()-1);
//		TalkCloudRoom talkCloudRoom=JSON.parseObject(t,new TypeReference<TalkCloudRoom>(){});
//
//		//product  对象
//		String pstr =product.toString();
//		String s=pstr.substring(1,pstr.length()-1);
//		Product product1 = JSON.parseObject(s,new TypeReference<Product>(){});
//
//		String string= talkCloudRoomClient.save(talkCloudRoom);
//
//		JSONObject jsons = JSONObject.parseObject(string);
//		if(jsons.getString("code").equals("0")){
//			//保存 product
//			if(jsons.getString("data")!=null){
//				product1.setPkTalkCloudRoom(jsons.getString("data"));
//			}
//			return productClient.save(product1);
//		}else{
//			return jsons.toJSONString();
//		}
//	}


	/**
     * 删除教室
     */
	@RequestMapping("/roomdelete")
	@ResponseBody
	public String delete(HttpServletRequest request, Model model, TalkCloudRoom talkCloudRoom) {
		
		String result = "";
		if(talkCloudRoom.getSerial()!=null&&!talkCloudRoom.getSerial().equals("") &&
				talkCloudRoom.getPkTalkCloudRoom() != null && !"".equals(talkCloudRoom.getPkTalkCloudRoom())){
			result = talkCloudRoomClient.delete(talkCloudRoom);
		}else {
		    result="{'message':'参数为空','code':1}";
		}
        return result;
	}


	/**
	 * 获取教室在线用户数
	 * @param request
	 * @param model
	 * @param talkCloudRoom
	 * @return
	 */
	@RequestMapping(value = "/roomonlinenum",method = RequestMethod.POST)
    public String roomonlinenum(HttpServletRequest request,Model model, TalkCloudRoom talkCloudRoom){
    	
		String result = talkCloudRoomClient.roomonlinenum(talkCloudRoom);
        return result;
    }

	/**
	 * 获取教室录制件列表
	 * @param request
	 * @param model
	 * @param talkCloudRoom
	 * @return
	 */
	@RequestMapping(value = "/getrecordlist",method = RequestMethod.POST)
	@ResponseBody
	public JSONObject getrecordlist(HttpServletRequest request, Model model,TalkCloudRoom talkCloudRoom){
		Response response=Response.newResponse();
		String result = talkCloudRoomClient.getrecordlist(talkCloudRoom);
		JSONObject json = JSONObject.parseObject(result);
		return  json;
//		if(json.getString("result").equals("0")){
//			String  str=json.getString("recordlist");
//
//			JSONObject jsonObject=JSONObject.parseObject(str.substring(1,str.length()-1));
//			result=jsonObject.getString("playpath");
//			response.setData(result);
//		}else{
//			response.setCode(1);
//		}
//		return response;
	}




	/**
	 * 获取教室录制件列表  公开课
	 * @param request
	 * @param model
	 * @param talkCloudRoom
	 * @return
	 */
	@RequestMapping(value = "/getrecordlist2",method = RequestMethod.POST)
	@ResponseBody
    public JSONObject getrecordlist2(HttpServletRequest request, Model model, TkLearnRecords tkLearnRecords,TalkCloudRoom talkCloudRoom){
		Response  response=Response.newResponse();
		response=isNotOrder(tkLearnRecords,response);
		if(response.getCode()==1){
			JSONObject jsonObject=new JSONObject();
			jsonObject.put("code",1);
			return  jsonObject;
		}

		String result = talkCloudRoomClient.getrecordlist(talkCloudRoom);
		JSONObject json = JSONObject.parseObject(result);
		return  json;
//		if(json.getString("result").equals("0")){
//			String  str=json.getString("recordlist");
//
//			JSONObject jsonObject=JSONObject.parseObject(str.substring(1,str.length()-1));
//			result=jsonObject.getString("playpath");
//			response.setData(result);
//		}else{
//			response.setCode(1);
//		}
//        return response;
    }



    @Autowired
	TkLearnRecordsClient tkLearnRecordsClient;

	@Autowired
	private TkPayRecordsClient tkPayRecordsClient;

	@Autowired
	private ScheduleClient scheduleClient;


	/**
	 *判断是否有学习记录
	 * @param tkLearnRecords
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/isnotOrder2",method = RequestMethod.POST)
	public Map<String,Object> isNotOrder2(TkLearnRecords tkLearnRecords,HttpServletRequest request){
		Map<String,Object> map=new HashMap<>();
		Response<List<TkLearnRecords>> listResponse=tkLearnRecordsClient.find(tkLearnRecords);
		List<TkLearnRecords> learnRecordsList=listResponse.getData();
		if(learnRecordsList.size()==0){
			Schedule schedule=new Schedule();
			schedule.setPkSchedule(tkLearnRecords.getPkSchedule());
			Response<Schedule> response= scheduleClient.findguanqiaoBypk(schedule);
//			if(myclasshours(tkLearnRecords.getPkStudent())>=response.getData().getProductUseclasshours()){
				int use=response.getData().getProductUseclasshours();
				int all=myclasshours(tkLearnRecords.getPkStudent());
				map.put("code",1);
				map.put("all",all);
				map.put("use",use);
			//}

		}else{
			map.put("code",0);
			map.put("msg","有学习记录");
		}
		return  map;
	}


	/**
	 * 判断是否有学习记录,
	 *       没有则添加学习记录（回放）
	 * @param tkLearnRecords
	 * @return
	 */
	public Response isNotOrder(TkLearnRecords tkLearnRecords,Response resp){
		Response<List<TkLearnRecords>> listResponse=tkLearnRecordsClient.find(tkLearnRecords);
		List<TkLearnRecords> learnRecordsList=listResponse.getData();
		if(learnRecordsList.size()==0){
			Schedule schedule=new Schedule();
			schedule.setPkSchedule(tkLearnRecords.getPkSchedule());
			Response<Schedule> response= scheduleClient.findguanqiaoBypk(schedule);
			if(myclasshours(tkLearnRecords.getPkStudent())>=response.getData().getProductUseclasshours()){
				tkLearnRecords.setType("1");
				String str=tkLearnRecordsClient.save(tkLearnRecords);
				resp = JSON.parseObject(str, new TypeReference<Response<Object>>(){});
			}else{
				resp.setCode(1);
				resp.setMessage("课时余额不足");
			}

		}
		return  resp;
	}

	//计算我剩余的课时
	public Integer myclasshours(String pkStudent){
		Integer total=0;//总数
		Integer use=0; //已使用
		Integer overplus=0; //剩余

		if(pkStudent!=null) {
			//学生支付订单
			TkPayRecords tkPayRecords = new TkPayRecords();
			tkPayRecords.setPkStudent(pkStudent);
			tkPayRecords.setStatus(0);
			Response<List<TkPayRecords>> recordsResponse = tkPayRecordsClient.find(tkPayRecords);
			if (recordsResponse.getData()!=null&&recordsResponse.getData().size()>0) {
				List<TkPayRecords> tkPayRecordsList = recordsResponse.getData();
				for (TkPayRecords t : tkPayRecordsList) {
					Map<String,Object> maps=t.getMap();
					Object object=maps.get(Light.TKSETMEAL);
					if(object!=null){
						total=total+(Integer)object;//总共购买的课时
					}
				}
			}

			//学生 学习记录
			TkLearnRecords tkLearnRecords = new TkLearnRecords();
			tkLearnRecords.setPkStudent(pkStudent);
			Response<List<TkLearnRecords>> listResponse = tkLearnRecordsClient.find(tkLearnRecords);

			if (listResponse.getData()!=null||listResponse.getData().size()>0) {
				for (TkLearnRecords tkLearnRecords1:listResponse.getData()) {
					Schedule schedule=new Schedule();
					schedule.setPkSchedule(tkLearnRecords1.getPkSchedule());
					Response<Schedule> scheduleResponse=scheduleClient.findguanqiaoBypk(schedule);
					use=use+scheduleResponse.getData().getProductUseclasshours();
				}

			}
		}
		overplus=total-use;
		return overplus;
	}



	/**
	 * 获取教室用户奖章或礼物
	 * @param request
	 * @param model
	 * @param talkCloudRoom
	 * @return
	 */
	@RequestMapping(value = "/getusergift",method = RequestMethod.POST)
    public String getusergift(HttpServletRequest request, Model model, TalkCloudRoom talkCloudRoom){
    	
		String result = talkCloudRoomClient.getusergift(talkCloudRoom);
        return result;
    }
	
	/**
	 * 获取教室聊天信息
	 * @param request
	 * @param model
	 * @param talkCloudRoom
	 * @return
	 */
	@RequestMapping(value = "/getroomchat",method = RequestMethod.POST)
    public String getroomchat(HttpServletRequest request, Model model, TalkCloudRoom talkCloudRoom){
    	
		String result = talkCloudRoomClient.getroomchat(talkCloudRoom);
        return result;
    }
	
	/**
	 * 获取教室用户登入登出情况
	 * @param request
	 * @param model
	 * @param talkCloudRoom
	 * @return
	 */
	@RequestMapping(value = "/getlogininfo",method = RequestMethod.POST)
    public String getlogininfo(HttpServletRequest request, Model model, TalkCloudRoom talkCloudRoom){
    	
		String result = talkCloudRoomClient.getlogininfo(talkCloudRoom);
        return result;
    }

//	/**
//	 * 进入教室
//	 * @param request
//	 * @param model
//	 * @param Str
//	 * @return
//	 */
//	@RequestMapping(value = "/entry",method = RequestMethod.POST)
//	public void entry(HttpServletRequest request, Model model,@RequestBody String Str){
//		JSONObject json = JSONObject.parseObject(Str);
//		try {
//			talkCloudRoomClient.entry(json);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	/**
	 * 教室关联文档
	 * @param request
	 * @param model
	 * @param talkCloudFile
	 * @return
	 */
//	@RequestMapping(value = "/roombindfile",method = RequestMethod.POST)
//    public String roombindfile(HttpServletRequest request,String[] filedIds, Model model, TalkCloudFile talkCloudFile){
//
//		String result = talkCloudRoomClient.roombindfile(filedIds,talkCloudFile);
//        return result;
//    }

	/**
	 * 取消教室关联文档
	 * @return
	 */
//	@RequestMapping(value = "/roomdeletefile",method = RequestMethod.POST)
//    public String roomdeletefile(HttpServletRequest request,String[] filedIds, Model model, TalkCloudFile talkCloudFile){
//		String result = talkCloudRoomClient.roomdeletefile(filedIds,talkCloudFile);
//        return result;
//    }



	public static void main(String[] args) {
		//获取当前时间戳
		int a = (int) (System.currentTimeMillis() / 1000.0);
		System.out.println(a);
		int b = a-24*3600;
		System.out.println(b);
		int ends = a+24*3600;
		System.out.println(ends);
	}

}
