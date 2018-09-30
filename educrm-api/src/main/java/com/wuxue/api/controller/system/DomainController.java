package com.wuxue.api.controller.system;

import com.wuxue.api.interfaces.IDeleteController;
import com.wuxue.api.interfaces.IFindController;
import com.wuxue.api.interfaces.ISaveController;
import com.wuxue.model.Domain;
import com.wuxue.api.service.DomainService;
import com.wuxue.model.SchoolBus;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Rogue on 2018/01/03.
 */
@RestController
@RequestMapping(value = "api/system/domain")
public class DomainController implements IFindController<Domain>,
        ISaveController<Domain>,IDeleteController<String>{
    @Autowired
    private DomainService domainService;

    @Override
    public Response Find(@RequestBody Request<Domain> domain) {
        return domainService.find(domain);
    }

    @Override
    public Response Save(@RequestBody Request<Domain> domain) {
        return domainService.save(domain);
    }

    @Override
    public Response Delete(@RequestBody Request<String> domain) {
        return domainService.delete(domain);
    }

    /**
     * 查询学校logo
     *
     * @param domain
     * @return
     */
    @RequestMapping(value = "/geturl", method = RequestMethod.POST)
    public Response getUrl(@RequestBody Request<Domain> domain) {
        return domainService.getUrl(domain);
    }
}
