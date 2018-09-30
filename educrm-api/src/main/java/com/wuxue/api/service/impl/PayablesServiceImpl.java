package com.wuxue.api.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wuxue.api.mapper.*;
import com.wuxue.api.service.SysAutoCodeService;
import com.wuxue.api.service.UtilsService;
import com.wuxue.api.utils.CommonUtils;
import com.wuxue.model.*;
import com.wuxue.api.service.PayablesService;
import com.wuxue.api.utils.GuidUtils;
import com.wuxue.utils.contract.Light;
import com.wuxue.utils.contract.LinkEntity;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

//import com.wuxue.api.utils.GuidUtils;

@Service("payablesService")
public class PayablesServiceImpl implements PayablesService {
    @Autowired
    PayablesMapper payablesMapper;
    @Autowired
    UtilsService utilsService;
    @Autowired
    ReceiptMapper receiptMapper;
    @Autowired
    PaymentMapper paymentMapper;
    @Autowired
    private ExpenseItemMapper expenseItemMapper;
    @Autowired
    private SysAutoCodeService sysAutoCodeService;
    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private ClassinfoStudentMapper classinfoStudentMapper;
    @Autowired
    private StudentPhaseRecordMapper studentPhaseRecordMapper;

    @Override
    public Response delete(Request<String> tParams) {
        Response response = Response.newResponse();
        String primaryKey = tParams.getData();
        if (primaryKey == null || primaryKey.equals("")) {
            return response.PARAMS_ISNULL();
        }
        int iReuslt = 1;
        String message = "";
        try {
            iReuslt = payablesMapper.deleteByPrimaryKey(primaryKey);
        } catch (Exception ex) {
            message = ex.getMessage();
            return response.DELETE_FAIL(message);
        }
        if (iReuslt > 0) {
            return response;
        }
        return response.DELETE_FAIL(message);
    }

    @Override
    public Response find(Request<Payables> tParams) {
        Response response = Response.newResponse();
        Payables payables = tParams.getData();

        if (payables == null) {
            return response.PARAMS_ISNULL();
        }
        String primaryKey = payables.getPkPayables();
        if (primaryKey != null && !primaryKey.equals("")) {
            Payables byPrimaryKey = payablesMapper.selectByPrimaryKey(primaryKey);
            utilsService.setSysUserKeyValue(byPrimaryKey, byPrimaryKey.getCreator(), LinkEntity.CREATOR_ENTITY);
            utilsService.setRefundStatusKeyValue(byPrimaryKey, byPrimaryKey.getPkPayables(), LinkEntity.REFUND_STATUS);
            utilsService.setSysUserKeyValue(byPrimaryKey, byPrimaryKey.getModifier(), LinkEntity.MODIFIER_ENTITY);
            utilsService.setSysUserKeyValue(byPrimaryKey, byPrimaryKey.getSubmitor(), LinkEntity.SUBMITOR_ENTITY);
            utilsService.setSysUserKeyValue(byPrimaryKey, byPrimaryKey.getAuditor(), LinkEntity.AUDITOR_ENTITY);
            utilsService.setSysUserKeyValue(byPrimaryKey, byPrimaryKey.getCancel(), LinkEntity.CANCEL_ENTITY);
            utilsService.setEmployeeKeyValue(byPrimaryKey, byPrimaryKey.getPkSysUser(), LinkEntity.EMP_ENTITY);

            response.setData(byPrimaryKey);
        } else {
            PageHelper.startPage(payables.getPageNo(), payables.getPageSize());
            List<Payables> payablesList = payablesMapper.select(payables);
            PageInfo page = new PageInfo(payablesList);
            response.setTotal(page.getTotal());
            if (payablesList.size() > 0) {
                for (Payables list : payablesList) {
                    utilsService.setSysUserKeyValue(list, list.getCreator(), LinkEntity.CREATOR_ENTITY);
                    utilsService.setRefundStatusKeyValue(list, list.getPkPayables(), LinkEntity.REFUND_STATUS);
                    utilsService.setSysUserKeyValue(list, list.getModifier(), LinkEntity.MODIFIER_ENTITY);
                    utilsService.setSysUserKeyValue(list, list.getSubmitor(), LinkEntity.SUBMITOR_ENTITY);
                    utilsService.setSysUserKeyValue(list, list.getAuditor(), LinkEntity.AUDITOR_ENTITY);
                    utilsService.setSysUserKeyValue(list, list.getCancel(), LinkEntity.CANCEL_ENTITY);
                    utilsService.setEmployeeKeyValue(list, list.getPkSysUser(), LinkEntity.EMP_ENTITY);
                    utilsService.setStudentKeyValue(list, list.getPkStudent(), LinkEntity.STUDENT_ENTITY);

                }
            }
            response.setData(payablesList);
            response.setTotal(payablesMapper.countBy(payables));

        }
        return response;
    }

