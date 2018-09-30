package com.wuxue.view.controllers;

import com.wuxue.view.utils.SessionCache;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;


/**
 * 控制器基类
 * 
 * @author Rogue
 *
 */
@Controller
public abstract class BaseController implements ErrorController {

	protected static final String RETURNRESULT = "returnResult";
	private static final String ERROR_PATH = "/error";
	/**
	 * 通用跳转，类似html <a 标签的页面转向。必须要与普通url格式有区分。如带后缀xxx.html表示从定向页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/**.do", method = RequestMethod.GET)
	public String requestGet(HttpServletRequest request) {
		String uri = request.getServletPath();
		uri = uri.substring(0, uri.lastIndexOf("."));
		String[] out = uri.split("/");
		uri = "/" + out[1] + "/" + out[out.length - 1];
		System.out.println("uri:" + uri);
		return uri;
	}

	protected String getRemoteHost(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip.equals("0:0:0:0:0:0:0:1") ? "127.0.0.1" : ip;
		
	
	}

    @RequestMapping(value=ERROR_PATH)
  	public String handleError(){
		return "/error/404";
	}

	@Override
 	public String getErrorPath() {
	   return ERROR_PATH;
  	}

	protected Map<String,Object> convertToPaging(long total, long displayTotal, Object data){
		Map map = new HashMap<String, Object>();
		map.put("aaData", data);
		//页数信息配置
		map.put("sEcho", null);
		map.put("iTotalRecords", total);
		map.put("iTotalDisplayRecords",displayTotal);
		return map;
	}



}
