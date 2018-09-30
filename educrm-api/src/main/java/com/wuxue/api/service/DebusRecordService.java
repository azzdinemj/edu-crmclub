package com.wuxue.api.service;


import com.wuxue.api.interfaces.*;
import com.wuxue.model.shuttle.DebusRecord;

import java.util.Date;
import java.util.List;

public interface DebusRecordService extends ISaveService<DebusRecord>,IFindService<DebusRecord>,IDeleteService<String>,ISubmitService<DebusRecord>,IAuditService<DebusRecord> {
    /**
     * 通过学生主键获取当天最新一条下车记录
     * @param pkStudentIds
     * @return
     */
    List<DebusRecord> getLatestDebusRecordByPkStudent(List<String> pkStudentIds, Date startTime, Date endTime);

}
