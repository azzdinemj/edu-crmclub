package com.wuxue.api.service;


import com.wuxue.api.interfaces.IDeleteService;
import com.wuxue.api.interfaces.IFindService;
import com.wuxue.api.interfaces.ISaveService;
import com.wuxue.model.Datavalid;
import com.wuxue.model.Employee;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;

public interface DatavalidService extends ISaveService<Datavalid>,IFindService<Datavalid>,IDeleteService<String> {



}
