package com.wuxue.api.service;


import com.alipay.api.AlipayApiException;
import com.wuxue.model.TkPayRecords;
import com.wuxue.utils.contract.Response;

public interface AliPayService {
    /**
     * 阿里支付预下单
     * 如果你调用的是当面付预下单接口(alipay.trade.precreate)，调用成功后订单实际上是没有生成，因为创建一笔订单要买家、卖家、金额三要素。
     * 预下单并没有创建订单，所以根据商户订单号操作订单，比如查询或者关闭，会报错订单不存在。
     * 当用户扫码后订单才会创建，用户扫码之前二维码有效期2小时，扫码之后有效期根据timeout_express时间指定。
     * @Author  Rogue
     * @return  String
     * @Date	2018年6月26日
     * 更新日志
     * 2018年6月26日  Rogue 首次创建
     *
     */
    Response aliPay(TkPayRecords tkPayRecords) throws AlipayApiException;
}
