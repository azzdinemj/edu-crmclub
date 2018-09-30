package com.wuxue.api.controller.system;

import com.wuxue.api.interfaces.IFindController;
import com.wuxue.api.service.SysTableColumnService;
import com.wuxue.model.SysTableColumn;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Jamie on 2018/4/1.
 */
@RestController
@RequestMapping(value = "api/system/sysTableColumn")
public class SysTableColumnController implements IFindController<SysTableColumn> {

    @Autowired
    SysTableColumnService sysTableColumnService;

    @Override
    public Response Find(@RequestBody Request<SysTableColumn> request) {
        return sysTableColumnService.find(request);
    }
}
