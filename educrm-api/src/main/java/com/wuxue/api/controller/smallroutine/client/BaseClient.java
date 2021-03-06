package com.wuxue.api.controller.smallroutine.client;

import com.alibaba.fastjson.JSON;
import com.wuxue.api.controller.smallroutine.enums.ActionEnum;
import com.wuxue.api.controller.smallroutine.utils.SessionCache;
import com.wuxue.utils.common.EncryptUtil;
import com.wuxue.utils.common.MD5Util;
import com.wuxue.utils.contract.Head;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.https.HttpRequest;
import org.springframework.beans.factory.annotation.Value;

public abstract class BaseClient {
    @Value("${wuxue.base_uri}")
    protected String base_Uri;

//    protected String base_Uri="http://localhost:18082/api";

    protected abstract String getPageName();

    protected abstract String getModuleName();

    protected String getSendUrl(ActionEnum actionName) {
        System.out.println( "api路径："+base_Uri + getModuleName() + getPageName() + "/" + actionName.toString().toLowerCase());
        return base_Uri + getModuleName() + getPageName() + "/" + actionName.toString().toLowerCase();
    }

    protected <TParms> String POST(String url, TParms parms) {
        Request<TParms> request = new Request<TParms>();
        Head head = new Head();
        head.setAppKey("zhonghang");
        head.setSalt(System.currentTimeMillis());
        head.setSign(EncryptUtil.encrypt(SessionCache.getPhone()+"||"+MD5Util.string2MD5(SessionCache.getPassword())));
        request.setHead(head);

        request.setData(parms);
        String requestXml = JSON.toJSONString(request);
        String responseXml = HttpRequest.sendPost(url, requestXml);
        //Response<TResponse> response = JSON.parseObject(responseXml,new TypeReference<Response<TResponse>>(){});
        System.out.println("POST:requestXml:" + requestXml + ",responseXml:" + responseXml);
        return responseXml;
    }
}
