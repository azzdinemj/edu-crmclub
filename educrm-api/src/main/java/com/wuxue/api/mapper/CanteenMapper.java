package com.wuxue.api.mapper;

import com.wuxue.api.interfaces.*;
import com.wuxue.model.Canteen;

import java.util.List;

public interface CanteenMapper  extends IInsertMapper<Canteen>,ICountMapper<Canteen,Integer>,
        IUpdateMapper<Canteen>,IDeleteByPrimaryKeyMapper<String>,
        ISelectByPrimaryKeyMapper<String,Canteen>,ISelectMapper<Canteen,List<Canteen>> {

}