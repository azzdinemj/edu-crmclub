package com.wuxue.api.service.impl;

import com.alipay.api.AlipayApiException;
import com.alipay.demo.trade.config.Configs;
import com.wuxue.api.service.AliPayService;
import com.wuxue.api.utils.AliPayConfig;
import com.wuxue.api.utils.GuidUtils;
import com.wuxue.model.TkPayRecords;
import com.wuxue.utils.common.PayCommonUtil;
import com.wuxue.utils.contract.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alipay.api.response.AlipayTradePrecreateResponse;
import com.alipay.demo.trade.model.ExtendParams;
import com.alipay.demo.trade.model.builder.AlipayTradePrecreateRequestBuilder;
import com.alipay.demo.trade.model.result.AlipayF2FPrecreateResult;
import com.alipay.demo.trade.utils.ZxingUtils;

@Service("aliPayService")
public class AliPayServiceImpl implements AliPayService {

    @Value("${alipay.notify.url}")
    private String notify_url;

    @Override
    public Response aliPay(TkPayRecords tkPayRecords) throws AlipayApiException {

//        logger.info("订单号：{}生成支付宝支付码",product.getOutTradeNo());
        Response response1 = Response.newResponse();
        String  message = "";
//        //二维码存放路径
//        String imgPath= Constants.QRCODE_PATH+Constants.SF_FILE_SEPARATOR+product.getOutTradeNo()+".png";
        String outTradeNo = tkPayRecords.getPkPayRecords();
        String subject = tkPayRecords.getTitle();
        String totalAmount =  PayCommonUtil.divide(tkPayRecords.getFee(), "100").toString();
        // 如果该字段为空，则默认为与支付宝签约的商户的PID，也就是appid对应的PID
        String sellerId = "";
        // (必填) 商户门店编号，通过门店号和商家后台可以配置精准到门店的折扣信息，详询支付宝技术支持
        String storeId = "2088102175546243";
        // 业务扩展参数，目前可添加由支付宝分配的系统商编号(通过setSysServiceProviderId方法)，详情请咨询支付宝技术支持
        String providerId = "2088100200300400500";
        ExtendParams extendParams = new ExtendParams();
        extendParams.setSysServiceProviderId(providerId);
        // 订单描述，可以对交易或商品进行一个详细地描述，比如填写"购买商品2件共15.00元"
        String body = tkPayRecords.getBody();
        // 支付超时，定义为120分钟
        String timeoutExpress = "10m";
        // 创建扫码支付请求builder，设置请求参数
        AlipayTradePrecreateRequestBuilder builder = new AlipayTradePrecreateRequestBuilder()
                .setSubject(subject)
                .setTotalAmount(totalAmount)
                .setOutTradeNo(outTradeNo)
                .setSellerId(sellerId)
                .setBody(body)//128长度 --附加信息
                .setStoreId(storeId)
                .setExtendParams(extendParams)
                .setTimeoutExpress(timeoutExpress)
                .setNotifyUrl(notify_url);//支付宝服务器主动通知商户服务器里指定的页面http路径,根据需要设置

        Configs.init("zfbinfo.properties");
        AlipayF2FPrecreateResult result = AliPayConfig.getAlipayTradeService().tradePrecreate(builder);

        switch (result.getTradeStatus()) {
            case SUCCESS:
                String url = GuidUtils.getGuid()+".png";
                AlipayTradePrecreateResponse response = result.getResponse();
                ZxingUtils.getQRCodeImge(response.getQrCode(), 256, "/home/tomcat/webapps/files/"+url);
                response1.setData("http://118.24.178.247:28080/files/"+url);
                break;

            case FAILED:
                message = "支付宝预下单失败!!!";
                return response1.SERVER_ERROR(message);

            case UNKNOWN:
                message = "系统异常，预下单状态未知!!!";
                return response1.SERVER_ERROR(message);

            default:
                message = "不支持的交易状态，交易返回异常!!!";
                return response1.SERVER_ERROR(message);
        }
        return response1;
    }

}
