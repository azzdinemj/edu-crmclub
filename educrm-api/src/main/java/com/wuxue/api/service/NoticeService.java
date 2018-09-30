package com.wuxue.api.service;


import com.wuxue.api.interfaces.IDeleteService;
import com.wuxue.api.interfaces.IFindService;
import com.wuxue.api.interfaces.ISaveService;
import com.wuxue.model.Notice;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;

public interface NoticeService extends ISaveService<Notice>,IFindService<Notice>,IDeleteService<String> {
    /**
     * 查询所有通知
     * */
    Response findAll(Request<Notice> notice);
}
