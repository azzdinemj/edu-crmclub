package com.wuxue.utils.contract;

/**
 * 返回-编码
 */
public class ResponseCode {

    /**
     * 成功
     */
    public static int SUCCESS = 0;
    /**
     * 非法请求
     */
    public static int ILLEGAL_REQUEST = 900;
    /**
     * 参数不能为空
     */
    public static int PARAMS_ISNULL = 901;
    /**
     * 删除失败
     */
    public static int DELETE_FAIL = 902;

    /**
     * 保存失败
     */
    public  static  int SAVE_FAIL=903;
    /**
     * 查询失败
     */
    public  static  int FIND_FAIL=904;
    /**
     * 重复提交
     */
    public  static  int SUBMIT_DOUBLE=905;
    /**
     * 审核失败,需先提交
     */
    public  static  int AUDIT_SUBMIT=906;
    /**
     * 审核失败,取消之后不可审核
     */
    public  static  int AUDIT_CANCEL=907;
    /**
     * 提交失败,审核之后不可提交
     */
    public  static  int SUBMIT_AUDIT=908;
    /**
     * 提交失败,取消之后不可提交
     */
    public  static  int SUBMIT_CANCEL=909;
    /**
     * 取消失败,审核之后不可取消
     */
    public  static  int CANCEL_AUDIT=910;
    /**
     * 不可重复提交
     */
    public  static  int SAVE_DOUBLE=911;
    /**
     * 无编码
     */
    public  static  int SYSAUTOCODE_ISNULL=912;
    /**
     * 重复报名
     */
    public static int REPEAT_SIGNUP = 913;
    /**
     * 注册失败
     */
    public static int REGISTER_FAIL = 914;
    /**
     * 服务器错误
     */
    public static int SERVER_ERROR = 500;
    /**
     * 账号密码不正确
     */
    public static int ACCOUNT_PASS_ERROR = 1001;
    /**
     * 账号锁定
     */
    public static int ACCOUNT_LOCKED = 1002;
    /**
     * 密码错误
     */
    public static int PASSWORD_ERROR = 1003;
    /**
     * 账号已存在
     */
    public static int ACCOUNT_EXISTS = 1004;
    /**
     * 账号不存在
     */
    public static int ACCOUNT_NOT_EXISTS = 1005;
    /**
     * 手机已存在
     */
    public static int MOBILE_EXISTS = 1006;

    /**
     * 用户不存在
     */
    public static int USER_NULL = 1007;

    /**
     * 用户被禁止
     */
    public static int USER_ISVALID = 1010;

    /**
     * 角色不存在
     */
    public static int ROLE_NULL = 1008;

    /**
     * 登录失败
     */
    public static int LOGIN_FAIL = 1009;

    /**
     * 用户已存在
     */
    public static int USER_DOUBLE = 1011;

    /**
     * 审核失败
     */
    public static int AUDIT_FAIL = 1012;

    /**
     * 审核失败。不可重复审核
     */
    public static int AUDIT_DOUBLE = 1013;

    /**
     * 兴趣班冲突
     */
    public static int CONFLICT = 1014;

    /**
     * 确认失败。
     */
    public static int CONFIRM_FAIL = 1015;
    /**
     * 暂无信息。
     */
    public static int NO_CONTEN = 1016;
    /**
     * 发送失败
     */
    public static int SEND_FAIL = 1017;
    /**
     * 验证码不正确
     */
    public static int PHONE_CODE_FAIL = 1018;
    /**
     * 微信不可多次绑定
     */
    public static int OPENID_DOUBLE = 1020;
    /**
     * 微信没绑定
     */
    public static int OPENID_NULL = 1021;
    /**
     * 操作失败
     */
    public static int OPERATION_FAIL = 1022;
    /**
     * 套餐编码重复
     */
    public static int CODE_DOUBLE = 1023;
    /**
     * 默认套餐
     */
    public static int DEFAULTS_DOUBLE = 1024;


}