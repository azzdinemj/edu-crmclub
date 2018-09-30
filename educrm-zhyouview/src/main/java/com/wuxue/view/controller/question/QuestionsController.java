package com.wuxue.view.controller.question;

import com.github.pagehelper.PageInfo;
import com.wuxue.model.*;
import com.wuxue.utils.contract.Light;
import com.wuxue.utils.contract.Response;
import com.wuxue.view.client.question.QuestionsClient;
import com.wuxue.view.client.question.QuestionsSubjectClient;
import com.wuxue.view.client.student.StudentClient;
import com.wuxue.view.client.system.SysDictValuesClient;
import com.wuxue.view.constant.Contsants;
import com.wuxue.view.interfaces.IQueryController;
import com.wuxue.view.interfaces.ISaveController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * 题库控制器
 */
@Controller
@RequestMapping(value="/questions")
public class QuestionsController implements IQueryController<Questions,String> {

    @Autowired
    private QuestionsClient questionsClient;
    @Autowired
    private SysDictValuesClient sysDictValuesClient;

    @Autowired
    private QuestionsSubjectClient questionsSubjectClient;

    /**
     * 查找题库上传的文件  -------已废弃
     * @param request
     * @param model
     * @param questions
     * @return
     */
    @Override
    public String query(HttpServletRequest request, Model model, Questions questions) {
        String strPage=request.getParameter("pageNo");
        String type=request.getParameter("type");
        if(strPage!=null&&strPage!=""){
            int pageNo=Integer.parseInt(strPage);
            questions.setPageNo(pageNo);
        }else{
            questions.setPageNo(Contsants.PAGE_NO);
        }
        //查询题库分类（数据字典）
        SysDictValues sysDictValues=new SysDictValues();
        sysDictValues.setPkSysDict(Contsants.DICT_VALUEQUESTIONS);
        Response<List<SysDictValues>> responsesysDictValues= sysDictValuesClient.find(sysDictValues);
        model.addAttribute("dict",responsesysDictValues.getData());
        //类别查询 条件
        if(type!=null&&type!=""){
            questions.setType(type);
            model.addAttribute("type",type);
        }
        //题库列表
        questions.setStatus(Contsants.QUESTIONS_STATUS);
        questions.setPageSize(Contsants.PAGE_SIZE);
        Response<PageInfo<Questions>> response=questionsClient.find(questions);
        PageInfo<Questions> pageInfo=response.getData();
        model.addAttribute("questionlist", pageInfo);
        return "/question";
    }

    /**
     *在线测试 跳转页
     *            初始化知识点
     * @return
     */
    @RequestMapping(value="question_",method = RequestMethod.GET)
    public String question_(HttpServletRequest request, Model model,QuestionsSubject questions) {
        questions.setStatus(Contsants.QUESTIONS_SUBJECT_STATUS);
        Response<List<QuestionsSubject>> response=questionsSubjectClient.find(questions);
        model.addAttribute("qSubjectList",response.getData());

        SysDictValues sysDictValues=new SysDictValues();
        sysDictValues.setPkSysDict("zhyquestions");
        Response<List<SysDictValues>> listResponse= sysDictValuesClient.find(sysDictValues);
        model.addAttribute("SysDictValues",listResponse.getData());

        initDict(model);

        return "/question_";
    }

    //加载课程分类（数据字典）
    private void initDict(Model model){
        SysDictValues sysDictValues=new SysDictValues();
        sysDictValues.setPkSysDict(Contsants.DICT_VALUECOURSE);
        Response<List<SysDictValues>> responsesysDictValues= sysDictValuesClient.find(sysDictValues);
        model.addAttribute("dict",responsesysDictValues.getData());
    }


}
