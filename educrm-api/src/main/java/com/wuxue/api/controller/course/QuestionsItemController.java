package com.wuxue.api.controller.course;

import com.wuxue.api.interfaces.IDeleteController;
import com.wuxue.api.interfaces.IFindController;
import com.wuxue.api.interfaces.ISaveController;
import com.wuxue.api.service.QuestionsItemService;
import com.wuxue.api.service.QuestionsSubjectService;
import com.wuxue.model.QuestionsItem;
import com.wuxue.model.QuestionsSubject;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *  题库 题目
 */
@RestController
@RequestMapping(value = "api/course/questionsItem")
public class QuestionsItemController implements IFindController<QuestionsItem>,
        ISaveController<QuestionsItem>,IDeleteController<QuestionsItem> {

    @Autowired
    QuestionsItemService questionsItemService;

    @Override
    public Response Find(@RequestBody Request<QuestionsItem> questions) {
        return questionsItemService.find(questions);
    }

    @Override
    public Response Save(@RequestBody Request<QuestionsItem> questions) {
        return questionsItemService.save(questions);
    }

    @Override
    public Response Delete(@RequestBody Request<QuestionsItem> questions) {
        return questionsItemService.delete(questions);
    }
}