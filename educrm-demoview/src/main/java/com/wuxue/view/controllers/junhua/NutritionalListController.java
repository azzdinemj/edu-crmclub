package com.wuxue.view.controllers.junhua;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 营养分析列表
 */
@Controller
@RequestMapping(value = "/junhua/nutritionalList")
public class NutritionalListController {

    @RequestMapping("/nutritionalList")
    public String forward(){
     return "/junhua/nutritionalList";
    }

}
