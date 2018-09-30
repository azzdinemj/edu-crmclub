package com.wuxue.api.controller.course;

import com.wuxue.api.interfaces.IDeleteController;
import com.wuxue.api.interfaces.IFindController;
import com.wuxue.api.interfaces.ISaveController;
import com.wuxue.api.service.QuestionsPaperItemService;
import com.wuxue.model.QuestionsPaperItem;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *  题库         组建试卷
 */
@RestController
@RequestMapping(value = "api/course/questionsPaperItem")
public class QuestionsPaperItemController implements IFindController<QuestionsPaperItem>,
        ISaveController<QuestionsPaperItem>,IDeleteController<String> {


    @Autowired
    QuestionsPaperItemService questionsPaperItemService;

    @Override
    public Response Find(@RequestBody Request<QuestionsPaperItem> questions) {
        return questionsPaperItemService.find(questions);
    }

    @Override
    public Response Save(@RequestBody Request<QuestionsPaperItem> questions) {
        return questionsPaperItemService.save(questions);
    }

    @Override
    public Response Delete(@RequestBody Request<String> questions) {
        return null;
    }

}