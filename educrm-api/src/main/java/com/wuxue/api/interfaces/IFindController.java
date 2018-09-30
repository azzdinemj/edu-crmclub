package com.wuxue.api.interfaces;

import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

/**
 * Created by Jamie on 2018/1/1.
 */
public interface IFindController<TParam> {
    @RequestMapping(value = "/find",method = RequestMethod.POST)
    Response Find(Request<TParam> request);
}
