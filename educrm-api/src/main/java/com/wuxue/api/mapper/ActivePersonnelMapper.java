package com.wuxue.api.mapper;

import com.wuxue.api.interfaces.*;
import com.wuxue.model.ActivePersonnel;

import java.util.List;

public interface ActivePersonnelMapper extends IInsertMapper<ActivePersonnel>,ICountMapper<ActivePersonnel,Integer>,
        IUpdateMapper<ActivePersonnel>,IDeleteByPrimaryKeyMapper<String>,
        ISelectByPrimaryKeyMapper<String,ActivePersonnel>,ISelectMapper<ActivePersonnel,List<ActivePersonnel>> {
}