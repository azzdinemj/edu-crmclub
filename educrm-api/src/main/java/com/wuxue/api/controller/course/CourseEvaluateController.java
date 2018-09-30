package com.wuxue.api.controller.course;

import com.wuxue.api.interfaces.IDeleteController;
import com.wuxue.api.interfaces.IFindController;
import com.wuxue.api.interfaces.ISaveController;
import com.wuxue.api.service.CourseEvaluateService;
import com.wuxue.model.CourseEvaluate;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Rogue on 2018/03/15.
 */
@RestController
@RequestMapping(value = "api/course/courseEvaluate")
public class CourseEvaluateController implements IFindController<CourseEvaluate>,
        ISaveController<CourseEvaluate>,IDeleteController<String>{


    @Autowired
    private CourseEvaluateService courseEvaluateService;

    @Override
    public Response Find(@RequestBody Request<CourseEvaluate> courseEvaluate) {
        return courseEvaluateService.find(courseEvaluate);
    }

    @Override
    public Response Save(@RequestBody Request<CourseEvaluate> courseEvaluate) {
        return courseEvaluateService.save(courseEvaluate);
    }

    @Override
    public Response Delete(@RequestBody Request<String> courseEvaluate) {
        return courseEvaluateService.delete(courseEvaluate);
    }

}
