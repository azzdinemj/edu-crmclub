package com.wuxue.view.controllers.attendance;

import com.wuxue.model.Questions;
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

/**
 * 管理
 */
@Controller
@RequestMapping(value = "/attendance/attendancestlist")
public class AttendancestListController extends BaseController
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

        return "/attendance/attendancestlist";
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

        return "/attendance/attendancest";
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
