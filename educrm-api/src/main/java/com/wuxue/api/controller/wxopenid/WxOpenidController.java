package com.wuxue.api.controller.wxopenid;

import com.wuxue.api.interfaces.IDeleteController;
import com.wuxue.api.interfaces.IFindController;
import com.wuxue.api.interfaces.ISaveController;
import com.wuxue.api.service.WxOpenidService;
import com.wuxue.model.WxOpenid;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: 阶点  wx_openid  控制
 */
@RestController
@RequestMapping(value = "api/wxopenid/wxopenid/")
public class WxOpenidController implements IFindController<WxOpenid>,
        ISaveController<WxOpenid>, IDeleteController<String> {


    @Autowired
    private WxOpenidService wxOpenidService;

    @Override
    public Response Find(@RequestBody Request<WxOpenid> course) {
        return wxOpenidService.find(course);
    }

    @Override
    public Response Save(@RequestBody Request<WxOpenid> course) {
        return wxOpenidService.save(course);
    }

    @Override
    public Response Delete(@RequestBody Request<String> course) {
        //return wxOpenidService.delete(course);
        return null;
    }


}
