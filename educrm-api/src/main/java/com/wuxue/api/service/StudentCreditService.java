package com.wuxue.api.service;


import com.wuxue.api.interfaces.IDeleteService;
import com.wuxue.api.interfaces.IFindService;
import com.wuxue.api.interfaces.ISaveService;
import com.wuxue.model.StudentCredit;

public interface StudentCreditService extends ISaveService<StudentCredit>,IFindService<StudentCredit>,IDeleteService<String> {
}
