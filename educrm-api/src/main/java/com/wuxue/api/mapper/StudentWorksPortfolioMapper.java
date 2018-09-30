package com.wuxue.api.mapper;

import com.wuxue.api.interfaces.*;
import com.wuxue.model.ClassinfoActivityDetails;
import com.wuxue.model.StudentWorksPortfolio;

import java.util.List;

public interface StudentWorksPortfolioMapper extends IInsertMapper<StudentWorksPortfolio>,ICountMapper<StudentWorksPortfolio,Integer>,
        IUpdateMapper<StudentWorksPortfolio>,IDeleteByPrimaryKeyMapper<String>,
        ISelectByPrimaryKeyMapper<String,StudentWorksPortfolio>,ISelectMapper<StudentWorksPortfolio,List<StudentWorksPortfolio>> {
}