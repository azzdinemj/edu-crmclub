package com.wuxue.view.controllers.zhyou.question;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wuxue.model.QuestionsProject;
import com.wuxue.model.QuestionsSubject;
import com.wuxue.model.SysMenu;
import com.wuxue.utils.contract.Response;
import com.wuxue.view.client.course.QuestionsProjectClient;
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
 * 管理
 */
@Controller
@RequestMapping(value = "/question/questionSubject")
public class QuestionSubjectController extends BaseController
        implements IQueryController<QuestionsSubject,String>,IEditController<QuestionsSubject,String>
        ,ISaveController<QuestionsSubject,String>,IQueryByPagingController<QuestionsProject,Map<String,Object>> {


    @Autowired
    QuestionsSubjectClient questionsSubjectClient;

    @Autowired
    QuestionsProjectClient questionsProjectClient;

    /**
     * 列表页面
     *
     * @param model
     * @return
     */
    @Override
    public String query(HttpServletRequest request, Model model, QuestionsSubject questions) {
        return "/zhyou/question/questionsubjectlist";
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
//    @Override
//    @ResponseBody
//    public Map<String, Object> queryByPaging(HttpServletRequest request, HttpServletResponse response, QuestionsSubject questionsSubject, Integer sEcho, Integer start, Integer length) {
//        questionsSubject.setPageNo((start / length) + 1);
//        questionsSubject.setPageSize(length);
//
//        Response<List<QuestionsSubject>> listResponse = questionsSubjectClient.find(questionsSubject);
//        List<QuestionsSubject> data = listResponse.getData();
//        if (data == null) {
//            data = new ArrayList<>();
//        }
//        return convertToPaging(listResponse.getTotal(), listResponse.getTotal(), data);
//    }

    //modal框 选择 科目学段
    @Override
    @ResponseBody
    public Map<String, Object> queryByPaging(HttpServletRequest request, HttpServletResponse response, QuestionsProject questionsProject, Integer sEcho, Integer start, Integer length) {
        questionsProject.setPageNo((start / length) + 1);
        questionsProject.setPageSize(length);
        questionsProject.setType(2);

        Response<List<QuestionsProject>> listResponse = questionsProjectClient.find(questionsProject);
        List<QuestionsProject> data = listResponse.getData();
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
        return "/zhyou/question/questionsubject";
    }

    @Override
    @ResponseBody
    public String save(HttpServletRequest request, Model model, QuestionsSubject questionsSubject) {
        return questionsSubjectClient.save(questionsSubject);
    }


    //创建 树形表格
    private TreeGridColumn createTreeGridColumn(String headText, String dataField) {
        TreeGridColumn treeGridColumn = new TreeGridColumn();
        treeGridColumn.setHeaderText(headText);
        treeGridColumn.setDataField(dataField);
        return treeGridColumn;
    }


    //treeGridModel 树形表格
//    @RequestMapping(value = "/getData", method = RequestMethod.GET)
//    @ResponseBody
//    public TreeGridModel<QuestionsSubject> getData() {
//        TreeGridModel<QuestionsSubject> treeGridModel = new TreeGridModel<QuestionsSubject>();
//        Response<List<QuestionsSubject>> response = questionsSubjectClient.find(new QuestionsSubject());
//        List<QuestionsSubject> list = new ArrayList<>();
//        for (QuestionsSubject sys : response.getData()) {
//            if (sys.getPid() == null || sys.getPid().equals("")) {
//                sys.setName("<a class='yellow'   href='/question/questionSubject/edit?id=" + sys.getId() + "'>" + sys.getName() + "</a>");
//                getData(response.getData(), sys);
//                list.add(sys);
//            }
//        }
//        treeGridModel.setData(list);
//
//        treeGridModel.setRenderTo("div1");
//        List<TreeGridColumn> columnList = new ArrayList<>();
//        columnList.add(createTreeGridColumn("id", "id"));
//        columnList.add(createTreeGridColumn("name", "name"));
//        columnList.add(createTreeGridColumn("pid", "pid"));
//        columnList.add(createTreeGridColumn("sort", "sort"));
//        columnList.add(createTreeGridColumn("status", "status"));
//
//        treeGridModel.setColumns(columnList);
//        treeGridModel.setFolderCloseIcon("/images/folderOpen.png");
//        treeGridModel.setFolderOpenIcon("/images/folderClose.png");
//        treeGridModel.setDefaultLeafIcon("/images/defaultLeaf.gif");
//
//        return treeGridModel;
//    }
//
//
//    //获取数据
//    private void getData(List<QuestionsSubject> list, QuestionsSubject sys) {
//        List<QuestionsSubject> listTmp = new ArrayList<>();
//        for (QuestionsSubject sysMenu : list) {
//            if (sys.getId().equals(sysMenu.getPid())) {
//                sysMenu.setName("<a class='yellow'  href='/question/questionSubject/edit?id=" + sysMenu.getId() + "'>" + sysMenu.getName() + "</a>");
//                listTmp.add(sysMenu);
//                getData(list, sysMenu);
//            }
//        }
//        if (listTmp.size() > 0) {
//            sys.setChildren(listTmp);
//        }
//
//    }

        //获取数据
    private void getDataP(List<QuestionsSubject> list, QuestionsSubject sys) {
        List<QuestionsSubject> listTmp = new ArrayList<>();
        for (QuestionsSubject sysMenu : list) {
            if (sys.getId().equals(sysMenu.getPid())) {
                sysMenu.setName("<a class='yellow'  href='/question/questionSubject/edit?id=" + sysMenu.getId() + "'>" + sysMenu.getName() + "</a>");
                listTmp.add(sysMenu);
                getDataP(list, sysMenu);
            }
        }
        if (listTmp.size() > 0) {
            sys.setChildren(listTmp);
        }

    }




    @RequestMapping(value = "/getData", method = RequestMethod.GET)
    @ResponseBody
    public TreeGridModel<QuestionsProject> getData() {
        TreeGridModel<QuestionsProject> treeGridModel = new TreeGridModel<QuestionsProject>();
        Response<List<QuestionsProject>> response = questionsProjectClient.find(new QuestionsProject());
        List<QuestionsProject> list = new ArrayList<>();
        for (QuestionsProject sys : response.getData()) {
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
//        columnList.add(createTreeGridColumn("sort", "sort"));
//        columnList.add(createTreeGridColumn("status", "status"));

        treeGridModel.setColumns(columnList);
        treeGridModel.setFolderCloseIcon("/images/folderOpen.png");
        treeGridModel.setFolderOpenIcon("/images/folderClose.png");
        treeGridModel.setDefaultLeafIcon("/images/defaultLeaf.gif");

        return treeGridModel;
    }


    //获取数据
    private void getData(List<QuestionsProject> list, QuestionsProject sys) {
        List<QuestionsProject> listTmp = new ArrayList<>();
        for (QuestionsProject sysMenu : list) {
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
