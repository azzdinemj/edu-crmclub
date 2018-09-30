package com.wuxue.api.controller.course;

import com.wuxue.api.interfaces.IDeleteController;
import com.wuxue.api.interfaces.IFindController;
import com.wuxue.api.interfaces.ISaveController;
import com.wuxue.api.service.StudentAssignService;
import com.wuxue.model.StudentAssign;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Rogue on 2018/03/15.
 */
@RestController
@RequestMapping(value = "api/course/studentAssign")
public class StudentAssignController implements IFindController<StudentAssign>,
        ISaveController<StudentAssign>,IDeleteController<String>{


    @Autowired
    private StudentAssignService studentAssignService;

    @Override
    public Response Find(@RequestBody Request<StudentAssign> studentAssign) {
        return studentAssignService.find(studentAssign);
    }

    @Override
    public Response Save(@RequestBody Request<StudentAssign> studentAssign) {
        return studentAssignService.save(studentAssign);
    }

    @Override
    public Response Delete(@RequestBody Request<String> studentAssign) {
        return studentAssignService.delete(studentAssign);
    }

    @RequestMapping(value = "/getstudentcs",method = RequestMethod.POST)
    public Response getStudentCourseStatus(@RequestBody Request<StudentAssign> studentAssign) {
        return studentAssignService.getStudentCourseStatus(studentAssign);
    }

}
