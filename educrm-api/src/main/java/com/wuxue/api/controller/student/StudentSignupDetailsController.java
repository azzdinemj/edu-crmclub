package com.wuxue.api.controller.student;

import com.wuxue.api.interfaces.IDeleteController;
import com.wuxue.api.interfaces.IFindController;
import com.wuxue.api.interfaces.ISaveController;
import com.wuxue.api.service.StudentSignupDetailsService;
import com.wuxue.model.StudentSignupDetails;
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
@RequestMapping(value = "api/student/studentSignupDetails")
public class StudentSignupDetailsController implements IFindController<StudentSignupDetails>,
        ISaveController<StudentSignupDetails>,IDeleteController<String> {
    @Autowired
    private StudentSignupDetailsService studentSignupDetailsService;

    @Override
    public Response Find(@RequestBody Request<StudentSignupDetails> studentSignupDetails) {
        return studentSignupDetailsService.find(studentSignupDetails);
    }

    @Override
    public Response Save(@RequestBody Request<StudentSignupDetails> studentSignupDetails) {
        return studentSignupDetailsService.save(studentSignupDetails);
    }
    @Override

    public Response Delete(@RequestBody Request<String> studentSignupDetails) {
        return studentSignupDetailsService.delete(studentSignupDetails);

    }
}
