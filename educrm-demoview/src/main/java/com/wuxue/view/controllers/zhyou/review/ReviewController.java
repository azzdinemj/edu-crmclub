package com.wuxue.view.controllers.zhyou.review;

import com.github.pagehelper.PageInfo;
import com.wuxue.constant.Contsants;
import com.wuxue.model.Review;
import com.wuxue.utils.contract.Response;
import com.wuxue.view.client.course.ReviewClient;
import com.wuxue.view.controllers.BaseController;
import com.wuxue.view.interfaces.IQueryController;
import com.wuxue.view.interfaces.ISaveController;
import com.wuxue.view.utils.GuidUtils;
import com.wuxue.view.utils.SessionCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 审核管理
 */
@Controller
@RequestMapping(value = "/zhyou/review")
public class ReviewController extends BaseController implements
        IQueryController<Review,String>{

    @Autowired
    ReviewClient reviewClient;

    /**
     * 审核列表页面
     * @param model
     * @return
     */
    @Override
    public String query(HttpServletRequest request, Model model, Review review)  {
        //页码，每页数量
        String str=request.getParameter("pageNo");
        if(str!=null&&str!=""){
            int pageNo=Integer.parseInt(str);
            review.setPageNo(pageNo);
        }else{
            review.setPageNo(Contsants.PAGE_NO);
        }
        review.setPageSize(Contsants.PAGE_SIZE);

        Response<PageInfo<Review>> response=reviewClient.find(review);
        PageInfo<Review> pageInfo=response.getData();

        model.addAttribute("review", pageInfo);
        return "/zhyou/review/reviewlist";
    }

    @RequestMapping(value = "/updateReview",method = RequestMethod.POST)
    @ResponseBody
    public Response updateReview(HttpServletRequest request, Model model, Review review) {
        review.setModifier(SessionCache.getUserCode());
        Response<Review> reviewResponse = reviewClient.audit(review);
        return reviewResponse;
    }
}
