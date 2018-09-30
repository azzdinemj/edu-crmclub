package com.wuxue.view.controllers.wxBinding;

import com.wuxue.model.Datavalid;
import com.wuxue.model.Employee;
import com.wuxue.model.Student;
import com.wuxue.model.WxOpenid;
import com.wuxue.utils.contract.Response;
import com.wuxue.utils.https.HttpRequest;
import com.wuxue.view.client.datavalid.DatavalidClient;
import com.wuxue.view.client.datavalid.WxOpenidClient;
import com.wuxue.view.client.student.StudentClient;
import com.wuxue.view.client.system.EmployeeClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Auther: wh
 * @Date: 2018/6/29 09:40
 * @Description: openid 绑定到employee/student
 */
@Controller
@RequestMapping("guanqiao/wxBinding")
public class EmployeeBindingWX {

    @Autowired
    private DatavalidClient datavalidClient;
    @Autowired
    private EmployeeClient employeeClient;
    @Autowired
    private StudentClient studentClient;
    @Autowired
    private WxOpenidClient wxOpenidClient;


    private final String  appid="wx65e4d2554b4ef653";
    private final String  secret="f28ba18b0025042be764f10bedccb7ed";


    /**
     * 获取openid  小程序获取 openid
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("/getOpenid")
    public  String getOpenid(HttpServletRequest request){
        String  string="{'code':'1','msg':'error'}";
        String code=request.getParameter("code");
        if(code!=null&&code!=""){
            String  str="appid="+appid+"&secret="+secret+"&grant_type=authorization_code&js_code="+code;
            string= HttpRequest.sendGet("https://api.weixin.qq.com/sns/jscode2session",str);
        }
        return string;
    }

    /**
     * 获取openid  公众号获取 openid
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("/getOpenidPublic")
    public  String getOpenidPublic(HttpServletRequest request){
        String  string="{'errcode':'1','errmsg':'code为空'}";
        String code=request.getParameter("code");
        if(code!=null&&!"".equals(code)){
            String  str="appid="+appid+"&secret="+secret+"&code="+code+"&grant_type=authorization_code";
            string= HttpRequest.sendGet("https://api.weixin.qq.com/sns/oauth2/access_token",str);
        }
        return string;
    }


    /**
     * 绑定操作
     * @return
     */
    @RequestMapping("/wxBinding")
    @ResponseBody
    public Response binDing(HttpServletRequest request){
        Response response=new Response();
        //验证码
        String verifyCode=request.getParameter("verifyCode");
        //phone
        String phone=request.getParameter("phone");
        //openid
        String openid=request.getParameter("openId");

        //获取储存的验证码
        Datavalid datavalid1=new Datavalid();
        datavalid1.setType(1);
        datavalid1.setPhone(phone);
        Response<Datavalid> responseDatavalid=datavalidClient.findByphone(datavalid1);
        //验证码判断
        if(responseDatavalid.getData()==null||!responseDatavalid.getData().getPkempstu().equals(verifyCode)){
            response.setCode(1);
            response.setMessage("验证码错误!!!");
            return response;
        }

        WxOpenid wxOpenid=getType(phone);
        if(wxOpenid==null){
            response.setCode(2);
            response.setMessage("该用户不存在!!!");
            return response;
        }

        WxOpenid wx=new WxOpenid();
        wx.setOpenid(openid);
        Response<List<WxOpenid>> listResponse1=wxOpenidClient.find(wx);
        if(listResponse1.getData()!=null){
            response.setCode(3);
            response.setMessage("微信不可多次绑定!!!");
            return response;
        }

        wxOpenid.setOpenid(openid);
        //新建绑定记录
        return  wxOpenidClient.save(wxOpenid);
    }


    /**
     * 根据电话查询类型
     * @param phone
     * @return
     */
    public  WxOpenid  getType(String phone){
        Integer  i=0;
        WxOpenid wxOpenid=new WxOpenid();
        //老师判断
        Employee employee=new Employee();
        employee.setPhone(phone);
        Response<List<Employee>> listResponse=employeeClient.find(employee);
        if(listResponse.getData().size()<=0){
            i=3;
        }else{
            wxOpenid.setType(0);
            wxOpenid.setPkEmpstu(listResponse.getData().get(0).getPkEmployee());
        }

        //不存在该老师手机号码，进行学生手机号查询
        if(i==3){
            //学生判断
            Student student=new Student();
            student.setPhone(phone);
            Response<List<Student>> listResponse2=studentClient.find(student);
            if(listResponse.getData().size()<=0){
                i=3;
            }else{
                wxOpenid.setType(1);
                wxOpenid.setPkEmpstu(listResponse.getData().get(0).getPkEmployee());
            }
        }

        return wxOpenid;
    }



}
