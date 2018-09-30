package com.wuxue.view.controllers.tkschedule;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.wuxue.model.Employee;
import com.wuxue.model.Product;
import com.wuxue.model.Schedule;
import com.wuxue.model.TalkCloudRoom;
import com.wuxue.utils.contract.Response;
import com.wuxue.view.client.product.ProductClient;
import com.wuxue.view.client.product.ScheduleClient;
import com.wuxue.view.client.system.EmployeeClient;
import com.wuxue.view.client.talkcloud.TalkCloudRoomClient;
import com.wuxue.view.controllers.BaseController;
import com.wuxue.view.interfaces.IDeleteController;
import com.wuxue.view.interfaces.IQueryByPagingController;
import com.wuxue.view.utils.SessionCache;
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
 * 排课
 */
@Controller
@RequestMapping(value = "/guanqiao/schedule")
public class tkScheduleController extends BaseController
implements IQueryByPagingController<Schedule,Map<String,Object>>
        ,IDeleteController<Schedule, String> {

    @Autowired
    private ProductClient productClient;
    @Autowired
    private TalkCloudRoomClient talkCloudRoomClient;
    @Autowired
    private ScheduleClient scheduleClient;
    @Autowired
    private EmployeeClient employeeClient;


    /**
     * 排课列表  ---跳转页面
     * @return
     */
    @RequestMapping(value = "/querybak3", method = RequestMethod.GET)
    public  String querybak3(HttpServletRequest request){
        request.setAttribute("pkEmployee",request.getParameter("pkEmployee"));
        return "guanqiao/schedule/querybak3";
    }

    /**
     * 排课列表
     * @param
     * @return
     */
    @RequestMapping("query")
    public String query(HttpServletRequest request) {
        request.setAttribute("pkEmployee",request.getParameter("pkEmployee"));
        return "jiedianhtml/schedule/querybak3";
    }



    /**
     * 排课列表
     * @param model
     * @param
     * @return
     */
   @RequestMapping("/querylist")
   @ResponseBody
    public Response querylist(HttpServletRequest request, Model model, Schedule schedule) {
        Response response=Response.newResponse();
        Map<String,Object> map=new HashMap<>();

        Employee employee=new Employee();
        String pkEmployee=request.getParameter("pkEmployee");
        String pkStudent=request.getParameter("pkStudent");
        String studentName=request.getParameter("studentName");
        String defaultView = request.getParameter("defaultView");
        employee.setPkEmployee(pkEmployee);
        Response<List<Employee>> listResponse = employeeClient.find(employee);

        map.put("pkEmployee",pkEmployee);
        map.put("studentName",studentName);
        map.put("pkStudent",pkStudent);
        map.put("employee",listResponse.getData());
         if(defaultView != null) {
             map.put("defaultView",defaultView);
         }else{
             map.put("defaultView","month");
         }

       response.setData(map);
       return response;
    }

    /**
     * 老师排课列表  ---跳转页面
     * @return
     */
    @RequestMapping(value = "/querybak2", method = RequestMethod.GET)
    public  String querybak2(){
        return "jiedianhtml/schedule/querybak2";
    }

    /**
     * 老师排课列表  ---跳转页面
     * @return
     */
    @RequestMapping(value = "/querybak2user", method = RequestMethod.GET)
    public  String querybak2user(){
        return "jiedianhtml/schedule/querybak2user";
    }

    /**
     * 获取所有老师
     * @param request
     * @return
     */
    @RequestMapping(value = "/teacher", method = RequestMethod.GET)
    @ResponseBody
    public Response teacher(HttpServletRequest request) {

        Employee employee=new Employee();
        Response<List<Employee>> listResponse = employeeClient.selectBy(employee);

        return listResponse;
    }

    /**
     * 返回指定格式的json数组
     * @param request
     * @return
     */
    @RequestMapping(value = "/teacher2", method = RequestMethod.GET)
    @ResponseBody
    public List<JSONObject> teacher2(HttpServletRequest request) {

        String str=request.getParameter("pkEmployee");

        Employee employee=new Employee();
        if(str!=null&&!"".equals(str)){
            employee.setPkEmployee(str);
        }
        Response<List<Employee>> listResponse = employeeClient.selectBy(employee);

        List<JSONObject> jsonObject=new ArrayList<>();
        for (Employee e:listResponse.getData()) {
            JSONObject jsonObject1=new JSONObject();
            jsonObject1.put("id",e.getPkEmployee());
            jsonObject1.put("title",e.getCaption());
            jsonObject.add(jsonObject1);
        }

        return jsonObject;
    }

    //获取排课数据
    @ResponseBody
    @RequestMapping(value = "/getschedule", method = RequestMethod.POST)
    public String getschedule(HttpServletRequest request, Model model, Schedule schedule) {
        Response<List<Schedule>> listResponse = scheduleClient.find(schedule);
        return  JSON.toJSONString(listResponse.getData());
    }

    //获取单条排课数据
    @ResponseBody
    @RequestMapping(value = "/getsch", method = RequestMethod.POST)
    public String getsch(HttpServletRequest request, Model model, Schedule schedule) {

        Response<Schedule> byPrimaryKey = scheduleClient.findByPrimaryKey(schedule);
        return  JSON.toJSONString(byPrimaryKey.getData());
    }

    /**
     * 删除
     * @param schedule
     * @return
     */
    @Override
    @ResponseBody
    public String delete(HttpServletRequest request, Model model, Schedule schedule) {
        String response = scheduleClient.delete(schedule.getPkSchedule());
        return response;
    }

    /**
     * 排课去重
     *
     * @param model
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/checkschedule", method = RequestMethod.POST)
    public String checkschedule(HttpServletRequest request, Model model, Schedule schedule) throws ParseException {
    	Schedule schedule2=new Schedule();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");

        schedule2.setStartTime(sdf.parse(schedule.getStartTimes())); //时间范围
        schedule2.setEndTime(sdf.parse(schedule.getEndTimes()));     //时间范围
        schedule2.setPkEmployee(schedule.getPkEmployee());          //教师

        Response<List<Schedule>> listResponse = scheduleClient.find(schedule2);
        List<Schedule> data = listResponse.getData();
     
        if(data.size()>0) {
        	for (Schedule sch : data) {
        	    if(sch.getPkEmployee().equals(schedule.getPkEmployee())){

                    //修改排课  判断
        	        if(schedule.getPkSchedule()!=null&&!"".equals(schedule.getPkSchedule())&&schedule.getPkSchedule().equals(sch.getPkSchedule())){
                        return "{\"code\":0,\"message\":\"ok \"}";
                    }
                    return "{\"code\":2,\"message\":\"老师该时间段已经有排课了 \"}";
                }
        	}
        }
        return "{\"code\":0,\"message\":\"ok \"}";
    }
    
//    /**
//     * 排课modal
//     *
//     * @param model
//     * @return
//     */
//    @RequestMapping(value = "/modalschedule", method = RequestMethod.GET)
//    public String modalschedule(HttpServletRequest request, Model model, Schedule schedule) {
//    	 model.addAttribute("schedule", schedule);
//         return "/jiedian/schedule/modalschedule";
//    }



    /**
     * 添加页面  ---跳转页面
     * @return
     */
    @RequestMapping(value = "/createhtml", method = RequestMethod.GET)
    public  String createhtml(){
        return "jiedianhtml/schedule/schedule";
    }

    /**
     * save
     * 排课   根据排课信息创建拓客云教室， 拓客云教室创建成功后 创建排课记录
     * @param request
     * @param schedule
     * @param cpwd 老师密码
     * @param apwd 助教密码
     * @param ppwd 巡检密码
     * @return
     * @throws ParseException
     */
    @ResponseBody
    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public String save(HttpServletRequest request, Schedule schedule,String cpwd,String apwd,String ppwd) throws ParseException {

        schedule.setPkDomain("gq");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");

        schedule.setCreator(SessionCache.getUserName());
        schedule.setModifier(SessionCache.getUserName());

        schedule.setStartTime(sdf.parse(schedule.getStartTimes()));
        schedule.setEndTime(sdf.parse(schedule.getEndTimes()));


        Product product1=new Product();
        product1.setPkProduct(schedule.getPkProduct());
        Response<Product> productResponse=productClient.findguanqiaobyPrimarykey(product1); //产品

        //计算一节课消耗的课时
        Integer i=0;
        Product product=productResponse.getData();
        if(product.getProductTotalclass()!=null &&product.getProductClasshours()!=null){
            i=product.getProductClasshours()/product.getProductTotalclass();
        }

        TalkCloudRoom talkCloudRoom=new TalkCloudRoom();
        // 新建拓客云教室
        talkCloudRoom.setRoomtype(String.valueOf(productResponse.getData().getProductTypetalkcloudroom()));
        talkCloudRoom.setRoomname(new Date().toString()+productResponse.getData().getCaption());
        talkCloudRoom.setChairmanpwd(cpwd);
        talkCloudRoom.setPatrolpwd(ppwd);
        talkCloudRoom.setAssistantpwd(apwd);
        talkCloudRoom.setStarttime((int)dateToStamp(schedule.getStartTimes()));
        talkCloudRoom.setEndtime((int)dateToStamp(schedule.getEndTimes()));

        String string= talkCloudRoomClient.save(talkCloudRoom);
        JSONObject jsons = JSONObject.parseObject(string);
        if(jsons.getString("code").equals("0")){
            //保存 schedule
            if(jsons.getString("data")!=null){
                schedule.setPkTalkCloudRoom(jsons.getString("data"));
                schedule.setProductUseclasshours(i);
            }
            return scheduleClient.save(schedule);
        }else{
            return jsons.toJSONString();
        }

    }

    @RequestMapping("saveNotes")
    @ResponseBody
    public String saveNotes(Schedule schedule) {
        return scheduleClient.save(schedule);
    }

    /*
         * 将时间转换为时间戳
         */
    public static long dateToStamp(String s) throws ParseException{
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date = simpleDateFormat.parse(s);
        long ts = date.getTime()/1000;
        return ts;
    }

    /**
     * 编辑页面  ---跳转页面
     * @return
     */
    @RequestMapping(value = "/edithtml", method = RequestMethod.GET)
    public  String edithtml(HttpServletRequest request,Schedule schedule){
        request.setAttribute("pkSchedule",schedule.getPkSchedule());
        return "jiedianhtml/schedule/scheduledit";
    }

    /**
     * edit
     * 修改拓客云教室，及关联的拓客云记录
     * @param request
     * @param schedule
     * @param cpwd 老师密码
     * @param apwd 助教密码
     * @param ppwd 巡检密码
     * @return
     * @throws ParseException
     */
    @ResponseBody
    @RequestMapping(value = "/edit",method = RequestMethod.POST)
    public String edit(HttpServletRequest request, Schedule schedule,String cpwd,String apwd,String ppwd) throws ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        schedule.setModifier(SessionCache.getUserName());
        schedule.setStartTime(sdf.parse(schedule.getStartTimes()));
        schedule.setEndTime(sdf.parse(schedule.getEndTimes()));

        //需要修改的schedule对象
        Schedule s2=new Schedule();
        s2.setPkSchedule(schedule.getPkSchedule());
        Response<Schedule> response=scheduleClient.findguanqiaoBypk(s2);

        //schedule 关联的拓客云教室
        Map<String,Object> map=response.getData().getMap();
        TalkCloudRoom talkCloudRoom = JSON.parseObject(map.get("talkCloudRoom").toString(),new TypeReference<TalkCloudRoom>(){});

        //修改拓客云教室
        TalkCloudRoom t=new TalkCloudRoom();
        t.setPkTalkCloudRoom(talkCloudRoom.getPkTalkCloudRoom());
        t.setSerial(talkCloudRoom.getSerial());
        t.setRoomname(talkCloudRoom.getRoomname());
        t.setAssistantpwd(apwd);
        t.setPatrolpwd(ppwd);
        t.setChairmanpwd(cpwd);
        t.setStarttime((int)dateToStamp(schedule.getStartTimes()));
        t.setEndtime((int)dateToStamp(schedule.getEndTimes()));


        String string=talkCloudRoomClient.save(t);
        JSONObject jsons = JSONObject.parseObject(string);
        if(jsons.getString("code").equals("0")){
            //更新 schedule
            return scheduleClient.save(schedule);
        }else{
            return jsons.toJSONString();
        }
    }



    @ResponseBody
    @RequestMapping("/getScheduleByPk")
    public Response getScheduleByPk(Schedule schedule){
        return scheduleClient.findguanqiaoBypk(schedule);
    }


    @Override
    @ResponseBody
    public Map<String, Object> queryByPaging(HttpServletRequest request, HttpServletResponse response, Schedule schedule, Integer sEcho, Integer start, Integer length) {
    	schedule.setPageNo((start/length)+1);
    	schedule.setPageSize(length);

        Response<List<Schedule>> listResponse = scheduleClient.find(schedule);
        List<Schedule> data = listResponse.getData();

        if(data==null) {
            data = new ArrayList<>();
        }
        return convertToPaging(listResponse.getTotal(),listResponse.getTotal(),data);
    }




}
