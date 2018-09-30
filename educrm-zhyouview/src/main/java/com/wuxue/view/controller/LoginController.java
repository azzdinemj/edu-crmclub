package com.wuxue.view.controller;

import com.alibaba.fastjson.JSON;
import com.wuxue.utils.common.MD5Util;
import com.wuxue.utils.contract.Response;
import com.wuxue.model.Student;
import com.wuxue.view.client.student.StudentClient;
import com.wuxue.view.interfaces.ISaveController;
import com.wuxue.view.utils.ChuangLanSmsUtil;
import com.wuxue.view.utils.SessionCache;
import com.wuxue.view.utils.SmsSendRequest;
import com.wuxue.view.utils.SmsSendResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Random;


/**
* @Description: 登录页面控制器
* @author wanghao
* @date  17:53 2018/3/8
* @version V1.0
*/
@Controller
@RequestMapping("/")
public class LoginController extends BaseController
        implements ISaveController<Student,Response> {

    @Autowired
    private StudentClient studentClient;

    @Value("${zhonghang.admin}")
    private String  admin;

    /**
     * 登陆页
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public String getSignon() {
        return "/login/login";
    }

    /**
     * 登陆页
     * @return
     */
    @RequestMapping(value="login",method = RequestMethod.GET)
    public String login() { return "/login/login"; }

    /**
     * 预约报名页
     *
     * @return
     */
    @RequestMapping(value = "studentMember", method = RequestMethod.GET)
    public String studentMember(HttpServletRequest request, Model model) {
//        String userCode = SessionCache.getUserCode();

        return "/member/studentMember";
    }


    /**
     * 注册页
     * @return
     */
    @RequestMapping(value="registered",method = RequestMethod.GET)
    public String registered() { return "/login/registered"; }

    /**
     * 忘记密码页
     * @return
     */
    @RequestMapping(value="forget",method = RequestMethod.GET)
    public String forget() { return "/login/forget"; }

    /**
     * 更换手机号码
     * @return
     */
    @RequestMapping(value="updatephone",method = RequestMethod.GET)
    public String updatephone() { return "/login/update"; }

    /**
     * 登录验证
     * @param request
     * @param model
     * @param student
     * @return
     */
    @RequestMapping(value="/signon",method = RequestMethod.POST)
    @ResponseBody
    public Response<Student> signon(HttpServletRequest request, Model model, Student student, HttpSession session) {
        //访问api,获得登录验证结果
        Response<Student> response = studentClient.login(student);
        if (response.getCode() == 0 && response.getData() != null) {
            String pwd=MD5Util.string2MD5(student.getPassword());
            SessionCache.setCheckpassword(pwd); //储存密码

            SessionCache.setPhone(response.getData().getPhone());//储存电话
            SessionCache.setF_CAPTION(response.getData().getCaption());
            SessionCache.setPkStudent(response.getData().getPkStudent());//储存主键

            session.setAttribute("admin",admin);//后台路径
        }
        return response;
    }

    /**
     * 退出
     * @return
     */
    @RequestMapping(value = "signout", method = RequestMethod.GET)
    public String signOut() {
        //清空静态常量
        SessionCache.setPhone(null);
        SessionCache.setPassword(null);
        return "/login/login";
    }


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
//    }




    public static final String charset = "utf-8";
    // 用户平台API账号(非登录账号,示例:N1234567)
    public static String account = "N2633207";
    // 用户平台API密码(非登录密码)
    public static String pswd ="I6gkYtdZxm497e";
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
            SessionCache.setSTR_CODE(strCode);

            // 短信内容
            String msg = "【253云通讯】你好,你的验证码是"+strCode;
            //手机号码
            String phone = "";
            phone = request.getParameter("phone");//传入需要获取验证码的手机号

            //状态报告
            String report= "true";

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
     *注册（根据输入电话在数据库中检索，若有账号则激活账号）
     * @param request
     * @param model
     * @param student
     * @return
     */
    @Override
    @ResponseBody
    public Response save(HttpServletRequest request, Model model, Student student) {
        //验证码
        String verifyCode=request.getParameter("verifyCode");
        if(!SessionCache.getStrCode().equals(verifyCode)){
                Response response=Response.newResponse();
                response.setMessage("验证码错误");
                response.setCode(1);
                return response;
        }

        //判断验证码操作，成功后执行激活账号操作
        Response response=studentClient.register(student);
        return  response;
    }

    /**
     * 修改密码 登录页
     * @param student  phone，password
     * @return
     */
    @RequestMapping(value="updatepwd",method = RequestMethod.POST)
    @ResponseBody
    public Response updatePwd(Student student,HttpServletRequest request) {
        String verifyCode= request.getParameter("valicode") ;
        //判断短信验证码成功后进入下面操作
        if(!SessionCache.getStrCode().equals(verifyCode)){
            Response response=Response.newResponse();
            response.setMessage("验证码错误");
            response.setCode(1);
            return response;
        }

        if(student.getPassword()==null){
            String strPhone=request.getParameter("ophone");//旧号码  改电话
            Response<Student> studentResponse=studentClient.findByphone(strPhone);
            if(studentResponse.getData()!=null){
                student.setPkStudent(studentResponse.getData().getPkStudent());
                return   studentClient.updatepassword(student);
            }
            return Response.newResponse().USER_NULL();
        }

        return   studentClient.updatepassword(student); // 改密码
    }



}
