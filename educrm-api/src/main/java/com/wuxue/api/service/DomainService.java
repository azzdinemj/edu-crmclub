package com.wuxue.api.service;


import com.wuxue.api.interfaces.IDeleteService;
import com.wuxue.api.interfaces.IFindService;
import com.wuxue.api.interfaces.ISaveService;
import com.wuxue.model.Domain;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;

public interface DomainService extends ISaveService<Domain>,IFindService<Domain>,IDeleteService<String> {
    /**
     * 获取学校logo
     * @param domain
     * @return
     */
    Response getUrl(Request<Domain> domain);
}
