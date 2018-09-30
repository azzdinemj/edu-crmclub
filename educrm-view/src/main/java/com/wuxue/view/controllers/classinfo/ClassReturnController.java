package com.wuxue.view.controllers.classinfo;

import com.wuxue.model.Classinfo;
import com.wuxue.utils.contract.Response;
import com.wuxue.view.client.classInfo.ClassInfoClient;
import com.wuxue.view.controllers.BaseController;
import com.wuxue.view.interfaces.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 学生转班
 */

@Controller
@RequestMapping(value = "/classinfo/classreturn")
public class ClassReturnController extends BaseController
        implements IQueryController<Classinfo, String>, ISaveController<Classinfo, String>,
        ICreateController<Classinfo, String>, IEditController<Classinfo, String>, IDeleteController<Classinfo, String> {

    @Autowired
    private ClassInfoClient classinfoClient;

    @InitBinder("classinfo")
    public void initBinder(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("clas.");
    }

    /**
     * 班级列表页面
     *
     * @param model
     * @return
     * @throws java.text.ParseException
     */
    @Override
    public String query(HttpServletRequest request, Model model, Classinfo classinfo) {
        /*Response<List<Domain>> listResponse = sysUserClient.find(Domain);

        model.addAttribute("list",listResponse.getData() );*/
        return "/classinfo/classturnList";
    }

    /**
     * 修改页面
     *
     * @param classinfo
     * @param model
     * @return
     */
    @Override
    public String edit(HttpServletRequest request, Model model, Classinfo classinfo) {
        Response<Classinfo> byPrimaryKey = classinfoClient.findByPrimaryKey(classinfo);
        model.addAttribute("classinfo", byPrimaryKey.getData());
        return "/classinfo/editCalssReturnStu";
    }


    /**
     * 保存
     *
     * @param classinfo
     * @param request
     * @return
     */
    @Override
    @ResponseBody
    public String save(HttpServletRequest request, Model model, Classinfo classinfo) {
        String response = classinfoClient.save(classinfo);
        return response;

    }

    /**
     * 添加页面
     *
     * @return
     */
    @Override
    public String create(HttpServletRequest request, Model model, Classinfo classinfo) {
        return "/classinfo/addClassReturn";
    }

    /**
     * 删除
     *
     * @param classinfo
     * @return
     */
    @Override
    @ResponseBody
    public String delete(HttpServletRequest request, Model model, Classinfo classinfo) {
        String response = classinfoClient.delete(classinfo.getPkClassinfo());
        return response;
    }


}
