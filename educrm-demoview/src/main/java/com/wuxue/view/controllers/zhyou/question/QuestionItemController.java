package com.wuxue.view.controllers.zhyou.question;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.wuxue.constant.Contsants;
import com.wuxue.model.QuestionsItem;
import com.wuxue.model.QuestionsSubject;
import com.wuxue.utils.contract.Response;
import com.wuxue.view.client.course.QuestionsItemClient;
import com.wuxue.view.client.course.QuestionsSubjectClient;
import com.wuxue.view.contract.TreeGridColumn;
import com.wuxue.view.contract.TreeGridModel;
import com.wuxue.view.controllers.BaseController;
import com.wuxue.view.interfaces.IEditController;
import com.wuxue.view.interfaces.IQueryController;
import com.wuxue.view.interfaces.ISaveController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * 管理 题目
 */
@Controller
@RequestMapping(value = "/question/questionItem")
public class QuestionItemController extends BaseController
        implements IQueryController<QuestionsItem,String>,IEditController<QuestionsItem,String>
        ,ISaveController<QuestionsItem,String> {

    @Autowired
    QuestionsItemClient questionsItemClient;

    @Autowired
    QuestionsSubjectClient questionsSubjectClient;


    /**
     * 列表页面
     * @param
     * @return
     */
    @Override
    public String query(HttpServletRequest request, Model model,QuestionsItem questionsItem)  {
            //页码，每页数量
            String str=request.getParameter("pageNo");
            if(str!=null&&str!=""){
                int pageNo=Integer.parseInt(str);
                questionsItem.setPageNo(pageNo);
            }else{
                questionsItem.setPageNo(Contsants.PAGE_NO);
            }
            questionsItem.setPageSize(Contsants.PAGE_SIZE);

           Response<PageInfo<QuestionsItem>> response= questionsItemClient.find(questionsItem);
           PageInfo<QuestionsItem> questionsOptionList=response.getData();
           model.addAttribute("optionList",questionsOptionList);
           return "/zhyou/question/questionitemlist";
    }


    @Override
    public String edit(HttpServletRequest request, Model model, QuestionsItem questionsOption) {
      if(questionsOption.getId()!=null&&questionsOption.getId()!=""){
          Response<QuestionsItem> questionsOptionResponse=questionsItemClient.findByPrimaryKey(questionsOption);
          model.addAttribute("questions",questionsOptionResponse.getData());
      }
        return "/zhyou/question/questionitem";
    }

    @Override
    @ResponseBody
    public String save(HttpServletRequest request, Model model, QuestionsItem questionsOption) {
        int strtype=questionsOption.getType();//题目类型，根据类型判断参数。
        if(strtype==1){
            int p1=Integer.parseInt(request.getParameter("partnum1"));//单选的选项数量
            String c1=request.getParameter("choice1");//单选的选项
            String r1=request.getParameter("result1");//单选的答案

            questionsOption.setPartnum(p1);
            questionsOption.setOption(c1);
            questionsOption.setAnswer(r1);
        }
        if(strtype==2){
            int p2=Integer.parseInt(request.getParameter("partnum2"));//多选的选项数量
            String c2=request.getParameter("choice2");//多选的选项
            String[] r2 =request.getParameterValues("result2[]");//多选的答案
            StringBuffer sb = new StringBuffer();
            for(int i = 0; i < r2.length; i++){
                sb.append(r2[i]+",");
            }
            String r22 = sb.toString();

            questionsOption.setPartnum(p2);
            questionsOption.setOption(c2);
            questionsOption.setAnswer(r22);
        }
        if(strtype==3){
            String r3= request.getParameter("result3");//判断的答案

            questionsOption.setAnswer(r3);
        }
        if(strtype==4){
            String r4= request.getParameter("result4");//填空的答案

            questionsOption.setAnswer(r4);
        }
        if(strtype==5){
            String r5=request.getParameter("result5");//问答的答案

            questionsOption.setAnswer(r5);
        }

        return questionsItemClient.save(questionsOption);
    }


    //json tree数据
    @RequestMapping(value = "/getData", method = RequestMethod.GET)
    @ResponseBody
    public String getData() {
        Response<List<QuestionsSubject>> response = questionsSubjectClient.find(new QuestionsSubject());
        List<QuestionsSubject> list = new ArrayList<>();
        for (QuestionsSubject sys : response.getData()) {
            if (sys.getPid() == null || sys.getPid().equals("")) {
               // sys.setName("<a class='yellow'   href='/question/questionSubject/edit?id=" + sys.getId() + "'>" + sys.getName() + "</a>");
                getData(response.getData(), sys);
                list.add(sys);
            }
        }
        return JSON.toJSONString(list);
    }

    //递归嵌套数据
    private void getData(List<QuestionsSubject> list, QuestionsSubject sys) {
        List<QuestionsSubject> listTmp = new ArrayList<>();
        for (QuestionsSubject sysMenu : list) {
            if (sys.getId().equals(sysMenu.getPid())) {
                //sysMenu.setName("<a class='yellow'  href='/question/questionSubject/edit?id=" + sysMenu.getId() + "'>" + sysMenu.getName() + "</a>");
                listTmp.add(sysMenu);
                getData(list, sysMenu);
            }
        }
        if (listTmp.size() > 0) {
            sys.setChildren(listTmp);
        }

    }


}
