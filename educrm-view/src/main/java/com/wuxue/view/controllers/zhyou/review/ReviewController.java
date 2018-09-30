package com.wuxue.view.controllers.zhyou.review;

import com.github.pagehelper.PageInfo;
import com.wuxue.model.Course;
import com.wuxue.model.Review;
import com.wuxue.model.StudentAssign;
import com.wuxue.utils.contract.Light;
import com.wuxue.utils.contract.Response;
import com.wuxue.view.client.course.CourseClient;
import com.wuxue.view.client.course.ReviewClient;
import com.wuxue.view.controllers.BaseController;
import com.wuxue.view.interfaces.IQueryByPagingController;
import com.wuxue.view.interfaces.IQueryController;
import com.wuxue.view.utils.SessionCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 审核管理
 */
@Controller
@RequestMapping(value = "/zhyou/review")
public class ReviewController extends BaseController implements IQueryByPagingController<Review,Map<String,Object>>,
        IQueryController<Review,String>{

    @Autowired
    ReviewClient reviewClient;
    @Autowired
    CourseClient courseClient;

    /**
     * 审核列表页面
     * @param model
     * @return
     */
    @Override
    public String query(HttpServletRequest request, Model model, Review review)  {
//        //页码，每页数量
//        String str=request.getParameter("pageNo");
//        if(str!=null&&str!=""){
//            int pageNo=Integer.parseInt(str);
//            review.setPageNo(pageNo);
//        }else{
//            review.setPageNo(Contsants.PAGE_NO);
//        }
//        review.setPageSize(Contsants.PAGE_SIZE);
//
//        Response<PageInfo<Review>> response=reviewClient.find(review);
//        PageInfo<Review> pageInfo=response.getData();
//
//        model.addAttribute("review", pageInfo);
        return "/zhyou/review/reviewlist";
    }

    @Override
    @ResponseBody
    public Map<String, Object> queryByPaging(HttpServletRequest request, HttpServletResponse response, Review review, Integer sEcho, Integer start, Integer length) {
        review.setPageNo((start/length)+1);
        review.setPageSize(length);

        Response<PageInfo<Review>> resp=reviewClient.find(review);
        List<Review> data = resp.getData().getList();
        if(data==null) {
            data = new ArrayList<>();
        }
        return convertToPaging(resp.getData().getTotal(),resp.getData().getTotal(),data);
    }



    @RequestMapping(value = "/updateReview",method = RequestMethod.POST)
    @ResponseBody
    public Response updateReview(HttpServletRequest request, Model model, Review review) {
        review.setModifier(SessionCache.getUserCode());
        Response<Review> reviewResponse = reviewClient.audit(review);
        return reviewResponse;
    }

    @RequestMapping(value = "/courseReview",method = RequestMethod.GET)
    public String updateReview(HttpServletRequest request, Model model, Course course) {
        Response<Course> response=courseClient.findByPrimaryKey(course);
        Course courseRemake=response.getData();
        //课程对象
        model.addAttribute("course1",courseRemake);
        Map<String,Object> objectMap=courseRemake.getMap();
        //课程目录
        model.addAttribute("lessonchapter",objectMap.get(Light.COURSE_LESSON_CHAPTER));
        //课程学生
        model.addAttribute("student",objectMap.get(Light.COURSE_STUDENT));
        //课程评价
        model.addAttribute("evaluate",objectMap.get(Light.COURSE_EVALUATE));

        return "/zhyou/course/courseReview";
    }

    /**
     * 根据pkCourse查询所有章节
     * @param request
     * @param model
     * @param course
     * @return
     */
    @RequestMapping("/queryclass")
    public String queryclass(HttpServletRequest request, Model model, Course course){
        String strPk=request.getParameter("pkCourse");//课程主键
        String strclPk=request.getParameter("pkCourseLesson");//节主键
        if (strPk!=null&&strPk!=""&&strclPk!=""&&strclPk!=null){

            // 课程、节主键都不为空则为播放视频。点击量加1操作。
            Response<Course> response=courseClient.findByPrimaryKey(course);
            Course course1=response.getData();
            if(course1.getNum()==null||course1.getNum()==0){
                course1.setNum(1);
            }else{
                course1.setNum(course1.getNum()+1);
            }
            courseClient.save(course1);
        }
        //若有主键为查询详情
        if (strPk!=null&&strPk!=""){
            Response<Course> response=courseClient.findByPrimaryKey(course);
            Course course1=response.getData();
            //课程对象
            //model.addAttribute("course1",course1);
            Map<String,Object> objectMap=course1.getMap();
            //课程章节对象
            model.addAttribute("lessonchapter",objectMap.get(Light.COURSE_LESSON_CHAPTER));
            model.addAttribute("pkCouLesson",strclPk);
        }
        return  "/zhyou/course/have_class";
    }
}
