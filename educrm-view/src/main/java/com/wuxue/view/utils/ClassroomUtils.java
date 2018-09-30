package com.wuxue.view.utils;

import com.wuxue.model.ClassRoom;
import com.wuxue.utils.contract.Response;
import com.wuxue.view.client.classInfo.ClassRoomClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassroomUtils {

    @Autowired
    private ClassRoomClient classRoomClient;

    /**
     * 获取所有教室
     */
    public List<ClassRoom> getClassRooms(){
        ClassRoom classRoom = new ClassRoom();
        classRoom.setPageSize(10000);
        Response<List<ClassRoom>> listResponse = classRoomClient.find(classRoom);
        return listResponse.getData();
    }
}
