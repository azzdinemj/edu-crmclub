package com.wuxue.api.controller.smallroutine.controller.schedule;

import com.wuxue.api.controller.smallroutine.client.product.ScheduleClient;
import com.wuxue.api.controller.smallroutine.client.teacher.EmployeeClient;
import com.wuxue.api.controller.smallroutine.client.wxOpenid.DatavalidClient;
import com.wuxue.api.controller.smallroutine.interfaces.ISaveController;
import com.wuxue.model.Datavalid;
import com.wuxue.model.Schedule;
import com.wuxue.utils.contract.Response;
import com.wuxue.api.controller.smallroutine.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * 课程
 */
@Controller
@RequestMapping(value = "/schedule")
public class ScheduleController2 extends BaseController implements ISaveController<Schedule,Response> {

    @Autowired
    private EmployeeClient employeeClient;

    @Autowired
    private ScheduleClient scheduleClient;

    @Autowired
    private DatavalidClient datavalidClient;


    //判断用户是否登录
    public  boolean validUserbingDing(String string){
        boolean flag=false;
        String []strs=string.split(",");
        if(strs.length<2){
            return flag;
        }
        Datavalid datavalid=new Datavalid();
        datavalid.setId(strs[0]);
        Response<Datavalid> response=datavalidClient.findByPrimaryKey(datavalid);
        if(response.getData()!=null){
            String  str=response.getData().getId()+","+response.getData().getPkempstu();
            if(str.equals(string)){
                flag=true;
            }
        }

        return  flag;
    }


    /**
     * 根据条件查询 （老师，学生）
     * @param request
     * @param schedule
     * @return
     * @throws ParseException
     */
    @RequestMapping("/queryby")
    @ResponseBody
    public  Response queryby(HttpServletRequest request, Schedule schedule) throws ParseException {
        String str=request.getParameter("userKey");
        if(str!=null&&str!=""){
            if(validUserbingDing(request.getParameter("userKey"))){
                String sfrom=request.getParameter("fromD");
                String send=request.getParameter("endD");
                SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");

                if(sfrom!=null&&sfrom!=""){
                    schedule.setFromDate(sdf.parse(sfrom));
                }
                if(send!=null&&send!=""){
                    schedule.setToDate(sdf.parse(send));
                }

                Response<List<Schedule>> listResponse= scheduleClient.find(schedule);
                return  listResponse;
            }
        }

        return Response.newResponse().USER_NULL();
    }




    /**
     * 根据主键查找课程详情
     * @param schedule
     * @return
     */
    @RequestMapping("/findschedule")
    @ResponseBody
    public Response findschedule(HttpServletRequest request,Schedule schedule) {
        String str=request.getParameter("userKey");
        if(str!=null&&str!=""){
            if(validUserbingDing(request.getParameter("userKey"))) {
                return scheduleClient.findByPrimaryKey(schedule);
            }
        }
        return Response.newResponse().USER_NULL();

    }


    /**
     * 修改课程
     * @param request
     * @param model
     * @param schedule
     * @return
     */
    @Override
    @ResponseBody
    public Response save(HttpServletRequest request, Model model, Schedule schedule) {
        String str=request.getParameter("userKey");
        if(str!=null&&str!=""){
            if(validUserbingDing(request.getParameter("userKey"))) {
                return scheduleClient.save(schedule);
            }
        }
        return Response.newResponse().USER_NULL();
    }


    /**
     * 开始上课
     * @param schedule
     * @return
     */
    @RequestMapping("/startschedule")
    @ResponseBody
    public Response startschedule(HttpServletRequest request,Schedule schedule) {
        String str=request.getParameter("userKey");
        if(str!=null&&str!=""){
            if(validUserbingDing(request.getParameter("userKey"))) {
                return scheduleClient.save(schedule);
            }
        }
        return Response.newResponse().USER_NULL();

    }

}
