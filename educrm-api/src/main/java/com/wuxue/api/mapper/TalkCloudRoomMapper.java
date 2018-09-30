package com.wuxue.api.mapper;

import com.wuxue.api.interfaces.*;
import com.wuxue.model.TalkCloudRoom;

import java.util.List;

/**
 * 
* @Title: TalkCloudRoomMapper.java  - 拓课云课堂
* @Package com.wuxue.api.mapper   
* @author zs  
* @date 2018年5月31日
 */
public interface TalkCloudRoomMapper extends IInsertMapper<TalkCloudRoom>,ICountMapper<TalkCloudRoom,Integer>,
        IUpdateMapper<TalkCloudRoom>,IDeleteByPrimaryKeyMapper<String>,
        ISelectByPrimaryKeyMapper<String,TalkCloudRoom>,ISelectMapper<TalkCloudRoom,List<TalkCloudRoom>> {
	
}