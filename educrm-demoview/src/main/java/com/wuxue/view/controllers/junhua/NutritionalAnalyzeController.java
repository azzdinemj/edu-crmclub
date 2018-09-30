package com.wuxue.view.controllers.junhua;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 营养分析
 */
@Controller
@RequestMapping(value = "/junhua/nutritionalAnalyze")
public class NutritionalAnalyzeController {

    @RequestMapping("/nutritionalAnalyze")
    public String forward(){
     return "/junhua/nutritionalAnalyze";
    }

}
