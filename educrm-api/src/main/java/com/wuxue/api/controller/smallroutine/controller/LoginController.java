package com.wuxue.api.controller.smallroutine.controller;

import com.alibaba.fastjson.JSON;
import com.wuxue.api.controller.smallroutine.client.student.StudentClient;
import com.wuxue.api.controller.smallroutine.client.teacher.EmployeeClient;
import com.wuxue.api.controller.smallroutine.client.wxOpenid.DatavalidClient;
import com.wuxue.api.controller.smallroutine.client.wxOpenid.WxOpenidClient;
import com.wuxue.api.controller.smallroutine.utils.ChuangLanSmsUtil;
import com.wuxue.api.controller.smallroutine.utils.SmsSendRequest;
import com.wuxue.api.controller.smallroutine.utils.SmsSendResponse;
import com.wuxue.model.Datavalid;
import com.wuxue.model.Employee;
import com.wuxue.model.Student;
import com.wuxue.model.WxOpenid;
import com.wuxue.utils.contract.Response;
import com.wuxue.utils.https.HttpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;


/**
 *
 *和韵，阶点两个小程序的接口，初步全部简单移动到api层。
* @Description: 首页页面控制器
*/
@Controller
@RequestMapping("/")
public class LoginController extends BaseController  {

    @Autowired
    private WxOpenidClient wxOpenidClient;

    @Autowired
    private EmployeeClient employeeClient;

    @Autowired
    private StudentClient studentClient;

    @Autowired
    DatavalidClient datavalidClient;


    public static final String charset = "utf-8";
    // 用户平台API账号(非登录账号,示例:N1234567)
    public static String account = "N2633207";
    // 用户平台API密码(非登录密码)
     public static String pswd ="I6gkYtdZxm497e";
   // public static String pswd ="";

