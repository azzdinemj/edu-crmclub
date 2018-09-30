package com.wuxue.api.controller.course;

import com.wuxue.api.interfaces.*;
import com.wuxue.api.service.CourseService;
import com.wuxue.model.Course;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/**
* @Description:  课程控制器
* @author wanghao
* @date  12:25 2018/3/13
* @version V1.0
*/
@RestController
@RequestMapping(value = "api/course/course")
public class CourseController implements IFindController<Course>,
        ISaveController<Course>,IDeleteController<String>,IAuditController<Course>{


    @Autowired
    private CourseService courseService;

    @Override
    public Response Find(@RequestBody Request<Course> course) {
        return courseService.find(course);
    }

    @Override
    public Response Save(@RequestBody Request<Course> course) {
        return courseService.save(course);
    }

    @Override
    public Response Delete(@RequestBody Request<String> course) {
        return courseService.delete(course);
    }

    @Override
    public Response Audit(@RequestBody Request<Course> course) {
        return courseService.audit(course);
    }
}
