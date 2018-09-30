package com.wuxue.api.interfaces;

public interface IUpdateMapper<TParams> {
    int updateByPrimaryKeySelective(TParams tParams);
}
