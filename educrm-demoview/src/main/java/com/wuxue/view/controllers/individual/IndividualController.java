package com.wuxue.view.controllers.individual;

import com.wuxue.model.Linkman;
import com.wuxue.model.SysUser;
import com.wuxue.utils.contract.Response;
import com.wuxue.view.client.student.LinkManClient;
import com.wuxue.view.client.system.SysUserClient;
import com.wuxue.view.controllers.BaseController;
import com.wuxue.view.interfaces.*;
import com.wuxue.view.utils.SessionCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 管理
 */
@Controller
@RequestMapping(value = "/individual/individual")
public class IndividualController extends BaseController
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

        return "/individual/individualList";
    }
    @RequestMapping(value = "/edit")
    public String edit(HttpServletRequest request, Model model, Linkman linkman)  {

        return "/individual/individual";
    }






}
