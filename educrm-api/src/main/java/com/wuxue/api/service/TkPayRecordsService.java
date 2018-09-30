package com.wuxue.api.service;

import com.wuxue.api.interfaces.*;
import com.wuxue.model.TkPayRecords;

public interface TkPayRecordsService extends ISaveService<TkPayRecords>,IFindService<TkPayRecords>,IDeleteService<String>,
        IAuditService<TkPayRecords>,ICancelService<TkPayRecords>,ISubmitService<TkPayRecords>{

    /**
     * 修改支付状态
     * */
    void updateStatus(TkPayRecords tkPayRecords);
}
