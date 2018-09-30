package com.wuxue.api.controller.product;

import com.wuxue.api.interfaces.IDeleteController;
import com.wuxue.api.interfaces.IFindController;
import com.wuxue.api.interfaces.ISaveController;
import com.wuxue.api.service.TkProductMarkService;
import com.wuxue.model.TkProductMark;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * description:  产品（课程）收藏表
 * @auther: wh
 * @date: 2018/6/13 14:56
 */
@RestController
@RequestMapping(value = "api/product/tkproductmark")
public class TkProductMarkController implements IFindController<TkProductMark>,
        ISaveController<TkProductMark>,IDeleteController<TkProductMark> {


    @Autowired
    TkProductMarkService tkProductMarkService;

   @Override
    public Response Find(@RequestBody Request<TkProductMark> request) {
        return tkProductMarkService.find(request);
    }

    @Override
    public Response Save(@RequestBody Request<TkProductMark> request) {
        return tkProductMarkService.save(request);
    }

    @Override
    public Response Delete(@RequestBody Request<TkProductMark> request) {
        return tkProductMarkService.delete(request);
    }
}