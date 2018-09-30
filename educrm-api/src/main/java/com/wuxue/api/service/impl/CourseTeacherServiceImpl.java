package com.wuxue.api.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wuxue.api.mapper.*;
import com.wuxue.api.service.CourseTeacherService;
import com.wuxue.api.service.UtilsService;
import com.wuxue.api.utils.JobPostEnum;
import com.wuxue.base.KeyValue;
import com.wuxue.model.*;
import com.wuxue.model.CourseTeacherKey;
import com.wuxue.utils.contract.LinkEntity;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

//import com.wuxue.api.utils.GuidUtils;

@Service("courseTeacherServiceImpl")
public class CourseTeacherServiceImpl implements CourseTeacherService {
    @Autowired
    CourseTeacherMapper courseTeacherMapper;

    @Autowired
    UtilsService utilsService;

    @Autowired
    private EmployeeMapper employeeMapper;


    @Override
    public Response delete(Request<CourseTeacher> tParams) {
        Response response = Response.newResponse();
        CourseTeacher courseTeacher = tParams.getData();

        if (courseTeacher.getPkEmployee() == null || courseTeacher.getPkEmployee().equals("") || courseTeacher.getPkSysDictValues() == null || courseTeacher.getPkSysDictValues().equals("")) {
            return response.PARAMS_ISNULL();
        }
        int iReuslt = 1;
        String message = "";
        try {
            iReuslt = courseTeacherMapper.updateByPrimaryKeySelective(courseTeacher);
        } catch (Exception ex) {
            message = ex.getMessage();
            return response.SAVE_FAIL(message);
        }
        if (iReuslt > 0) {
            return response;
        }
        return response.DELETE_FAIL(message);
    }

    @Override
    public Response find(Request<CourseTeacher> tParams) {
        Response response = Response.newResponse();
        CourseTeacher courseTeacher = tParams.getData();
        if (courseTeacher == null) {
            return response.PARAMS_ISNULL();
        }
        String pkEmployee = courseTeacher.getPkEmployee();
        String pkSysDictValues = courseTeacher.getPkSysDictValues();


        if ((pkEmployee == null || "".equals(pkEmployee)) && (pkSysDictValues == null || "".equals(pkSysDictValues))) {
            PageHelper.startPage(courseTeacher.getPageNo(), courseTeacher.getPageSize());
            courseTeacher.setIsvalid(1);
            List<CourseTeacher> select = courseTeacherMapper.select(courseTeacher);
            PageInfo page = new PageInfo(select);
            response.setTotal(page.getTotal());
            if (select.size()>0){
                for (CourseTeacher teacher : select) {
                    utilsService.setSysDictKeyValue(teacher,teacher.getPkSysDictValues(),LinkEntity.SYSDIC_ENTITY);
                    Employee employee = employeeMapper.selectByPrimaryKey(teacher.getPkEmployee());
                    teacher.put(LinkEntity.EMP_ENTITY,employee);
                }
            }

            response.setData(select);

        }


        if (pkEmployee != null && !"".equals(pkEmployee) && pkSysDictValues != null && !"".equals(pkSysDictValues)) {
            CourseTeacher byPrimaryKey = courseTeacherMapper.selectByDoublePrimaryKey(courseTeacher);
            utilsService.setSysUserKeyValue(byPrimaryKey, byPrimaryKey.getCreator(), LinkEntity.CREATOR_ENTITY);
            utilsService.setSysUserKeyValue(byPrimaryKey, byPrimaryKey.getModifier(), LinkEntity.MODIFIER_ENTITY);
            response.setData(byPrimaryKey);
        }

        if (pkSysDictValues != null && !"".equals(pkSysDictValues)) {
            List<CourseTeacher> teachetlist = courseTeacherMapper.selectByPrimary(pkEmployee);
            if (teachetlist != null && teachetlist.size() > 0) {
                for (CourseTeacher list : teachetlist) {
                    utilsService.setSysUserKeyValue(list, list.getCreator(), LinkEntity.CREATOR_ENTITY);
                    utilsService.setSysUserKeyValue(list, list.getModifier(), LinkEntity.MODIFIER_ENTITY);
                }
            }
            response.setData(teachetlist);
        }

        if (pkEmployee != null && !"".equals(pkEmployee)) {
            List<CourseTeacher> courselist = courseTeacherMapper.selectByPrimary(pkEmployee);
            if (courselist != null && courselist.size() > 0) {
                for (CourseTeacher list : courselist) {
                    utilsService.setSysUserKeyValue(list, list.getCreator(), LinkEntity.CREATOR_ENTITY);
                    utilsService.setSysUserKeyValue(list, list.getModifier(), LinkEntity.MODIFIER_ENTITY);
                }
            }
            response.setData(courselist);
        }


        return response;
    }

