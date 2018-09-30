package com.wuxue.view.interfaces;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

public interface IDeleteController<TParam,TResponse> {
    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    TResponse delete(HttpServletRequest request, Model model, TParam param);
}
