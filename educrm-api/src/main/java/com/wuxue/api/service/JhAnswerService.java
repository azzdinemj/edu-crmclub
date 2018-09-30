package com.wuxue.api.service;


import com.wuxue.api.interfaces.IDeleteService;
import com.wuxue.api.interfaces.IFindService;
import com.wuxue.api.interfaces.ISaveService;
import com.wuxue.model.JhAnswer;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;

public interface JhAnswerService extends ISaveService<JhAnswer>,IFindService<JhAnswer>,IDeleteService<JhAnswer> {
    /**
     * 根据题目，选项查询家长
     * */
    Response queryAnswerList(Request<JhAnswer> request);
}
