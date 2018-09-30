package com.wuxue.api.mapper;

import com.wuxue.api.interfaces.*;
import com.wuxue.model.WxUnionid;

import java.util.List;

public interface WxUnionidMapper  extends IInsertMapper<WxUnionid>,ICountMapper<WxUnionid,Integer>,
        IUpdateMapper<WxUnionid>,IDeleteByPrimaryKeyMapper<String>,
        ISelectByPrimaryKeyMapper<Integer,WxUnionid>,ISelectMapper<WxUnionid,List<WxUnionid>>{
}