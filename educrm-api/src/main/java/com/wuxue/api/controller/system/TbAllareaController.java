package com.wuxue.api.controller.system;

import com.wuxue.api.interfaces.IDeleteController;
import com.wuxue.api.interfaces.IFindController;
import com.wuxue.api.interfaces.ISaveController;
import com.wuxue.api.service.TbAllareaService;
import com.wuxue.model.TbAllarea;
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
@RequestMapping(value = "api/system/tballarea")
public class TbAllareaController implements IFindController<TbAllarea>,
        ISaveController<TbAllarea>, IDeleteController<String> {
    @Autowired
    private TbAllareaService sysUserService;

    @Override
    public Response Find(@RequestBody Request<TbAllarea> tbAllarea) {
        return sysUserService.find(tbAllarea);
    }

    @Override
    public Response Save(@RequestBody Request<TbAllarea> tbAllarea) {
        return sysUserService.save(tbAllarea);
    }

    @Override
    public Response Delete(@RequestBody Request<String> tbAllarea) {
        return sysUserService.delete(tbAllarea);
    }



}
