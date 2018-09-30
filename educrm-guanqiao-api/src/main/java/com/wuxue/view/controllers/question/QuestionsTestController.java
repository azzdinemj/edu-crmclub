package com.wuxue.view.controllers.question;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.github.pagehelper.PageInfo;
import com.sun.org.apache.regexp.internal.RE;
import com.wuxue.model.QuestionsItem;
import com.wuxue.model.QuestionsTest;
import com.wuxue.utils.common.DataUtils;
import com.wuxue.utils.contract.Light;
import com.wuxue.utils.contract.Response;
import com.wuxue.view.client.course.QuestionsTestClient;
import com.wuxue.view.controllers.BaseController;
import com.wuxue.view.utils.GuidUtils;
import com.wuxue.view.utils.SessionCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 *   测试记录
 */
@Controller
@RequestMapping(value = "/guanqiao/questionsTest")
public class QuestionsTestController extends BaseController {

    @Autowired
    QuestionsTestClient questionsTestClient;


    /**
     * 历史测试记录列表
     * @return
     */
    @RequestMapping(value="/questionstest",method = RequestMethod.POST)
    @ResponseBody
    public Response questionstest(HttpServletRequest request ,Model model,QuestionsTest questionsTest) {
            questionsTest.setPageNo(1);
            questionsTest.setPageSize(1000);
            questionsTest.setType(0);
            Response<PageInfo<QuestionsTest>> response = questionsTestClient.find(questionsTest);
            return  response;
    }

    /**
     * 历史练习记录列表详情
     *           questionTest    param: testid
     * @return
     */
    @RequestMapping(value="/questionstestdetail")
    @ResponseBody
    public Response questionstestdetail(HttpServletRequest request ,Model model,QuestionsTest questionsTest) {
        Response resp=Response.newResponse();
        questionsTest.setPageNo(1);
        questionsTest.setPageSize(1000);
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
        Map<String,Object> map=new HashMap<>();
        map.put("listSelect",listSelect);
        map.put("listIsNot",listISNot);
        resp.setData(map);
        return  resp;
    }


    /**
     * 历史练习记录列表详情--全部错题
     *           questionTest    param: studentid
     * @return
     */
    @RequestMapping(value="/questionstestdetailerror")
    @ResponseBody
    public Response questionstestdetailerror(HttpServletRequest request ,Model model,QuestionsTest questionsTest) {
        Response resp=Response.newResponse();

        questionsTest.setPageNo(1);
        questionsTest.setPageSize(1000);
        questionsTest.setIstrue(0); // 错题
        questionsTest.setType(1);   //题目

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
        Map<String,Object> map=new HashMap<>();
        map.put("listSelect",listSelect);
        map.put("listIsNot",listISNot);
        resp.setData(map);
        return  resp;
    }


    /**
     * 保存测试记录
     * @param questionsTest
     * @param request Select[]  select="QuestionItemId,answer,youanswer";
     * @return
     */
    @RequestMapping("/savetest")
    @ResponseBody
    public Response savetest(HttpServletRequest request, Model model,QuestionsTest questionsTest){
        Response response =Response.newResponse();

        //测试记录
        questionsTest.setTestid(GuidUtils.getGuid()); //本次测试的唯一id;
        questionsTest.setType(0);
        questionsTestClient.save(questionsTest);

        //测试记录详情集合
        String[] Strarr =request.getParameterValues("Select[]");
        for (String str: Strarr) {
            String [] strtest=str.split(",");
            questionsTest.setPkQuestionsItem(strtest[0]);
            questionsTest.setAnswer(strtest[1]);

            if(strtest[1].equals(strtest[2])){
                questionsTest.setIstrue(0); // 0 错
            }else{
                questionsTest.setIstrue(1); // 1 对
            }
            questionsTest.setType(1);
            response= JSON.parseObject(questionsTestClient.save(questionsTest),new TypeReference<Response<QuestionsTest>>(){});
        }

        return response;
    }


}
