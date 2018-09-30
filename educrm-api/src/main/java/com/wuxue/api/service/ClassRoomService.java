package com.wuxue.api.service;


import com.wuxue.api.interfaces.IDeleteService;
import com.wuxue.api.interfaces.IFindService;
import com.wuxue.api.interfaces.ISaveService;
import com.wuxue.model.ClassRoom;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;

public interface ClassRoomService extends ISaveService<ClassRoom>,IFindService<ClassRoom>,IDeleteService<String> {
    /**
     * 不分页查询
     * */
    Response selectBy(Request<ClassRoom> classRoom);
}
