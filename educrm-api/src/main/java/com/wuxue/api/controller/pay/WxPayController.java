package com.wuxue.api.controller.pay;

import com.alipay.demo.trade.config.Configs;
import com.wuxue.api.service.TkPayRecordsService;
import com.wuxue.api.service.WeixinPayService;
import com.wuxue.api.utils.ConfigUtil;
import com.wuxue.model.ParentOrder;
import com.wuxue.model.TkPayRecords;
import com.wuxue.utils.common.HttpUtil;
import com.wuxue.utils.common.PayCommonUtil;
import com.wuxue.utils.common.XMLUtil;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;


@RestController
@RequestMapping(value = "api/pay/wxPay")
public class WxPayController{

    @Autowired
    private WeixinPayService weixinPayService;
    @Autowired
    private TkPayRecordsService tkPayRecordsService;
    @Value("${wexinpay.notify.url}")
    private String notify_url;

    @RequestMapping(value = "/qcPay",method = RequestMethod.POST)
    public Response qcPay(@RequestBody TkPayRecords tkPayRecords) {
//        if(response.getCode() == 0){
//            String img= "../qrcode/"+ GuidUtils.getGuid()+".png";
//            map.addAttribute("img", img);
//        }
        return weixinPayService.weixinPay(tkPayRecords);
    }

//    @RequestMapping(value = "/unifiedOrder",method = RequestMethod.POST)
//    public Response unifiedOrder(@RequestBody ParentOrder parentOrder) {
////        if(response.getCode() == 0){
////            String img= "../qrcode/"+ GuidUtils.getGuid()+".png";
////            map.addAttribute("img", img);
////        }
//        return weixinPayService.unifiedOrder(parentOrder);
//    }

