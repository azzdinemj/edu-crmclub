package com.wuxue.api.service;


import com.wuxue.api.interfaces.IDeleteService;
import com.wuxue.api.interfaces.IFindService;
import com.wuxue.api.interfaces.ISaveService;
import com.wuxue.base.ResultEntity;
import com.wuxue.model.SchoolBus;
import com.wuxue.utils.contract.Response;

public interface SchoolBusService extends ISaveService<SchoolBus>,IFindService<SchoolBus>,IDeleteService<String> {
    /**
     * 根据校车id查询校车对应的所有学生信息
     * @param resultEntity
     * @return
     */
    Response findStudentInfosByBusId(ResultEntity resultEntity);

    /**
     * 根据槛车老师id找对应的busId
     *
     * @param schoolBus#guardianTeacher
     * @return
     */
    Response findBusIdByUserId(SchoolBus schoolBus);
}
