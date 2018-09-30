package com.wuxue.api.controller.student;

import com.wuxue.api.interfaces.IDeleteController;
import com.wuxue.api.interfaces.IFindController;
import com.wuxue.api.interfaces.ISaveController;
import com.wuxue.model.StudentAwards;
import com.wuxue.api.service.StudentAwardsService;
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
@RequestMapping(value = "api/student/studentAwards")
public class StudentAwardsController implements IFindController<StudentAwards>,
        ISaveController<StudentAwards>,IDeleteController<String> {
    @Autowired
    private StudentAwardsService studentAwardsService;

    @Override
    public Response Find(@RequestBody Request<StudentAwards> studentAwards) {
        return studentAwardsService.find(studentAwards);
    }

    @Override
    public Response Save(@RequestBody Request<StudentAwards> studentAwards) {
        return studentAwardsService.save(studentAwards);
    }
    @Override

    public Response Delete(@RequestBody Request<String> studentAwards) {
        return studentAwardsService.delete(studentAwards);

    }
}
