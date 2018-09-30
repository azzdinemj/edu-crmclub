package com.wuxue.view.interfaces;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

public interface ICreateController<TParam,TResponse> {
    @RequestMapping(value = "/create",method = RequestMethod.GET)
    TResponse create(HttpServletRequest request, Model model, TParam param);
}
