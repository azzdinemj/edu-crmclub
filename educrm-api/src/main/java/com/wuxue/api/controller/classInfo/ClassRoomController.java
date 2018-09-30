package com.wuxue.api.controller.classInfo;

import com.wuxue.api.interfaces.IDeleteController;
import com.wuxue.api.interfaces.IFindController;
import com.wuxue.api.interfaces.ISaveController;
import com.wuxue.model.ClassRoom;
import com.wuxue.api.service.ClassRoomService;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Rogue on 2018/01/02.
 */
@RestController
@RequestMapping(value = "api/classinfo/classRoom")
public class ClassRoomController implements IFindController<ClassRoom>,
        ISaveController<ClassRoom>,IDeleteController<String>{
    @Autowired
    private ClassRoomService classRoomService;

    @Override
    public Response Find(@RequestBody Request<ClassRoom> classRoom) {
        return classRoomService.find(classRoom);
    }

    @Override
    public Response Save(@RequestBody Request<ClassRoom> classRoom) {
        return classRoomService.save(classRoom);
    }

    @Override
    public Response Delete(@RequestBody Request<String> classRoom) {
        return classRoomService.delete(classRoom);
    }

    @RequestMapping(value = "/selectby",method = RequestMethod.POST)
    public Response selectBy(@RequestBody Request<ClassRoom> classRoom) {
        return classRoomService.selectBy(classRoom);
    }
}
