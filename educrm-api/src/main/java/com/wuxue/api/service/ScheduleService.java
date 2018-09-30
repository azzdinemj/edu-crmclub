package com.wuxue.api.service;


import com.wuxue.api.interfaces.IDeleteService;
import com.wuxue.api.interfaces.IFindService;
import com.wuxue.api.interfaces.ISaveService;
import com.wuxue.model.ClassTime;
import com.wuxue.model.Classinfo;
import com.wuxue.model.ClassinfoStudent;
import com.wuxue.model.Schedule;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;

import java.util.List;

public interface ScheduleService extends ISaveService<Schedule>,IFindService<Schedule>,IDeleteService<String> {
    Response check(Request<List<Schedule>> request);

    Response findClassSchodule(Request<ClassTime> schedule);

    /**
     * 批量保存
     * */
    Response saveAll(Request<List<Schedule>> schedule);
    /**
     * 循环排课
     * */
    Response saveLoop(Request<Schedule> schedule);
    /**
     * 排课记录
     * */
    Response selectBy(Request<Schedule> schedule);

    /**
     *  排课记录  ---guanqiao
     * @param tParams
     * @return
     */
    Response findguanqiao(Request<Schedule> tParams);


    /**
     *  根据老师查找出所有的scheduele
     * @param tParams
     * @return
     */
    Response findguanqiaoBy(Request<Schedule> tParams);


    /**
     * pkStudent没有预约过的排课记录
     * */
    Response selectByNotOrder(Request<Schedule> schedule);


    /**
     * 检查排课约束
     * */
    Response checkScheduleConstraint(Request<List<Schedule>> schedule);

    Response findElecSchedule(Request<Schedule> scheduleRequest);

    Response findClassDoubleSchodule(Request<ClassTime> scheduleRequest);

    Response findEmpDoubleSchodule(Request<ClassTime> scheduleRequest);

    Response findElective(Request<Schedule> scheduleRequest);

    Response findElectiveintroduce(Request<Classinfo> classinfoRequest);

    Response signUpElective(Request<ClassinfoStudent> request);

    Response deleteElective(Request<ClassinfoStudent> request);
}
