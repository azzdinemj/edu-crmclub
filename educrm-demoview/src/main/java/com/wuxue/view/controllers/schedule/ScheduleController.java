package com.wuxue.view.controllers.schedule;

import com.alibaba.fastjson.JSON;
import com.wuxue.model.ClassRoom;
import com.wuxue.model.ClassTime;
import com.wuxue.model.Schedule;
import com.wuxue.utils.common.DateUtils;
import com.wuxue.utils.contract.Response;
import com.wuxue.view.client.classInfo.ClassInfoClient;
import com.wuxue.view.client.classInfo.ClassRoomClient;
import com.wuxue.view.client.classInfo.ClassTimeClient;
import com.wuxue.view.client.product.ScheduleClient;
import com.wuxue.view.client.system.EmployeeClient;
import com.wuxue.view.controllers.BaseController;
import com.wuxue.view.interfaces.*;
import com.wuxue.view.utils.GuidUtils;
import com.wuxue.view.utils.SessionCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 课程表
 */

@Controller
@RequestMapping(value = "/schedule/schedule")
public class ScheduleController extends BaseController
        implements IQueryController<Schedule, String>, ISaveController<Schedule, String>,IQueryByPagingController<Schedule,Map<String,Object>>,
        ICreateController<Schedule, String>, IEditController<Schedule, String>, IDeleteController<Schedule, String>,Serializable{

    @Autowired
    private ScheduleClient scheduleClient;
    @Autowired
    private EmployeeClient employeeClient;
    @Autowired
    private ClassInfoClient classInfoClient;
    @Autowired
    private ClassRoomClient classRoomClient;
    @Autowired
    private ClassTimeClient classTimeClient;
    /**
     * 班级老师列表页面
     *
     * @param model
     * @return
     * @throws
     */
    @Override
    public String query(HttpServletRequest request, Model model, Schedule student) {
        return "/jiedian/schedule/scheduleList";
    }


    @Override
    @ResponseBody
    public Map<String, Object> queryByPaging(HttpServletRequest request, HttpServletResponse response, Schedule courseTeacher, Integer sEcho, Integer start, Integer length) {
        courseTeacher.setPageNo((start/length)+1);
        courseTeacher.setPageSize(length);

        String dateTime = request.getParameter("dateTime");
        if (dateTime != null && !"".equals(dateTime)) {
            String substring = dateTime.substring(0, 10);
            String substring1 = dateTime.substring(12);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
//            SimpleDateFormat sDateFormat=new SimpleDateFormat("yyyy-MM-dd");
            try {
                Date strTime = simpleDateFormat.parse(substring);
                Date endTime = simpleDateFormat.parse(substring1);
                courseTeacher.setFromDate(strTime);
                courseTeacher.setToDate(endTime);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        Response<List<Schedule>> listResponse = scheduleClient.selectBy(courseTeacher);
        List<Schedule> data = listResponse.getData();
        if(data==null) {
            data = new ArrayList<>();
        }
        return convertToPaging(listResponse.getTotal(),listResponse.getTotal(),data);
    }

    /**
     * 修改页面
     *
     * @param student
     * @param model
     * @return
     */
    @Override
    public String edit(HttpServletRequest request, Model model, Schedule student) {

        return "/classinfo/classInfo";
    }


    /**
     * 保存
     *
     * @param schedule
     * @param request
     * @return
     */
    @Override
    @ResponseBody
    public String save(HttpServletRequest request, Model model, Schedule schedule) {
        if (schedule.getPkSchedule() == null || "".equals(schedule.getPkSchedule())){
            schedule.setPkSchedule(GuidUtils.getGuid());
            schedule.setCreator(SessionCache.getUserCode());
            schedule.setPkDomain(SessionCache.getPkdomain());
        }
        schedule.setModifier(SessionCache.getUserCode());
        return scheduleClient.save(schedule);

    }



    /**
     * 添加页面
     *
     * @return
     */
    @Override
    public String create(HttpServletRequest request, Model model, Schedule student) {

        ClassRoom classRoom = new ClassRoom();

        Response<List<ClassRoom>> listResponse = classRoomClient.find(classRoom);
        model.addAttribute("classRoom",listResponse.getData());

        return "/classinfo/schedule";
    }

    /**
     * 删除
     * @return
     */
    @Override
    @ResponseBody
    public String delete(HttpServletRequest request, Model model, Schedule schedule) {
        schedule.setModifier(SessionCache.getUserCode());
        return scheduleClient.delete(schedule.getPkSchedule());
    }

    @ResponseBody
    @RequestMapping(value = "/saveAll",method = RequestMethod.POST)
    public String saveAll(HttpServletRequest request, Model model,Schedule schedule) throws ParseException {
        String scheduleList = request.getParameter("scheduleList");
        String[] split = scheduleList.split("-----");
        List<Schedule> schedules = new ArrayList<>();
        for (int i = 0; i < split.length; i++) {
            if(!split[i].equals("")) {
                Schedule succesResponse = JSON.parseObject(split[i],Schedule.class);
                succesResponse.setPkDomain(SessionCache.getPkdomain());

                succesResponse.setPkSchedule(GuidUtils.getGuid());
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
                SimpleDateFormat time = new SimpleDateFormat("yyyyMMdd hh:mm");
                Date weekDate = DateUtils.getWeekDate(Integer.valueOf(succesResponse.getNotes()));
                String format = sdf.format(weekDate);
                ClassTime classTime = new ClassTime();
                classTime.setPkClassTime(succesResponse.getPkClassTime());
                Response<ClassTime> byPrimaryKey = classTimeClient.findByPrimaryKey(classTime);

                succesResponse.setStartTime(time.parse(format + " " + byPrimaryKey.getData().getStartTime()));
                succesResponse.setEndTime(time.parse(format + " " + byPrimaryKey.getData().getEndTime()));
                schedules.add(succesResponse);
            }
        }

        return scheduleClient.saveAll(schedules);
    }

}
