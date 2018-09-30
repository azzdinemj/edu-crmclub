package com.wuxue.view.controllers.junhua;

import com.wuxue.model.*;
import com.wuxue.utils.contract.Response;
import com.wuxue.view.client.junhua.JhAnswerClient;
import com.wuxue.view.client.junhua.JhExaminationClient;
import com.wuxue.view.client.junhua.JhQuestionClient;
import com.wuxue.view.controllers.BaseController;
import com.wuxue.view.interfaces.*;
import com.wuxue.view.utils.SessionCache;
import com.wuxue.view.utils.SysDicEmnuUtils;
import com.wuxue.view.utils.SysDictUtils;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.util.*;

/**
 * 个性化课表
 */
@Controller
@RequestMapping(value = "/junhua/jhExamination")
public class JunhuaExaminationController extends BaseController implements IQueryController<JhExamination, String>,
        IQueryByPagingController<JhExamination, Map<String, Object>> ,ICreateController<JhExamination, String>,ISaveController<JhExamination, String>, IEditController<JhExamination, String> {


    @Autowired
    private JhExaminationClient jhExaminationClient;
    @Autowired
    private JhQuestionClient jhQuestionClient;
    @Autowired
    private SysDictUtils sysDictUtils;
    @Autowired
    private JhAnswerClient jhAnswerClient;

    /**
     * 列表页面
     *
     * @param model
     * @return
     * @throws ParseException
     */
    @Override
    public String query(HttpServletRequest request, Model model, JhExamination jhExamination) {

        return "/junhua/JhExaminationList";
    }


    @Override
    @ResponseBody
    public Map<String, Object> queryByPaging(HttpServletRequest request, HttpServletResponse response, JhExamination jhExamination, Integer sEcho, Integer start, Integer length) {

        jhExamination.setPageNo((start / length) + 1);
        jhExamination.setPageSize(length);
        Response<List<JhExamination>> listResponse = jhExaminationClient.find(jhExamination);
        List<JhExamination> data = listResponse.getData();
        if (data == null) {
            data = new ArrayList<>();
        }
        return convertToPaging(listResponse.getTotal(), listResponse.getTotal(), data);


    }
    @Override
    @ResponseBody
    public String save(HttpServletRequest request, Model model,JhExamination jhExamination) throws ParseException {
        return jhExaminationClient.save(jhExamination);
    }

    /**
     * 添加页面
     *
     * @return
     */
    @Override
    public String create(HttpServletRequest request, Model model, JhExamination jhExamination) {
        sysDictUtils.setModeAttribute(model,"objectList", SysDicEmnuUtils.EX_OBJECT);
        Response<List<JhQuestion>> listResponse = jhQuestionClient.findByUser(new JhQuestion());
        List<String> split = new ArrayList<>();
        if(jhExamination.getObject() != null && !jhExamination.getObject().equals("")){
            String[] split1 = jhExamination.getObject().split(",");
            split = Arrays.asList(split1);
        }
        model.addAttribute("questionList",listResponse.getData());
        model.addAttribute("jhExamination",jhExamination);
        model.addAttribute("split",split);
        model.addAttribute("quesionAddType",0);
        return "/junhua/questionnaire";
    }

    /**
     * 修改页面
     *
     * @param jhExamination
     * @param model
     * @return
     */
    @Override
    public String edit(HttpServletRequest request, Model model, JhExamination jhExamination) {
        sysDictUtils.setModeAttribute(model,"objectList", SysDicEmnuUtils.EX_OBJECT);
        Response<JhExamination> byPrimaryKey = jhExaminationClient.findByPrimaryKey(jhExamination);
        jhExamination = byPrimaryKey.getData();
        JhQuestion jhQuestion = new JhQuestion();
        Response<List<JhQuestion>> listResponse = jhQuestionClient.find(jhQuestion);
        List<JhQuestion> data = listResponse.getData();
        jhQuestion.setPkExamination(jhExamination.getPkExamination());
        Response<List<JhQuestion>> byUser = jhQuestionClient.findByUser(jhQuestion);
        if (byUser.getData().size() > 0) {
            for (JhQuestion question : byUser.getData()) {
                if(!data.contains(question)){
                    data.add(question);
                }
            }
        }
        ArrayList<JhQuestion> jhQuestions = removeDuplicteUsers(data);
        List<String> split = new ArrayList<>();
        if(jhExamination.getObject() != null && !jhExamination.getObject().equals("")){
            String[] split1 = jhExamination.getObject().split(",");
            split = Arrays.asList(split1);
        }
        model.addAttribute("questionList",jhQuestions);
        model.addAttribute("jhExamination",jhExamination);
        model.addAttribute("split",split);
        model.addAttribute("quesionAddType",1);
        return "/junhua/questionnaire";
    }

    /**
     * 将列表中重复的用户移除，重复指的是pk相同
     *
     * @param userList
     * @return
     */
    public static ArrayList<JhQuestion> removeDuplicteUsers(List<JhQuestion> userList) {
        Set<JhQuestion> s = new TreeSet<JhQuestion>(new Comparator<JhQuestion>() {

            @Override
            public int compare(JhQuestion o1, JhQuestion o2) {
                return o1.getPkQuestion().compareTo(o2.getPkQuestion());
            }
        });
        s.addAll(userList);
        return new ArrayList<JhQuestion>(s);
    }

    @RequestMapping(value = "/questionAdd",method = RequestMethod.GET)
    public String questionAdd(HttpServletRequest request, Model model, JhExamination jhExamination){
        String quesionAddType = request.getParameter("quesionAddType");
        model.addAttribute("quesionAddType",quesionAddType);
        model.addAttribute("jhExamination",jhExamination);
        return "/junhua/addQuestion";
    }

    @RequestMapping(value = "/questionDelete",method = RequestMethod.GET)
    public String questionDelete(HttpServletRequest request, Model model,JhQuestion jhQuestion){
//        String number = request.getParameter("number");
//        List<JhQuestion> list = new ArrayList<>();
//        if (!SessionCache.getQuestionList().equals("")) {
//            list = (List<JhQuestion>)JSONArray.toList(JSONArray.fromObject(SessionCache.getQuestionList()), JhQuestion.class);
//        }
//        list.remove(Integer.valueOf(number)-1);
//        String str= JSONArray.fromObject(list).toString();
//        SessionCache.setQuestionList(str);

        String caption = request.getParameter("caption");
        String datas = request.getParameter("datas");
        String section = request.getParameter("section");
        String object = request.getParameter("object");
        jhQuestionClient.delete(jhQuestion);
        return "redirect:/junhua/jhExamination/create?caption="+caption+"&datas="+datas+"&section="+section+"&object="+object;
    }

    @RequestMapping(value = "/questionSave",method = RequestMethod.POST)
    @ResponseBody
    public String questionSave(HttpServletRequest request, Model model,String requestData,String jhOptionList) throws IOException, ServletException {
        Response response = Response.newResponse();
        JhQuestion jhQuestion;
        JSONObject jsonObject= JSONObject.fromObject(requestData);
        jhQuestion=(JhQuestion)JSONObject.toBean(jsonObject, JhQuestion.class);
        List<JhOption> jhOptions=(List<JhOption>)JSONArray.toList(JSONArray.fromObject(jhOptionList), JhOption.class);
        jhQuestion.setJhOptionList(jhOptions);


//        List<JhQuestion> list = new ArrayList<>();
//        if (!SessionCache.getQuestionList().equals("")) {
//            list = (List<JhQuestion>)JSONArray.toList(JSONArray.fromObject(SessionCache.getQuestionList()), JhQuestion.class);
//        }
//        list.add(jhQuestion);
//        String str= JSONArray.fromObject(list).toString();
//        SessionCache.setQuestionList(str);

        return jhQuestionClient.save(jhQuestion);
    }

    @RequestMapping(value = "/statistics",method = RequestMethod.GET)
    public String statistics(HttpServletRequest request, Model model, JhQuestion jhQuestion){
//        jhQuestion.setPkExamination("1");
        Response<List<JhQuestion>> listResponse = jhExaminationClient.statistics(jhQuestion);
        List<JhQuestion> data = listResponse.getData();
//        List<JhQuestion> otherList = new ArrayList<>();
//        List<JhOption> jhOptionList = new ArrayList<>();

//        if(data != null && data.size() > 0){
//            for (JhQuestion question : data) {
////                选项listadd
//                if(question.getJhOptionList() != null && question.getJhOptionList().size() > 0){
//                    for (JhOption jhOption : question.getJhOptionList()) {
//                        jhOptionList.add(jhOption);
//                    }
//                }
////                其他listadd
//                if(question.getOtherList() != null && question.getOtherList().size() > 0){
//                    for (JhQuestion jhQuestion1 : question.getOtherList()) {
//                        otherList.add(jhQuestion1);
//                    }
//                }
//            }
//        }
        model.addAttribute("jhQuestionList",data);
//        model.addAttribute("jhOptionList",jhOptionList);
//        model.addAttribute("otherList",otherList);
        return "/junhua/question_results";
    }


    @RequestMapping(value = "/getNumberList",method = RequestMethod.GET)
    public String getNumberList(HttpServletRequest request, Model model, JhExamination jhExamination){
        model.addAttribute("pkExamination",jhExamination.getPkExamination());
        return "/junhua/JhNumberList";
    }


    @ResponseBody
    @RequestMapping(value = "/findAnswerList",method = RequestMethod.GET)
    public Map<String, Object> findAnswerList(HttpServletRequest request, HttpServletResponse response, JhAnswer jhAnswer, Integer sEcho, Integer start, Integer length) {

        jhAnswer.setPageNo((start / length) + 1);
        jhAnswer.setPageSize(length);
        Response<List<JhAnswer>> listResponse = jhAnswerClient.find(jhAnswer);
        List<JhAnswer> data = listResponse.getData();
        if (data == null) {
            data = new ArrayList<>();
        }
        return convertToPaging(listResponse.getTotal(), listResponse.getTotal(), data);


    }


    @RequestMapping(value = "/getAnswerList",method = RequestMethod.GET)
    public String getAnswerList(HttpServletRequest request, Model model, JhAnswer jhAnswer){
        model.addAttribute("jhAnswer",jhAnswer);
        return "/model/answerlistModel";
    }

    @ResponseBody
    @RequestMapping(value = "/queryAnswerList",method = RequestMethod.GET)
    public Map<String, Object> queryAnswerList(HttpServletRequest request, HttpServletResponse response, JhAnswer jhAnswer, Integer sEcho, Integer start, Integer length) {

        jhAnswer.setPageNo((start / length) + 1);
        jhAnswer.setPageSize(length);
        Response<List<JhAnswer>> listResponse = jhAnswerClient.queryAnswerList(jhAnswer);
        List<JhAnswer> data = listResponse.getData();
        if (data == null) {
            data = new ArrayList<>();
        }
        return convertToPaging(listResponse.getTotal(), listResponse.getTotal(), data);


    }
}
