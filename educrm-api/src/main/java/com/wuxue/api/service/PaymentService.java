package com.wuxue.api.service;


import com.wuxue.api.interfaces.*;
import com.wuxue.model.Payment;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;

public interface PaymentService extends ISaveService<Payment>,IFindService<Payment>,IDeleteService<String>,ISubmitService<Payment>,IAuditService<Payment> {
    /**
     * 驳回
     * */
    Response retreat(Request<Payment> payment);
}
