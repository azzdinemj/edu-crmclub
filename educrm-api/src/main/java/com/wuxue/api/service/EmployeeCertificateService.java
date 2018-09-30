package com.wuxue.api.service;


import com.wuxue.api.interfaces.IDeleteService;
import com.wuxue.api.interfaces.IFindService;
import com.wuxue.api.interfaces.ISaveService;
import com.wuxue.model.EmployeeCertificate;

public interface EmployeeCertificateService extends ISaveService<EmployeeCertificate>,IFindService<EmployeeCertificate>,IDeleteService<String> {
}
