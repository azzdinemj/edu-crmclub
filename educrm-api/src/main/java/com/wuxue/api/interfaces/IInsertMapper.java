package com.wuxue.api.interfaces;

public interface IInsertMapper<TParams> {
    int insertSelective(TParams tParams);
}