/***
 *  created by jamie at 2017-12-25
 *  Response用户返回数据，包括状态，数据对象(List, Entity)
 */
package com.wuxue.utils.contract;

@SuppressWarnings("serial")
public class Response<T> {
    private int code;

    public int getCode() {
        return code;
    }


    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    private T data;
    private String message;


    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    private long total;

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public Response() {
        SUCCESS();
    }

    public static Response newResponse() {
        return new Response();
    }


//============状态===================================================================================	

    public Response SUCCESS() {
        setCode(ResponseCode.SUCCESS);
        setMessage("ok");
        return this;
    }

    public Response SERVER_ERROR(String message) {
        setCode(ResponseCode.SERVER_ERROR);
        setMessage("server error," + message);
        return this;
    }

    public Response ILLEGAL_REQUEST() {
        setCode(ResponseCode.ILLEGAL_REQUEST);
        setMessage("非法请求!!!");
        return this;
    }

    public Response PARAMS_ISNULL() {
        setCode(ResponseCode.PARAMS_ISNULL);
        setMessage("请求参数为空!!!");
        return this;
    }

    public Response PHONE_CODE_FAIL() {
        setCode(ResponseCode.PHONE_CODE_FAIL);
        setMessage("验证码不正确!!!");
        return this;
    }

    public Response CODE_DOUBLE() {
        setCode(ResponseCode.CODE_DOUBLE);
        setMessage("编号不可使用，已有套餐使用!!!");
        return this;
    }

    public Response DEFAULTS_DOUBLE() {
        setCode(ResponseCode.DEFAULTS_DOUBLE);
        setMessage("已有默认套餐!!!");
        return this;
    }

    public Response OPENID_DOUBLE() {
        setCode(ResponseCode.OPENID_DOUBLE);
        setMessage("微信不可多次绑定!!!");
        return this;
    }

    public Response OPENID_NULL() {
        setCode(ResponseCode.OPENID_NULL);
        setMessage("微信没绑定!!!");
        return this;
    }

    public Response REPEAT_SIGNUP(){
        setCode(ResponseCode.REPEAT_SIGNUP);
        setMessage("已经在该班级报名,不允许重复报名!!!");
        return this;
    }

    public Response SYSAUTOCODE_ISNULL() {
        setCode(ResponseCode.SYSAUTOCODE_ISNULL);
        setMessage("无该编码，请确认之后重试");
        return this;
    }

    public Response AUDIT_FAIL(String message) {
        setCode(ResponseCode.AUDIT_FAIL);
        setMessage("审核失败"+message);
        return this;
    }

    public Response AUDIT_DOUBLE() {
        setCode(ResponseCode.AUDIT_DOUBLE);
        setMessage("审核失败,不可重复审核");
        return this;
    }

    public Response AUDIT_SUBMIT() {
        setCode(ResponseCode.AUDIT_SUBMIT);
        setMessage("审核失败,需先提交");
        return this;
    }

    public Response AUDIT_CANCEL() {
        setCode(ResponseCode.AUDIT_CANCEL);
        setMessage("审核失败,取消之后不可审核");
        return this;
    }

    public Response SUBMIT_AUDIT() {
        setCode(ResponseCode.SUBMIT_AUDIT);
        setMessage("提交失败,审核成功之后不可提交");
        return this;
    }

    public Response SUBMIT_CANCEL() {
        setCode(ResponseCode.SUBMIT_CANCEL);
        setMessage("提交失败,取消之后不可提交");
        return this;
    }

    public Response CANCEL_AUDIT() {
        setCode(ResponseCode.CANCEL_AUDIT);
        setMessage("取消失败,审核之后不可取消");
        return this;
    }

    public Response SAVE_DOUBLE() {
        setCode(ResponseCode.SAVE_DOUBLE);
        setMessage("不可重复提交");
        return this;
    }

    public Response SAVE_FAIL(String message) {
        setCode(ResponseCode.SAVE_FAIL);
        setMessage("保存失败," + message);
        return this;
    }
    public Response SIGNUP_FAIL(String message) {
        setCode(ResponseCode.SAVE_FAIL);
        setMessage(message);
        return this;
    }

    public Response REGISTER_FAIL(String message) {
        setCode(ResponseCode.REGISTER_FAIL);
        setMessage("注册失败," + message);
        return this;
    }

    public Response FIND_FAIL(String message) {
        setCode(ResponseCode.FIND_FAIL);
        setMessage("查询失败,稍后重试," + message);
        return this;
    }

    public Response DELETE_FAIL(String message) {
        setCode(ResponseCode.DELETE_FAIL);
        setMessage("删除失败," + message);
        return this;
    }

    public Response USER_NULL() {
        setCode(ResponseCode.USER_NULL);
        setMessage("找不到该用户");
        return this;
    }

    public Response USER_DOUBLE() {
        setCode(ResponseCode.USER_DOUBLE);
        setMessage("用户已存在");
        return this;
    }

    public Response USER_ISVALID() {
        setCode(ResponseCode.USER_ISVALID);
        setMessage("该用户被禁止登录");
        return this;
    }

    public Response LOGIN_FAIL() {
        setCode(ResponseCode.LOGIN_FAIL);
        setMessage("登录失败!");
        return this;
    }

    public Response ROLE_NULL() {
        setCode(ResponseCode.ROLE_NULL);
        setMessage("找不到角色");
        return this;
    }

    public Response SUBMIT_FAIL(String message) {
        setCode(ResponseCode.SAVE_FAIL);
        setMessage("提交失败," + message);
        return this;
    }

    public Response CONFLICT() {
        setCode(ResponseCode.CONFLICT);
        setMessage("该兴趣课有冲突！！！");
        return this;
    }

    public Response STUDENT_NULL() {
        setCode(ResponseCode.ROLE_NULL);
        setMessage("未找到学生信息，无法选课");
        return this;
    }

    public Response PHONE_EXISTS() {
        setCode(ResponseCode.MOBILE_EXISTS);
        setMessage("电话已存在");
        return this;
    }

    public Response CONFIRM_FAIL(String message) {
        setCode(ResponseCode.CONFIRM_FAIL);
        setMessage("确认失败："+message);
        return this;
    }
    public Response NO_CONTEN() {
        setCode(ResponseCode.NO_CONTEN);
        setMessage("暂无信息");
        return this;
    }
    public Response SEND_FAIL(String message) {
        setCode(ResponseCode.SEND_FAIL);
        setMessage("发送失败");
        return this;
    }

    public Response OPERATION_FAIL(String message) {
        setCode(ResponseCode.OPERATION_FAIL);
        setMessage("操作失败" + message);
        return this;
    }
}