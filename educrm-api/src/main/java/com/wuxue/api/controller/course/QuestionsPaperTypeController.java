package com.wuxue.api.controller.course;

import com.wuxue.api.interfaces.IDeleteController;
import com.wuxue.api.interfaces.IFindController;
import com.wuxue.api.interfaces.ISaveController;
import com.wuxue.api.service.QuestionsPaperTypeService;
import com.wuxue.model.QuestionsPaperType;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *  题库         试卷类型
 */
@RestController
@RequestMapping(value = "api/course/questionsPaperType")
public class QuestionsPaperTypeController implements IFindController<QuestionsPaperType>,
        ISaveController<QuestionsPaperType>,IDeleteController<String> {


    @Autowired
    QuestionsPaperTypeService questionsPaperTypeService;

    @Override
    public Response Find(@RequestBody Request<QuestionsPaperType> questions) {
        return questionsPaperTypeService.find(questions);
    }

    @Override
    public Response Save(@RequestBody Request<QuestionsPaperType> questions) {
        return questionsPaperTypeService.save(questions);
    }

    @Override
    public Response Delete(@RequestBody Request<String> questions) {
        return null;
    }

}