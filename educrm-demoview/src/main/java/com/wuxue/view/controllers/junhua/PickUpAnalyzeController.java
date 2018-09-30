package com.wuxue.view.controllers.junhua;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 接送分析
 */
@Controller
@RequestMapping(value = "/junhua/pickUpAnalyze")
public class PickUpAnalyzeController {

    @RequestMapping("/pickUpAnalyze")
    public String forward(){
     return "/junhua/pickUpAnalyze";
    }

}
