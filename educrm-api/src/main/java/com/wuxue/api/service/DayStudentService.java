package com.wuxue.api.service;


import com.wuxue.api.interfaces.IDeleteService;
import com.wuxue.api.interfaces.IFindService;
import com.wuxue.api.interfaces.ISaveService;
import com.wuxue.model.junhwa.DayStudent;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;

public interface DayStudentService extends ISaveService<DayStudent>,IFindService<DayStudent>,IDeleteService<Integer> {

    Response saveAll(Request<DayStudent> dayStudent);
}
