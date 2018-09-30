package com.wuxue.api.service;


import com.wuxue.api.interfaces.*;
import com.wuxue.model.Receivable;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;

public interface ReceivableService extends ISaveService<Receivable>,IFindService<Receivable>,IDeleteService<String>,
        IAuditService<Receivable>,ICancelService<Receivable>,ISubmitService<Receivable> {
    /**
     * 保存时直接收款
     * */
    Response saveAudit(Request<Receivable> receivable);

    /**
     * 多项目新建
     * */
    Response saveAll(Request<Receivable> receivable);
}