    @SuppressWarnings({ "unchecked", "rawtypes"})
    @RequestMapping(value = "pay")
    public void weixin_notify(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 读取参数
        InputStream inputStream = request.getInputStream();
        StringBuffer sb = new StringBuffer();
        String s;
        BufferedReader in = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
        while ((s = in.readLine()) != null) {
            sb.append(s);
        }
        in.close();
        inputStream.close();

        // 解析xml成map
        Map<String, String> m = new HashMap<String, String>();
        m = XMLUtil.doXMLParse(sb.toString());

        // 过滤空 设置 TreeMap
        SortedMap<Object, Object> packageParams = new TreeMap<Object, Object>();
        Iterator it = m.keySet().iterator();
        while (it.hasNext()) {
            String parameter = (String) it.next();
            String parameterValue = m.get(parameter);

            String v = "";
            if (null != parameterValue) {
                v = parameterValue.trim();
            }
            packageParams.put(parameter, v);
        }
        // 账号信息
        String key = "qT4cIHgFftAgKMN7yAAQpJSIYxCqLbbw"; // key
        // 判断签名是否正确
        if (PayCommonUtil.isTenpaySign("UTF-8", packageParams, key)) {
//            logger.info("微信支付成功回调");
            // ------------------------------
            // 处理业务开始
            // ------------------------------
            String resXml = "";
            if ("SUCCESS".equals((String) packageParams.get("result_code"))) {
                // 这里是支付成功
                String orderNo = (String) packageParams.get("out_trade_no");
//                logger.info("微信订单号{}付款成功",orderNo);
                //这里 根据实际业务场景 做相应的操作
                TkPayRecords tkPayRecords = new TkPayRecords();
                tkPayRecords.setPkPayRecords(orderNo);
                tkPayRecords.setStatus(0);
                tkPayRecordsService.updateStatus(tkPayRecords);
                // 通知微信.异步确认成功.必写.不然会一直通知后台.八次之后就认为交易失败了.
                resXml = "<xml>" + "<return_code><![CDATA[SUCCESS]]></return_code>" + "<return_msg><![CDATA[OK]]></return_msg>" + "</xml> ";
            } else {
//                logger.info("支付失败,错误信息：{}",packageParams.get("err_code"));
                resXml = "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>" + "<return_msg><![CDATA[报文为空]]></return_msg>" + "</xml> ";
            }
            // ------------------------------
            // 处理业务完毕
            // ------------------------------
            BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());
            out.write(resXml.getBytes());
            out.flush();
            out.close();
        } else {
//            logger.info("通知签名验证失败");
        }

    }

    @SuppressWarnings({ "unchecked", "rawtypes"})
    @RequestMapping(value = "/bizpayurl",method = RequestMethod.POST)
    public void bizpayurl(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //读取参数
        InputStream inputStream = request.getInputStream();
        StringBuffer sb = new StringBuffer();
        String s;
        BufferedReader in = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
        while ((s = in.readLine()) != null) {
            sb.append(s);
        }
        in.close();
        inputStream.close();

        //解析xml成map
        Map<String, String> map = XMLUtil.doXMLParse(sb.toString());
        //过滤空 设置 TreeMap
        SortedMap<Object, Object> packageParams = new TreeMap<Object, Object>();
        Iterator it = map.keySet().iterator();
        while (it.hasNext()) {
            String parameter = (String) it.next();
            String parameterValue = map.get(parameter);

            String v = "";
            if (null != parameterValue) {
                v = parameterValue.trim();
            }
            packageParams.put(parameter, v);
        }
        //判断签名是否正确
        if (PayCommonUtil.isTenpaySign("UTF-8", packageParams, ConfigUtil.API_KEY)) {
            //统一下单
            SortedMap<Object, Object> params = new TreeMap<Object, Object>();
            ConfigUtil.commonParams(params);
            //随即生成一个 入库 走业务逻辑
            String out_trade_no=Long.toString(System.currentTimeMillis());
            params.put("body", "模式一扫码支付");// 商品描述
            params.put("out_trade_no", out_trade_no);// 商户订单号
            params.put("total_fee", "100");// 总金额
            params.put("spbill_create_ip", "192.168.1.66");// 发起人IP地址
            params.put("notify_url", notify_url);// 回调地址
            params.put("trade_type", "NATIVE");// 交易类型

            String paramsSign = PayCommonUtil.createSign("UTF-8", params, ConfigUtil.API_KEY);
            params.put("sign", paramsSign);// 签名
            String requestXML = PayCommonUtil.getRequestXml(params);

            String resXml = HttpUtil.postData(ConfigUtil.UNIFIED_ORDER_URL, requestXML);
            Map<String, String>  payResult = XMLUtil.doXMLParse(resXml);
            String returnCode = (String) payResult.get("return_code");
            if("SUCCESS".equals(returnCode)){
                String resultCode = (String) payResult.get("result_code");
                if("SUCCESS".equals(resultCode)){
//                    logger.info("(订单号：{}生成微信支付码成功)",out_trade_no);

                    String prepay_id = payResult.get("prepay_id");
                    SortedMap<Object, Object> prepayParams = new TreeMap<Object, Object>();
                    ConfigUtil.commonParams(params);
                    prepayParams.put("prepay_id", prepay_id);
                    prepayParams.put("return_code", "SUCCESS");
                    prepayParams.put("result_code", "SUCCESS");
                    String prepaySign =  PayCommonUtil.createSign("UTF-8", prepayParams, ConfigUtil.API_KEY);
                    prepayParams.put("sign", prepaySign);
                    String prepayXml = PayCommonUtil.getRequestXml(prepayParams);

                    //通知微信 预下单成功
                    BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());
                    out.write(prepayXml.getBytes());
                    out.flush();
                    out.close();
                }else{
                    String errCodeDes = (String) map.get("err_code_des");
//                    logger.info("(订单号：{}生成微信支付码(系统)失败[{}])",out_trade_no,errCodeDes);
                }
            }else{
                String returnMsg = (String) map.get("return_msg");
//                logger.info("(订单号：{} 生成微信支付码(通信)失败[{}])",out_trade_no,returnMsg);
            }
        }else{
//            logger.info("签名错误");
        }
    }
}
