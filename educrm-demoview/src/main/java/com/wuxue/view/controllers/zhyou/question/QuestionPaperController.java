package com.wuxue.view.controllers.zhyou.question;

import com.github.pagehelper.PageInfo;
import com.wuxue.constant.Contsants;
import com.wuxue.model.Questions;
import com.wuxue.model.QuestionsPaper;
import com.wuxue.model.QuestionsSubject;
import com.wuxue.model.SysMenu;
import com.wuxue.utils.contract.Response;
import com.wuxue.view.client.course.QuestionsPaperClient;
import com.wuxue.view.client.course.QuestionsSubjectClient;
import com.wuxue.view.contract.TreeGridColumn;
import com.wuxue.view.contract.TreeGridModel;
import com.wuxue.view.controllers.BaseController;
import com.wuxue.view.interfaces.IEditController;
import com.wuxue.view.interfaces.IQueryByPagingController;
import com.wuxue.view.interfaces.IQueryController;
import com.wuxue.view.interfaces.ISaveController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 试卷 管理器
 */
@Controller
@RequestMapping(value = "/question/questionPaper")
public class QuestionPaperController extends BaseController
        implements IQueryController<QuestionsPaper,String>,IEditController<QuestionsSubject,String>
        ,ISaveController<QuestionsSubject,String>,IQueryByPagingController<QuestionsSubject,Map<String,Object>> {


    @Autowired
    QuestionsSubjectClient questionsSubjectClient;

    @Autowired
    QuestionsPaperClient questionsPaperClient;

    /**
     * 列表页面
     *
     * @param model
     * @return
     */
    @Override
    public String query(HttpServletRequest request, Model model, QuestionsPaper questions) {
        //页码，每页数量
        String str=request.getParameter("pageNo");
        if(str!=null&&str!=""){
            int pageNo=Integer.parseInt(str);
            questions.setPageNo(pageNo);
        }else{
            questions.setPageNo(Contsants.PAGE_NO);
        }
        questions.setPageSize(Contsants.PAGE_SIZE);

        Response<PageInfo<QuestionsPaper>> response=questionsPaperClient.find(questions);
        PageInfo<QuestionsPaper> pageInfo=response.getData();

        model.addAttribute("questions", pageInfo);

        return "/zhyou/question/questionpaperlist";
    }

    /*
     *查询列表 modal框
     *
     */
    @RequestMapping("/querylist")
    public String querylist(HttpServletRequest request, Model model, SysMenu sysMenu) throws IOException {
        return "/model/questionsSubjectModel";
    }

    //modal框 选择
    @Override
    @ResponseBody
    public Map<String, Object> queryByPaging(HttpServletRequest request, HttpServletResponse response, QuestionsSubject questionsSubject, Integer sEcho, Integer start, Integer length) {
        questionsSubject.setPageNo((start / length) + 1);
        questionsSubject.setPageSize(length);

        Response<List<QuestionsSubject>> listResponse = questionsSubjectClient.find(questionsSubject);
        List<QuestionsSubject> data = listResponse.getData();
        if (data == null) {
            data = new ArrayList<>();
        }
        return convertToPaging(listResponse.getTotal(), listResponse.getTotal(), data);
    }


    @Override
    public String edit(HttpServletRequest request, Model model, QuestionsSubject questionsSubject) {
        if (questionsSubject.getId() != null && questionsSubject.getId() != "") {
            Response<QuestionsSubject> questionsOptionResponse = questionsSubjectClient.findByPrimaryKey(questionsSubject);
            model.addAttribute("questions", questionsOptionResponse.getData());
        }
        return "/zhyou/question/questionpaper";
    }



    @Override
    @ResponseBody
    public String save(HttpServletRequest request, Model model, QuestionsSubject questionsSubject) {
        return questionsSubjectClient.save(questionsSubject);
    }


    //treeGridModel 树形表格
    @RequestMapping(value = "/getData", method = RequestMethod.GET)
    @ResponseBody
    public TreeGridModel<QuestionsSubject> getData() {
        TreeGridModel<QuestionsSubject> treeGridModel = new TreeGridModel<QuestionsSubject>();
        Response<List<QuestionsSubject>> response = questionsSubjectClient.find(new QuestionsSubject());
        List<QuestionsSubject> list = new ArrayList<>();
        for (QuestionsSubject sys : response.getData()) {
            if (sys.getPid() == null || sys.getPid().equals("")) {
                sys.setName("<a class='yellow'   href='/question/questionSubject/edit?id=" + sys.getId() + "'>" + sys.getName() + "</a>");
                getData(response.getData(), sys);
                list.add(sys);
            }
        }
        treeGridModel.setData(list);

        treeGridModel.setRenderTo("div1");
        List<TreeGridColumn> columnList = new ArrayList<>();
        columnList.add(createTreeGridColumn("id", "id"));
        columnList.add(createTreeGridColumn("name", "name"));
        columnList.add(createTreeGridColumn("pid", "pid"));
        columnList.add(createTreeGridColumn("sort", "sort"));
        columnList.add(createTreeGridColumn("status", "status"));

        treeGridModel.setColumns(columnList);
        treeGridModel.setFolderCloseIcon("/images/folderOpen.png");
        treeGridModel.setFolderOpenIcon("/images/folderClose.png");
        treeGridModel.setDefaultLeafIcon("/images/defaultLeaf.gif");

        return treeGridModel;
    }

    //创建 树形表格
    private TreeGridColumn createTreeGridColumn(String headText, String dataField) {
        TreeGridColumn treeGridColumn = new TreeGridColumn();
        treeGridColumn.setHeaderText(headText);
        treeGridColumn.setDataField(dataField);
        return treeGridColumn;
    }


    //获取数据
    private void getData(List<QuestionsSubject> list, QuestionsSubject sys) {
        List<QuestionsSubject> listTmp = new ArrayList<>();
        for (QuestionsSubject sysMenu : list) {
            if (sys.getId().equals(sysMenu.getPid())) {
                sysMenu.setName("<a class='yellow'  href='/question/questionSubject/edit?id=" + sysMenu.getId() + "'>" + sysMenu.getName() + "</a>");
                listTmp.add(sysMenu);
                getData(list, sysMenu);
            }
        }
        if (listTmp.size() > 0) {
            sys.setChildren(listTmp);
        }

    }







}
