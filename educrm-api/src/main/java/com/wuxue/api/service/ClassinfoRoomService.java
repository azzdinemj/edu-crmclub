package com.wuxue.api.service;


import com.wuxue.api.interfaces.IDeleteService;
import com.wuxue.api.interfaces.IFindService;
import com.wuxue.api.interfaces.ISaveService;
import com.wuxue.model.ClassinfoEmployee;
import com.wuxue.model.ClassinfoRoom;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;

public interface ClassinfoRoomService extends ISaveService<ClassinfoRoom>,IFindService<ClassinfoRoom>,IDeleteService<ClassinfoRoom> {
    /**
     * 批量保存
     * */
    Response saveAll(Request<ClassinfoRoom> classinfoEmployee);
    Response findTeacher(Request<ClassinfoRoom> classinfoEmployee);

    /**
     * 查询班级教室关联
     * */
    Response findClassinfoRoom(Request<ClassinfoRoom> classinfoRoom);
}
