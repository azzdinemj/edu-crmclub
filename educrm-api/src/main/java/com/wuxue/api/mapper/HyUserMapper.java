package com.wuxue.api.mapper;

import com.wuxue.api.interfaces.*;
import com.wuxue.model.HyUser;

import java.util.List;

public interface HyUserMapper   extends IInsertMapper<HyUser>,ICountMapper<HyUser,Integer>,
        IUpdateMapper<HyUser>,IDeleteByPrimaryKeyMapper<Integer>,
        ISelectByPrimaryKeyMapper<Integer,HyUser>,ISelectMapper<HyUser,List<HyUser>>{

}