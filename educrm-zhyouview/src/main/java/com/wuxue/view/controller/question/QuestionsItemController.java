package com.wuxue.view.controller.question;

import com.github.pagehelper.PageInfo;
import com.wuxue.model.QuestionsItem;
import com.wuxue.model.QuestionsSubject;
import com.wuxue.utils.contract.Response;
import com.wuxue.view.client.question.QuestionsItemClient;
import com.wuxue.view.client.question.QuestionsSubjectClient;
import com.wuxue.view.constant.Contsants;
import com.wuxue.view.controller.BaseController;
import com.wuxue.view.utils.GetRamdomArray;
import com.wuxue.view.utils.SessionCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

import java.util.*;

/**
 *  题目控制器
 */
@Controller
@RequestMapping(value = "/questionsItem")
public class QuestionsItemController extends BaseController {

    @Autowired
    private QuestionsItemClient questionsItemClient;

    @Autowired
    private QuestionsSubjectClient questionsSubjectClient;


    /**
     * 根据条件组合试题列表（查重，已经做过的不显示）
     * @param
     * @return
     */
    @RequestMapping("/findItemBy")
    public String findItemBy(HttpServletRequest request, Model model,QuestionsItem questionsItem)  {

        Response<PageInfo<QuestionsItem>> response=new Response<>();
        List<QuestionsItem> itemList;

        String type1=request.getParameter("checkbox1");//选择
        String type2=request.getParameter("checkbox2");//判断

        String radio1=request.getParameter("radio1");//选择题难度
        String radio2=request.getParameter("radio2");//判断题难度

        String input1=request.getParameter("input1");//选择题数量
        String input2=request.getParameter("input2");//判断题数量

        questionsItem.setPkStudent(SessionCache.getPkStudent()); //登陆的学生主键,去重条件
        questionsItem.setPageNo(1);
        questionsItem.setPageSize(1000);
        List<QuestionsItem> listSelect=new ArrayList<>();  //选择题集合
        if(type1!=null&&input1!=null){
            questionsItem.setType(Short.parseShort(type1));
            questionsItem.setRank(radio1);
            response= questionsItemClient.find(questionsItem);
            itemList=response.getData().getList();

            int  num=Integer.parseInt(input1);
            if(itemList.size()>0){
                if(itemList.size()<=num){ //输入的数量大于总数则不随机抽取直接返回
                    for (QuestionsItem q :itemList) {
                        listSelect.add(q);
                    }
                }else{ // 随机抽题
                    HashSet<Integer> hs=GetRamdomArray.getramdomArr(num,itemList.size());
                    Iterator<Integer> iterator=hs.iterator();
                    while(iterator.hasNext()){
                        listSelect.add(itemList.get(iterator.next()));
                    }
                }
            }

        }

        List<QuestionsItem> listISNot=new ArrayList<>();//判断题集合
        if(type2!=null&&input2!=null){
            questionsItem.setType(Short.parseShort(type2));
            questionsItem.setRank(radio2);
            response= questionsItemClient.find(questionsItem);
            itemList=response.getData().getList();

            int  num=Integer.parseInt(input2);
            if(itemList.size()>0){
                if(itemList.size()<=num){
                    for (QuestionsItem q :itemList) {
                        listISNot.add(q);
                    }
                }else{// 随机抽题
                    HashSet<Integer> hs=GetRamdomArray.getramdomArr(Integer.parseInt(input2),itemList.size());
                    Iterator<Integer> iterator=hs.iterator();
                    while(iterator.hasNext()){
                        listISNot.add(itemList.get(iterator.next()));
                    }
                }
            }
        }

        //初始化知识点
        QuestionsSubject questions=new QuestionsSubject();
        questions.setStatus(Contsants.QUESTIONS_SUBJECT_STATUS);
        Response<List<QuestionsSubject>> respSub=questionsSubjectClient.find(questions);
        model.addAttribute("qSubjectList",respSub.getData());



        if(type1==null||type1.equals("")){
            model.addAttribute("listSelect1",1);
        }else{
            model.addAttribute("listSelect",listSelect);
        }

        if(type2==null||type2.equals("")){
            model.addAttribute("listIsNot1",1);
        }else{
            model.addAttribute("listIsNot",listISNot);
        }
        return "/question_bank";
    }




}
