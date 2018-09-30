/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


package com.wuxue.interceptors;

import com.wuxue.model.SysMenu;
import com.wuxue.utils.contract.Light;
import com.wuxue.view.utils.SessionCache;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 *
 * description:  拦截器。
 * @auther: wh
 * @date: 2018/6/28 11:44
 */
public class SignonInterceptor extends HandlerInterceptorAdapter {

	// 不需要拦截的路径
	private static final String ERRORURI = "/error";



	public static Log logger = LogFactory.getLog(SignonInterceptor.class);

	@SuppressWarnings("unchecked")
	@Override
	public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
		String uri = request.getServletPath();   // 管理端请求 来源判断


		//String uri2=request.getRemoteAddr();
		String uri2=request.getHeader("Origin");//学生端、微信绑定请求 来源判断



		String frontip="http://118.25.102.165:83";//学生端部署地址
		String wxip="";                           //微信绑定地址


		logger.debug(uri);



		String userName = SessionCache.getUserName();

		if(uri2!=null){  //学生端请求
			if ("".equals(userName) && !uri.equals("/") && !uri.equals("/getValidCode")&&
					!uri.equals("/signon")&& !uri2.equals(frontip)) {
				response.sendRedirect("/");
				return true;
			}
		}else if(uri!=null){          // 管理端请求
			if ("".equals(userName) && !uri.equals("/") && !uri.equals("/getValidCode")&&
					!uri.equals("/signon")) {
				response.sendRedirect("/");
				return true;
			}
		}

		 //处理错误跳转
		if (ERRORURI.equals(uri)) {
		    response.sendRedirect("/");
			return true;
		}

		return true;
	}

}

