package com.wuxue.api.service;


import com.wuxue.api.interfaces.IDeleteService;
import com.wuxue.api.interfaces.IFindService;
import com.wuxue.api.interfaces.ISaveService;
import com.wuxue.model.ActivityStudent;
import com.wuxue.model.ParentOrder;
import com.wuxue.model.TkPayRecords;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;

public interface WeixinPayService{
    /**
     * 微信支付
     * @Author  Rogue
     * @return  String
     * @Date	2018年6月26日
     * 更新日志
     * 2018年6月26日  Rogue 首次创建
     *
     */
    Response weixinPay(TkPayRecords tkPayRecords);

    /**
     * 小程序支付
     * */
//    Response unifiedOrder(ParentOrder parentOrder);
}
