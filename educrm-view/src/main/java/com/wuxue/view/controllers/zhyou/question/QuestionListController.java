package com.wuxue.view.controllers.zhyou.question;

import com.github.pagehelper.PageInfo;
import com.wuxue.constant.Contsants;
import com.wuxue.model.Questions;
import com.wuxue.model.SysDictValues;
import com.wuxue.utils.contract.Response;
import com.wuxue.view.client.course.QuestionsClient;
import com.wuxue.view.client.system.SysDictValuesClient;
import com.wuxue.view.controllers.BaseController;
import com.wuxue.view.interfaces.IEditController;
import com.wuxue.view.interfaces.IQueryController;
import com.wuxue.view.interfaces.ISaveController;
import com.wuxue.view.utils.SessionCache;
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
@RequestMapping(value = "/question/questionlist")
public class QuestionListController extends BaseController
        implements IQueryController<Questions,String>,IEditController<Questions,String>
        ,ISaveController<Questions,String> {

    @Autowired
    QuestionsClient questionsClient;
    @Autowired
    SysDictValuesClient sysDictValuesClient;


    /**
     * 列表页面
     * @param model
     * @return
     */
    @Override
    public String query(HttpServletRequest request, Model model, Questions questions)  {

        //页码，每页数量
        String str=request.getParameter("pageNo");
        if(str!=null&&str!=""){
            int pageNo=Integer.parseInt(str);
            questions.setPageNo(pageNo);
        }else{
            questions.setPageNo(Contsants.PAGE_NO);
        }
        questions.setPageSize(Contsants.PAGE_SIZE);

        Response<PageInfo<Questions>> response=questionsClient.find(questions);
        PageInfo<Questions> pageInfo=response.getData();

        model.addAttribute("questions", pageInfo);

        return "/zhyou/question/questionlist";
    }

    /**
     * 编辑跳转
     * @param request
     * @param model
     * @param questions
     * @return
     */
    @Override
    public String edit(HttpServletRequest request, Model model, Questions questions) {
        //查询课程分类（数据字典）
        SysDictValues sysDictValues=new SysDictValues();
        sysDictValues.setPkSysDict("zhy");
        Response<List<SysDictValues>> responsesysDictValues= sysDictValuesClient.find(sysDictValues);
        model.addAttribute("dict",responsesysDictValues.getData());

        if(questions.getPkQuestions()==""||questions.getPkQuestions()==null){
            model.addAttribute("questionsAdd",1);
        }else{
            Response<Questions> questionsResponse=questionsClient.findByPrimaryKey(questions);
            model.addAttribute("questions",questionsResponse.getData());
        }
        return "/zhyou/question/question";
    }


    @Override
    @ResponseBody
    public String save(HttpServletRequest request, Model model, Questions questions) {
        //若主键为空，则执行添加对象赋值，并创建审核对象
        if(questions.getPkQuestions()==null||questions.getPkQuestions()==""){
            questions.setIsdel(0);
            questions.setCreator(SessionCache.getUserCode());
            questions.setModifier(SessionCache.getUserCode());
            questions.setStatus(0); //状态 未提交
            String response=questionsClient.save(questions);

            return  response;
        }
        //添加修改人usercode
        questions.setModifier(SessionCache.getUserCode());
        return questionsClient.save(questions);
    }


    //提交课程,创建审核对象
    @RequestMapping("/submitQuestions")
    @ResponseBody
    public String submitCourse(Questions questions){
        questions.setStatus(1);//提交
        questions.setModifier(SessionCache.getUserCode()); //修改用户
        questionsClient.save(questions);//修改 课程 status 状态
        return  questionsClient.audit(questions);//创建 审核对象
    }

}
