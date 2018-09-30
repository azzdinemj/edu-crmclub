package com.wuxue.api.controller.student;

import com.wuxue.api.interfaces.IDeleteController;
import com.wuxue.api.interfaces.IFindController;
import com.wuxue.api.interfaces.ISaveController;
import com.wuxue.model.StudentInterviewRecords;
import com.wuxue.api.service.StudentInterviewRecordsService;
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
@RequestMapping(value = "api/student/studentInterviewRecords")
public class StudentInterviewRecordsController implements IFindController<StudentInterviewRecords>,
        ISaveController<StudentInterviewRecords>,IDeleteController<String> {
    @Autowired
    private StudentInterviewRecordsService studentInterviewRecordsService;

    @Override
    public Response Find(@RequestBody Request<StudentInterviewRecords> studentInterviewRecords) {
        return studentInterviewRecordsService.find(studentInterviewRecords);
    }

    @Override
    public Response Save(@RequestBody Request<StudentInterviewRecords> studentInterviewRecords) {
        return studentInterviewRecordsService.save(studentInterviewRecords);
    }
    @Override

    public Response Delete(@RequestBody Request<String> studentInterviewRecords) {
        return studentInterviewRecordsService.delete(studentInterviewRecords);

    }
}
