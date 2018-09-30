package com.wuxue.api.service.junhwa;

import com.wuxue.model.Employee;
import com.wuxue.model.Student;
import com.wuxue.model.junhwa.RaceRecord;
import com.wuxue.utils.contract.Response;

/**
 * 竞赛记录service
 * @author tly
 * @date 2018-08-03
 */
public interface RaceRecordService {


    /**
     * 根据班级id查询班级竞赛记录列表
     *
     * @param raceRecord
     * @return 竞赛记录结果集
     */
    Response findRaceRecordPageByClassInfoId(RaceRecord raceRecord);

    /**
     * 保存竞赛记录
     *
     * @param raceRecord#classInfoId 班级id
     * @param raceRecord             竞赛记录信息
     * @return 保存结果
     */
    Response saveOrUpdateRaceRecord(RaceRecord raceRecord);

    /**
     * 根据竞赛记录id查询竞赛记录
     *
     * @param raceRecord 竞赛记录
     * @return 竞赛记录详情
     */
    Response findRaceRecordInfoById(RaceRecord raceRecord);

    /**
     * 根据教师id查询竞赛记录列表分页
     *
     * @param employee
     * @return 竞赛记录列表
     */
    Response findRaceRecordListByTeacherId( Employee employee);

    /**
     * 根据学生id查询竞赛记录列表分页
     *
     * @param student
     * @return 竞赛记录列表
     */
    Response findRaceRecordListByStudentId(Student student);
}