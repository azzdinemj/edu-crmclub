
package com.wuxue.interceptor;

import com.wuxue.view.utils.SessionCache;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
* @Description: 登录拦截
* @author wanghao
* @date  10:37 2018/3/8
* @version V1.0
*/

public class SingonInterceptor extends HandlerInterceptorAdapter {
    public static Log logger = LogFactory.getLog(SingonInterceptor.class);
    private static final String STUDENTMEMBER = "/studentMember";
    private static final String SAVESTUDENTMAMBER = "/zhyou/member/save";
    private static final String SAVEMEMBERSUCCESS = "/zhyou/member/successlist";

    @SuppressWarnings("unchecked")
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        System.out.println("拦截器");
        String uri = request.getServletPath();
        logger.debug(uri);

        if(uri.equals("/getValidCode")||uri.equals("/updatephone")||uri.equals("/signon")||uri.equals("/forget")||uri.equals("/save")||uri.equals("/updatepwd")
                ||uri.equals("/registered")||uri.equals("/")||uri.equals(STUDENTMEMBER)||uri.equals(SAVESTUDENTMAMBER)||uri.equals(SAVEMEMBERSUCCESS)){
            return true;
        }

        //session判断
        String phone = SessionCache.getPhone();
        if (StringUtils.isEmpty(phone)) {
            response.sendRedirect("/");
            return false;
        }

        return true;
    }
}

