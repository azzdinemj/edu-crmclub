package com.wuxue.api.controller.course;

import com.wuxue.api.interfaces.IFindController;
import com.wuxue.api.interfaces.ISaveController;
import com.wuxue.api.service.QuestionsOptionService;
import com.wuxue.model.QuestionsOption;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *  题库选项
 */
@RestController
@RequestMapping(value = "api/course/questionsOption")
public class QuestionsOptionController implements IFindController<QuestionsOption>,
        ISaveController<QuestionsOption> {


    @Autowired
    private QuestionsOptionService questionsOptionService;

    @Override
    public Response Find(@RequestBody Request<QuestionsOption> questions) {
        return questionsOptionService.find(questions);
    }

    @Override
    public Response Save(@RequestBody Request<QuestionsOption> questions) {
        return questionsOptionService.save(questions);
    }

}