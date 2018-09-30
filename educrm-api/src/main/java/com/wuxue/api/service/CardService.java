package com.wuxue.api.service;


import com.wuxue.api.interfaces.ISaveService;
import com.wuxue.model.Recharge;
import com.wuxue.utils.contract.Response;

public interface CardService extends ISaveService<Recharge> {

    /**
     * 获取学生充值记录
     *
     * @param request*/
    Response selectByRechargeList(Recharge request);
}
