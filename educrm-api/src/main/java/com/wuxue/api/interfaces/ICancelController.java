package com.wuxue.api.interfaces;

import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Rogue on 2018/1/19.
 */
public interface ICancelController<TParam> {
    @RequestMapping(value = "/cancel",method = RequestMethod.POST)
    Response Cancel(Request<TParam> request);
}
