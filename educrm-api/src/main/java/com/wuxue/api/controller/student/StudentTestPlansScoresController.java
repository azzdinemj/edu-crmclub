package com.wuxue.api.controller.student;

import com.wuxue.api.interfaces.IDeleteController;
import com.wuxue.api.interfaces.IFindController;
import com.wuxue.api.interfaces.ISaveController;
import com.wuxue.model.StudentTestPlansScores;
import com.wuxue.api.service.StudentTestPlansScoresService;
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
@RequestMapping(value = "api/student/studentTestPlansScores")
public class StudentTestPlansScoresController implements IFindController<StudentTestPlansScores>,
        ISaveController<StudentTestPlansScores>,IDeleteController<String> {
    @Autowired
    private StudentTestPlansScoresService studentTestPlansScoresService;

    @Override
    public Response Find(@RequestBody Request<StudentTestPlansScores> studentTestPlansScores) {
        return studentTestPlansScoresService.find(studentTestPlansScores);
    }

    @Override
    public Response Save(@RequestBody Request<StudentTestPlansScores> studentTestPlansScores) {
        return studentTestPlansScoresService.save(studentTestPlansScores);
    }
    @Override

    public Response Delete(@RequestBody Request<String> studentTestPlansScores) {
        return studentTestPlansScoresService.delete(studentTestPlansScores);

    }
}
