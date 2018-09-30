package com.wuxue.view.interfaces;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public interface IQueryByPagingController<TParam, TResponse> {
    @RequestMapping(value = "/queryByPaging", method = RequestMethod.GET)
    public TResponse queryByPaging(HttpServletRequest request, HttpServletResponse response, TParam param, @RequestParam(value = "sEcho", required = false) Integer sEcho, @RequestParam("start") Integer start, @RequestParam("length") Integer length);
}
