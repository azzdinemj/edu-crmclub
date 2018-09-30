package com.wuxue.api.interfaces;

import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Jamie on 2018/1/1.
 */
public interface IDeleteController<TParam> {
    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    Response Delete(Request<TParam> request);
}