    /**
     * 获取随机数
     * @return
     */
    public  int getRenadom(){
        int flag = new Random().nextInt(999999);
        if (flag < 100000)
        {
            flag += 100000;
        }
        return  flag;
    }

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
            String  str="appid=wx94f5713d71f238ba&secret=b38925aaa616ed732ca8dbb3c2152ee7&grant_type=authorization_code&js_code="+code;
            string=HttpRequest.sendGet("https://api.weixin.qq.com/sns/jscode2session",str);
        }
        return string;
    }


    /**
     * 创蓝短信验证码获取接口
     * */
    @ResponseBody
    @RequestMapping(value="/getValidCode")
    public Response getValidCode(HttpServletRequest request ) throws Exception{
        Response resp=Response.newResponse();
        try{
            //请求地址请登录253云通讯自助通平台查看或者询问您的商务负责人获取
            String smsSingleRequestServerUrl = "http://smssh1.253.com/msg/send/json";

            //生成验证码
            String strCode=String.valueOf(getRenadom());

            //短信内容
            String msg = "【阶点教育】你好,你的验证码是"+strCode;
            //手机号码
            String phone = "";
            phone = request.getParameter("phone");//传入需要获取验证码的手机号

            if(phone.equals("")||phone==null){
                resp.setCode(1);
                resp.setMessage("电话为空");
                return resp;
            }
            //状态报告
            String report= "true";

            //储存验证码   判断
            Datavalid datavalid=new Datavalid();
            datavalid.setType(1);
            datavalid.setPhone(phone);
            datavalid.setPkempstu(strCode);
            Response responseData=datavalidClient.save(datavalid);
            if(responseData.getCode()==1){ return  responseData; }

            SmsSendRequest smsSingleRequest = new SmsSendRequest(account, pswd, msg, phone,report);

            String requestJson = JSON.toJSONString(smsSingleRequest);
            System.out.println("before request string is: " + requestJson);

            String response = ChuangLanSmsUtil.sendSmsByPost(smsSingleRequestServerUrl, requestJson);
            System.out.println("response after request result is :" + response);

            SmsSendResponse smsSingleResponse = JSON.parseObject(response, SmsSendResponse.class);
            System.out.println("response  toString is :" + smsSingleResponse);

            resp.setCode(Integer.parseInt(smsSingleResponse.getCode()));
            resp.setMessage(smsSingleResponse.getErrorMsg());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            resp.setCode(1);
            resp.setMessage("异常");
        }
        return resp;
    }

    /**
     * 阶点
     * 判断openid是否已经存在， 若存在 则已登录，  返回 身份验证码，用户主键 ，用户类型
     *                        不存在 返回 null， 则需要进入binDingWx
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/openidExist")
    public String openidExist(HttpServletRequest request,WxOpenid wxOpenid){
        Map<String,Object> map=new HashMap<>();

        //先查询 该openid 是否已经存在
        Response<WxOpenid> response= wxOpenidClient.find(wxOpenid);
        if(response!=null&&response.getData()!=null) {
            Datavalid datavalid = new Datavalid();
            datavalid.setType(0);
            datavalid.setPkempstu(response.getData().getPkEmpstu());
            Response<Datavalid> datavalidResponse = datavalidClient.findBypkempstu(datavalid);
            if (datavalidResponse != null && datavalidResponse.getData() != null) {
                //创建身份验证数据
                Datavalid data = new Datavalid();
                datavalid.setPkempstu(datavalidResponse.getData().getPkempstu());
                datavalid.setType(0);
                Response responseDataValid = datavalidClient.save(datavalid);

                map.put("code", "0");
                map.put("userKey", responseDataValid.getData());
                map.put("type", response.getData().getType());
                map.put("pkEmpStu", response.getData().getPkEmpstu());
                map.put("msg","ok");
                return JSON.toJSONString(map);
            }
        }
        map.put("code","1");
        map.put("msg","is null");
        return  JSON.toJSONString(map);
    }



    /**
     * 登录小程序
     *教师、学生绑定微信openid接口
     * @param request
     *             datavalid(openid，type)
     *             request phone：电话   validcode：验证码
     * @return
     */
    @ResponseBody
    @RequestMapping("/binDingWx")
    public String binDingWx(HttpServletRequest request,WxOpenid wxOpenid){
        Map<String,Object> map=new HashMap<>();
        String phone=request.getParameter("phone");
        String validcode=request.getParameter("validcode");

        Datavalid datavalid1=new Datavalid();
        datavalid1.setType(1);
        datavalid1.setPhone(phone);
        Response<Datavalid> responseDatavalid=datavalidClient.findByphone(datavalid1);

        if(responseDatavalid.getData()==null||!responseDatavalid.getData().getPkempstu().equals(validcode)){
            map.put("code",1);
            map.put("msg","验证码错误!!!");
            return JSON.toJSONString(map);
        }
        String  pkEmpStu="";
        //根据电话查找出主键, 先进入教师查找，在进入学生查找
        //        不存在 ：则返回不是jiedian 用户
        //        若存在： 进入openid表查询，不存在则插入一条数据。
            Response<Employee> response=employeeClient.findByphone(phone);
            if(response.getData()==null){
                Response<Student> responseStu=studentClient.findByphone(phone);
                if(responseStu.getData()==null){
                    map.put("code",1);
                    map.put("msg","用户不存在!!!");
                    return JSON.toJSONString(map);
                }else{
                    wxOpenid.setType(1);
                    wxOpenid.setPkEmpstu(responseStu.getData().getPkStudent());
                    pkEmpStu=responseStu.getData().getPkStudent();
                    map.put("type","1");
                    map.put("pkEmpstu",pkEmpStu);

                    //学生绑定微信号
                    WxOpenid wx=new WxOpenid();
                    wx.setOpenid(wxOpenid.getOpenid());
                    Response<List<WxOpenid>> listResponse=wxOpenidClient.findbyopenid(wx);
                    if(listResponse.getData()==null){
                        Response resWX=wxOpenidClient.save(wxOpenid);
                        if(resWX.getCode()!=0){
                            map.put("code",1);
                            map.put("msg","出错");
                            return JSON.toJSONString(map);
                        }
                    }else if(!listResponse.getData().get(0).getPkEmpstu().equals(pkEmpStu)){
                        map.put("code",1);
                        map.put("msg","微信不可多次绑定!!!");
                        return JSON.toJSONString(map);
                    }

                }
            }else{
                wxOpenid.setType(0);
                wxOpenid.setPkEmpstu(response.getData().getPkEmployee());
                pkEmpStu=response.getData().getPkEmployee();
                map.put("type","0");
                map.put("pkEmpstu",pkEmpStu);

                //老师绑定微信号
                WxOpenid wx=new WxOpenid();
                wx.setOpenid(wxOpenid.getOpenid());
                Response<List<WxOpenid>> listResponse=wxOpenidClient.findbyopenid(wx);
                if(listResponse.getData()==null){
                    Response resWX=wxOpenidClient.save(wxOpenid);
                    if(resWX.getCode()!=0){
                        Map<String,Object> map1=new HashMap<>();
                        map1.put("code",1);
                        map1.put("msg","出错");
                        return JSON.toJSONString(map1);
                    }
                }else if(!listResponse.getData().get(0).getPkEmpstu().equals(pkEmpStu)){
                    Map<String,Object> map1=new HashMap<>();
                    map1.put("code",1);
                    map1.put("msg","微信不可多次绑定!!!");
                    return JSON.toJSONString(map1);
                }

            }

        //创建(更新)身份验证数据
        Datavalid datavalid=new Datavalid();
        datavalid.setPkempstu(pkEmpStu);
        datavalid.setType(0);
        Response responseDataValid=datavalidClient.save(datavalid);

        map.put("code",0);
        map.put("msg","ok");
        map.put("data",responseDataValid.getData());
        return JSON.toJSONString(map);

    }


    //    /**
