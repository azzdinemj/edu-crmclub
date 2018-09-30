package com.wuxue.api.controller.student;

import com.wuxue.api.interfaces.IDeleteController;
import com.wuxue.api.interfaces.IFindController;
import com.wuxue.api.interfaces.ISaveController;
import com.wuxue.model.StudentInterviewRecord;
import com.wuxue.api.service.StudentInterviewRecordService;
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
@RequestMapping(value = "api/student/studentInterviewRecord")
public class StudentInterviewRecordController implements IFindController<StudentInterviewRecord>,
        ISaveController<StudentInterviewRecord>,IDeleteController<String> {
    @Autowired
    private StudentInterviewRecordService studentInterviewRecordService;

    @Override
    public Response Find(@RequestBody Request<StudentInterviewRecord> studentInterviewRecord) {
        return studentInterviewRecordService.find(studentInterviewRecord);
    }

    @Override
    public Response Save(@RequestBody Request<StudentInterviewRecord> studentInterviewRecord) {
        return studentInterviewRecordService.save(studentInterviewRecord);
    }
    @Override

    public Response Delete(@RequestBody Request<String> studentInterviewRecord) {
        return studentInterviewRecordService.delete(studentInterviewRecord);

    }
}
