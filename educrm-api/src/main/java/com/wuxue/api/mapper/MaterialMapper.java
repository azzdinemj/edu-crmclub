package com.wuxue.api.mapper;

import com.wuxue.api.interfaces.*;
import com.wuxue.model.junhwa.Material;

import java.util.List;

public interface MaterialMapper extends IInsertMapper<Material>,ICountMapper<Material,Integer>,
        IUpdateMapper<Material>,IDeleteByPrimaryKeyMapper<String>,
        ISelectByPrimaryKeyMapper<String,Material>,ISelectMapper<Material,List<Material>>  {
}