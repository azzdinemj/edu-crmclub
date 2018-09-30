package com.wuxue.api.mapper;

import com.wuxue.api.interfaces.*;
import com.wuxue.model.JhExamination;

import java.util.List;

public interface JhExaminationMapper  extends IInsertMapper<JhExamination>,ICountMapper<JhExamination,Integer>,
        IUpdateMapper<JhExamination>,IDeleteByPrimaryKeyMapper<String>,
        ISelectByPrimaryKeyMapper<String,JhExamination>,ISelectMapper<JhExamination,List<JhExamination>>{
}