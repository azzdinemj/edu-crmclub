package com.wuxue.api.interfaces;

import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Administrator on 2018/4/24.
 */
public interface ICheckController<TParam> {
    @RequestMapping(value = "/check",method = RequestMethod.POST)
    Response Check(Request<TParam> request);
}
