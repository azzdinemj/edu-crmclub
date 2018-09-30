package com.wuxue.view.controllers.junhua;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 接送管理
 */
@Controller
@RequestMapping(value = "/junhua/pickUp")
public class PickUpController {

    @RequestMapping("/pickUp")
    public String forward(){
     return "/junhua/pickUp";
    }

}
