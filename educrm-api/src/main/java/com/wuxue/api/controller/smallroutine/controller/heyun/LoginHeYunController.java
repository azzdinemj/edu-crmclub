package com.wuxue.api.controller.smallroutine.controller.heyun;

import com.alibaba.fastjson.JSON;
import com.wuxue.api.controller.smallroutine.client.wxOpenid.DatavalidClient;
import com.wuxue.api.controller.smallroutine.client.wxOpenid.WxOpenidClient;
import com.wuxue.api.controller.smallroutine.utils.WXUtilsMethod;
import com.wuxue.model.Datavalid;
import com.wuxue.model.WxOpenid;
import com.wuxue.utils.contract.Response;
import com.wuxue.utils.https.HttpRequest;
import com.wuxue.api.controller.smallroutine.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;


/**
* @Description: 和韵 小程序登录控制器
*/
@Controller
@RequestMapping("/heyun")
public class LoginHeYunController extends BaseController  {

    @Autowired
    private WxOpenidClient wxOpenidClient;

    @Autowired
    private DatavalidClient datavalidClient;

    private final String  appid="wx33f7da36c04e353d";
    private final String  secret="9e6dba9bfbf44296ba3e92d00b13d4c0";
    /**
     * 获取openid
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("/getOpenid")
    public  String getOpenid(HttpServletRequest request){
        String  string="{'code':'1','msg':'ok}";
        String code=request.getParameter("code");
        if(code!=null&&code!=""){
            String  str="appid="+appid+"&secret="+secret+"&grant_type=authorization_code&js_code="+code;
            string=HttpRequest.sendGet("https://api.weixin.qq.com/sns/jscode2session",str);
        }
        return string;
    }

    /**
     * 判断openid是否已经存在， 若存在 则已登录，  返回 身份验证码
     *                        不存在 返回 null， 则需要进入binDingWx
     * @return
     */
    @ResponseBody
    @RequestMapping("/openidExist")
    public String openidExist(HttpServletRequest request,WxOpenid wxOpenid){
        Map<String,Object> map=new HashMap<>();

        //先查询 该openid 是否已经存在
        Response<WxOpenid> response= wxOpenidClient.find(wxOpenid);
        if(response!=null&&response.getData()!=null) {
            //创建新的身份验证数据
//            Datavalid data = new Datavalid();
//            data.setPkempstu(WXUtilsMethod.getEven(datavalid.getOpenid()));
//            data.setType(0);
//            Response responseDataValid = datavalidClient.save(data);

            map.put("code", "0");
//            map.put("userKey", responseDataValid.getData());
            map.put("msg","ok");
            return JSON.toJSONString(map);
        }
        map.put("code","1");
        map.put("msg","is null");
        return  JSON.toJSONString(map);
    }

    /**
     * 登录小程序 bingding openid
     * @param request
     *             参数: openid
     *              eg: openid:123456
     * @return
     */
    @ResponseBody
    @RequestMapping("/binDingWx")
    public String binDingWx(HttpServletRequest request,WxOpenid wxOpenid){
        Map<String,Object> map=new HashMap<>();

        //创建(更新)身份验证数据
        Datavalid datavalid=new Datavalid();
        datavalid.setType(0);
        datavalid.setPkempstu(WXUtilsMethod.getEven(wxOpenid.getOpenid()));
        Response responseDataValid=datavalidClient.save(datavalid);
        Response resWX=wxOpenidClient.save(wxOpenid);

        if(responseDataValid.getCode()!=0){
            map.put("code",1);
            map.put("msg","出错");
            return JSON.toJSONString(map);
        }
        if(resWX.getCode()!=0){
            map.put("code",1);
            map.put("msg","出错");
            return JSON.toJSONString(map);
        }
        map.put("code",0);
        map.put("msg","ok");
        map.put("data",responseDataValid.getData());
        return JSON.toJSONString(map);
    }

}
