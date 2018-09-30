/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wuxue.constant;

/**
 * 错误代码定义
 * 
 * @author Rogue
 */
public class ErrorCode {

	public final static int SUCCESS = 0;

	/**
	 * 没有用户session
	 */
	public final static int E_NOT_USER_SESSION = 10;
	/**
	 * 用户验证认证
	 */
	public final static int E_NOT_USER_AUTH = 11;
	/**
	 * 用户密码错误
	 */
	public final static int E_NOT_USER_PASSWORD = 12;
	/**
	 * 用户未设置支付密码
	 */
	public final static int E_NOT_USER_PAYPASSWORD = 13;

	/**
	 * 未知类型的错误
	 */
	public final static int E_UNKNOWN_ERROR = 99;

	/**
	 * 非法参数定义
	 */
	public final static int E_INVALID_PARAMETER = 100;
	public final static int E_USER_NOT_EXIST = E_INVALID_PARAMETER + 1;
	public final static int E_USER_INVALID_PASSWORD = E_INVALID_PARAMETER + 2;
	public final static int E_USER_NAME_EMPTY = E_INVALID_PARAMETER + 3;
	public final static int E_USER_PASSWORD_EMPTY = E_INVALID_PARAMETER + 4;
	public final static int E_PARAMETER_EMPTY = E_INVALID_PARAMETER + 5;
	public final static int E_INVALID_FORMAT_ERROR = E_INVALID_PARAMETER + 6;

	/**
	 * 用户不存在或用户未做实名认证
	 */
	public final static int E_NAMED_USER_NOT_EXISTS = E_INVALID_PARAMETER + 7;

	/**
	 * 提现金额不足
	 */
	public final static int E_AVAILAMOUNT_LESS_WITHDRAW_MONEY = E_INVALID_PARAMETER + 8;
	/**
	 * 购买金额不足
	 */
	public final static int E_AVAILAMOUNT_LESS_AMOUNT = E_INVALID_PARAMETER + 11;
	/**
	 * 用户身份证号已存在
	 */
	public final static int E_IDCRAD_USER_EXISTS = E_INVALID_PARAMETER + 9;

	/**
	 * 查询数据为空
	 */
	public final static int E_DATA_EMPTY = E_INVALID_PARAMETER + 8;

	/**
	 * 错误状态
	 */
	public final static int E_ILLEGAL_STATE = E_INVALID_PARAMETER + 9;

	/**
	 * 返回对象错误
	 */
	public final static int E_OBJECT_RESULT = 200;

	/**
	 * 服务器端错误信息
	 */

	/**
	 * 参数数据为空
	 */
	// 手机号码为空
	public final static int E_MOBILE_NULL = 1001;
	// 手机号码错误
	public final static int E_MOBILE_WRONG = E_MOBILE_NULL + 1;

	// 密码为空
	public final static int E_PASSWORD_NULL = 2001;
	// 确认密码为空
	public final static int E_REPASSWORD_NULL = E_PASSWORD_NULL + 1;
	// 两次输入的密码不一样
	public final static int E_PASSWORD_NOT_SAME = E_PASSWORD_NULL + 2;
	// 密码错误
	public final static int E_PASSWORD_WRONG = E_PASSWORD_NULL + 3;

	// 验证码为空
	public final static int E_VALIDATECODE_NULL = 3001;
	// 验证码错误
	public final static int E_VALIDATECODE_WRONG = E_VALIDATECODE_NULL + 1;
	/**
	 * 内部错误
	 */

	public final static int E_INTERNAL_SERVICE_ERROR = 500;

	/**
	 * 数据库连接错误
	 */
	public final static int E_DB_CONNECT_ERROR = E_INTERNAL_SERVICE_ERROR + 1;

	/**
	 * 数据库错误,数据库执行SQL出错
	 */
	public final static int E_DB_ERROR = E_INTERNAL_SERVICE_ERROR + 2;

	/*
	 * 数据库连接超时错误
	 */
	public final static int E_DB_CONNECT_TIMEOUT = E_INTERNAL_SERVICE_ERROR + 3;
	/**
	 * 业务错误
	 */
	public final static int E_BUSINESS_ERROR = 300;
	/**
	 * 未定义的错误类型，需要修改程序处理
	 */
	public final static int E_UNDEFINED_ERROR = 2000;

	public final static int E_INCONSISTENCY_ERROR = 3000;

}
