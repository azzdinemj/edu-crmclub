package com.wuxue.api.mapper;

import com.wuxue.api.interfaces.*;
import com.wuxue.model.WxOpenid;

import java.util.List;

public interface WxOpenidMapper extends IInsertMapper<WxOpenid>,ICountMapper<WxOpenid,Integer>,
        IUpdateMapper<WxOpenid>,IDeleteByPrimaryKeyMapper<String>,
        ISelectByPrimaryKeyMapper<String,WxOpenid>,ISelectMapper<WxOpenid,List<WxOpenid>>{

}