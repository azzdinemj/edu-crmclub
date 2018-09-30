package com.wuxue.view.controllers.zhyou.course;

import com.wuxue.model.Linkman;
import com.wuxue.view.controllers.BaseController;
import com.wuxue.view.interfaces.IEditController;
import com.wuxue.view.interfaces.IQueryController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * 类型设置
 */
@Controller
@RequestMapping(value = "/course/courseset")
public class CoursesetController extends BaseController
        implements IQueryController<Linkman,String>,IEditController<Linkman,String> {

    /**
     * 列表页面
     * @param model
     * @return
     */
    @Override
    public String query(HttpServletRequest request, Model model, Linkman linkman)  {
        return "/zhyou/course/coursesetlist";
    }

    @Override
    public String edit(HttpServletRequest request, Model model, Linkman linkman) {
        return "/zhyou/course/courseset";
    }

}
