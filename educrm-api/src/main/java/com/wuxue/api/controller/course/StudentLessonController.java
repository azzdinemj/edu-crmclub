package com.wuxue.api.controller.course;

import com.wuxue.api.interfaces.IDeleteController;
import com.wuxue.api.interfaces.IFindController;
import com.wuxue.api.interfaces.ISaveController;
import com.wuxue.api.service.StudentLessonService;
import com.wuxue.model.StudentLesson;
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
@RequestMapping(value = "api/course/studentLesson")
public class StudentLessonController implements IFindController<StudentLesson>,
        ISaveController<StudentLesson>,IDeleteController<String>{


    @Autowired
    private StudentLessonService studentLessonService;

    @Override
    public Response Find(@RequestBody Request<StudentLesson> studentLesson) {
        return studentLessonService.find(studentLesson);
    }

    @Override
    public Response Save(@RequestBody Request<StudentLesson> studentLesson) {
        return studentLessonService.save(studentLesson);
    }

    @Override
    public Response Delete(@RequestBody Request<String> studentLesson) {
        return studentLessonService.delete(studentLesson);
    }

}