    @Override
    public Response save(Request<CourseTeacher> tParams) {
        Response response = Response.newResponse();
        CourseTeacher courseTeacher = tParams.getData();

        if (courseTeacher == null || courseTeacher.getPkEmployee() == null || "".equals(courseTeacher.getPkEmployee()) || courseTeacher.getPkSysDictValues() == null || "".equals(courseTeacher.getPkSysDictValues())) {
            return response.PARAMS_ISNULL();
        }

        CourseTeacher ct = new CourseTeacher();
        ct.setPkEmployee(courseTeacher.getPkEmployee());
        ct.setPkSysDictValues(courseTeacher.getPkSysDictValues());
        CourseTeacher select = courseTeacherMapper.selectByDoublePrimaryKey(ct);
        String message = "";
        try {
            if (select != null) {
                courseTeacher.setCreator(null);
                courseTeacher.setLasteditDate(new Date());
                courseTeacherMapper.updateByPrimaryKeySelective(courseTeacher);
            } else {
                courseTeacher.setCreationDate(new Date());
                courseTeacher.setLasteditDate(new Date());
                courseTeacher.setIsvalid(1);
                courseTeacherMapper.insertSelective(courseTeacher);
            }
        } catch (Exception e) {
            message = e.getMessage();
            return response.SAVE_FAIL(message);
        }


        return response;
    }

    @Override
    public Response saveAll(Request<CourseTeacher> tParams) {
        Response response = Response.newResponse();
        CourseTeacher courseTeacher = tParams.getData();


        return response;
    }

    @Override
    public Response getTeacherCourse(Request<CourseTeacher> tParams) {
        Response response = Response.newResponse();
        CourseTeacher courseTeacher = tParams.getData();
        if (courseTeacher == null){
            return response.PARAMS_ISNULL();
        }

        String message = "";
        try {
            List<CourseTeacher> courseTeacherList = courseTeacherMapper.select(courseTeacher);
            if (courseTeacherList.size() > 0) {
                for (CourseTeacher teacher : courseTeacherList) {
                    utilsService.setSysDictKeyValue(teacher, teacher.getPkSysDictValues(), LinkEntity.COURSE_ENTITY);
                }
            }
            response.setData(courseTeacherList);
        }catch (Exception ex){
            message = ex.getMessage();
            return response.FIND_FAIL(message);
        }

        return response;
    }

    @Override
    public List<CourseTeacher> selectByEmp(String pkEmployee,String jobPost) {

        if (JobPostEnum.HEAD_TEACHER_EMPLOYEE.equals(jobPost)){
            List<CourseTeacher> list = courseTeacherMapper.selectByHeadEmp(pkEmployee);
            return list;
        }else {
            List<CourseTeacher> list = courseTeacherMapper.selectByEmp(pkEmployee);
            return list;
        }


    }


//    @Override
//    public Response find(Request<CourseTeacher> tParams) {
//        Response response = Response.newResponse();
//        CourseTeacher courseTeacher = tParams.getData();
//        if (courseTeacher == null){
//            return response.PARAMS_ISNULL();
//        }
//        String pkEmployee = courseTeacher.getPkEmployee();
//        String pkSysDictValues = courseTeacher.getPkSysDictValues();
//        if(pkEmployee != null && !"".equals(pkEmployee) && pkSysDictValues != null && !"".equals(pkSysDictValues)){
//            CourseTeacher byPrimaryKey = courseTeacherMapper.selectByDoublePrimaryKey(courseTeacher);
//            utilsService.setSysUserKeyValue(byPrimaryKey,byPrimaryKey.getCreator(), LinkEntity.CREATOR_ENTITY);
//            utilsService.setSysUserKeyValue(byPrimaryKey,byPrimaryKey.getModifier(),LinkEntity.MODIFIER_ENTITY);
//            response.setData(byPrimaryKey);
//        }else if (pkEmployee != null && !"".equals(pkEmployee)){
//            List<CourseTeacher> courselist = courseTeacherMapper.selectByPrimary(pkEmployee);
//            if (courselist != null && courselist.size()>0){
//                for (CourseTeacher list : courselist) {
//                    utilsService.setSysUserKeyValue(list, list.getCreator(), LinkEntity.CREATOR_ENTITY);
//                    utilsService.setSysUserKeyValue(list, list.getModifier(), LinkEntity.MODIFIER_ENTITY);
//                }
//            }
//            response.setData(courselist);
//        }else if (pkSysDictValues != null && !"".equals(pkSysDictValues)){
//            List<CourseTeacher> teachetlist = courseTeacherMapper.selectByPrimary(pkEmployee);
//            if (teachetlist != null && teachetlist.size()>0){
//                for (CourseTeacher list : teachetlist) {
//                    utilsService.setSysUserKeyValue(list, list.getCreator(), LinkEntity.CREATOR_ENTITY);
//                    utilsService.setSysUserKeyValue(list, list.getModifier(), LinkEntity.MODIFIER_ENTITY);
//                }
//            }
//            response.setData(teachetlist);
//        }
//
//
//        return response;
//    }


}
