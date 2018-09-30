package com.wuxue.api.service;


import com.wuxue.api.interfaces.*;
import com.wuxue.model.shuttle.BoardingRecord;

import java.util.Date;
import java.util.List;

public interface BoardingRecordService extends ISaveService<BoardingRecord>,IFindService<BoardingRecord>,IDeleteService<String>,ISubmitService<BoardingRecord>,IAuditService<BoardingRecord> {


    /**
     * 通过学生主键获取当天最新一条上车记录
     * @param pkStudentIds
     * @return
     */
    List<BoardingRecord> getLatestBoardingRecordByPkStudent(List<String> pkStudentIds, Date startTime, Date endTime);

}
