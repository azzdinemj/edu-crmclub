package com.wuxue.api.service;


import com.wuxue.api.interfaces.IDeleteService;
import com.wuxue.api.interfaces.IFindService;
import com.wuxue.api.interfaces.ISaveService;
import com.wuxue.model.JhExamination;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;


public interface JhExaminationService extends ISaveService<JhExamination>,IFindService<JhExamination>,IDeleteService<JhExamination> {


    /**
     * 统计
     *
     * */
    Response statistics(Request<JhExamination> request);
}
