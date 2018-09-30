package com.wuxue.api.controller.finance;

import com.wuxue.api.interfaces.*;
import com.wuxue.model.Receivable;
import com.wuxue.api.service.ReceivableService;
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
@RequestMapping(value = "api/finance/receivable")
public class ReceivableController implements IFindController<Receivable>,
        ISaveController<Receivable>,IDeleteController<String>,
        ICancelController<Receivable>,IAuditController<Receivable>,ISubmitController<Receivable>{
    @Autowired
    private ReceivableService receivableService;

    @Override
    public Response Find(@RequestBody Request<Receivable> receivable) {
        return receivableService.find(receivable);
    }

    @Override
    public Response Save(@RequestBody Request<Receivable> receivable) {
        return receivableService.save(receivable);
    }

    @Override
    public Response Delete(@RequestBody Request<String> receivable) {
        return receivableService.delete(receivable);
    }

    @Override
    public Response Audit(@RequestBody Request<Receivable> receivable) {
        return receivableService.audit(receivable);
    }

    @Override
    public Response Cancel(@RequestBody Request<Receivable> receivable) {
        return receivableService.cancel(receivable);
    }

    @Override
    public Response Submit(@RequestBody Request<Receivable> receivable) {
        return receivableService.submit(receivable);
    }

    @RequestMapping(value = "/saveaudit",method = RequestMethod.POST)
    public Response saveAudit(@RequestBody Request<Receivable> receivable) {
        return receivableService.saveAudit(receivable);
    }

    @RequestMapping(value = "/saveall",method = RequestMethod.POST)
    public Response saveAll(@RequestBody Request<Receivable> receivable) {
        return receivableService.saveAll(receivable);
    }
}
