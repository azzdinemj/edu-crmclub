package com.wuxue.api.controller.student;

import com.wuxue.api.interfaces.IDeleteController;
import com.wuxue.api.interfaces.IFindController;
import com.wuxue.api.interfaces.ISaveController;
import com.wuxue.model.StudentActivityExp;
import com.wuxue.api.service.StudentActivityExpService;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Rogue on 2018/01/02.
 */
@RestController
@RequestMapping(value = "api/student/studentActivityExp")
public class StudentActivityExpController implements IFindController<StudentActivityExp>,
        ISaveController<StudentActivityExp>,IDeleteController<String> {
    @Autowired
    private StudentActivityExpService studentActivityExpService;

    @Override
    public Response Find(@RequestBody Request<StudentActivityExp> studentActivityExp) {
        return studentActivityExpService.find(studentActivityExp);
    }

    @Override
    public Response Save(@RequestBody Request<StudentActivityExp> studentActivityExp) {
        return studentActivityExpService.save(studentActivityExp);
    }
    @Override

    public Response Delete(@RequestBody Request<String> studentActivityExp) {
        return studentActivityExpService.delete(studentActivityExp);

    }
}
