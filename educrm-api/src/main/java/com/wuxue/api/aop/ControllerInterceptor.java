//package com.wuxue.api.aop;
//
//import java.lang.reflect.Method;
//import java.util.*;
//
//import com.alibaba.fastjson.JSON;
//import com.wuxue.api.mapper.StudentMapper;
//import com.wuxue.api.mapper.SysMenuMapper;
//import com.wuxue.api.mapper.SysPowerMapper;
//import com.wuxue.api.mapper.SysUserMapper;
//import com.wuxue.api.service.SysUserService;
//import com.wuxue.model.Student;
//import com.wuxue.model.SysMenu;
//import com.wuxue.model.SysPowerKey;
//import com.wuxue.model.SysUser;
//import com.wuxue.utils.common.EncryptUtil;
//import com.wuxue.utils.common.MD5Util;
//import com.wuxue.utils.contract.Head;
//import com.wuxue.utils.contract.Request;
//import com.wuxue.utils.contract.Response;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Pointcut;
//import org.aspectj.lang.reflect.MethodSignature;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Component;
//
//@Aspect
//@Component
//public class ControllerInterceptor {
//    private static final Logger logger = (Logger) LoggerFactory.getLogger(ControllerInterceptor.class);
//
//    private static final String SIGNONURL = "/signon";
//    private static final String COLUMNURL="/system/sysTableColumn/query";
//    private static final String SIGONINDEX="/sigonindex";
//
//    @Value("${spring.profiles}")
//    private String env;
//
//    @Autowired
//    SysUserMapper sysUserMapper;
//
//    @Autowired
//    StudentMapper studentMapper;
//    @Autowired
//    private SysPowerMapper sysPowerMapper;
//
//    /**
//     * 定义拦截规则：拦截com.xjj.web.controller包下面的所有类中，有@RequestMapping注解的方法。
//     */
//    @Pointcut("execution(* com.wuxue.api.controller..*(..))")
//    // && @annotation(org.springframework.web.bind.annotation.RequestMapping)")
//    public void controllerMethodPointcut() {
//    }
//
//    /**
//     * 拦截器具体实现
//     *
//     * @param pjp
//     * @return JsonResult（被拦截方法的执行结果，或需要登录的错误提示。）
//     */
//    @Around("controllerMethodPointcut()") //指定拦截器规则；也可以直接把"execution(* com.xjj………)"写进这里
//    public Object Interceptor(ProceedingJoinPoint pjp) {
//        long beginTime = System.currentTimeMillis();
//        MethodSignature signature = (MethodSignature) pjp.getSignature();
//        Method method = signature.getMethod(); //获取被拦截的方法
//        String methodName = method.getName(); //获取被拦截的方法名
//        Set<Object> allParams = new LinkedHashSet<>(); //保存所有请求参数，用于输出到日志中
//        logger.info("请求开始，方法：{}", methodName);
//        Object result = null;
//        Object[] args = pjp.getArgs();
//        String requestXml = "";
//        String responseXml = "";
//        if (!"Login".equals(methodName)) {
//            for (Object arg : args) {
//                if (arg instanceof Request<?>) {
//                    Request<?> request = (Request<?>) arg;
//                    requestXml = JSON.toJSONString(request);
//                    if (request == null || request.getHead() == null || !isValidHeadInfo(request.getHead())) {
//                        result = Response.newResponse().ILLEGAL_REQUEST();
//                    }
//                }
//            }
//        }
//        try {
//            if (result == null) {
//                // 一切正常的情况下，继续执行被拦截的方法
//                result = pjp.proceed();
//            }
//        } catch (Throwable e) {
//            logger.info("exception: ", e);
//            result = Response.newResponse().SERVER_ERROR(e.getMessage());
//        }
//
//        if (result instanceof Response) {
//            long costMs = System.currentTimeMillis() - beginTime;
//            logger.info("{}请求结束，耗时：{}ms", methodName, costMs);
//        }
//        responseXml = JSON.toJSONString(result);
//        logger.info("requestXml:{},responseXml:{}", requestXml, responseXml);
//        return result;
//    }
//
//    /**
//     * 验证请求头安全参数
//     *
//     * @param head
//     * @return
//     */
//    private boolean isValidHeadInfo(Head head) {
//        if (!env.equals("prod")) { //只有生产环境才需要登录
//            return true;
//        }
//        if(head.getAppKey().toLowerCase().equals("zhonghang")){
//            return true;
//        }else if (head.getAppKey().toLowerCase().equals("baidu")) {
//            return true;
//        }else if (!head.getAppKey().toLowerCase().equals("wuxueapp")) {
//            return false;
//        }
//        long currentTime = System.currentTimeMillis();
//        long afterTime = currentTime + 30000;
//        long beforeTime = currentTime - 30000;
//        if (!(head.getSalt() > beforeTime && head.getSalt() < afterTime)) {
//            return false;
//        }
//        String sign = EncryptUtil.decrypt(head.getSign());
//        String[] split = sign.split("\\|\\|");
//        if (split.length < 2) {
//            return false;
//        }
//        String userCode = split[0];
//        String password = split[1];
//        if(head.getAppKey().toLowerCase().equals("wuxueapp")) {
//            SysUser sysUser = sysUserMapper.selectByPrimaryKey(userCode);
//          //  if (head.getUrl() != null && (SIGNONURL.equals(head.getUrl()) || COLUMNURL.equals(head.getUrl()) || SIGONINDEX.equals(head.getUrl()))){
//                if (sysUser == null || !password.equals(sysUser.getPassword())) {
//                    return false;
//               }
////            }else {
////                Map map = new HashMap();
////                String url = head.getUrl();
////                String[] split1 = url.split("/");
////                if (split1.length>3){
////                    url="/"+split1[1]+"/"+split1[2]+"/query";
////                }
////                map.put("url",url);
////                map.put("userCode",sysUser.getPkSysUser());
////                SysPowerKey sysPowerKey = sysPowerMapper.selectforURL(map);
////                if (sysUser == null || !password.equals(sysUser.getPassword()) /*|| sysPowerKey ==null*/) {
////                    return false;
////                }
////             }
//
//        }else if(head.getAppKey().toLowerCase().equals("zhonghang")){
//            Student student = new Student();
//            student.setPhone(userCode);
//            student.setPassword(password);
//            List<Student> studentList = studentMapper.select(student);
//            if(studentList.size() < 1){
//                return false;
//            }
//        }
//        return true;
//    }
//}