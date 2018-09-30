package com.wuxue.api.controller.student;

import com.wuxue.api.interfaces.IDeleteController;
import com.wuxue.api.interfaces.IFindController;
import com.wuxue.api.interfaces.ISaveController;
import com.wuxue.model.StudentTestPlans;
import com.wuxue.api.service.StudentTestPlansService;
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
@RequestMapping(value = "api/student/studentTestPlans")
public class StudentTestPlansController implements IFindController<StudentTestPlans>,
        ISaveController<StudentTestPlans>,IDeleteController<String> {
    @Autowired
    private StudentTestPlansService studentTestPlansService;

    @Override
    public Response Find(@RequestBody Request<StudentTestPlans> studentTestPlans) {
        return studentTestPlansService.find(studentTestPlans);
    }

    @Override
    public Response Save(@RequestBody Request<StudentTestPlans> studentTestPlans) {
        return studentTestPlansService.save(studentTestPlans);
    }
    @Override

    public Response Delete(@RequestBody Request<String> studentTestPlans) {
        return studentTestPlansService.delete(studentTestPlans);

    }
}
