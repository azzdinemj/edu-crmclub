package com.wuxue.api.controller.course;

import com.wuxue.api.interfaces.IDeleteController;
import com.wuxue.api.interfaces.IFindController;
import com.wuxue.api.interfaces.ISaveController;
import com.wuxue.api.service.QuestionsPaperDataService;
import com.wuxue.model.QuestionsPaperData;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *  题库         考试--试卷
 */
@RestController
@RequestMapping(value = "api/course/questionsPaperData")
public class QuestionsPaperDataController implements IFindController<QuestionsPaperData>,
        ISaveController<QuestionsPaperData>,IDeleteController<String> {


    @Autowired
    QuestionsPaperDataService questionsPaperDataService;

    @Override
    public Response Find(@RequestBody Request<QuestionsPaperData> questions) {
        return questionsPaperDataService.find(questions);
    }

    @Override
    public Response Save(@RequestBody Request<QuestionsPaperData> questions) {
        return questionsPaperDataService.save(questions);
    }

    @Override
    public Response Delete(@RequestBody Request<String> questions) {
        return null;
    }

}