//     * 腾讯云短信验证码获取接口
//     *
//     * */
//    @ResponseBody
//    @RequestMapping(value="/getValidCode")
//    public Response getValidCode(HttpServletRequest request ) throws Exception{
//
//        Response response=Response.newResponse();
//        try{
//            String phone = "18690011974";
//            //phone = request.getParameter("phone");//传入需要获取验证码的手机号
//
//            int appid=1400083124;//腾讯云平台上短信模块上自己的应用
//            String appkey = "fb4ca821c116372a3765de1f4b77e459";//腾讯云平台上短信模块上自己的应用appsecret
//
//            String activetime = "30min";//自己设置短信的有效期
//            //初始化单发
//            SmsSingleSender singleSender = new SmsSingleSender(appid,appkey);
//            SmsSingleSenderResult singleSenderResult;
//
//            //生成随机6位数验证码
//            String non_str = String.valueOf(getRenadom());
//
//            //普通单发
//            singleSenderResult = singleSender.send(0, "86", phone, non_str+"为您登陆验证码，请于"+activetime+"分钟内填写。如非本人操作，请忽略本短信。", "", "");
//            System.out.println(singleSenderResult);
//
//            //拉取短信回执和回复
//            SmsStatusPuller pullstatus = new SmsStatusPuller(appid,appkey);
//            SmsStatusPullCallbackResult callbackResult = pullstatus.pullCallback(10);
//            System.out.println(callbackResult);
//            SmsStatusPullReplyResult replyResult = pullstatus.pullReply(10);
//            System.out.println(replyResult);
//
//            // 发送通知内容
//            SmsVoicePromptSender smsVoicePromtSender = new SmsVoicePromptSender(appid,appkey);
//            SmsVoicePromptSenderResult smsSingleVoiceSenderResult = smsVoicePromtSender.send("86", phone, 2,2,"欢迎使用", "");
//            System.out.println(smsSingleVoiceSenderResult);
//
//            response.setCode(0);
//            response.setMessage("短信已发送");
//        } catch (Exception e) {
//            e.printStackTrace();
//            System.out.println(e.getMessage());
//            response.setCode(1);
//            response.setMessage("异常");
//        }
//        return response;
//

}
