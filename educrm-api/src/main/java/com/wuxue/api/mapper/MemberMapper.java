package com.wuxue.api.mapper;

import com.wuxue.api.interfaces.*;
import com.wuxue.model.Member;

import java.util.List;

public interface MemberMapper extends IInsertMapper<Member>,ICountMapper<Member,Integer>,
        IUpdateMapper<Member>,IDeleteByPrimaryKeyMapper<String>,
        ISelectByPrimaryKeyMapper<String,Member>,ISelectMapper<Member,List<Member>>{
}