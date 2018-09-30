package com.wuxue.api.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wuxue.api.mapper.PaymentMapper;
import com.wuxue.api.service.UtilsService;
import com.wuxue.api.utils.CommonUtils;
import com.wuxue.model.Payment;
import com.wuxue.api.service.PaymentService;
import com.wuxue.api.utils.GuidUtils;
import com.wuxue.utils.contract.LinkEntity;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

//import com.wuxue.api.utils.GuidUtils;

@Service("paymentService")
public class PaymentServiceImpl implements PaymentService{
    @Autowired
    PaymentMapper paymentMapper;
    @Autowired
    UtilsService utilsService;

    @Override
    public Response delete(Request<String> tParams) {
        Response response = Response.newResponse();
        String primaryKey = tParams.getData();
        if(primaryKey== null || primaryKey.equals("")){
            return  response.PARAMS_ISNULL();
        }
        int iReuslt = 1;
        String message= "";
        try {
            iReuslt=paymentMapper.deleteByPrimaryKey(primaryKey);
        }catch (Exception ex){
            message = ex.getMessage();
            return response.SAVE_FAIL(message);
        }
        if(iReuslt>0) {
            return response;
        }
        return response.DELETE_FAIL(message);
    }

    @Override
    public Response find(Request<Payment> tParams) {
        Response response = Response.newResponse();
        Payment payment = tParams.getData();

        if(payment== null){
            return  response.PARAMS_ISNULL();
        }
        String primaryKey = payment.getPkPayment();
        if(primaryKey !=null && !primaryKey.equals("")){
            Payment byPrimaryKey = paymentMapper.selectByPrimaryKey(primaryKey);
            utilsService.setSysUserKeyValue(byPrimaryKey,byPrimaryKey.getCreator(), LinkEntity.CREATOR_ENTITY);
            utilsService.setSysUserKeyValue(byPrimaryKey,byPrimaryKey.getModifier(),LinkEntity.MODIFIER_ENTITY);
            utilsService.setSysUserKeyValue(byPrimaryKey,byPrimaryKey.getSubmitor(),LinkEntity.SUBMITOR_ENTITY);
            utilsService.setSysUserKeyValue(byPrimaryKey,byPrimaryKey.getAuditor(),LinkEntity.AUDITOR_ENTITY);
            utilsService.setSysUserKeyValue(byPrimaryKey,byPrimaryKey.getCancel(),LinkEntity.CANCEL_ENTITY);
            utilsService.setStudentKeyValue(byPrimaryKey,byPrimaryKey.getPkStudent(),LinkEntity.STUDENT_ENTITY);
            utilsService.setEmployeeKeyValue(byPrimaryKey,byPrimaryKey.getPkSysUser(),LinkEntity.EMP_ENTITY);
            response.setData(byPrimaryKey);
        }else{
            PageHelper.startPage(payment.getPageNo(), payment.getPageSize());
            List<Payment> paymentList = paymentMapper.select(payment);
            PageInfo page = new PageInfo(paymentList);
            response.setTotal(page.getTotal());
            if (paymentList.size() > 0) {
                for (Payment list : paymentList) {
                    utilsService.setSysUserKeyValue(list, list.getCreator(), LinkEntity.CREATOR_ENTITY);
                    utilsService.setSysUserKeyValue(list, list.getModifier(), LinkEntity.MODIFIER_ENTITY);
                    utilsService.setSysUserKeyValue(list, list.getSubmitor(), LinkEntity.SUBMITOR_ENTITY);
                    utilsService.setSysUserKeyValue(list, list.getAuditor(), LinkEntity.AUDITOR_ENTITY);
                    utilsService.setSysUserKeyValue(list, list.getCancel(), LinkEntity.CANCEL_ENTITY);
                    utilsService.setStudentKeyValue(list,list.getPkStudent(),LinkEntity.STUDENT_ENTITY);
                    utilsService.setEmployeeKeyValue(list,list.getPkSysUser(),LinkEntity.EMP_ENTITY);
                }
            }
            response.setData(paymentList);
            //response.setTotal(paymentMapper.countBy(payment));

        }
        return response;
    }

    @Override
    public Response save(Request<Payment> tParams) {
        Response response = Response.newResponse();
        Payment payment = tParams.getData();

        if(payment== null){
            return  response.PARAMS_ISNULL();
        }
        String primaryKey = payment.getPkPayment();
        Payment select = null;
        if (primaryKey != null && !primaryKey.equals("")) {
            select = paymentMapper.selectByPrimaryKey(primaryKey);
        }

        int iReuslt = 1;
        String message= "";
        try {
            if (select != null) {
                payment.setLasteditDate(new Date());
                iReuslt = paymentMapper.updateByPrimaryKeySelective(payment);
            } else {
                payment.setCreationDate(new Date());
                payment.setLasteditDate(new Date());
                payment.setDate(new Date());

                String value = utilsService.getSysSetValue(payment.getPkDomain(), "generate_payment_submit");
                if (value == null || value.equals("true") || value.equals("")) {
                    payment.setIssubmit(1);
                    payment.setSubmitor(payment.getModifier());
                    payment.setSubmitDate(new Date());
                }

                iReuslt = paymentMapper.insertSelective(payment);
            }
        }catch (Exception ex){
            message = ex.getMessage();
            return response.SAVE_FAIL(message);
        }
        if(iReuslt>0) {
            return response;
        }
        return response.SAVE_FAIL(message);
    }

