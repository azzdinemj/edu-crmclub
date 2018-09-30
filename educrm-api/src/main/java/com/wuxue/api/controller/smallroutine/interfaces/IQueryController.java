package com.wuxue.api.controller.smallroutine.interfaces;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

public interface IQueryController<TParam,TResponse> {
    @RequestMapping(value = "/query",method = RequestMethod.GET)
    TResponse query(HttpServletRequest request, Model model, TParam param);
}
