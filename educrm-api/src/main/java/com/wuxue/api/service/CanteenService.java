package com.wuxue.api.service;


import com.wuxue.api.interfaces.*;
import com.wuxue.model.Canteen;
import com.wuxue.model.Classinfo;
import com.wuxue.model.ClassinfoStudent;
import com.wuxue.model.Schedule;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;

public interface CanteenService extends ISaveService<Canteen>,IFindService<Canteen>,IDeleteService<String> {

}
