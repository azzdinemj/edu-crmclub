package com.wuxue.view.interfaces;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

public interface ISaveController<TParam,TResponse> {
    @RequestMapping(value = "/save",method = RequestMethod.POST)
    TResponse save(HttpServletRequest request, Model model, TParam param);
}
