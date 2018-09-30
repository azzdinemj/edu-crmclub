package com.wuxue.api.controller.student;

import com.wuxue.api.interfaces.IAuditController;
import com.wuxue.api.interfaces.IDeleteController;
import com.wuxue.api.interfaces.IFindController;
import com.wuxue.api.interfaces.ISaveController;
import com.wuxue.api.service.StudentShiftService;
import com.wuxue.model.StudentShift;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Rogue on 2018/03/23.
 */
@RestController
@RequestMapping(value = "api/student/studentShift")
public class StudentShiftController implements IFindController<StudentShift>,
        ISaveController<StudentShift>,IDeleteController<String>{
    @Autowired
    private StudentShiftService studentShiftService;

    @Override
    public Response Find(@RequestBody Request<StudentShift> studentShift) {
        return studentShiftService.find(studentShift);
    }

    @Override
    public Response Save(@RequestBody Request<StudentShift> studentShift) {
        return studentShiftService.save(studentShift);
    }
    @Override

    public Response Delete(@RequestBody Request<String> studentShift) {
        return studentShiftService.delete(studentShift);

    }
}
