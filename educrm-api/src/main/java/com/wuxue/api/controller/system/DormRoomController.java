package com.wuxue.api.controller.system;

import com.wuxue.api.interfaces.IDeleteController;
import com.wuxue.api.interfaces.IFindController;
import com.wuxue.api.interfaces.ISaveController;
import com.wuxue.model.DormRoom;
import com.wuxue.api.service.DormRoomService;
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
@RequestMapping(value = "api/system/dormRoom")
public class DormRoomController implements IFindController<DormRoom>,
        ISaveController<DormRoom>,IDeleteController<String> {
    @Autowired
    private DormRoomService dormRoomService;

    @Override
    public Response Find(@RequestBody Request<DormRoom> dormRoom) {
        return dormRoomService.find(dormRoom);
    }

    @Override
    public Response Save(@RequestBody Request<DormRoom> dormRoom) {
        return dormRoomService.save(dormRoom);
    }
    @Override

    public Response Delete(@RequestBody Request<String> dormRoom) {
        return dormRoomService.delete(dormRoom);

    }
}
