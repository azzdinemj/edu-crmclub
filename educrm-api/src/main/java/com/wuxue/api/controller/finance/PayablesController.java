package com.wuxue.api.controller.finance;

import com.wuxue.api.interfaces.*;
import com.wuxue.model.Payables;
import com.wuxue.api.service.PayablesService;
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
@RequestMapping(value = "api/finance/payables")
public class PayablesController implements IFindController<Payables>,
        ISaveController<Payables>,IDeleteController<String>,ISubmitController<Payables>,IAuditController<Payables> {
    @Autowired
    private PayablesService payablesService;

    @Override
    public Response Find(@RequestBody Request<Payables> payables) {
        return payablesService.find(payables);
    }

    @Override
    public Response Save(@RequestBody Request<Payables> payables) {
        return payablesService.save(payables);
    }
    @Override

    public Response Delete(@RequestBody Request<String> payables) {
        return payablesService.delete(payables);

    }

    @Override
    public Response Audit(@RequestBody Request<Payables> payables) {
        return payablesService.audit(payables);
    }

    @Override
    public Response Submit(@RequestBody Request<Payables> payables) {
        return payablesService.submit(payables);
    }

    @RequestMapping(value = "/receiptsavepayables",method = RequestMethod.POST)
    public Response receiptSavePayables(@RequestBody Request<Payables> payables) {
        return payablesService.receiptSavePayables(payables);
    }

    @RequestMapping(value = "/refund",method = RequestMethod.POST)
    public Response refund(@RequestBody Request<Payables> payables) {
        return payablesService.refund(payables);
    }
}
