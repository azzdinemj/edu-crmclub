package com.wuxue.view.client;

import com.alibaba.fastjson.JSON;
import com.wuxue.utils.common.EncryptUtil;
import com.wuxue.utils.common.MD5Util;
import com.wuxue.utils.contract.Head;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.https.HttpRequest;
import com.wuxue.view.enums.ActionEnum;
import com.wuxue.view.utils.SessionCache;
import org.springframework.beans.factory.annotation.Value;

import java.util.Optional;

public abstract class BaseClient {
    @Value("${wuxue.base_uri}")
    protected String base_Uri;

    protected abstract String getPageName();

    protected abstract String getModuleName();

    protected String getSendUrl(ActionEnum actionName) {
//        SessionCache.
        System.out.print("API:"+base_Uri + getModuleName() + getPageName() + "/" + actionName.toString().toLowerCase());
        return base_Uri + getModuleName() + getPageName() + "/" + actionName.toString().toLowerCase();
    }

    protected <TParms> String POST(String url, TParms parms) {
        Request<TParms> request = new Request<TParms>();
        Head head = new Head();
        head.setAppKey("wuxueApp");
        head.setSalt(System.currentTimeMillis());
        head.setSign(EncryptUtil.encrypt(SessionCache.getUserCode()+"||"+MD5Util.string2MD5(SessionCache.getPassword())));
        head.setUrl(SessionCache.getUrl1());
        request.setHead(head);
        request.setCurrentUser(SessionCache.getUserCode());
        request.setCurrendDomain(SessionCache.getPkdomain());
        request.setData(parms);
        String requestXml = JSON.toJSONString(request);
        String responseXml =  HttpRequest.sendPost(url, requestXml);
        //Response<TResponse> response = JSON.parseObject(responseXml,new TypeReference<Response<TResponse>>(){});
        System.out.println("POST:requestXml:" + requestXml + ",responseXml:" + responseXml);
        return responseXml;
    }
}
