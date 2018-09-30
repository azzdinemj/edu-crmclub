package com.wuxue.api.service.junhwa;

import com.wuxue.model.Employee;
import com.wuxue.model.Student;
import com.wuxue.model.junhwa.ClassActivity;
import com.wuxue.utils.contract.Response;

/**
 * 班级活动service
 * @author tly
 * @date 2018-08-03
 */
public interface ClassActivityService {
    /**
     *发布班级活动
     * @param activity#classInfoId  班级id
     * @param activity 活动详情
     * @return 保存结果
     */
    Response saveOrUpdateClassActivity(ClassActivity activity);

    /**
     * 根据活动id查询班级活动
     * @param activity
     * @return 活动详情
     */
    Response findClassActivityInfoById(ClassActivity activity);

    /**
     * 根据班级id查询班级活动列表
     * @param activity
     * @return 活动结果集
     */
   Response findClassActivityPageByClassInfoId(ClassActivity activity);

    /**
     * 根据老师id查询对应的班级列表
     * @param employee
     * @return 班级活动列表
     */
   Response findClassActivityListByTeacherId(Employee employee);

    /**
     * 根据学生id班级活动列表
     * @param student
     * @return
     */
   Response findClassActivityListByStudentId(Student student);
}
