package com.wuxue.api.controller.smallroutine.interfaces;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

public interface ISubmitController<TParam,TResponse> {
    @RequestMapping(value = "/submit",method = RequestMethod.POST)
    TResponse submit(HttpServletRequest request, Model model, TParam param);
}
