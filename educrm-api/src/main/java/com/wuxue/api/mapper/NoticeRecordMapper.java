package com.wuxue.api.mapper;

import com.wuxue.api.interfaces.*;
import com.wuxue.model.shuttle.NoticeRecord;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface NoticeRecordMapper extends IInsertMapper<NoticeRecord>,ICountMapper<NoticeRecord,Integer>,
        IUpdateMapper<NoticeRecord>,IDeleteByPrimaryKeyMapper<String>,
        ISelectByPrimaryKeyMapper<String,NoticeRecord>,ISelectMapper<NoticeRecord,List<NoticeRecord>>{
    /**
     * 根據站點id查詢今天的通知記錄
     * @param indexNos 站點id
     * @param startTime
     * @param endTime
     * @return
     */
    List<String> selectNoticeRecordByIndexNo(@Param("ids") List<String> indexNos, @Param("startTime")Date startTime, @Param("endTime")Date endTime);
}