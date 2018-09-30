/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wuxue.constant;

import java.io.Serializable;

/**
 * 返回结果
 * 
 * @author Rogue
 */
public class GenericReturnResult<T> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected int status;

	private String parentId;

	private String info;

	private T result;

	private String error;

	public GenericReturnResult() {
	}

	public int getStatus() {
		return status;
	}

	public T getResult() {
		return result;
	}

	public String getError() {
		return error;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public void setResult(T result) {
		this.result = result;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}
}
