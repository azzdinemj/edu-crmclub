package com.wuxue.api.service;


import com.wuxue.api.interfaces.*;
import com.wuxue.model.Receipt;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;

public interface ReceiptService extends ISaveService<Receipt>,IFindService<Receipt>,IDeleteService<String>,IAuditService<Receipt>,ISubmitService<Receipt> {
    /**
     * 驳回
     * */
    Response retreat(Request<Receipt> receipt);

    Response findFroRefund(Request<Receipt> receipt);
}
