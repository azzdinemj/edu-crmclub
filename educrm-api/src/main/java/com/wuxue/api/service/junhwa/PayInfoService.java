package com.wuxue.api.service.junhwa;

import com.wuxue.model.ParentPay;
import com.wuxue.utils.contract.Response;

/**
 * @author tly
 * @date 2018-08-03
 */
public interface PayInfoService {

    /**
     * 保存支付记录
     *
     * @param parentPayList
     * @return
     */
    Response saveParentPay(ParentPay parentPayList);

    /**
     * 支付成功修改状态
     * */
    Response updatePayOrder(String orderId);
}
