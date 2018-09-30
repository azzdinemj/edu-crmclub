package com.wuxue.api.service.impl;

import com.wuxue.api.mapper.ActivityStudentMapper;
import com.wuxue.api.service.ActivityStudentService;
import com.wuxue.api.service.UtilsService;
import com.wuxue.api.service.WeixinPayService;
import com.wuxue.api.utils.ConfigUtil;
import com.wuxue.api.utils.GuidUtils;
import com.wuxue.model.ActivityStudent;
import com.wuxue.model.ParentOrder;
import com.wuxue.model.TkPayRecords;
import com.wuxue.utils.common.HttpUtil;
import com.wuxue.utils.common.PayCommonUtil;
import com.wuxue.utils.common.XMLUtil;
import com.wuxue.utils.common.ZxingUtils;
import com.wuxue.utils.contract.Constants;
import com.wuxue.utils.contract.LinkEntity;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;


@Service("weixinPayService")
public class WeixinPayServiceImpl implements WeixinPayService {

    private static final Logger logger = LoggerFactory.getLogger(WeixinPayServiceImpl.class);
    @Value("${wexinpay.notify.url}")
    private String notify_url;

    @SuppressWarnings("rawtypes")
    @Override
    public Response weixinPay(TkPayRecords tkPayRecords) {
//        TkPayRecords tkPayRecords = tparm.getData();
        Response response = Response.newResponse();
        String message;
        try {
//            String imgPath= Constants.QRCODE_PATH+Constants.SF_FILE_SEPARATOR+ GuidUtils.getGuid()+".png";
            // 账号信息
//            String key = ConfigUtil.API_KEY; // key
            String key = "qT4cIHgFftAgKMN7yAAQpJSIYxCqLbbw"; // key
            String trade_type = "NATIVE";// 交易类型 原生扫码支付
            SortedMap<Object, Object> packageParams = new TreeMap<Object, Object>();
            ConfigUtil.commonParams(packageParams);
            packageParams.put("total_fee", tkPayRecords.getFee());// 总金额
            packageParams.put("body", tkPayRecords.getBody());// 总金额
            packageParams.put("out_trade_no", tkPayRecords.getPkPayRecords());// 总金额
            packageParams.put("spbill_create_ip", "192.168.0.100");// 总金额
            packageParams.put("mch_id", "1503499461");// 总金额
            packageParams.put("appid", "wxa896e5ebea011bfe");// 总金额
            packageParams.put("notify_url", notify_url);// 回调地址
            packageParams.put("trade_type", trade_type);// 交易类型
            String sign = PayCommonUtil.createSign("UTF-8", packageParams, key);
            packageParams.put("sign", sign);// 签名

            String requestXML = PayCommonUtil.getRequestXml(packageParams);
            String resXml = HttpUtil.postData(ConfigUtil.UNIFIED_ORDER_URL, requestXML);
            Map map = XMLUtil.doXMLParse(resXml);
            String returnCode = (String) map.get("return_code");
            if("SUCCESS".equals(returnCode)){
                String resultCode = (String) map.get("result_code");
                if("SUCCESS".equals(resultCode)){
                    String urlCode = (String) map.get("code_url");
                    ConfigUtil.shorturl(urlCode);//转换为短链接
                    String url = GuidUtils.getGuid()+".png";
                    ZxingUtils.getQRCodeImge(urlCode, 256, "/home/tomcat/webapps/files/"+url);// 生成二维码
                    response.setData("http://118.24.178.247:28080/files/"+url);
                }else{
                    String errCodeDes = (String) map.get("err_code_des");
                    message = "生成微信支付码(系统)失败"+errCodeDes;
                    return response.SERVER_ERROR(message);
                }
            }else{
                String returnMsg = (String) map.get("return_msg");
                message = "生成微信支付码(通信)失败"+returnMsg;
                return response.SERVER_ERROR(message);
            }
        } catch (Exception e) {
            message = "生成微信支付码失败(系统异常)";
            return response.SERVER_ERROR(message);
        }
        return response;
    }

//    @Override
//    public Response unifiedOrder(ParentOrder parentOrder) {
//        Response response = Response.newResponse();
//        String message;
//        try {
////            String imgPath= Constants.QRCODE_PATH+Constants.SF_FILE_SEPARATOR+ GuidUtils.getGuid()+".png";
//            // 账号信息
////            String key = ConfigUtil.API_KEY; // key
//            String key = "qT4cIHgFftAgKMN7yAAQpJSIYxCqLbbw"; // key
//            String trade_type = "NATIVE";// 交易类型 原生扫码支付
//            SortedMap<Object, Object> packageParams = new TreeMap<Object, Object>();
//            ConfigUtil.commonParams(packageParams);
//            packageParams.put("total_fee", tkPayRecords.getFee());// 总金额
//            packageParams.put("body", tkPayRecords.getBody());// 总金额
//            packageParams.put("out_trade_no", tkPayRecords.getPkPayRecords());// 总金额
//            packageParams.put("spbill_create_ip", "192.168.0.100");// 总金额
//            packageParams.put("mch_id", "1503499461");// 总金额
//            packageParams.put("appid", "wxa896e5ebea011bfe");// 总金额
//            packageParams.put("notify_url", notify_url);// 回调地址
//            packageParams.put("trade_type", trade_type);// 交易类型
//            String sign = PayCommonUtil.createSign("UTF-8", packageParams, key);
//            packageParams.put("sign", sign);// 签名
//
//            String requestXML = PayCommonUtil.getRequestXml(packageParams);
//            String resXml = HttpUtil.postData(ConfigUtil.UNIFIED_ORDER_URL, requestXML);
//            Map map = XMLUtil.doXMLParse(resXml);
//            String returnCode = (String) map.get("return_code");
//            if("SUCCESS".equals(returnCode)){
//                String resultCode = (String) map.get("result_code");
//                if("SUCCESS".equals(resultCode)){
//                    String urlCode = (String) map.get("code_url");
//                    ConfigUtil.shorturl(urlCode);//转换为短链接
//                    String url = GuidUtils.getGuid()+".png";
//                    ZxingUtils.getQRCodeImge(urlCode, 256, "/home/tomcat/webapps/files/"+url);// 生成二维码
//                    response.setData("http://118.24.178.247:28080/files/"+url);
//                }else{
//                    String errCodeDes = (String) map.get("err_code_des");
//                    message = "生成微信支付码(系统)失败"+errCodeDes;
//                    return response.SERVER_ERROR(message);
//                }
//            }else{
//                String returnMsg = (String) map.get("return_msg");
//                message = "生成微信支付码(通信)失败"+returnMsg;
//                return response.SERVER_ERROR(message);
//            }
//        } catch (Exception e) {
//            message = "生成微信支付码失败(系统异常)";
//            return response.SERVER_ERROR(message);
//        }
//        return response;
//    }
}