    @Override
    public Response retreat(Request<Payment> tParams) {
        Response response = Response.newResponse();
        Payment payment = tParams.getData();

        if (payment == null) {
            return response.PARAMS_ISNULL();
        }

        String primaryKey = payment.getPkPayment();
        if (primaryKey != null && !primaryKey.equals("")) {
            try {
                Payment byPrimaryKey = paymentMapper.selectByPrimaryKey(primaryKey);
                if (byPrimaryKey.getIsaudit() != null && byPrimaryKey.getIsaudit() == 1) {
                    response.setCode(1052);
                    response.setMessage("审核后，不能驳回");
                    return response;
                }
                if (byPrimaryKey.getIssubmit() == 1) {
                    byPrimaryKey.setIssubmit(0);
                    byPrimaryKey.setSubmitor(null);
                    byPrimaryKey.setSubmitDate(null);
                    paymentMapper.updateByPrimaryKey(byPrimaryKey);
                } else {
                    return response.SAVE_DOUBLE();
                }
            } catch (Exception ex) {
                response.setCode(1052);
                response.setMessage("驳回失败");
                return response;
            }
        } else {
            return response.PARAMS_ISNULL();
        }

        return response;
    }

    @Override
    public Response audit(Request<Payment> tParams) {
        Response response = Response.newResponse();
        Payment payment = tParams.getData();


        if(payment== null){
            return  response.PARAMS_ISNULL();
        }

        String primaryKey = payment.getPkPayment();
        int iReuslt = 1;
        String message= "";
        if (primaryKey != null && !primaryKey.equals("")) {
            try {
                Payment byPrimaryKey = paymentMapper.selectByPrimaryKey(primaryKey);

                Response verification = CommonUtils.auditVerification(byPrimaryKey);
                if(verification == null) {
                    if (byPrimaryKey.getIsaudit() == 0 || byPrimaryKey.getIsaudit() == null) {
                        byPrimaryKey.setAuditor(payment.getAuditor());
                        byPrimaryKey.setIsaudit(1);
                        byPrimaryKey.setAuditDate(new Date());
                        byPrimaryKey.setModifier(payment.getAuditor());
                        byPrimaryKey.setLasteditDate(new Date());
                        paymentMapper.updateByPrimaryKeySelective(byPrimaryKey);
                    } else {
                        return response.SAVE_DOUBLE();
                    }
                }
            }catch (Exception ex){
                message = ex.getMessage();
                return response.SAVE_FAIL(message);
            }
        }else{
            return  response.PARAMS_ISNULL();
        }
        if(iReuslt>0) {
            return response;
        }

        return response.SAVE_FAIL(message);
    }

    @Override
    public Response submit(Request<Payment> tParams) {
        Response response = Response.newResponse();
        Payment payment = tParams.getData();

        if (payment == null) {
            return response.PARAMS_ISNULL();
        }

        String primaryKey = payment.getPkPayment();
        int iReuslt = 1;
        String message = "";
        if (primaryKey != null && !primaryKey.equals("")) {
            try {
                Payment byPrimaryKey = paymentMapper.selectByPrimaryKey(primaryKey);
                Response verification = CommonUtils.submitVerification(byPrimaryKey);

                if(verification == null) {
                    if (byPrimaryKey.getIssubmit() == 0) {
                        byPrimaryKey.setIssubmit(1);
                        byPrimaryKey.setSubmitor(payment.getSubmitor());
                        byPrimaryKey.setSubmitDate(new Date());
                        byPrimaryKey.setModifier(payment.getSubmitor());
                        byPrimaryKey.setLasteditDate(new Date());
                        iReuslt = paymentMapper.updateByPrimaryKeySelective(byPrimaryKey);
//                        receiptMapper.updateByPrimaryKeySelective(receipt);
                    } else {
                        return response.SAVE_DOUBLE();
                    }
                }
            } catch (Exception ex) {
                message = ex.getMessage();
                return response.SAVE_FAIL(message);
            }
        } else {
            return response.PARAMS_ISNULL();
        }
        if (iReuslt > 0) {
            return response;
        }

        return response.SAVE_FAIL(message);
    }
}
