package com.wuxue.api.mapper;

import com.wuxue.api.interfaces.*;
import com.wuxue.model.shuttle.SiteNotification;

import java.util.List;

public interface SiteNotificationMapper extends IInsertMapper<SiteNotification>,ICountMapper<SiteNotification,Integer>,
        IUpdateMapper<SiteNotification>,IDeleteByPrimaryKeyMapper<String>,
        ISelectByPrimaryKeyMapper<String,SiteNotification>,ISelectMapper<SiteNotification,List<SiteNotification>>{
}