package com.wuxue.api.controller.student;

import com.wuxue.api.interfaces.IDeleteController;
import com.wuxue.api.interfaces.IFindController;
import com.wuxue.api.interfaces.ISaveController;
import com.wuxue.model.StudentEduExperience;
import com.wuxue.api.service.StudentEduExperienceService;
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
@RequestMapping(value = "api/student/studentEduExperience")
public class StudentEduExperienceController implements IFindController<StudentEduExperience>,
        ISaveController<StudentEduExperience>,IDeleteController<String> {
    @Autowired
    private StudentEduExperienceService studentEduExperienceService;

    @Override
    public Response Find(@RequestBody Request<StudentEduExperience> studentEduExperience) {
        return studentEduExperienceService.find(studentEduExperience);
    }

    @Override
    public Response Save(@RequestBody Request<StudentEduExperience> studentEduExperience) {
        return studentEduExperienceService.save(studentEduExperience);
    }
    @Override

    public Response Delete(@RequestBody Request<String> studentEduExperience) {
        return studentEduExperienceService.delete(studentEduExperience);

    }
}
