package com.wuxue.api.mapper;

import com.wuxue.api.interfaces.*;
import com.wuxue.model.StudentPlansCaption;

import java.util.List;

public interface StudentPlansCaptionMapper extends IInsertMapper<StudentPlansCaption>,ICountMapper<StudentPlansCaption,Integer>,
        IUpdateMapper<StudentPlansCaption>,IDeleteByPrimaryKeyMapper<String>,
        ISelectByPrimaryKeyMapper<String,StudentPlansCaption>,ISelectMapper<StudentPlansCaption,List<StudentPlansCaption>>  {

}