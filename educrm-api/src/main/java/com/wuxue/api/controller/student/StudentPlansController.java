package com.wuxue.api.controller.student;

import com.wuxue.api.interfaces.IDeleteController;
import com.wuxue.api.interfaces.IFindController;
import com.wuxue.api.interfaces.ISaveController;
import com.wuxue.model.StudentPlans;
import com.wuxue.api.service.StudentPlansService;
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
@RequestMapping(value = "api/student/studentPlans")
public class StudentPlansController implements IFindController<StudentPlans>,
        ISaveController<StudentPlans>,IDeleteController<String> {
    @Autowired
    private StudentPlansService studentPlansService;

    @Override
    public Response Find(@RequestBody Request<StudentPlans> studentPlans) {
        return studentPlansService.find(studentPlans);
    }

    @Override
    public Response Save(@RequestBody Request<StudentPlans> studentPlans) {
        return studentPlansService.save(studentPlans);
    }
    @Override

    public Response Delete(@RequestBody Request<String> studentPlans) {
        return studentPlansService.delete(studentPlans);

    }
}
