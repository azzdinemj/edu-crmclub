package com.wuxue.api.service;


import com.wuxue.api.interfaces.*;
import com.wuxue.model.SchoolBusStudent;

import java.util.List;

public interface SchoolBusStudentService extends ISaveService<SchoolBusStudent>,IFindService<SchoolBusStudent>,IDeleteService<SchoolBusStudent>,IFindAllService<SchoolBusStudent>,IAuditService<SchoolBusStudent> {
    /**
     * 根據校车id查询学生ID
     * @param busId
     * @return
     */
    List<String> getStudentIdsByBusId(String busId);
}
