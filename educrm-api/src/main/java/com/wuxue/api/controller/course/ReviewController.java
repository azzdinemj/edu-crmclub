package com.wuxue.api.controller.course;

import com.wuxue.api.interfaces.IAuditController;
import com.wuxue.api.interfaces.IDeleteController;
import com.wuxue.api.interfaces.IFindController;
import com.wuxue.api.interfaces.ISaveController;
import com.wuxue.api.service.ReviewService;
import com.wuxue.model.Review;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Rogue on 2018/03/15.
 */
@RestController
@RequestMapping(value = "api/course/review")
public class ReviewController implements IFindController<Review>,
        ISaveController<Review>,IDeleteController<String>,IAuditController<Review>{


    @Autowired
    private ReviewService reviewService;

    @Override
    public Response Find(@RequestBody Request<Review> review) {
        return reviewService.find(review);
    }

    @Override
    public Response Save(@RequestBody Request<Review> review) {
        return reviewService.save(review);
    }

    @Override
    public Response Delete(@RequestBody Request<String> review) {
        return reviewService.delete(review);
    }

    @Override
    public Response Audit(@RequestBody Request<Review> review) {
        return reviewService.audit(review);
    }
}
