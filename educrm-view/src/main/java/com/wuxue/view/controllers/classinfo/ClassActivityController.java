package com.wuxue.view.controllers.classinfo;

import com.wuxue.model.junhwa.ClassActivity;
import com.wuxue.utils.contract.Response;
import com.wuxue.view.client.activity.ClassActivityClient;
import com.wuxue.view.controllers.BaseController;
import com.wuxue.view.interfaces.*;
import com.wuxue.view.utils.DateTimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 班级升班
 */

@Controller
@RequestMapping(value = "/classinfo/classActivity")
public class ClassActivityController extends BaseController
        implements IQueryController<ClassActivity, String>, ISaveController<ClassActivity, String>,IQueryByPagingController<ClassActivity,Map<String,Object>>,
        ICreateController<ClassActivity, String>, IEditController<ClassActivity, String>, IDeleteController<ClassActivity, String> {

    @Autowired
    private ClassActivityClient classActivityClient;

    @InitBinder("classActivity")
    public void initBinder(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("act.");
    }

    /**
     * 班级活动列表页面
     *
     * @param model
     * @return
     * @throws java.text.ParseException
     */
    @Override
    public String query(HttpServletRequest request, Model model, ClassActivity classActivity) {
        /*Response<List<Domain>> listResponse = sysUserClient.find(Domain);

        model.addAttribute("list",listResponse.getData() );*/
        return "/classActivity/classOnList";
    }

    @Override
    @ResponseBody
    public Map<String, Object> queryByPaging(HttpServletRequest request, HttpServletResponse response, ClassActivity classActivity, Integer sEcho, Integer start, Integer length) {
        classActivity.setPageNo((start/length)+1);
        classActivity.setPageSize(length);

        Response<List<ClassActivity>> listResponse = classActivityClient.find(classActivity);
        List<ClassActivity> data = listResponse.getData();
        if(data==null) {
            data = new ArrayList<>();
        }
        return convertToPaging(listResponse.getTotal(),listResponse.getTotal(),data);
    }

    /**
     * 修改页面
     *
     * @param classActivity
     * @param model
     * @return
     */
    @Override
    public String edit(HttpServletRequest request, Model model, ClassActivity classActivity) {
        Response<ClassActivity> byPrimaryKey = classActivityClient.findClassActivityInfoById(classActivity);
        model.addAttribute("classActivity", byPrimaryKey.getData());
        return "/classinfo/clsasActive";
    }


    /**
     * 保存
     *
     * @param classActivity
     * @param request
     * @return
     */
    @Override
    @ResponseBody
    public String save(HttpServletRequest request, Model model, ClassActivity classActivity) {
        classActivity.setActivityTime(DateTimeUtils.stringToDate(classActivity.getActivityTimes()));


        String response = classActivityClient.save(classActivity);
        return response;

    }

    /**
     * 添加页面
     *
     * @return
     */
    @Override
    public String create(HttpServletRequest request, Model model, ClassActivity classActivity) {

        model.addAttribute("classActivity",classActivity);


        return "/classinfo/clsasActive";
    }

    /**
     * 删除
     *
     * @param classActivity
     * @return
     */
    @Override
    @ResponseBody
    public String delete(HttpServletRequest request, Model model, ClassActivity classActivity) {
        String response = classActivityClient.delete(classActivity.getPkClassinfo());
        return response;
    }


}
