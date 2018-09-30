package com.wuxue.api.mapper;

import com.wuxue.api.interfaces.*;
import com.wuxue.base.ResultEntity;
import com.wuxue.model.SchoolBus;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface SchoolBusMapper extends IInsertMapper<SchoolBus>,ICountMapper<SchoolBus,Integer>,
        IUpdateMapper<SchoolBus>,IDeleteByPrimaryKeyMapper<String>,
        ISelectByPrimaryKeyMapper<String,SchoolBus>,ISelectMapper<SchoolBus,List<SchoolBus>> {
    /**
     * 根据校车id查询校车对应的所有学生信息
     * @param pkSchoolBus
     * @return
     */
    List<SchoolBus> findStudentInfosByBusId(String pkSchoolBus);

    /**
     * 根据槛车人id查校车id
     *
     * @param userId
     * @return
     */
    ResultEntity findBusIdByUserId(String userId);
 }