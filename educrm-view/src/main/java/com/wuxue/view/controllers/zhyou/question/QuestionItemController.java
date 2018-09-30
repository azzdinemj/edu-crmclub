package com.wuxue.view.controllers.zhyou.question;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.wuxue.model.QuestionsItem;
import com.wuxue.model.QuestionsSubject;
import com.wuxue.model.SysDictValues;
import com.wuxue.utils.contract.Response;
import com.wuxue.view.client.course.QuestionsItemClient;
import com.wuxue.view.client.course.QuestionsSubjectClient;
import com.wuxue.view.client.system.SysDictValuesClient;
import com.wuxue.view.controllers.BaseController;
import com.wuxue.view.interfaces.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 管理 题目
 */
@Controller
@RequestMapping(value = "/question/questionItem")
public class QuestionItemController extends BaseController
        implements IQueryController<QuestionsItem,String>,IEditController<QuestionsItem,String>
        ,ISaveController<QuestionsItem,String>,IQueryByPagingController<QuestionsItem,Map<String,Object>> , IDeleteController<QuestionsItem, String> {

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
           return "/zhyou/question/questionitemlist";
    }

    @Override
    @ResponseBody
    public Map<String, Object> queryByPaging(HttpServletRequest request, HttpServletResponse response, QuestionsItem questionsItem, Integer sEcho, Integer start, Integer length) {
        questionsItem.setPageNo((start/length)+1);
        questionsItem.setPageSize(length);

        Response<PageInfo<QuestionsItem>> resp=questionsItemClient.find(questionsItem);
        List<QuestionsItem> data = resp.getData().getList();
        if(data==null) {
            data = new ArrayList<>();
        }
        return convertToPaging(resp.getData().getTotal(),resp.getData().getTotal(),data);
    }

    @Autowired
    SysDictValuesClient sysDictValuesClient;

    @Override
    public String edit(HttpServletRequest request, Model model, QuestionsItem questionsOption) {

        SysDictValues sysDictValues=new SysDictValues();
        sysDictValues.setPkSysDict("zhyquestions");
        Response<List<SysDictValues>> listResponse= sysDictValuesClient.find(sysDictValues);
        model.addAttribute("SysDictValues",listResponse.getData());

        initDict(model);

        if(questionsOption.getId()!=null&&questionsOption.getId()!=""){
          Response<QuestionsItem> questionsOptionResponse=questionsItemClient.findByPrimaryKey(questionsOption);
          model.addAttribute("questions",questionsOptionResponse.getData());
      }
        return "/zhyou/question/questionitem";
    }

    //加载课程分类（数据字典）
    private void initDict(Model model){
        SysDictValues sysDictValues=new SysDictValues();
        sysDictValues.setPkSysDict("zhy");
        Response<List<SysDictValues>> responsesysDictValues= sysDictValuesClient.find(sysDictValues);
        model.addAttribute("dict",responsesysDictValues.getData());
    }

    @Override
    @ResponseBody
    public String save(HttpServletRequest request, Model model, QuestionsItem questionsItem) {
        int strtype=questionsItem.getType();//题目类型，根据类型判断参数。
        if(strtype==1){
            int p1=Integer.parseInt(request.getParameter("partnum1"));//单选的选项数量
            String c1=request.getParameter("choice1");//单选的选项
            String r1=request.getParameter("result1");//单选的答案

            questionsItem.setPartnum(p1);
            questionsItem.setOption(c1);
            questionsItem.setAnswer(r1);
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

            questionsItem.setPartnum(p2);
            questionsItem.setOption(c2);
            questionsItem.setAnswer(r22);
        }
        if(strtype==3){
            String r3= request.getParameter("result3");//判断的答案

            questionsItem.setAnswer(r3);
        }
        if(strtype==4){
            String r4= request.getParameter("result4");//填空的答案

            questionsItem.setAnswer(r4);
        }
        if(strtype==5){
            String r5=request.getParameter("result5");//问答的答案

            questionsItem.setAnswer(r5);
        }

        return questionsItemClient.save(questionsItem);
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


    @Override
    @ResponseBody
    public String delete(HttpServletRequest request, Model model, QuestionsItem questionsItem) {
        return questionsItemClient.delete(questionsItem);
    }
}
