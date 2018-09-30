package com.wuxue.api.controller.system;

import com.wuxue.api.interfaces.IDeleteController;
import com.wuxue.api.interfaces.IFindByParentController;
import com.wuxue.api.interfaces.IFindController;
import com.wuxue.api.interfaces.ISaveController;
import com.wuxue.model.SysLog;
import com.wuxue.api.service.SysLogService;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Rogue on 2018/01/03.
 */
@RestController
@RequestMapping(value = "api/system/sysLog")
public class SysLogController implements IFindController<SysLog>
       {
    @Autowired
    private SysLogService sysLogService;

    @Override
    public Response Find(@RequestBody Request<SysLog> sysLog) {
        return sysLogService.find(sysLog);
    }

   

}
