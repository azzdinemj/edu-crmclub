package com.wuxue.api.controller.activity;

import com.wuxue.api.service.junhwa.ClassActivityService;
import com.wuxue.model.Employee;
import com.wuxue.model.Student;
import com.wuxue.model.junhwa.ClassActivity;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 班级活动
 * @author tly
 * @date 2018-08-08
 */
@RestController
@RequestMapping(value = "api/activity/classActivity")
public class ClassActivityController {
    @Autowired
    private ClassActivityService classActivityService;

    /**
     * 保存或修改班级活动
     * @param request
     * @return
     */
    @RequestMapping(value = "/saveorupdate",method = RequestMethod.POST)
    public Response saveOrUpdateClassActivity(@RequestBody Request<ClassActivity>  request){
        return classActivityService.saveOrUpdateClassActivity(request.getData());
    }

    /**
     * 查询班级活动详情
     * @param request 班级活动对象
     * @return
     */
    @RequestMapping(value = "/findinfo",method = RequestMethod.POST)
    public Response findClassActivityInfoById(@RequestBody Request<ClassActivity> request){
        return classActivityService.findClassActivityInfoById(request.getData());
    }

    /**
     * 查询班级活动列表
     * @param request
     * @return
     */
    @RequestMapping(value = "/findlist",method = RequestMethod.POST)
    public Response findClassActivityListByClassInfoId(@RequestBody Request<ClassActivity> request){
        return classActivityService.findClassActivityPageByClassInfoId(request.getData());
    }

    //***********教师端***********

    /**
     * 根据教师id查询班级列表
     * @param employee
     * @return
     */
    @RequestMapping(value = "/findClassActivityListByTeacherId",method = RequestMethod.POST)
    public Response findClassActivityListByTeacherId(@RequestBody Employee employee){
        return classActivityService.findClassActivityListByTeacherId(employee);
    }

    //***********家长端***********

    /**
     * 根据学生id分页查询班级活动列表
     * @param student
     * @return
     */
    @RequestMapping(value = "/findClassActivityListByStudentId",method = RequestMethod.POST)
    public Response findClassActivityListByStudentId(@RequestBody Student student){
        return classActivityService.findClassActivityListByStudentId(student);
    }


 }
