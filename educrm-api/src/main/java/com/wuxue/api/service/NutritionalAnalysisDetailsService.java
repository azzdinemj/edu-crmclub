package com.wuxue.api.service;


import com.wuxue.api.interfaces.IDeleteService;
import com.wuxue.api.interfaces.IFindService;
import com.wuxue.api.interfaces.ISaveService;
import com.wuxue.model.NutritionalAnalysisDetails;

public interface NutritionalAnalysisDetailsService extends ISaveService<NutritionalAnalysisDetails>,IFindService<NutritionalAnalysisDetails>,IDeleteService<String> {

}
