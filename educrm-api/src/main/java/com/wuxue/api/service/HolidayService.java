package com.wuxue.api.service;


import com.wuxue.api.interfaces.IDeleteService;
import com.wuxue.api.interfaces.IFindService;
import com.wuxue.api.interfaces.ISaveService;
import com.wuxue.model.junhwa.Holiday;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;

public interface HolidayService extends ISaveService<Holiday>,IFindService<Holiday>,IDeleteService<Integer> {


    Response saveAll(Request<Holiday> holiday);
}
