package com.wuxue.api.controller.course;

import com.wuxue.api.interfaces.IDeleteController;
import com.wuxue.api.interfaces.IFindController;
import com.wuxue.api.interfaces.ISaveController;
import com.wuxue.api.service.QuestionsSubjectService;
import com.wuxue.model.QuestionsSubject;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *  题库知识点
 */
@RestController
@RequestMapping(value = "api/course/questionsSubject")
public class QuestionsSubjectController implements IFindController<QuestionsSubject>,
        ISaveController<QuestionsSubject>,IDeleteController<String> {


    @Autowired
    private QuestionsSubjectService questionsSubjectService;

    @Override
    public Response Find(@RequestBody Request<QuestionsSubject> questions) {
        return questionsSubjectService.find(questions);
    }

    @Override
    public Response Save(@RequestBody Request<QuestionsSubject> questions) {
        return questionsSubjectService.save(questions);
    }

    @Override
    public Response Delete(@RequestBody Request<String> questions) {
        return null;
    }

}