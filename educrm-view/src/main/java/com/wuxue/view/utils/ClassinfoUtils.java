package com.wuxue.view.utils;

import com.wuxue.base.KeyValue;
import com.wuxue.model.ClassRoom;
import com.wuxue.model.Classinfo;
import com.wuxue.utils.contract.Response;
import com.wuxue.view.client.classInfo.ClassInfoClient;
import com.wuxue.view.client.classInfo.ClassRoomClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassinfoUtils {

    @Autowired
    private ClassInfoClient classInfoClient;

    /**
     * 获取所有班级
     */
    public List<Classinfo> getClassinfo(){
        Classinfo classinfo = new Classinfo();
        classinfo.setPageSize(10000);
        Response<List<Classinfo>> listResponse = classInfoClient.find(classinfo);
        return listResponse.getData();
    }
    /**
     * 分类获取所有班级
     */
    public List<KeyValue> getClassinfoByType(Integer type){
        Classinfo classinfo = new Classinfo();
        classinfo.setPageSize(10000);
        classinfo.setType(type);
        Response<List<KeyValue>> listResponse = classInfoClient.getClassinfo(classinfo);
        return listResponse.getData();
    }
//    public List<Classinfo> getClassinfoByType(Integer type){
//        Classinfo classinfo = new Classinfo();
//        classinfo.setPageSize(10000);
//        classinfo.setType(type);
//        Response<List<Classinfo>> listResponse = classInfoClient.find(classinfo);
//        return listResponse.getData();
//    }


}
