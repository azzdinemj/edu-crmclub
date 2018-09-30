package com.wuxue.api.controller.student;

import com.wuxue.api.interfaces.*;
import com.wuxue.model.StudentInterview;
import com.wuxue.api.service.StudentInterviewService;
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
@RequestMapping(value = "api/student/studentInterview")
public class StudentInterviewController implements IFindController<StudentInterview>,
        ISaveController<StudentInterview>,IDeleteController<String>,IAuditController<StudentInterview>,
        ICancelController<StudentInterview>,ISubmitController<StudentInterview> {
    @Autowired
    private StudentInterviewService studentInterviewService;

    @Override
    public Response Find(@RequestBody Request<StudentInterview> studentInterview) {
        return studentInterviewService.find(studentInterview);
    }

    @Override
    public Response Save(@RequestBody Request<StudentInterview> studentInterview) {
        return studentInterviewService.save(studentInterview);
    }
    @Override

    public Response Delete(@RequestBody Request<String> studentInterview) {
        return studentInterviewService.delete(studentInterview);

    }

    @Override
    public Response Audit(@RequestBody Request<StudentInterview> studentInterview) {
        return studentInterviewService.audit(studentInterview);
    }

    @Override
    public Response Cancel(@RequestBody Request<StudentInterview> studentInterview) {
        return studentInterviewService.cancel(studentInterview);
    }

    @Override
    public Response Submit(@RequestBody Request<StudentInterview> studentInterview) {
        return studentInterviewService.submit(studentInterview);
    }
}
