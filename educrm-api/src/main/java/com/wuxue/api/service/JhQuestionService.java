package com.wuxue.api.service;


import com.wuxue.api.interfaces.IDeleteService;
import com.wuxue.api.interfaces.IFindService;
import com.wuxue.api.interfaces.ISaveService;
import com.wuxue.model.JhQuestion;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;


public interface JhQuestionService extends ISaveService<JhQuestion>,IFindService<JhQuestion>,IDeleteService<JhQuestion> {

    /**
     * 根据用户查询
     * */
    Response FindByUser(Request<JhQuestion> request);
}
