package com.wuxue.api.controller.system;

import com.wuxue.api.interfaces.IFindController;
import com.wuxue.api.interfaces.ISaveController;
import com.wuxue.api.service.DormRoomPerNumService;
import com.wuxue.model.DormRoomPerNum;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Rogue on 2018/01/02.
 */
@RestController
@RequestMapping(value = "api/system/dormroompernum")
public class DormRoomPerNumController implements IFindController<DormRoomPerNum>{
    @Autowired
    private DormRoomPerNumService dormRoomPerNumService;

    @Override
    public Response Find(@RequestBody Request<DormRoomPerNum> dormRoomPerNum) {
        return dormRoomPerNumService.find(dormRoomPerNum);
    }


   
}
