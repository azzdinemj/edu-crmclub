package com.wuxue.api.service;


import com.wuxue.api.interfaces.IDeleteService;
import com.wuxue.api.interfaces.IFindService;
import com.wuxue.api.interfaces.ISaveService;
import com.wuxue.model.ActivityStudent;

public interface ActivityStudentService extends ISaveService<ActivityStudent>,IFindService<ActivityStudent>,IDeleteService<String> {
}
