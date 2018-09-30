package com.wuxue.api.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wuxue.api.mapper.TalkCloudRoomMapper;
import com.wuxue.api.service.TalkCloudRoomService;
import com.wuxue.api.utils.GuidUtils;
import com.wuxue.model.TalkCloudRoom;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 
* @Title: TalkCloudRoomServiceImpl.java  - 拓课云课堂 
* @Package com.wuxue.api.service.impl   
* @author zs  
* @date 2018年5月30日
 */
@Service("talkCloudRoomService")
public class TalkCloudRoomServiceImpl implements TalkCloudRoomService {

	@Autowired
	private TalkCloudRoomMapper talkCloudRoomMapper;
	
	@Override
	public Response save(Request<TalkCloudRoom> tParams) {
		Response response = Response.newResponse();
		TalkCloudRoom talkCloudRoom = tParams.getData();

        if (talkCloudRoom == null) {
            return response.PARAMS_ISNULL();
        }

        int iReuslt = 1;
        String message = "";
        try {
        	if (talkCloudRoom.getPkTalkCloudRoom() != null && !("").equals(talkCloudRoom.getPkTalkCloudRoom())) {
                //更新
                iReuslt = talkCloudRoomMapper.updateByPrimaryKeySelective(talkCloudRoom);
            } else {
            	//保存
            	talkCloudRoom.setPkTalkCloudRoom(GuidUtils.getGuid());
                iReuslt = talkCloudRoomMapper.insertSelective(talkCloudRoom);
                if(iReuslt>0){
					response.setData(talkCloudRoom.getPkTalkCloudRoom());
				}
            }
        } catch (Exception ex) {
            message = ex.getMessage();
            return response.SAVE_FAIL(message);
        }
        if (iReuslt > 0) {
            return response;
        }
        return response.SAVE_FAIL(message);
	}


	public Response find(Request<TalkCloudRoom> tParams) {
		Response response = Response.newResponse();
		TalkCloudRoom talkCloudRoom = tParams.getData();

        if (talkCloudRoom == null) {
            return response.PARAMS_ISNULL();
        }
        if(talkCloudRoom.getPkTalkCloudRoom()!=null && !("").equals(talkCloudRoom.getPkTalkCloudRoom())){
			TalkCloudRoom talkCloudRoomResponse = talkCloudRoomMapper.selectByPrimaryKey(talkCloudRoom.getPkTalkCloudRoom());
        	response.setData(talkCloudRoomResponse);
        }else{
        	PageHelper.startPage(talkCloudRoom.getPageNo(),talkCloudRoom.getPageSize());
        	List<TalkCloudRoom> talkCloudRoomList = talkCloudRoomMapper.select(talkCloudRoom);
            PageInfo<TalkCloudRoom> pageInfo = new PageInfo<>(talkCloudRoomList);

            response.setData(talkCloudRoomList);
			response.setTotal(pageInfo.getTotal());
        }
        
        return response;
	}


	public Response delete(Request<String> tParams) {
		Response response = Response.newResponse();
		String str = tParams.getData();
		if (str == null||str.equals("")) {
			return response.PARAMS_ISNULL();
		}
		int iReuslt = 1;
		String message= "";
		try {
			iReuslt=talkCloudRoomMapper.deleteByPrimaryKey(str);
		}catch (Exception ex){
			message = ex.getMessage();
			return response.DELETE_FAIL(message);
		}
		if(iReuslt>0) {
			return response;
		}
		return response.DELETE_FAIL(message);
	}

	public Date convertunixTimeStamp2Date(String unixTimeStamp){
		long timeStamp=Long.parseLong(unixTimeStamp);
		//long timeStamp = System.currentTimeMillis();  //获取当前时间戳,也可以是你自已给的一个随机的或是别人给你的时间戳(一定是long型的数据)
		System.out.println(timeStamp*1000);
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");//这个是你要转成后的时间的格式
		String sd = sdf.format(new Date(Long.parseLong(String.valueOf(timeStamp))));   // 时间戳转换成时间

		Date date=null;
		try {
			date=sdf.parse(sd);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	public static Date transForDate(Integer ms){
		if(ms==null){
			ms=0;
		}
		long msl=(long)ms*1000;
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date temp=null;
		if(ms!=null){
			try {
				String str=sdf.format(msl);
				temp=sdf.parse(str);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return temp;
	}

}
