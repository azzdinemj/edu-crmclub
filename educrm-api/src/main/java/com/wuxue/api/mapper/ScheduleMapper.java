package com.wuxue.api.mapper;

import com.wuxue.api.interfaces.*;
import com.wuxue.model.Schedule;

import java.util.List;
import java.util.Map;

public interface ScheduleMapper extends IInsertMapper<Schedule>,ICountMapper<Schedule,Integer>,
        IUpdateMapper<Schedule>,IDeleteByPrimaryKeyMapper<String>,
        ISelectByPrimaryKeyMapper<String,Schedule>,ISelectMapper<Schedule,List<Schedule>>{

    Schedule selectByPkClassinfo(Schedule schedule);

    /**
     * 查询没有预约过的课程
     * @param schedule
     * @return
     */
    List<Schedule> selectByNotOrder(Schedule schedule);

    List<Schedule> selectElecSchedule(Schedule data);

    List<Schedule> selectClasByStudent(String pkStudent);

    int deleteByPkClassinfo(String pkClassinfo);

    List<Map<String,Object>> findElective(String pkStudent);
}