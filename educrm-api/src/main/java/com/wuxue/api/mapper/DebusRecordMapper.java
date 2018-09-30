package com.wuxue.api.mapper;
import com.wuxue.api.interfaces.*;
import com.wuxue.model.shuttle.DebusRecord;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface DebusRecordMapper extends IInsertMapper<DebusRecord>,ISelectMapper<DebusRecord,List<DebusRecord>>,
        IUpdateMapper<DebusRecord>,IDeleteByPrimaryKeyMapper<String>,ISelectByPrimaryKeyMapper<String,DebusRecord> {
    /**
     * 获取最新一条学生下车时间
     * @param pkStudentIds
     * @param startTime
     * @param endTime
     * @return
     */
     List<DebusRecord> getLatestDebusRecordByPkStudent(@Param("ids") List<String> pkStudentIds, @Param("startTime") Date startTime, @Param("endTime") Date endTime);
}