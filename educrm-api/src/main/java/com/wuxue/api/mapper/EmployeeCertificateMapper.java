package com.wuxue.api.mapper;

import com.wuxue.api.interfaces.*;
import com.wuxue.model.ClassinfoActivityDetails;
import com.wuxue.model.EmployeeCertificate;

import java.util.List;

public interface EmployeeCertificateMapper extends IInsertMapper<EmployeeCertificate>,ICountMapper<EmployeeCertificate,Integer>,
        IUpdateMapper<EmployeeCertificate>,IDeleteByPrimaryKeyMapper<String>,
        ISelectByPrimaryKeyMapper<String,EmployeeCertificate>,ISelectMapper<EmployeeCertificate,List<EmployeeCertificate>> {
}