package com.wuxue.api.service;


import com.github.pagehelper.PageInfo;
import com.wuxue.api.interfaces.*;
import com.wuxue.base.ResultEntity;
import com.wuxue.model.Student;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;

import java.util.List;

public interface StudentService extends ISaveService<Student>,IFindService<Student>,IDeleteService<String>,IFindAllService<Student>,IAuditService<Student> {
    /**
     * 学生登录 中航油
     * */
    Response login(Request<Student> student);

    /**
     * 学生注册 中航油
     * */
    Response register(Request<Student> student);

    /**
     * 忘记密码 中航油
     * */
    Response updatePassword(Request<Student> student);


    /**
     * 学生详情（返回值不包含所有学生关联数据）
     * */
    Response getStudent(Request<String> student);

    /**
     * 新增学生，中航油
     * */
    Response saveStudentzhy(Request<Student> tParams);

    /**
     * 根据电话查找学生 中航油
     * */
    Response findStudentByPhone(Request<String> tParams);

    /**
     * 学生列表 中航油
     * @param tParams
     * @return
     */
    Response<PageInfo<Student>> findzhyou(Request<Student> tParams);

    /**
     * 我的学生
     * */
    Response findEmployeeStudent(Request<Student> student);

    /**
     * 查找 jiedian
     * */
    Response findjiedian(Request<Student> student);

    /**
     * 欠费学生
     * */
    Response arrearsStudent(Request<Student> student);
    /**
     * 根据家长查找学生
     * */
    List<Student> selectByParent(String pkLinkman);


    /**
     * 根据班级主键和学生姓名查询学生列表
     * @return
     */
    Response selectStudentListByPkClassInfo(ResultEntity entity);

    /**
     * 根据班级主键和学生姓名查询学生列表
     * @return
     */
    Response saveForCollect(Request<Student> request);

    Response findByClassinfo(Request<Student> student);

    Response getStudentIdCode(Request<String> pkClassinfo);
}
