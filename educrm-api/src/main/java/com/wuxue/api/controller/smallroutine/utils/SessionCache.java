package com.wuxue.api.controller.smallroutine.utils;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
* @Description: session缓存
* @author wanghao
* @date  10:25 2018/3/15
* @version V1.0
*/
public class SessionCache {


    private static final String F_CAPTION="caption";
    private static final String F_PHONE = "Phone";
    private static final String F_PASSWORD = "Password";
    private static final String CHECK_PASSWORD="Checkpassword";
    private static final String PK_STUDENT="pkStudent";
    private static final String STR_CODE="strCode";


    public static void setSTR_CODE(String code) {
        getRequest().getSession().setAttribute(STR_CODE, code);
    }
    public static String getStrCode() {
        Object obj = getRequest().getSession().getAttribute(PK_STUDENT);
        if (obj == null) {
            return "";
        }
        return obj.toString();
    }


    private static HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }


    public static String getF_CAPTION() {
        Object obj = getRequest().getSession().getAttribute(F_CAPTION);
        if (obj == null) {
            return "";
        }
        return obj.toString();
    }
    public static void setF_CAPTION(String caption) {
        getRequest().getSession().setAttribute(F_CAPTION, caption);
    }


    public static String getPkStudent() {
        Object obj = getRequest().getSession().getAttribute(PK_STUDENT);
        if (obj == null) {
            return "";
        }
        return obj.toString();
    }
    public static void setPkStudent(String pkStudent) {
        getRequest().getSession().setAttribute(PK_STUDENT, pkStudent);
    }


    public static String getCheckpassword() {
        Object obj = getRequest().getSession().getAttribute(CHECK_PASSWORD);
        if (obj == null) {
            return "";
        }
        return obj.toString();
    }

    public static void setCheckpassword(String Checkpassword) {
        getRequest().getSession().setAttribute(CHECK_PASSWORD, Checkpassword);
    }


    public static String getPhone() {
        Object obj = getRequest().getSession().getAttribute(F_PHONE);
        if (obj == null) {
            return "";
        }
        return obj.toString();
    }

    public static void setPhone(String phone) {
        getRequest().getSession().setAttribute(F_PHONE, phone);
    }


    public static String getPassword() {
        Object obj = getRequest().getSession().getAttribute(F_PASSWORD);
        if (obj == null) {
            return "";
        }
        return obj.toString();
    }

    public static void setPassword(String password) {
        getRequest().getSession().setAttribute(F_PASSWORD, password);
    }

}
