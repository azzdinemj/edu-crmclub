package com.wuxue.api.service;


import com.wuxue.api.interfaces.IDeleteService;
import com.wuxue.api.interfaces.IFindService;
import com.wuxue.api.interfaces.ISaveService;
import com.wuxue.model.Employee;
import com.wuxue.model.WxOpenid;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;

public interface EmployeeService extends ISaveService<Employee>,IFindService<Employee>,IDeleteService<Employee> {
    /**
     * 离职
     * */
    Response quit(Request<Employee> employee);

    /**
     * 查找
     * */
    Response findzhyou(Request<Employee> employee);
    /**
     * 保存
     * */
    Response savezhyou(Request<Employee> employee);

    /**
     * 根据电话查找老师
     * */
    Response findEmployeeByPhone(Request<String> tParams);

    /**
     * 查找 jiedian
     * */
    Response findjiedian(Request<Employee> employee);

    /**
     * 查询所有老师（不分页）
     * */
    Response selectBy(Request<Employee> employee);

    Employee selectByUser(String sysUser);

    /**
     * 判断openid是否已经存在， 若存在 则已登录，  返回 身份验证码
     *                        不存在 返回 null， 则需要进入binDingWx
     * @return
     * @param wxOpenid
     */
    Response selectOpenid(Request<WxOpenid> wxOpenid);

    /**
     * 获取验证码
     * */
    Response getPhoneCode(Request<Employee> employee);

    /***
     * 绑定微信
     */
    Response bindWx(Request<WxOpenid> wxOpenid);

    /**
     * 获取Openid
     * */
    Response getOpenid(Request<WxOpenid> wxOpenid);

    Employee findById(String pkEmployee);

    Response getCode();

    Response findForMap(Request<Employee> employee);
}
