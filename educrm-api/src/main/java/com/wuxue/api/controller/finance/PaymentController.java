package com.wuxue.api.controller.finance;

import com.wuxue.api.interfaces.*;
import com.wuxue.model.Payment;
import com.wuxue.api.service.PaymentService;
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
@RequestMapping(value = "api/finance/payment")
public class PaymentController implements IFindController<Payment>,
        ISaveController<Payment>,IDeleteController<String>,IAuditController<Payment>,ISubmitController<Payment> {
    @Autowired
    private PaymentService paymentService;

    @Override
    public Response Find(@RequestBody Request<Payment> payment) {
        return paymentService.find(payment);
    }

    @Override
    public Response Save(@RequestBody Request<Payment> payment) {
        return paymentService.save(payment);
    }
    @Override

    public Response Delete(@RequestBody Request<String> payment) {
        return paymentService.delete(payment);

    }

    @Override
    public Response Audit(@RequestBody Request<Payment> payment) {
        return paymentService.audit(payment);
    }

    @Override
    public Response Submit(@RequestBody Request<Payment> payment) {
        return paymentService.submit(payment);
    }

    @RequestMapping(value = "/retreat",method = RequestMethod.POST)
    public Response Retreat(@RequestBody Request<Payment> payment){
        return paymentService.retreat(payment);
    }
}
