package com.wuxue.view.controllers.question;

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
import com.wuxue.view.interfaces.IEditController;
import com.wuxue.view.interfaces.IQueryByPagingController;
import com.wuxue.view.interfaces.IQueryController;
import com.wuxue.view.interfaces.ISaveController;
import com.wuxue.view.utils.SessionCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.SocketUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * 管理 题目
 */
@Controller
@RequestMapping(value = "/guanqiao/questionItem")
public class QuestionItemController extends BaseController
        implements IQueryController<QuestionsItem,String>,IEditController<QuestionsItem,String>
        ,ISaveController<QuestionsItem,String>,IQueryByPagingController<QuestionsItem,Map<String,Object>>{

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
           return "questionhtml/questionitemlist";
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

    /**
     * 编辑、添加页面跳转
     * @param request
     * @param model
     * @param questionsItem
     * @return
     */
    @RequestMapping("editoraddhtml")
    public String editoraddhtml(HttpServletRequest request, Model model,QuestionsItem questionsItem){
        return "questionhtml/questionitem";
    }

    @Override
    public String edit(HttpServletRequest request, Model model, QuestionsItem questionsOption) {

        SysDictValues sysDictValues=new SysDictValues();
        sysDictValues.setPkSysDict("zhyquestions");
        Response<List<SysDictValues>> listResponse= sysDictValuesClient.find(sysDictValues);
        model.addAttribute("SysDictValues",listResponse.getData());

        if(questionsOption.getId()!=null&&questionsOption.getId()!=""){
          Response<QuestionsItem> questionsOptionResponse=questionsItemClient.findByPrimaryKey(questionsOption);
          model.addAttribute("questions",questionsOptionResponse.getData());
         }
        return "/zhyou/question/questionitem";
    }

    @RequestMapping("findbypk")
    @ResponseBody
    public Response findByPk(QuestionsItem request){
        return  questionsItemClient.findByPrimaryKey(request);
    }

    /**
     * save
     * @param request
     * @param model
     * @param questionsItem
     * @return
     */
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

        if(strtype==3){
            String r3= request.getParameter("result3");//判断的答案
            questionsItem.setAnswer(r3);
        }

        return questionsItemClient.save(questionsItem);
    }



    /**
     * 前台---学生端
     *
     * pkSysdictValues       储存的课程类型                SysDictValues表
     * questionsSubjectId    课程类型下关联的课程           Product表
     *
     *
     * 根据条件组合试题列表（查重，已经做过的不显示）
     * @param pkStudent
     * @param questionsItem        questionsSubjectId   pkSysDictValues
     * @param  request   checkbox1  input1   checkbox2  input2
     * @return
     */
    @RequestMapping("/finditemby")
    @ResponseBody
    public Response findItemBy(HttpServletRequest request, Model model,QuestionsItem questionsItem,String  pkStudent)  {
        Response resp=Response.newResponse();

        Response<PageInfo<QuestionsItem>> response=new Response<>();
        List<QuestionsItem> itemList;

        String type1=request.getParameter("checkbox1");//选择
        String type2=request.getParameter("checkbox2");//判断

        String input1=request.getParameter("input1");//选择题数量
        String input2=request.getParameter("input2");//判断题数量

        questionsItem.setPkStudent(pkStudent); //登陆的学生主键,去重条件
        questionsItem.setPageNo(1);
        questionsItem.setPageSize(1000);
        List<QuestionsItem> listSelect=new ArrayList<>();  //选择题集合
        if(type1!=null&&input1!=null){
            questionsItem.setType(Short.parseShort(type1));
            response= questionsItemClient.find(questionsItem);
            itemList=response.getData().getList();

            int  num=Integer.parseInt(input1);
            if(itemList.size()>0){
                if(itemList.size()<=num){ //输入的数量大于总数则不随机抽取直接返回
                    for (QuestionsItem q :itemList) {
                        listSelect.add(q);
                    }
                }else{ // 随机抽题
                    HashSet<Integer> hs=getramdomArr(num,itemList.size());
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
            response= questionsItemClient.find(questionsItem);
            itemList=response.getData().getList();

            int  num=Integer.parseInt(input2);
            if(itemList.size()>0){
                if(itemList.size()<=num){
                    for (QuestionsItem q :itemList) {
                        listISNot.add(q);
                    }
                }else{// 随机抽题
                    HashSet<Integer> hs=getramdomArr(Integer.parseInt(input2),itemList.size());
                    Iterator<Integer> iterator=hs.iterator();
                    while(iterator.hasNext()){
                        listISNot.add(itemList.get(iterator.next()));
                    }
                }
            }
        }

        Map<String,Object> map=new HashMap<>();

        if(type1==null||type1.equals("")){
           // map.put("listSelect",1);//选择题集合
        }else{
            map.put("listSelect",listSelect);//选择题集合
        }

        if(type2==null||type2.equals("")){
            //map.put("listIsNot","");//判断题集合
        }else{
            map.put("listIsNot",listISNot);//判断题集合
        }
        resp.setData(map);
        return  resp;
    }


    //获取随机数组   param1 数量   param2 范围
    public static HashSet<Integer> getramdomArr(Integer size,Integer allsize) {
        HashSet<Integer> hs = new HashSet<Integer>();
        int intArray[] =new int[size];
        while (true) {
            int a = (int)(Math.random() * allsize);
            if(a >= 0 && a <= allsize) {
                hs.add(a);
            }
            if (hs.size() == size) {
                break;
            }
        }
        return hs;
    }



}
