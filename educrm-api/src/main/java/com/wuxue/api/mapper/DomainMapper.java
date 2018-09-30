package com.wuxue.api.mapper;

import com.wuxue.api.interfaces.*;
import com.wuxue.model.Domain;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DomainMapper extends IInsertMapper<Domain>,ICountMapper<Domain,Integer>,
        IUpdateMapper<Domain>,IDeleteByPrimaryKeyMapper<String>,
        ISelectByPrimaryKeyMapper<String,Domain>,ISelectMapper<Domain,List<Domain>> {
}