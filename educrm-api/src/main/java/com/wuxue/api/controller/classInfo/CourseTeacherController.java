package com.wuxue.api.controller.classInfo;

import com.wuxue.api.interfaces.IDeleteController;
import com.wuxue.api.interfaces.IFindController;
import com.wuxue.api.interfaces.ISaveController;
import com.wuxue.api.service.CourseTeacherService;
import com.wuxue.model.CourseTeacher;
import com.wuxue.model.CourseTeacherKey;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Rogue on 2018/01/02.
 */
@RestController
@RequestMapping(value = "api/classinfo/courseTeacher")
public class CourseTeacherController implements IFindController<CourseTeacher>,
        ISaveController<CourseTeacher>,IDeleteController<CourseTeacher> {
    @Autowired
    private CourseTeacherService courseTeacherService;

    @Override
    public Response Find(@RequestBody Request<CourseTeacher> courseTeacher) {
        return courseTeacherService.find(courseTeacher);
    }

    @Override
    public Response Save(@RequestBody Request<CourseTeacher> courseTeacher) {
        return courseTeacherService.save(courseTeacher);
    }
    @Override
    public Response Delete(@RequestBody Request<CourseTeacher> courseTeacher) {
        return courseTeacherService.delete(courseTeacher);

    }

    @RequestMapping(value = "/saveall",method = RequestMethod.POST)
    public Response saveAll(@RequestBody Request<CourseTeacher> courseTeacher) {
        return courseTeacherService.saveAll(courseTeacher);

    }

    @RequestMapping(value = "/getteachercourse",method = RequestMethod.POST)
    public Response getTeacherCourse(@RequestBody Request<CourseTeacher> courseTeacher) {
        return courseTeacherService.getTeacherCourse(courseTeacher);
    }

}
