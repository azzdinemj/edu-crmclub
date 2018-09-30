package com.wuxue.api.service;


import com.wuxue.api.interfaces.*;
import com.wuxue.model.Payables;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;

public interface PayablesService extends ISaveService<Payables>,IFindService<Payables>,IDeleteService<String>,ISubmitService<Payables>,IAuditService<Payables> {

    /**
     * 根据收款单保存应付单
     * */
    Response receiptSavePayables(Request<Payables> payables);

    /**
     * 退费 生成收款单
     * */
    Response refund(Request<Payables> payables);
}
