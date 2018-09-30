package com.wuxue.api.interfaces;

import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Jamie on 2018/1/1.
 */
public interface IFindByPagingController<TParam> {
    @RequestMapping(value = "/findByPaging",method = RequestMethod.POST)
    Response FindByPaging(Request<TParam> request);
}
