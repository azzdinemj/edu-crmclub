package com.wuxue.api.controller.student;

import com.wuxue.api.interfaces.IDeleteController;
import com.wuxue.api.interfaces.IFindController;
import com.wuxue.api.interfaces.ISaveController;
import com.wuxue.model.StudentBehaviorRecord;
import com.wuxue.api.service.StudentBehaviorRecordService;
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
@RequestMapping(value = "api/student/studentBehaviorRecord")
public class StudentBehaviorRecordController implements IFindController<StudentBehaviorRecord>,
        ISaveController<StudentBehaviorRecord>,IDeleteController<String> {
    @Autowired
    private StudentBehaviorRecordService studentBehaviorRecordService;

    @Override
    public Response Find(@RequestBody Request<StudentBehaviorRecord> studentBehaviorRecord) {
        return studentBehaviorRecordService.find(studentBehaviorRecord);
    }

    @Override
    public Response Save(@RequestBody Request<StudentBehaviorRecord> studentBehaviorRecord) {
        return studentBehaviorRecordService.save(studentBehaviorRecord);
    }
    @Override

    public Response Delete(@RequestBody Request<String> studentBehaviorRecord) {
        return studentBehaviorRecordService.delete(studentBehaviorRecord);

    }
}
