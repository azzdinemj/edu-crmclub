package com.wuxue.api.service;


import com.wuxue.api.interfaces.IDeleteService;
import com.wuxue.api.interfaces.IFindService;
import com.wuxue.api.interfaces.ISaveService;
import com.wuxue.model.NutritionalAnalysis;

public interface NutritionalAnalysisService extends ISaveService<NutritionalAnalysis>,IFindService<NutritionalAnalysis>,IDeleteService<String> {

}
