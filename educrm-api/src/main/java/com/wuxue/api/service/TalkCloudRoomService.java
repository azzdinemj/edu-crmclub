package com.wuxue.api.service;


import com.wuxue.api.interfaces.IDeleteService;
import com.wuxue.api.interfaces.IFindService;
import com.wuxue.api.interfaces.ISaveService;
import com.wuxue.model.ClassinfoRoom;
import com.wuxue.model.TalkCloudRoom;
import com.wuxue.utils.contract.Response;

/**
 * 
* @Title: TalkCloudRoomService.java  - 拓课云课堂
* @Package com.wuxue.api.service   
* @author zs  
* @date 2018年5月30日
 */
public interface TalkCloudRoomService extends ISaveService<TalkCloudRoom>,IFindService<TalkCloudRoom>,IDeleteService<String> {

}
