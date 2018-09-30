package com.wuxue.api.controller.course;

import com.wuxue.api.interfaces.IDeleteController;
import com.wuxue.api.interfaces.IFindController;
import com.wuxue.api.interfaces.ISaveController;
import com.wuxue.api.service.QuestionsProjectService;
import com.wuxue.model.QuestionsProject;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *  题库 科目
 */
@RestController
@RequestMapping(value = "api/course/questionsProject")
public class QuestionsProjectController implements IFindController<QuestionsProject>,
        ISaveController<QuestionsProject>,IDeleteController<String> {


    @Autowired
    private QuestionsProjectService questionsProjectService;

    @Override
    public Response Find(@RequestBody Request<QuestionsProject> questions) {
        return questionsProjectService.find(questions);
    }

    @Override
    public Response Save(@RequestBody Request<QuestionsProject> questions) {
        return questionsProjectService.save(questions);
    }

    @Override
    public Response Delete(@RequestBody Request<String> questions) {
        return null;
    }

}