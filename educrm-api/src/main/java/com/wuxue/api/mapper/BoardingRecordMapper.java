package com.wuxue.api.mapper;

import com.wuxue.api.interfaces.*;
import com.wuxue.model.shuttle.BoardingRecord;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface BoardingRecordMapper extends IInsertMapper<BoardingRecord>,ISelectMapper<BoardingRecord,List<BoardingRecord>>,
        IUpdateMapper<BoardingRecord>,IDeleteByPrimaryKeyMapper<String>,ISelectByPrimaryKeyMapper<String,BoardingRecord> {

    List<BoardingRecord> getLatestBoardingRecordByPkStudent(@Param("ids") List<String> pkStudentIds, @Param("startTime") Date startTime, @Param("endTime") Date endTime);
}