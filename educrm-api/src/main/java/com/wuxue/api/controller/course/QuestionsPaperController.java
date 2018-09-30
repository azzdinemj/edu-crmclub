package com.wuxue.api.controller.course;

import com.wuxue.api.interfaces.IDeleteController;
import com.wuxue.api.interfaces.IFindController;
import com.wuxue.api.interfaces.ISaveController;
import com.wuxue.api.service.QuestionsPaperService;
import com.wuxue.model.QuestionsPaper;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *  题库  试卷
 */
@RestController
@RequestMapping(value = "api/course/questionsPaper")
public class QuestionsPaperController implements IFindController<QuestionsPaper>,
        ISaveController<QuestionsPaper>,IDeleteController<String> {


    @Autowired
    QuestionsPaperService questionsPaperService;

    @Override
    public Response Find(@RequestBody Request<QuestionsPaper> questions) {
        return questionsPaperService.find(questions);
    }

    @Override
    public Response Save(@RequestBody Request<QuestionsPaper> questions) {
        return questionsPaperService.save(questions);
    }

    @Override
    public Response Delete(@RequestBody Request<String> questions) {
        return null;
    }

}