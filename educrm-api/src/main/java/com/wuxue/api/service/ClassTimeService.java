package com.wuxue.api.service;


import com.wuxue.api.interfaces.IDeleteService;
import com.wuxue.api.interfaces.IFindService;
import com.wuxue.api.interfaces.ISaveService;
import com.wuxue.model.ClassTime;

public interface ClassTimeService extends ISaveService<ClassTime>,IFindService<ClassTime>,IDeleteService<String> {
}
