/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wuxue.interceptors;

import com.wuxue.utils.contract.Light;
import com.wuxue.view.utils.SessionCache;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Rogue
 */
public class SignonInterceptor extends HandlerInterceptorAdapter {

	// 不需要拦截的路径
	private static final String ERRORURI = "/error";
	private static final String STUDENTMEMBER = "/studentMember";
	private static final String SAVESTUDENTMAMBER = "/zhyou/member/save";
	private static final String SAVEMEMBERSUCCESS = "/zhyou/member/successlist";


	public static Log logger = LogFactory.getLog(SignonInterceptor.class);

	@SuppressWarnings("unchecked")
	@Override
	public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
		String uri = request.getServletPath();
		logger.debug(uri);

		String userName = SessionCache.getUserName();
		if ("".equals(userName) && !uri.equals("/") && !uri.equals("/signon") && !uri.equals(STUDENTMEMBER) && !uri.equals(SAVESTUDENTMAMBER) && !uri.equals(SAVEMEMBERSUCCESS)) {
			response.sendRedirect("/");
			return true;
		}

//		HttpSession session = request.getSession();
//		SessionCache signinUser = (SessionCache) session
//				.getAttribute(Light.SESSION_USER);
//		if (signinUser != null && uri.equals("/")) {
//			session.setAttribute(Light.LEFT_MENU, null);
//			response.sendRedirect("/");
//			return true;
//		}

		// 处理错误跳转
		if (ERRORURI.equals(uri)) {
//			response.sendRedirect("/error/404.ftl");
			return true;
		}

		return true;
	}

}
