package com.wuxue.view.controllers.junhua;


import com.wuxue.model.*;
import com.wuxue.utils.common.DataUtils;
import com.wuxue.utils.contract.Light;
import com.wuxue.utils.contract.Response;
import com.wuxue.view.client.student.StudentClient;
import com.wuxue.view.controllers.BaseController;
import com.wuxue.view.interfaces.IEditController;
import com.wuxue.view.interfaces.IQueryController;
import com.wuxue.view.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * 学生管理
 */
@Controller
@RequestMapping(value = "/junhua/studentlist")
public class StudentListController2 extends BaseController  {

    @RequestMapping("/studentlist")
    public String forward(){
     return "/junhua/studentlist";
    }


    @RequestMapping("/edit")
    public String forward2(){
        return "/junhua/student";
    }


    @RequestMapping("/classInfo")
    public String forward3(){
        return "/junhua/classInfo";
    }


}
