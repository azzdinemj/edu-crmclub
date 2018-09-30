package com.wuxue.api.mapper;

import com.wuxue.api.interfaces.*;
import com.wuxue.model.AskForLeave;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface AskForLeaveMapper extends IInsertMapper<AskForLeave>,ICountMapper<AskForLeave,Integer>,
        IUpdateMapper<AskForLeave>,IDeleteByPrimaryKeyMapper<String>,
        ISelectByPrimaryKeyMapper<String,AskForLeave>,ISelectMapper<AskForLeave,List<AskForLeave>> {
    /**
     * 查询确认上车时请假学生列表
     * @param ids 学生id
     * @param time 确认上车时间
     * @return
     */
    List<String> selectAskForLeaveStudentList(@Param("ids") List<String> ids, @Param("time")Date time);

}