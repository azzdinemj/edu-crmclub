package com.wuxue.view.interfaces;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * 公用泛型Controller接口
 * @param <TParam>
 * @param <TResponse>
 */
public interface IAuditController<TParam,TResponse> {
    @RequestMapping(value = "/audit",method = RequestMethod.POST)
    TResponse audit(HttpServletRequest request, Model model, TParam param);
}
