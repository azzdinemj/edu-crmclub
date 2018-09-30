package com.wuxue.view.controllers.schedule;

import com.wuxue.model.Linkman;
import com.wuxue.view.controllers.BaseController;
import com.wuxue.view.interfaces.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * 管理
 */
@Controller
@RequestMapping(value = "/schedule/arrnging")
public class ArrngingController extends BaseController
        implements IQueryController<Linkman,String>{

    /**
     * 列表页面
     * @param model
     * @return
     */
    @Override
    public String query(HttpServletRequest request, Model model, Linkman linkman)  {
        return "/schedule/arrangingList";
    }

}
