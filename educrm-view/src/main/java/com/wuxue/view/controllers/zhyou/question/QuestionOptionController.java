package com.wuxue.view.controllers.zhyou.question;


import com.wuxue.model.Questions;
import com.wuxue.model.QuestionsOption;
import com.wuxue.utils.contract.Response;
import com.wuxue.view.client.course.QuestionsOptionClient;
import com.wuxue.view.controllers.BaseController;
import com.wuxue.view.interfaces.IEditController;
import com.wuxue.view.interfaces.IQueryController;
import com.wuxue.view.interfaces.ISaveController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 管理
 */
@Controller
@RequestMapping(value = "/question/questionOption")
public class QuestionOptionController extends BaseController
        implements IQueryController<QuestionsOption,String>,IEditController<QuestionsOption,String>
        ,ISaveController<QuestionsOption,String> {

    @Autowired
    QuestionsOptionClient questionsOptionClient;


    /**
     * 列表页面
     * @param model
     * @return
     */
    @Override
    public String query(HttpServletRequest request, Model model,QuestionsOption questions)  {
           Response<List<QuestionsOption>> response= questionsOptionClient.find(questions);

           List<QuestionsOption> questionsOptionList=response.getData();
           model.addAttribute("optionList",questionsOptionList);
           return "/zhyou/question/questionoptionlist";
    }


    @Override
    public String edit(HttpServletRequest request, Model model, QuestionsOption questionsOption) {
        Response<QuestionsOption> questionsOptionResponse=questionsOptionClient.findByPrimaryKey(questionsOption);
        model.addAttribute("questions",questionsOptionResponse.getData());
        return "/zhyou/question/questionoption";
    }

    @Override
    @ResponseBody
    public String save(HttpServletRequest request, Model model, QuestionsOption questionsOption) {
        return questionsOptionClient.save(questionsOption);
    }
}
