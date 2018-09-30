package com.wuxue.api.controller.student;

import com.wuxue.api.interfaces.IDeleteController;
import com.wuxue.api.interfaces.IFindController;
import com.wuxue.api.interfaces.ISaveController;
import com.wuxue.model.StudentCredit;
import com.wuxue.api.service.StudentCreditService;
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
@RequestMapping(value = "api/student/studentCredit")
public class StudentCreditController implements IFindController<StudentCredit>,
        ISaveController<StudentCredit>,IDeleteController<String> {
    @Autowired
    private StudentCreditService studentCreditService;

    @Override
    public Response Find(@RequestBody Request<StudentCredit> studentCredit) {
        return studentCreditService.find(studentCredit);
    }

    @Override
    public Response Save(@RequestBody Request<StudentCredit> studentCredit) {
        return studentCreditService.save(studentCredit);
    }
    @Override

    public Response Delete(@RequestBody Request<String> studentCredit) {
        return studentCreditService.delete(studentCredit);

    }
}
