package com.wuxue.api.controller.course;

import com.wuxue.api.interfaces.IAuditController;
import com.wuxue.api.interfaces.IDeleteController;
import com.wuxue.api.interfaces.IFindController;
import com.wuxue.api.interfaces.ISaveController;
import com.wuxue.api.service.QuestionsService;
import com.wuxue.model.Questions;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Rogue on 2018/03/15.
 */
@RestController
@RequestMapping(value = "api/course/questions")
public class QuestionsController implements IFindController<Questions>,
        ISaveController<Questions>,IDeleteController<String>,IAuditController<Questions>{


    @Autowired
    private QuestionsService questionsService;

    @Override
    public Response Find(@RequestBody Request<Questions> questions) {
        return questionsService.find(questions);
    }

    @Override
    public Response Save(@RequestBody Request<Questions> questions) {
        return questionsService.save(questions);
    }

    @Override
    public Response Delete(@RequestBody Request<String> questions) {
        return questionsService.delete(questions);
    }

    @Override
    public Response Audit(@RequestBody Request<Questions> questions) {
        return questionsService.audit(questions);
    }
}
