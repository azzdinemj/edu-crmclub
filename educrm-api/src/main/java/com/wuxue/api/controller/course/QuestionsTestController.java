package com.wuxue.api.controller.course;

import com.wuxue.api.interfaces.IDeleteController;
import com.wuxue.api.interfaces.IFindController;
import com.wuxue.api.interfaces.ISaveController;
import com.wuxue.api.service.QuestionsTestService;
import com.wuxue.model.QuestionsTest;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *  题库  测试表
 */
@RestController
@RequestMapping(value = "api/course/questionsTest")
public class QuestionsTestController implements IFindController<QuestionsTest>,
        ISaveController<QuestionsTest>,IDeleteController<String> {

    @Autowired
    QuestionsTestService questionsTestService;

    @Override
    public Response Find(@RequestBody Request<QuestionsTest> questions) {
        return questionsTestService.find(questions);
    }

    @Override
    public Response Save(@RequestBody Request<QuestionsTest> questions) {
        return questionsTestService.save(questions);
    }

    @Override
    public Response Delete(@RequestBody Request<String> questions) {
        return null;
    }

}