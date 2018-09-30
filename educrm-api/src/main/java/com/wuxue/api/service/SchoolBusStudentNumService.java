package com.wuxue.api.service;


import com.wuxue.api.interfaces.IDeleteService;
import com.wuxue.api.interfaces.IFindService;
import com.wuxue.api.interfaces.ISaveService;
import com.wuxue.model.DormRoom;
import com.wuxue.model.SchoolBusStudentNum;

public interface SchoolBusStudentNumService extends ISaveService<SchoolBusStudentNum>,IFindService<SchoolBusStudentNum> {
}