    @Override
    public Response save(Request<Payables> tParams) {
        Response response = Response.newResponse();
        Payables payables = tParams.getData();

        if (payables == null) {
            return response.PARAMS_ISNULL();
        }
        String primaryKey = payables.getPkPayables();
        Payables select = null;
        if (primaryKey != null && !primaryKey.equals("")) {
            select = payablesMapper.selectByPrimaryKey(primaryKey);
        }
        int iReuslt = 1;
        String message = "";
        try {
            if (select != null) {
                payables.setLasteditDate(new Date());
                payables.setModifier(tParams.getCurrentUser());
                iReuslt = payablesMapper.updateByPrimaryKeySelective(payables);
            } else {
                payables.setPkPayables(GuidUtils.getGuid());
                payables.setCreationDate(new Date());
                payables.setLasteditDate(new Date());
                iReuslt = payablesMapper.insertSelective(payables);
            }
        } catch (Exception ex) {
            message = ex.getMessage();
            return response.SAVE_FAIL(message);
        }
        if (iReuslt > 0) {
            return response;
        }
        return response.SAVE_FAIL(message);
    }

    @Override
    public Response audit(Request<Payables> tParams) {
        Response response = Response.newResponse();
        Payables payables = tParams.getData();


        if (payables == null) {
            return response.PARAMS_ISNULL();
        }

        String primaryKey = payables.getPkPayables();
        int iReuslt = 1;
        String message = "";
        if (primaryKey != null && !primaryKey.equals("")) {
            try {
                Payables byPrimaryKey = payablesMapper.selectByPrimaryKey(primaryKey);

                Response verification = CommonUtils.auditVerification(byPrimaryKey);
                if (verification == null) {
                    if (byPrimaryKey.getIsaudit() == 0 || byPrimaryKey.getIsaudit().equals("")) {
                        byPrimaryKey.setAuditor(tParams.getCurrentUser());
                        byPrimaryKey.setIsaudit(1);
                        byPrimaryKey.setAuditDate(new Date());
                        byPrimaryKey.setModifier(tParams.getCurrentUser());
                        byPrimaryKey.setLasteditDate(new Date());
                        payablesMapper.updateByPrimaryKeySelective(byPrimaryKey);
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

    @Override
    public Response submit(Request<Payables> tParams) {
        Response response = Response.newResponse();
        Payables payables = tParams.getData();


        if (payables == null) {
            return response.PARAMS_ISNULL();
        }

        String primaryKey = payables.getPkPayables();
        int iReuslt = 1;
        String message = "";
        if (primaryKey != null && !primaryKey.equals("")) {
            try {
                Payables byPrimaryKey = payablesMapper.selectByPrimaryKey(primaryKey);

                Response verification = CommonUtils.submitVerification(byPrimaryKey);
                if (verification == null) {
                    if (byPrimaryKey.getIssubmit() != 1 || byPrimaryKey.getIssubmit().equals("")) {
                        byPrimaryKey.setSubmitor(tParams.getCurrentUser());
                        byPrimaryKey.setIssubmit(1);
                        byPrimaryKey.setSubmitDate(new Date());
                        byPrimaryKey.setModifier(tParams.getCurrentUser());
                        byPrimaryKey.setLasteditDate(new Date());
                        payablesMapper.updateByPrimaryKeySelective(byPrimaryKey);
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

    @Override
    public Response receiptSavePayables(Request<Payables> tParams) {
        Response response = Response.newResponse();
        Payables payables = tParams.getData();

        if (payables == null) {
            return response.PARAMS_ISNULL();
        }

        String message = "";
        try {
            String[] reveiptList = payables.getPkExpenseItem().split(",");

            BigDecimal sumMoney = BigDecimal.valueOf(0);
            String notes = "";
            if (!reveiptList.equals("")) {
                for (int i = 0; i < reveiptList.length; i++) {
                    Receipt receipt = receiptMapper.selectByPrimaryKey(reveiptList[i]);

                    if (receipt.getPkExpenseItem() != null && !"".equals(receipt.getPkExpenseItem())) {
                        ExpenseItem expenseItem = expenseItemMapper.selectByPrimaryKey(receipt.getPkExpenseItem());
                        if (expenseItem != null) {
                            notes = notes + expenseItem.getCaption() + " ";
                        }
                    }
                    receipt.setIspayment(1);
                    receiptMapper.updateByPrimaryKey(receipt);
                    sumMoney = receipt.getCost().add(sumMoney);
                }
            }

            payables.setPkExpenseItem(null);
            payables.setDate(new Date());
            payables.setCost(sumMoney);
            payables.setCreationDate(new Date());
            payables.setLasteditDate(new Date());
            payables.setCreator(tParams.getCurrentUser());
            payables.setModifier(tParams.getCurrentUser());
            payables.setStatus(0);
            payables.setNotes(notes);
            int i = payablesMapper.insertSelective(payables);


        } catch (Exception ex) {
            message = ex.getMessage();
            return response.SAVE_FAIL(message);
        }

        return response;
    }

    @Override
    @Transactional
    public Response refund(Request<Payables> tParams) {
        Response response = Response.newResponse();
        Payables payables = tParams.getData();

        if (payables == null && payables.getPkPayables() != null) {
            return response.PARAMS_ISNULL();
        }

        Payables byPrimaryKey = payablesMapper.selectByPrimaryKey(payables.getPkPayables());
        if (byPrimaryKey == null) {
            return response.PARAMS_ISNULL();
        }

        String meassge = "";
        try {
            Payment payment = new Payment();
            payment.setDate(new Date());
            payment.setCreationDate(new Date());
            payment.setLasteditDate(new Date());
            payment.setPkPayment(GuidUtils.getGuid());
            payment.setCode(sysAutoCodeService.getCode("paymentcode"));
            payment.setPkDomain(byPrimaryKey.getPkDomain());
            payment.setCost(payables.getCost());
            payment.setPkStudent(byPrimaryKey.getPkStudent());
            payment.setCreator(tParams.getCurrentUser());
            payment.setModifier(tParams.getCurrentUser());
            payment.setNotes(payables.getNotes());
            paymentMapper.insertSelective(payment);

//            修改应付单状态
            byPrimaryKey.setStatus(2);
            byPrimaryKey.setModifier(tParams.getCurrentUser());
            byPrimaryKey.setLasteditDate(new Date());
            int i = payablesMapper.updateByPrimaryKeySelective(byPrimaryKey);
            if (i > 0) {
                Student student = studentMapper.selectByPrimaryKey(payables.getPkStudent());
                if (student != null && student.getIstype() != null && student.getIstype() == 4) {
                    student.setIstype(3);
                    studentMapper.updateByPrimaryKey(student);
                    //记录学生退学状态
                    StudentPhaseRecord studentPhaseRecord = new StudentPhaseRecord();
                    studentPhaseRecord.setType(Light.DROP_SCHOOL_CODE);
                    ClassinfoStudent classinfoStudent = new ClassinfoStudent();
                    classinfoStudent.setPkStudent(student.getPkStudent());
                    List<ClassinfoStudent> classinfoStudentList = classinfoStudentMapper.selectPkClassinfo(classinfoStudent);
                    if (classinfoStudentList.size() > 0) {
                        studentPhaseRecord.setPkClassinfo(classinfoStudentList.get(0).getPkClassinfo());
                    }
                    studentPhaseRecord.setPkDomain(tParams.getCurrendDomain());
                    studentPhaseRecord.setCode(sysAutoCodeService.getCode("phaserecordcode"));
                    studentPhaseRecord.setPkStudentPhaseRecord(GuidUtils.getGuid());
                    studentPhaseRecord.setStageTime(new Date());
                    studentPhaseRecordMapper.insertSelective(studentPhaseRecord);
                    classinfoStudentMapper.updateByPkStudent(student.getPkStudent());
                }


            }

        } catch (Exception ex) {
            meassge = ex.getMessage();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return response.SAVE_FAIL(meassge);
        }
        return response;
    }
}
