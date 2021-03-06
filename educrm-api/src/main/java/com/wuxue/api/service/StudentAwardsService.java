package com.wuxue.api.service;


import com.wuxue.api.interfaces.IDeleteService;
import com.wuxue.api.interfaces.IFindService;
import com.wuxue.api.interfaces.ISaveService;
import com.wuxue.model.StudentAwards;

public interface StudentAwardsService extends ISaveService<StudentAwards>,IFindService<StudentAwards>,IDeleteService<String> {
}
