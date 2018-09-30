package com.wuxue.api.controller.course;

import com.wuxue.api.interfaces.IAuditController;
import com.wuxue.api.interfaces.IDeleteController;
import com.wuxue.api.interfaces.IFindController;
import com.wuxue.api.interfaces.ISaveController;
import com.wuxue.api.service.CourseService;
import com.wuxue.api.service.OptionalCourseService;
import com.wuxue.model.Course;
import com.wuxue.model.OptionalCourse;
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
@RequestMapping(value = "api/course/optionalcourse")
public class OptionalCourseController implements IFindController<OptionalCourse>,
        ISaveController<OptionalCourse>,IDeleteController<OptionalCourse>{


    @Autowired
    private OptionalCourseService courseService;

    @Override
    public Response Find(@RequestBody Request<OptionalCourse> course) {
        return courseService.find(course);
    }

    @Override
    public Response Save(@RequestBody Request<OptionalCourse> course) {
        return courseService.save(course);
    }

    @Override
    public Response Delete(@RequestBody Request<OptionalCourse> course) {
        return courseService.delete(course);
    }

}
