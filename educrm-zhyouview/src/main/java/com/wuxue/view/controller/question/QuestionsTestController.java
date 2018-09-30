package com.wuxue.view.controller.question;

import com.github.pagehelper.PageInfo;
import com.wuxue.model.QuestionsItem;
import com.wuxue.model.QuestionsTest;
import com.wuxue.utils.common.DataUtils;
import com.wuxue.utils.contract.Light;
import com.wuxue.utils.contract.Response;
import com.wuxue.view.client.question.QuestionsTestClient;
import com.wuxue.view.controller.BaseController;
import com.wuxue.view.utils.SessionCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *   测试记录控制器
 */
@Controller
@RequestMapping(value = "/questionsTest")
public class QuestionsTestController extends BaseController {

    @Autowired
    QuestionsTestClient questionsTestClient;


    /**
     * 历史测试记录列表
     * @return
     */
    @RequestMapping(value="/historylist",method = RequestMethod.GET)
    public String historylist(HttpServletRequest request ,Model model,QuestionsTest questionsTest) {
            questionsTest.setPageNo(1);
            questionsTest.setPageSize(1000);
            questionsTest.setPkStudent(SessionCache.getPkStudent());
            questionsTest.setType(0);
            Response<PageInfo<QuestionsTest>> response = questionsTestClient.find(questionsTest);
            model.addAttribute("qTesthistorylist", response.getData().getList());
            return "/historylist";

    }

    /**
     * 历史练习记录列表详情
     *           questionTest    param: testid
     * @return
     */
    @RequestMapping(value="/history")
    public String history(HttpServletRequest request ,Model model,QuestionsTest questionsTest) {
        questionsTest.setPageNo(1);
        questionsTest.setPageSize(1000);
        questionsTest.setPkStudent(SessionCache.getPkStudent());
        questionsTest.setType(1);

        List<QuestionsItem> listSelect=new ArrayList<>();  //选择题集合
        List<QuestionsItem> listISNot=new ArrayList<>();//判断题集合
        QuestionsItem questionsItem=new QuestionsItem();

        Response<PageInfo<QuestionsTest>> response = questionsTestClient.find(questionsTest);
        List<QuestionsTest> list=response.getData().getList();
        for (QuestionsTest qt:list){ //数据重组
            questionsItem=DataUtils.objectToObject(qt.get(Light.QUESTIONS_ITEM),QuestionsItem.class);
            questionsItem.put("userAnswer",qt.getAnswer());
            if(questionsItem.getType()==1){ //选择
                listSelect.add(questionsItem);
            }else if(questionsItem.getType()==3){
                listISNot.add(questionsItem);
            }
        }

        model.addAttribute("listSelect",listSelect);
        model.addAttribute("listIsNot",listISNot);
        return "/history";
    }


    /**
     * 历史练习记录列表详情--全部错题
     *           questionTest    param: studentid
     * @return
     */
    @RequestMapping(value="/historyerror")
    public String historyerror(HttpServletRequest request ,Model model,QuestionsTest questionsTest) {
        questionsTest.setPageNo(1);
        questionsTest.setPageSize(1000);
        questionsTest.setPkStudent(SessionCache.getPkStudent());
        questionsTest.setIstrue(1);
        questionsTest.setType(1);

        List<QuestionsItem> listSelect=new ArrayList<>();  //选择题集合
        List<QuestionsItem> listISNot=new ArrayList<>();   //判断题集合
        QuestionsItem questionsItem=new QuestionsItem();

        Response<PageInfo<QuestionsTest>> response = questionsTestClient.find(questionsTest);
        List<QuestionsTest> list=response.getData().getList();
        for (QuestionsTest qt:list){ //数据重组
            questionsItem=DataUtils.objectToObject(qt.get(Light.QUESTIONS_ITEM),QuestionsItem.class);
            questionsItem.put("userAnswer",qt.getAnswer());
            if(questionsItem.getType()==1){ //选择
                listSelect.add(questionsItem);
            }else if(questionsItem.getType()==3){
                listISNot.add(questionsItem);
            }
        }
        model.addAttribute("listSelect",listSelect);
        model.addAttribute("listIsNot",listISNot);
        return "/historyerror";
    }



    /**
     * 保存测试记录
     * @param
     * @return
     */
    @RequestMapping("/saveTest")
    @ResponseBody
    public String saveTest(HttpServletRequest request, Model model,QuestionsTest questionsTest){

        String strResp="";
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyymmddhhMM");
        String strDate=simpleDateFormat.format(new Date());

        String  testid=SessionCache.getPkStudent()+strDate; //本次测试的唯一id;

        //测试记录
        questionsTest.setPkStudent(SessionCache.getPkStudent());
        questionsTest.setTestid(testid);
        questionsTest.setType(0);
        questionsTestClient.save(questionsTest);


        //测试记录详情集合
        String[] Strarr =request.getParameterValues("Select[]");
        for (String str: Strarr) {
            String [] strtest=str.split(",");
            questionsTest.setPkQuestionsItem(strtest[0]);
            questionsTest.setAnswer(strtest[1]);

            if(strtest[1].equals(strtest[2])){ //做题对错   0 对 1 错
                questionsTest.setIstrue(0);
            }else{
                questionsTest.setIstrue(1);
            }
            questionsTest.setType(1);
            questionsTest.setPkStudent(SessionCache.getPkStudent());
            strResp= questionsTestClient.save(questionsTest);
        }

        return strResp;

    }

}
