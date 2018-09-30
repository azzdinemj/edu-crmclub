package com.wuxue.api.controller.classInfo;

import com.wuxue.api.interfaces.IDeleteController;
import com.wuxue.api.interfaces.IFindController;
import com.wuxue.api.interfaces.ISaveController;
import com.wuxue.api.service.ClassinfoRoomService;
import com.wuxue.model.ClassinfoRoom;
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
@RequestMapping(value = "api/classinfo/classinfoRoom")
public class ClassinfoRoomController implements IFindController<ClassinfoRoom>,
        ISaveController<ClassinfoRoom>,IDeleteController<ClassinfoRoom> {
    @Autowired
    private ClassinfoRoomService classinfoRoomService;

    @Override
    public Response Find(@RequestBody Request<ClassinfoRoom> classinfoRoom) {
        return classinfoRoomService.find(classinfoRoom);
    }

    @Override
    public Response Save(@RequestBody Request<ClassinfoRoom> classinfoRoom) {
        return classinfoRoomService.save(classinfoRoom);
    }
    @Override
    public Response Delete(@RequestBody Request<ClassinfoRoom> classinfoRoom) {
        return classinfoRoomService.delete(classinfoRoom);

    }

    @RequestMapping(value = "/saveall",method = RequestMethod.POST)
    public Response saveAll(@RequestBody Request<ClassinfoRoom> classinfoRoom) {
        return classinfoRoomService.saveAll(classinfoRoom);

    }
    @RequestMapping(value = "/findroom",method = RequestMethod.POST)
    public Response findTeacher(@RequestBody Request<ClassinfoRoom> classinfoRoom) {
        return classinfoRoomService.findTeacher(classinfoRoom);

    }

    @RequestMapping(value = "/findclassinforoom",method = RequestMethod.POST)
    public Response findClassinfoRoom(@RequestBody Request<ClassinfoRoom> classinfoRoom) {
        return classinfoRoomService.findClassinfoRoom(classinfoRoom);

    }
}
