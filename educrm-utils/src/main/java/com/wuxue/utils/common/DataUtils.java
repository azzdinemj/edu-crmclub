package com.wuxue.utils.common;

import com.alibaba.fastjson.*;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.ResponseCode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jamie on 2018/1/15.
 */
public class DataUtils {
    /**
     * 对象转List对象
     * @param object
     * @param <T>
     * @return
     */
    public static <T> List<T> objectToList(Object object,Class<T> clazz){
        String jsonString = JSON.toJSONString(object);
        return JSON.parseArray(jsonString,clazz);
    }

    /**
     * 对象转对象
     * @param object
     * @param <T>
     * @return
     */
    public static <T> T objectToObject(Object object,Class<T> clazz){

        String jsonString = JSON.toJSONString(object);
        return JSON.parseObject(jsonString,clazz);
    }

    public static  <T> Request<T> objToRequest(T t){
        Request<T> request = new Request<>();
        request.setData(t);
        return request;
    }


}
