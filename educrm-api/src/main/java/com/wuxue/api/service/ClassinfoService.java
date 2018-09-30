package com.wuxue.api.service;


import com.wuxue.api.interfaces.*;
import com.wuxue.model.Classinfo;
import com.wuxue.model.ClassinfoStudent;
import com.wuxue.model.Schedule;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;

import java.util.List;

public interface ClassinfoService extends ISaveService<Classinfo>,IFindService<Classinfo>,IDeleteService<String>,ISubmitService<Classinfo>,IAuditService<Classinfo> {
    /**
     * 学生进班
     * @param classinfo
     * @return
     */
    Response studentGoClass(Request<Classinfo> classinfo);
    /**
     * 学生升学
     * @param classinfo
     * @return
     */
    Response goToSchool(Request<Classinfo> classinfo);

    /**
     * 班级学生成绩信息
     * @param classinfo
     * @return
     */
    Response findClassStudentScores(Request<ClassinfoStudent> classinfo);

    /**
     * '查询个性化班级
     * @param classinfo
     * @return
     */
    Response findClassForCourse(Request<Classinfo> classinfo);
    /*
     *
     * 选课冲突验证
     * */
    Response selectiveCheck(Request<Schedule> schedule);

    /**
     * 查询班级列表（不分页）
     * */
    Response selectBy(Request<Classinfo> classinfo);

    /**
     * 查询班级列表
     * @param classinfo
     * @return
     */
    Response selectByType(Classinfo classinfo);

    /**
     * 兴趣学生入班
     * */
    Response studentGoInterestClass(Request<Classinfo> classinfo);

    /**
     * 查询兴趣列表/详情
     * */
    Response findByIsPay(Request<Classinfo> classinfoRequest);

    /**
     * 获取老师下的班级集合
     * @param teacherId
     * @return
     */
    List<String> getClassIdsByTeacherId(String teacherId);
    /**
     * 获取学生下的班级集合
     * @param studentId
     * @return
     */
    List<String> getClassIdsByStudentId(String studentId);

    /**
     * x修改班级状态
     * */
    Response updateStatus(Request<Classinfo> classinfo);

    Response classOn(Request<Classinfo> classinfoRequest);

    Response saveForSche(Request<String> pkClassinfo);

    Response getClassinfo(Request<Classinfo> classinfo);

    Response saveElective(Request<Classinfo> classinfoRequest);
}
