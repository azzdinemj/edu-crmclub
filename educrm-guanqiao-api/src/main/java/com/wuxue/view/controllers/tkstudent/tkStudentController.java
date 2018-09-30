package com.wuxue.view.controllers.tkstudent;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.fasterxml.jackson.databind.type.ReferenceType;
import com.wuxue.model.*;
import com.wuxue.utils.common.MD5Util;
import com.wuxue.utils.contract.Light;
import com.wuxue.utils.contract.Response;
import com.wuxue.view.client.datavalid.DatavalidClient;
import com.wuxue.view.client.product.ScheduleClient;
import com.wuxue.view.client.product.TkLearnRecordsClient;
import com.wuxue.view.client.product.TkProductOrderClient;
import com.wuxue.view.client.student.StudentClient;
import com.wuxue.view.client.system.TkPayRecordsClient;
import com.wuxue.view.controllers.BaseController;
import com.wuxue.view.interfaces.IDeleteController;
import com.wuxue.view.interfaces.IQueryByPagingController;
import com.wuxue.view.interfaces.IQueryController;
import com.wuxue.view.interfaces.ISaveController;
import com.wuxue.view.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 *
 * description:  学生管理
 * @auther: wh
 * @date: 2018/6/14 17:29
 */
@Controller
@RequestMapping(value = "/guanqiao/student")
public class tkStudentController extends BaseController
        implements IQueryController<Student, String>, ISaveController<Student, Response>,IQueryByPagingController<Student,Map<String,Object>>,
        IDeleteController<Student, Response>{

    @Autowired
    private StudentClient studentClient;

    @Autowired
    private TkPayRecordsClient tkPayRecordsClient;

    @Autowired
    private TkProductOrderClient tkProductOrderClient;

    @Autowired
    private ScheduleClient scheduleClient;

    @Autowired
    private TkLearnRecordsClient tkLearnRecordsClient;

    /**
     * 计算课时(总 --已使用-- 剩余)
     * @return
     */
    @RequestMapping("/getmyclasshours")
    @ResponseBody
    public Map<String,Object> getmyclasshours(HttpServletRequest request){
        Map<String,Object> map=new HashMap<>();
        String str=request.getParameter("pkStudent");
        Integer total=0;//总数
        Integer use=0; //已使用
        Integer overplus=0; //剩余

        if(str!=null) {
            //学生支付订单
            TkPayRecords tkPayRecords = new TkPayRecords();
            tkPayRecords.setPkStudent(str);
            tkPayRecords.setStatus(0);
            Response<List<TkPayRecords>> recordsResponse = tkPayRecordsClient.find(tkPayRecords);
            if (recordsResponse.getData()!=null&&recordsResponse.getData().size()>0) {
                List<TkPayRecords> tkPayRecordsList = recordsResponse.getData();
                for (TkPayRecords t : tkPayRecordsList) {
                    Map<String,Object> maps=t.getMap();
                    Object object=maps.get(Light.TKSETMEAL);
                    if(object!=null){
                        total=total+(Integer)object;//总共购买的课时
                    }
                }
            }

            //学生 学习记录
            TkLearnRecords tkLearnRecords = new TkLearnRecords();
            tkLearnRecords.setPkStudent(str);
            Response<List<TkLearnRecords>> listResponse = tkLearnRecordsClient.find(tkLearnRecords);

            if (listResponse.getData()!=null&&listResponse.getData().size()>0) {
                for (TkLearnRecords tkLearnRecords1:listResponse.getData()) {
                       Schedule schedule=new Schedule();
                       schedule.setPkSchedule(tkLearnRecords1.getPkSchedule());
                       Response<Schedule> scheduleResponse=scheduleClient.findguanqiaoBypk(schedule);
                       use=use+scheduleResponse.getData().getProductUseclasshours();
                }

            }
        }
        overplus=total-use;
        map.put("total",total);
        map.put("use",use);
        map.put("overplus",overplus);
        return map;
    }

    /**
     * 学生 登陆验证
     */
    @RequestMapping(value = "/stusignon", method = RequestMethod.POST)
    @ResponseBody
    public Response<Student> signon(HttpServletRequest request, Student student) {

        Response<Student> response = studentClient.login(student);
        if (response.getCode() == 0 && response.getData() != null) {
            SessionCache.setPk(response.getData().getPkStudent());
            SessionCache.setUserName(response.getData().getCaption());
            SessionCache.setPassword(response.getData().getPassword());

            //更新上次登录时间
            response.getData().setLastloginDate(new Date());
            response.getData().setPassword(MD5Util.string2MD5(student.getPassword()));
            studentClient.save(response.getData());

            //判断是否是新用户，（创建日期是30天内，并且没有预约过课程）
            Date creationDate=response.getData().getCreationDate();
            long i= getDistanceDays(creationDate);
            if(i+30>0){

                //查询该学生的预约记录，
                //是否有体验课预约记录
                TkProductOrder t=new TkProductOrder();
                t.setPkStudent(response.getData().getPkStudent());
                Response<List<TkProductOrder>> listResponse= tkProductOrderClient.find(t);
                if(listResponse.getData().size()>0){
                    for (TkProductOrder tk:listResponse.getData()) {
                        Schedule schedule=new Schedule();
                        schedule.setPkSchedule(tk.getPkSchedule());
                        Response<Schedule> s= scheduleClient.findguanqiaoBypk(schedule);
                        if(s.getData()!=null){
                            Map<String,Object> map=s.getData().getMap();
                            Product product= JSONObject.parseObject(map.get("product").toString(),new TypeReference<Product>(){});
                            if(!product.getProductType().equals("201830497523622781")){
                                response.getData().put("isnew",0);
                            }
                        }
                    }

                }

            }
        }

        return response;
    }


    /**
     * 计算 new Date() 和 输入Date相差几天。
     * @param date
     * @return
     */
    public static long getDistanceDays(Date date) {
        //DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

        long days = 0;
        try {
           // Date time = df.parse(date);//String转Date
            Date time=date;
            Date now = new Date();//获取当前时间
            long time1 = time.getTime();
            long time2 = now.getTime();
            long diff = time1 - time2;
            days = diff / (1000 * 60 * 60 * 24);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return days;//正数表示在当前时间之后，负数表示在当前时间之前
    }

    public static  void main(String [] args){

        System.out.println(getDistanceDays(new Date()));
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

    @Autowired
    DatavalidClient datavalidClient;

    public static final String charset = "utf-8";
    // 用户平台API账号(非登录账号,示例:N1234567)
    public static String account = "1400083124";
    // 用户平台API密码(非登录密码)
    public static String pswd ="fb4ca821c116372a3765de1f4b77e459";
    /**
     * 创蓝短信验证码获取接口
     * */
    @ResponseBody
    @RequestMapping(value="/getValidCode")
    public Response getValidCode(HttpServletRequest request ) throws Exception{

        Response resp=Response.newResponse();
        try{
            //请求地址请登录253云通讯自助通平台查看或者询问您的商务负责人获取
            String smsSingleRequestServerUrl = "https://cloud.tencent.com/document/product/382/9557";

            //生成验证码
            String strCode=String.valueOf(getRenadom());
            SessionCache.setSTR_CODE(strCode);

            // 短信内容
            String msg = "【冠桥英语】你好,你的验证码是"+strCode;
            //手机号码
            String phone = "";
            phone = request.getParameter("phone");//传入需要获取验证码的手机号

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
     * 登出
     */
    @RequestMapping(value = "/stusignOut", method = RequestMethod.GET)
    @ResponseBody
    public Response stusignout(){
        Response response=Response.newResponse();
        return response;
    }

    /**
     * 前台注册
     *
     * @param request
     * @param model
     * @param student
     * @return
     */
    @RequestMapping("/register")
    @ResponseBody
    public Response register(HttpServletRequest request, Model model, Student student) {
        //验证码
        String verifyCode=request.getParameter("verifyCode");

        //获取储存的验证码
        Datavalid datavalid1=new Datavalid();
        datavalid1.setType(1);
        datavalid1.setPhone(student.getPhone());
        Response<Datavalid> responseDatavalid=datavalidClient.findByphone(datavalid1);

        //验证码判断
        if(responseDatavalid.getData()==null||!responseDatavalid.getData().getPkempstu().equals(verifyCode)){
            Response response=Response.newResponse();
            response.setCode(1);
            response.setMessage("验证码错误!!!");
            return response;
        }

        //电话注册验证
        if(!PhoneExist(student.getPhone())){
            return Response.newResponse().PHONE_EXISTS();
        }

        student.setPkDomain("gq");
        student.setPkStudent(GuidUtils.getGuid());
        student.setPassword(MD5Util.string2MD5(student.getPassword()));
        if(student.getCaption()==null||student.getCaption().equals("")){
            long caption=new Date().getTime()/1000;
            student.setCaption(String.valueOf(caption));
        }
        return JSON.parseObject(studentClient.save(student), new TypeReference<Response<Object>>(){});
    }

    //手机号判断
    public boolean PhoneExist(String phone){
        Student student=new Student();
        student.setPhone(phone);
        if(studentClient.find(student).getData().size()>0){
           return false;
        }
          return true;
    }

    /**
     * 忘记密码
     *
     * @param request
     * @param model
     * @param student
     * @return
     */
    @RequestMapping("/forget")
    @ResponseBody
    public Response updatepwd(HttpServletRequest request, Model model, Student student) {
        //验证码
        String verifyCode=request.getParameter("verifyCode");

        Datavalid datavalid1=new Datavalid();
        datavalid1.setType(1);
        datavalid1.setPhone(student.getPhone());
        Response<Datavalid> responseDatavalid=datavalidClient.findByphone(datavalid1);

        if(responseDatavalid.getData()==null||!responseDatavalid.getData().getPkempstu().equals(verifyCode)){
            Response response=Response.newResponse();
            response.setCode(1);
            response.setMessage("验证码错误!!!");
            return response;
        }
        return studentClient.updatepassword(student);
    }

    /**
     * 修改密码
     *
     * @param request
     * @param model
     * @param student
     * @return
     */
    @RequestMapping("/updatepwd")
    @ResponseBody
    public Response updatepwd(HttpServletRequest request, Model model, Student student,String oldpwd) {

        Response r= Response.newResponse();
        Student student1=new Student();
        student1.setPhone(student.getPhone());
        Response<List<Student> > response=studentClient.find(student1);
        if(response.getData()==null){
            r.setCode(1);
            r.setMessage("电话错误");
            return r;
        }
        String md5Oldpwd=MD5Util.string2MD5(oldpwd);
        if(md5Oldpwd.equals(response.getData().get(0).getPassword())){
            return studentClient.updatepassword(student);
        }else{
            r.setCode(1);
            r.setMessage("密码错误");
            return r;
        }
    }


    /**
     * 新建用户
     *
     * @param request
     * @param model
     * @param student
     * @return
     */
    @Override
    @ResponseBody
    public Response save(HttpServletRequest request, Model model, Student student) {
        student.setPkDomain("gq");
        student.setPkStudent(GuidUtils.getGuid());
        student.setPassword(MD5Util.string2MD5(student.getPassword()));
        return JSON.parseObject(studentClient.save(student), new TypeReference<Response<Object>>(){});
    }

    @ResponseBody
    @RequestMapping("/editor")
    public Response editor(Student student){
        Date birthday = DateTimeUtils.stringToDate(student.getBirthdayTime()); //日期转换 string转date
        student.setBirthday(birthday);
        return JSON.parseObject(studentClient.save(student), new TypeReference<Response<Object>>(){});
    }

    /**
     * 删除
     * @param student
     * @return
     */
    @Override
    @ResponseBody
    public Response delete(HttpServletRequest request, Model model, Student student) {
        return JSONObject.parseObject( studentClient.delete(student.getPkStudent()),new TypeReference<Response<Object>>(){});
    }

    /**
     * 学生列表  跳转页面
     * @param model
     * @param student
     * @return
     */
    @Override
    public String query(HttpServletRequest request, Model model, Student student) {
        return "studenthtml/studentlist";
    }


    /**
     * 学生分页
     * @param request
     * @param response
     * @param student
     * @param sEcho
     * @param start
     * @param length
     * @return
     */
    @Override
    @ResponseBody
    public Map<String, Object> queryByPaging(HttpServletRequest request, HttpServletResponse response, Student student, Integer sEcho, Integer start, Integer length) {
        student.setPageNo((start/length)+1);
        student.setPageSize(length);

       // student.setIsvalid(1);//未删除
        Response<List<Student>> listResponse = studentClient.find(student);
        List<Student> data = listResponse.getData();

        if(data==null) {
            data = new ArrayList<>();
        }
        return convertToPaging(listResponse.getTotal(),listResponse.getTotal(),data);
    }


    /**
     *  新建跳转
     * @return
     */
    @RequestMapping("/create")
    public String create(String pkSutdent){
        return "studenthtml/student";
    }

    /**
     *  编辑跳转
     * @return
     */
    @RequestMapping("/edit")
    public String edit(){
        return "studenthtml/studentedit";
    }



    /**
     * 根据主键查找学生
     * @return
     */
    @RequestMapping("/findByPk")
    @ResponseBody
    public  Response findByPk(Student student){
       return studentClient.findByPrimaryKey(student);
    }

}
