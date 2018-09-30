package com.wuxue.api.service;


import com.wuxue.api.interfaces.IDeleteService;
import com.wuxue.api.interfaces.IFindService;
import com.wuxue.api.interfaces.ISaveService;
import com.wuxue.model.ClassinfoActivityDetails;

public interface ClassinfoActivityDetailsService extends ISaveService<ClassinfoActivityDetails>,IFindService<ClassinfoActivityDetails>,IDeleteService<String> {
}
