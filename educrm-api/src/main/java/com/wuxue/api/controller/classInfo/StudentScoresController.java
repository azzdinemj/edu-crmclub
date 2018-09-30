package com.wuxue.api.controller.classInfo;

import com.wuxue.api.interfaces.IDeleteController;
import com.wuxue.api.interfaces.IFindController;
import com.wuxue.api.interfaces.ISaveController;
import com.wuxue.api.service.StudentScoresService;
import com.wuxue.model.Student;
import com.wuxue.model.StudentScores;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Rogue on 2018/01/02.
 */
@RestController
@RequestMapping(value = "api/classinfo/studentScores")
public class StudentScoresController implements IFindController<StudentScores>,
        ISaveController<StudentScores>,IDeleteController<String> {
    @Autowired
    private StudentScoresService studentScoresService;

    @Override
    public Response Find(@RequestBody Request<StudentScores> studentScores) {
        return studentScoresService.find(studentScores);
    }

    @Override
    public Response Save(@RequestBody Request<StudentScores> studentScores) {
        return studentScoresService.save(studentScores);
    }

    @Override
    public Response Delete(@RequestBody Request<String> studentScores) {
        return studentScoresService.delete(studentScores);
    }
    @RequestMapping(value = "/saveall",method = RequestMethod.POST)
    public Response saveAll(@RequestBody Request<List<StudentScores>> studentScores) {
        return studentScoresService.saveAll(studentScores);

    }
    @RequestMapping(value = "/findschoolreport",method = RequestMethod.POST)
    public Response findSchoolReport(@RequestBody Request<Student> studentScores) {
        return studentScoresService.findSchoolReport(studentScores);

    }
}
