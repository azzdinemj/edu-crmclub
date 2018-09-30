package com.wuxue.view.interfaces;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

public interface IEditController<TParam,TResponse> {
    @RequestMapping(value = "/edit",method = RequestMethod.GET)
    TResponse edit(HttpServletRequest request, Model model, TParam param);
}
