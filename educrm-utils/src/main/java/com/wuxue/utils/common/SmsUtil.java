package com.wuxue.utils.common;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.TypeReference;
import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import com.wuxue.utils.contract.Response;

import javax.xml.ws.http.HTTPException;
import java.io.IOException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class SmsUtil {
    // 短信应用SDK AppID
    public static final int appid = 1400083124; // 1400开头

    // 短信应用SDK AppKey
    public static final String appkey = "fb4ca821c116372a3765de1f4b77e459";

    // 需要发送短信的手机号码
//    String[] phoneNumbers = {"18326091157",};

    // 短信模板ID，需要在短信应用中申请
    public static final int templateId = 108173; // NOTE: 这里的模板ID`7839`只是一个示例，真实的模板ID需要在短信控制台中申请

    // 签名
    public static final String smsSign = ""; // NOTE: 这里的签名"腾讯云"只是一个示例，真实的签名需要在短信控制台中申请，另外签名参数使用的是`签名内容`，而不是`签名ID`
    public static Response sendMessage(String phone){

        Response resp=Response.newResponse();
        try{
            SmsSingleSender ssender = new SmsSingleSender(appid, appkey);
            //生成验证码
            String code = String.valueOf((int)((Math.random()*9+1)*100000));
            String[] strCode={code};
//            SessionCache.setSTR_CODE(strCode);

            // 短信内容
//            String msg = "【君华教育】你好,你的验证码是"+strCode;

            //状态报告
            String report= "true";

            //储存验证码   判断
//            Datavalid datavalid=new Datavalid();
//            datavalid.setType(1);
//            datavalid.setPhone(phone);
//            datavalid.setPkempstu(strCode);
//            Response responseData=datavalidClient.save(datavalid);
//            if(responseData.getCode()==1){ return  responseData; }

            SmsSingleSenderResult result = ssender.sendWithParam("86", phone,
                    templateId, strCode, smsSign, "", "");
            System.out.print(result);
            resp.setData(code);
            Map maps = (Map)JSON.parse(JSON.toJSONString(result));
            if(Integer.valueOf(maps.get("result").toString()) != 0){
                resp.setCode(Integer.valueOf(maps.get("result").toString()));
                resp.setMessage(maps.get("errmsg").toString());
                return resp;
            }
//            System.out.println("before request string is: " + requestJson);
//
//            String response = ChuangLanSmsUtil.sendSmsByPost(smsSingleRequestServerUrl, requestJson);
//            System.out.println("response after request result is :" + response);
//
//            SmsSendResponse smsSingleResponse = JSON.parseObject(response, SmsSendResponse.class);
//            System.out.println("response  toString is :" + smsSingleResponse);
//
//            resp.setCode(Integer.parseInt(smsSingleResponse.getCode()));
//            resp.setMessage(smsSingleResponse.getErrorMsg());

        }catch (HTTPException e) {
            // HTTP响应码错误
            e.printStackTrace();
        } catch (JSONException e) {
            // json解析错误
            e.printStackTrace();
        } catch (IOException e) {
            // 网络IO错误
            e.printStackTrace();
        }catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            resp.setCode(1);
            resp.setMessage("异常");
        }
        return resp;
    }



    public static void main(String[] args){
        Response response = sendMessage("18326091157");
        System.out.println(response.getCode());
        System.out.println(response.getMessage());
        System.out.println(response.getData());
    }
}
