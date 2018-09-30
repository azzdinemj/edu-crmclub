package com.wuxue.view.controllers.individual;

import com.wuxue.model.Linkman;
import com.wuxue.view.client.student.LinkManClient;
import com.wuxue.view.client.system.SysUserClient;
import com.wuxue.view.controllers.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * 管理
 */
@Controller
@RequestMapping(value = "/individual/individualstudent")
public class IndividualStudentController extends BaseController
        {


    @InitBinder("linkman")
    public void initBinder(WebDataBinder binder){
        binder.setFieldDefaultPrefix("man.");
    }

    @Autowired
    private LinkManClient linkmanClient;
    @Autowired
    private SysUserClient sysUserClient;

    /**
     * 列表页面
     * @param model
     * @return
     * @throws java.text.ParseException
     */
    @RequestMapping(value = "/query")
    public String query(HttpServletRequest request, Model model, Linkman linkman)  {

        return "/individual/indexList";
    }
    @RequestMapping(value = "/edit")
    public String edit(HttpServletRequest request, Model model, Linkman linkman)  {

        return "/individual/index";
    }






}
