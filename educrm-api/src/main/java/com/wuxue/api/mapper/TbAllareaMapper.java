package com.wuxue.api.mapper;

import com.wuxue.api.interfaces.*;
import com.wuxue.model.SysUser;
import com.wuxue.model.TbAllarea;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TbAllareaMapper extends IInsertMapper<TbAllarea>,ICountMapper<TbAllarea,Integer>,
        IUpdateMapper<TbAllarea>,IDeleteByPrimaryKeyMapper<String>,
        ISelectByPrimaryKeyMapper<Integer,TbAllarea>,ISelectMapper<TbAllarea,List<TbAllarea>> {
}