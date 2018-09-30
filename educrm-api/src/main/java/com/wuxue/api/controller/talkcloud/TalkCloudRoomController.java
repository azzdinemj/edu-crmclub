package com.wuxue.api.controller.talkcloud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wuxue.api.interfaces.IAuditController;
import com.wuxue.api.interfaces.IDeleteController;
import com.wuxue.api.interfaces.IFindAllController;
import com.wuxue.api.interfaces.IFindController;
import com.wuxue.api.interfaces.ISaveController;
import com.wuxue.api.service.TalkCloudRoomService;
import com.wuxue.model.TalkCloudRoom;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;

/**
 * 
* @Title: TalkCloudRoomController.java  - 拓课云教室 
* @Package com.wuxue.api.controller.talkcloud   
* @author zs  
* @date 2018年5月30日
 */
@RestController
@RequestMapping(value = "api/talkCloud/room")
public class TalkCloudRoomController implements IFindController<TalkCloudRoom>,
ISaveController<TalkCloudRoom>,IDeleteController<String> {

	@Autowired
	private TalkCloudRoomService talkCloudRoomService;
	



	@Override
	public Response Delete(@RequestBody Request<String> request) {

		return talkCloudRoomService.delete(request);
	}

	@Override
	public Response Save(@RequestBody Request<TalkCloudRoom> request) {

		return talkCloudRoomService.save(request);
	}

	@Override
	public Response Find(@RequestBody Request<TalkCloudRoom> request) {
		
		return talkCloudRoomService.find(request); 
	}

}
