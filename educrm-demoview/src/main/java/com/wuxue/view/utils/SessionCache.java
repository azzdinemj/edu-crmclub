package com.wuxue.view.utils;

import com.wuxue.model.SysMenu;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class SessionCache {
    private static final String F_USERNAME = "UserName";
    private static final String F_USERCODE = "UserCode";
    private static final String F_PASSWORD = "Password";
    private static final String F_PKDOMAIN = "PkDomain";
    private static final String F_DOMAIN_CAPTION = "DomainCaption";
    private static final String F_DEPAREMENT = "PkDeparement";
    private static final String F_EMPLOYEE = "Employee";
    private static final String F_SysMenu = "SysMenu";
    private static final String F_LoginVersion = "LoginVersion";
    private static final String F_SysRole = "SysRole";

    private static HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

    public static String getLoginVersion() {
        Object obj = getRequest().getSession().getAttribute(F_LoginVersion);
        if (obj == null) {
            return "";
        }
        return obj.toString();
    }
    public static void setLoginVersion(String loginVersion) {
        getRequest().getSession().setAttribute(F_LoginVersion, loginVersion);
    }

    public static String getPkdomain() {
        Object obj = getRequest().getSession().getAttribute(F_PKDOMAIN);
        if (obj == null) {
            return "";
        }
        return obj.toString();
    }
    public static void setPkdomain(String pkDomain) {
        getRequest().getSession().setAttribute(F_PKDOMAIN, pkDomain);
    }
    public static String getDomainCaption() {
        Object obj = getRequest().getSession().getAttribute(F_DOMAIN_CAPTION);
        if (obj == null) {
            return "";
        }
        return obj.toString();
    }
    public static void setDomainCaption(String domainCaption) {
        getRequest().getSession().setAttribute(F_DOMAIN_CAPTION, domainCaption);
    }

    public static void setEmployee(String employee) {
        getRequest().getSession().setAttribute(F_EMPLOYEE, employee);
    }

    public static String getEmployee() {
        Object obj = getRequest().getSession().getAttribute(F_EMPLOYEE);
        if (obj == null) {
            return "";
        }
        return obj.toString();
    }


    public static void setSysRole(String sysRole) {
        getRequest().getSession().setAttribute(F_SysRole, sysRole);
    }

    public static String getSysRole() {
        Object obj = getRequest().getSession().getAttribute(F_SysRole);
        if (obj == null) {
            return "";
        }
        return obj.toString();
    }


    public static String getUserName() {
        Object obj = getRequest().getSession().getAttribute(F_USERNAME);
        if (obj == null) {
            return "";
        }
        return obj.toString();
    }

    public static void setUserName(String userName) {
        getRequest().getSession().setAttribute(F_USERNAME, userName);
    }

    public static String getUserCode() {
        Object obj = getRequest().getSession().getAttribute(F_USERCODE);
        if (obj == null) {
            return "";
        }
        return obj.toString();
    }

    public static void setUserCode(String userCode) {
        getRequest().getSession().setAttribute(F_USERCODE, userCode);
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

    public static void setfDeparement(String pkDepatement) {
        getRequest().getSession().setAttribute(F_DEPAREMENT, pkDepatement);
    }

    public static String getfDeparement() {
        Object obj = getRequest().getSession().getAttribute(F_DEPAREMENT);
        if (obj == null) {
            return "";
        }
        return obj.toString();
    }

    public static List<SysMenu> getSysMenu() {
        Object obj = getRequest().getSession().getAttribute(F_SysMenu);
        if (obj == null) {
            return null;
        }
        return (List<SysMenu>)obj;
    }

    public static void setSysMenu(List<SysMenu> list) {
        getRequest().getSession().setAttribute(F_SysMenu, list);
    }

}
