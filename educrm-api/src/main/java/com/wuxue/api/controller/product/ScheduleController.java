package com.wuxue.api.controller.product;

import com.wuxue.api.interfaces.ICheckController;
import com.wuxue.api.interfaces.IDeleteController;
import com.wuxue.api.interfaces.IFindController;
import com.wuxue.api.interfaces.ISaveController;
import com.wuxue.api.service.ScheduleService;
import com.wuxue.model.ClassTime;
import com.wuxue.model.Classinfo;
import com.wuxue.model.ClassinfoStudent;
import com.wuxue.model.Schedule;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Rogue on 2018/01/02.
 */
@RestController
@RequestMapping(value = "api/product/schedule")
public class ScheduleController implements IFindController<Schedule>,
        ISaveController<Schedule>,IDeleteController<String>,ICheckController<List<Schedule>> { ;

    @Autowired
    private ScheduleService  scheduleService;

    @Override
    public Response Find(@RequestBody Request<Schedule> schedule) {
        return scheduleService.find(schedule);
    }

    @Override
    public Response Save(@RequestBody Request<Schedule> schedule) {
        return scheduleService.save(schedule);
    }

    @Override
    public Response Delete(@RequestBody Request<String> schedule) {
        return scheduleService.delete(schedule);
    }


    @Override
    public Response Check(Request<List<Schedule>> request) {
        return scheduleService.check(request);
    }
    @RequestMapping(value = "/findclassschodule",method = RequestMethod.POST)
    public Response findClassSchodule(@RequestBody Request<ClassTime> schedule) {
        return scheduleService.findClassSchodule(schedule);
    }

    @RequestMapping(value = "/saveall",method = RequestMethod.POST)
    public Response saveAll(@RequestBody Request<List<Schedule>> schedule) {
        return scheduleService.saveAll(schedule);
    }

    @RequestMapping(value = "/checkScheduleConstraint",method = RequestMethod.POST)
    public Response checkScheduleConstraint(@RequestBody Request<List<Schedule>> schedule) {
        return scheduleService.checkScheduleConstraint(schedule);
    }
    @RequestMapping(value = "/saveloop",method = RequestMethod.POST)
    public Response saveLoop(@RequestBody Request<Schedule> schedule) {
        return scheduleService.saveLoop(schedule);
    }
    @RequestMapping(value = "/selectby",method = RequestMethod.POST)
    public Response selectBy(@RequestBody Request<Schedule> schedule) {
        return scheduleService.selectBy(schedule);
    }

    @RequestMapping(value = "/findguanqiao",method = RequestMethod.POST)
    public Response findGuanqiao(@RequestBody Request<Schedule> scheduleRequest) {
        return scheduleService.findguanqiao(scheduleRequest);
    }

    @RequestMapping(value = "/findguanqiaoby",method = RequestMethod.POST)
    public Response findGuanqiaoby(@RequestBody Request<Schedule> scheduleRequest) {
        return scheduleService.findguanqiaoBy(scheduleRequest);
    }

    @RequestMapping(value = "/findbynotorder",method = RequestMethod.POST)
    public Response findByNotOrder(@RequestBody Request<Schedule> scheduleRequest) {
        return scheduleService.selectByNotOrder(scheduleRequest);
    }
    @RequestMapping(value = "/findelecschedule",method = RequestMethod.POST)
    public Response findElecSchedule(@RequestBody Request<Schedule> scheduleRequest) {
        return scheduleService.findElecSchedule(scheduleRequest);
    }
    @RequestMapping(value = "/findclassdoubleschodule",method = RequestMethod.POST)
    public Response findClassDoubleSchodule(@RequestBody Request<ClassTime> scheduleRequest) {
        return scheduleService.findClassDoubleSchodule(scheduleRequest);
    }
    @RequestMapping(value = "/findempdoubleschodule",method = RequestMethod.POST)
    public Response findEmpDoubleSchodule(@RequestBody Request<ClassTime> scheduleRequest) {
        return scheduleService.findEmpDoubleSchodule(scheduleRequest);
    }

    /**
     * 根据学生年级获取选修课
     * @param scheduleRequest
     * @return
     */
    @RequestMapping(value = "/findElective",method = RequestMethod.POST)
    public Response findElective(@RequestBody Request<Schedule> scheduleRequest) {
        return scheduleService.findElective(scheduleRequest);
    }
    /**
     * 根据学生年级获取选修课课程介绍
     * @param classinfoRequest
     * @return
     */
    @RequestMapping(value = "/findElectiveintroduce",method = RequestMethod.POST)
    public Response findElectiveintroduce(@RequestBody Request<Classinfo> classinfoRequest) {
        return scheduleService.findElectiveintroduce(classinfoRequest);
    }

    /**
     * 小程序 选课报名
     * @param request
     * @return
     */
    @RequestMapping(value = "/signUpElective",method = RequestMethod.POST)
    public Response signUpElective(@RequestBody Request<ClassinfoStudent> request) {
        return scheduleService.signUpElective(request);
    }
    /**
     * 小程序 选课取消报名
     * @param request
     * @return
     */
    @RequestMapping(value = "/deleteElective",method = RequestMethod.POST)
    public Response deleteElective(@RequestBody Request<ClassinfoStudent> request) {
        return scheduleService.deleteElective(request);
    }
}
