package com.wuxue.api.service;


import com.wuxue.api.interfaces.IDeleteService;
import com.wuxue.api.interfaces.IFindService;
import com.wuxue.api.interfaces.ISaveService;
import com.wuxue.model.LookAnalysis;

public interface LookAnalysisService extends ISaveService<LookAnalysis>,IFindService<LookAnalysis>,IDeleteService<String> {

}
