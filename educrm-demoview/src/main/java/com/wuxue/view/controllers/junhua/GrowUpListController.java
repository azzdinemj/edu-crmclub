package com.wuxue.view.controllers.junhua;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 成长记录
 */
@Controller
@RequestMapping(value = "/junhua/growUpList")
public class GrowUpListController {

    @RequestMapping("/growUpList")
    public String forward(){
     return "/junhua/growUpList";
    }

}
