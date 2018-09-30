package com.wuxue.api.mapper;

import com.wuxue.api.interfaces.*;
import com.wuxue.model.Datavalid;
import com.wuxue.model.WxOpenid;

import java.util.List;

public interface DatavalidMapper extends IInsertMapper<Datavalid>,ICountMapper<WxOpenid,Integer>,
        IUpdateMapper<Datavalid>,IDeleteByPrimaryKeyMapper<String>,
        ISelectByPrimaryKeyMapper<String,Datavalid>,ISelectMapper<Datavalid,List<Datavalid>> {

    //查找24小时内该电话号码获取 验证码的次数
        int countByphone24(Datavalid datavalid);
}