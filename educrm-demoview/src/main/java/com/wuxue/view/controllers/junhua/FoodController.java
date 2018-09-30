package com.wuxue.view.controllers.junhua;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 选餐记录
 */
@Controller
@RequestMapping(value = "/junhua/food")
public class FoodController {

    @RequestMapping("/food")
    public String forward(){
     return "/junhua/food";
    }

}
