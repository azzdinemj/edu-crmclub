package com.wuxue.view.controllers.zhyou.lecturer;

import com.wuxue.model.Linkman;
import com.wuxue.view.controllers.BaseController;
import com.wuxue.view.interfaces.IEditController;
import com.wuxue.view.interfaces.IQueryController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * 管理
 */
@Controller
@RequestMapping(value = "/lecturer/lecturerlist")
public class LecturerListController extends BaseController
        implements IQueryController<Linkman,String>,IEditController<Linkman,String> {

    /**
     * 列表页面
     * @param model
     * @return
     */
    @Override
    public String query(HttpServletRequest request, Model model, Linkman linkman)  {
        return "/zhyou/lecturer/lecturerList";
    }

    @Override
    public String edit(HttpServletRequest request, Model model, Linkman linkman) {
        return "/zhyou/lecturer/lecturer";
    }
}
