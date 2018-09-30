package com.wuxue.view.controllers.zhyou.course;

import com.github.pagehelper.PageInfo;
import com.wuxue.constant.Contsants;
import com.wuxue.model.Course;
import com.wuxue.model.CourseEvaluate;
import com.wuxue.model.Linkman;
import com.wuxue.utils.contract.Response;
import com.wuxue.view.client.course.CourseTalkClient;
import com.wuxue.view.controllers.BaseController;
import com.wuxue.view.interfaces.IEditController;
import com.wuxue.view.interfaces.IQueryController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.soap.Addressing;

/**
 * 课程评论控制器
 */
@Controller
@RequestMapping(value = "/course/coursetalk")
public class CoursetalkController extends BaseController
        implements IQueryController<CourseEvaluate,String>,IEditController<CourseEvaluate,String> {

    @Autowired
   private CourseTalkClient courseTalkClient;

    /**
     * 课程评论列表页面
     * @param model
     * @return
     */
    @Override
    public String query(HttpServletRequest request, Model model, CourseEvaluate courseEvaluate)  {

        //页码，每页数量
        String str=request.getParameter("pageNo");
        if(str!=null&&str!=""){
            int pageNo=Integer.parseInt(str);
            courseEvaluate.setPageNo(pageNo);
        }else{
            courseEvaluate.setPageNo(Contsants.PAGE_NO);
        }
        courseEvaluate.setPageSize(Contsants.PAGE_SIZE);

        Response<PageInfo<CourseEvaluate>> response=courseTalkClient.find(courseEvaluate);
        PageInfo<CourseEvaluate> pageInfo=response.getData();
        model.addAttribute("coursetalk", pageInfo);
        return "/zhyou/course/coursetalklist";
    }

    @Override
    public String edit(HttpServletRequest request, Model model, CourseEvaluate courseEvaluate) {
        return "/zhyou/course/coursetalk";
    }
}
