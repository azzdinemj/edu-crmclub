package com.wuxue.view.controllers.system;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 日志管理
 */
@Controller
@RequestMapping(value = "/log")
public class LogController {

    @RequestMapping(value = "/userlog")
    public String userLog(){
        return "/system/userlog";
    }

    @RequestMapping(value = "/search",method = RequestMethod.GET)
    public String logSerach(){
        return "/system/userlog";
    }


}
