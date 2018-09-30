package com.wuxue.api.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wuxue.api.mapper.*;
import com.wuxue.api.service.SysAutoCodeService;
import com.wuxue.api.service.UtilsService;
import com.wuxue.api.utils.CommonUtils;
import com.wuxue.model.*;
import com.wuxue.api.service.ReceiptService;
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
import java.util.Map;

//import com.wuxue.api.utils.GuidUtils;

@Service("receiptService")
public class ReceiptServiceImpl implements ReceiptService {
    @Autowired
    ReceiptMapper receiptMapper;
    @Autowired
    UtilsService utilsService;
    @Autowired
    StudentSignupMapper studentSignupMapper;
    @Autowired
    ReceivableMapper receivableMapper;
    @Autowired
    StudentMapper studentMapper;
    @Autowired
    SysAutoCodeService sysAutoCodeService;
    @Autowired
    private StudentPhaseRecordMapper studentPhaseRecordMapper;
    @Autowired
    private ClassinfoStudentMapper classinfoStudentMapper;

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
            iReuslt = receiptMapper.deleteByPrimaryKey(primaryKey);
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
    public Response find(Request<Receipt> tParams) {
        Response response = Response.newResponse();
        Receipt receipt = tParams.getData();

        if (receipt == null) {
            return response.PARAMS_ISNULL();
        }
        String primaryKey = receipt.getPkReceipt();
        if (primaryKey != null && !primaryKey.equals("")) {
            Receipt byPrimaryKey = receiptMapper.selectByPrimaryKey(primaryKey);
            utilsService.setSysUserKeyValue(byPrimaryKey, byPrimaryKey.getCreator(), LinkEntity.CREATOR_ENTITY);
            utilsService.setSysUserKeyValue(byPrimaryKey, byPrimaryKey.getModifier(), LinkEntity.MODIFIER_ENTITY);
            utilsService.setSysUserKeyValue(byPrimaryKey, byPrimaryKey.getSubmitor(), LinkEntity.SUBMITOR_ENTITY);
            utilsService.setSysUserKeyValue(byPrimaryKey, byPrimaryKey.getAuditor(), LinkEntity.AUDITOR_ENTITY);
            utilsService.setSysUserKeyValue(byPrimaryKey, byPrimaryKey.getCancel(), LinkEntity.CANCEL_ENTITY);
            utilsService.setEmployeeKeyValue(byPrimaryKey, byPrimaryKey.getPkSysUser(), LinkEntity.EMP_ENTITY);
            utilsService.setExpenseItemKeyValue(byPrimaryKey,byPrimaryKey.getPkExpenseItem(),LinkEntity.ITEMS_ENTITY);
            response.setData(byPrimaryKey);
        } else {
            PageHelper.startPage(receipt.getPageNo(), receipt.getPageSize());
            List<Receipt> receiptList = receiptMapper.select(receipt);
            PageInfo pageInfo = new PageInfo(receiptList);
            response.setTotal(pageInfo.getTotal());
            if (receiptList.size() > 0) {
                for (Receipt list : receiptList) {
                    utilsService.setSysUserKeyValue(list, list.getCreator(), LinkEntity.CREATOR_ENTITY);
                    utilsService.setSysUserKeyValue(list, list.getModifier(), LinkEntity.MODIFIER_ENTITY);
                    utilsService.setSysUserKeyValue(list, list.getSubmitor(), LinkEntity.SUBMITOR_ENTITY);
                    utilsService.setSysUserKeyValue(list, list.getAuditor(), LinkEntity.AUDITOR_ENTITY);
                    utilsService.setSysUserKeyValue(list, list.getCancel(), LinkEntity.CANCEL_ENTITY);
                    utilsService.setEmployeeKeyValue(list, list.getPkSysUser(), LinkEntity.EMP_ENTITY);
                    utilsService.setStudentKeyValue(list, list.getPkStudent(), LinkEntity.STUDENT_ENTITY);
                    utilsService.setExpenseItemKeyValue(list,list.getPkExpenseItem(),LinkEntity.ITEMS_ENTITY);
                }
            }
            response.setData(receiptList);
            //response.setTotal(receiptMapper.countBy(receipt));

        }
        return response;
    }

    @Override
    @Transactional
    public Response save(Request<Receipt> tParams) {
        Response response = Response.newResponse();
        Receipt receipt = tParams.getData();

        if (receipt == null) {
            return response.PARAMS_ISNULL();
        }
        String primaryKey = receipt.getPkReceipt();
        Receipt select = null;
        if (primaryKey != null && !primaryKey.equals("")) {
            select = receiptMapper.selectByPrimaryKey(primaryKey);
        }
        int iReuslt = 0;
        String message = "";
        try {
            if (select != null) {
                receipt.setLasteditDate(new Date());
                iReuslt = receiptMapper.updateByPrimaryKeySelective(receipt);
            } else {

                String pkExpenseItem = receipt.getPkExpenseItem();
                if (pkExpenseItem.contains(",")) {
                    String[] split = pkExpenseItem.split(",");
                    for (String s : split) {
                        iReuslt = saveReceipt(receipt, tParams.getCurrentUser(), iReuslt, s);
                    }
                } else {
                    iReuslt = saveReceipt(receipt, tParams.getCurrentUser(), iReuslt, pkExpenseItem);
                }
                Student student = studentMapper.selectByPrimaryKey(receipt.getPkStudent());
                if (student != null && student.getIstype() != null && student.getIstype()==4){
                    student.setIstype(3);
                    iReuslt = studentMapper.updateByPrimaryKey(student);
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
            message = ex.getMessage();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return response.SAVE_FAIL(message);
        }
        if (iReuslt > 0) {
            return response;
        }
        return response.SAVE_FAIL(message);
    }

    //保存应收款
    public Integer saveReceipt(Receipt receipt, String pkSysUser, Integer iReuslt, String pkExpenseItem) {
        String[] split = pkExpenseItem.split("=");
        if (split.length > 0) {


            receipt.setPkExpenseItem(split[0]);
            receipt.setCost(BigDecimal.valueOf(Double.parseDouble(split[1])));
            receipt.setPkReceipt(GuidUtils.getGuid());
            receipt.setCode(sysAutoCodeService.getCode("receiptcode"));
            receipt.setMoney(BigDecimal.valueOf(0));
            receipt.setCreationDate(new Date());
            receipt.setLasteditDate(new Date());
            iReuslt = receiptMapper.insertSelective(receipt);

//                反写金额入应收款已收款字段
            Receivable receivable = receivableMapper.selectByPrimaryKey(receipt.getPkParent());
            if (receivable.getMoney() == null) {
                receivable.setMoney(BigDecimal.valueOf(0));
            }
            BigDecimal countMoney = receipt.getCost().add(receivable.getMoney());
//                判断金额是否收齐，收齐应收款状态修改
            if (receivable.getCost().compareTo(countMoney) == 0) {
                receivable.setStatus(1);
                Student student = studentMapper.selectByPrimaryKey(receivable.getPkStudent());
                if (student.getIstype() != 1) {
                    student.setIstype(2);
                    student.setModifier(receipt.getAuditor());
                    student.setLasteditDate(new Date());
                    iReuslt = studentMapper.updateByPrimaryKeySelective(student);
                }
            }
            receivable.setMoney(countMoney);
            receivable.setModifier(pkSysUser);
            receivable.setLasteditDate(new Date());
            iReuslt = receivableMapper.updateByPrimaryKeySelective(receivable);
        }
        return iReuslt;
    }
//    @Override
//    public Response save(Request<Receipt> tParams) {
//        Response response = Response.newResponse();
//        Receipt receipt = tParams.getData();
//
//        if(receipt== null){
//            return  response.PARAMS_ISNULL();
//        }
//        String primaryKey = receipt.getPkReceipt();
//        Receipt select = null;
//        if (primaryKey != null && !primaryKey.equals("")) {
//            select = receiptMapper.selectByPrimaryKey(primaryKey);
//        }
//        int iReuslt = 1;
//        String message= "";
//        try {
//            if (select != null) {
//                receipt.setLasteditDate(new Date());
//                iReuslt = receiptMapper.updateByPrimaryKeySelective(receipt);
//            } else {
//                receipt.setCost(receipt.getMoney());
//                receipt.setCode(sysAutoCodeService.getCode("receiptcode"));
//                receipt.setMoney(BigDecimal.valueOf(0));
//                receipt.setCreationDate(new Date());
//                receipt.setLasteditDate(new Date());
//                iReuslt = receiptMapper.insertSelective(receipt);
//
////                反写金额入应收款已收款字段
//                Receivable receivable = receivableMapper.selectByPrimaryKey(receipt.getPkParent());
//                if(receivable.getMoney() == null){
//                    receivable.setMoney(BigDecimal.valueOf(0));
//                }
//                BigDecimal countMoney = receipt.getCost().add(receivable.getMoney());
////                判断金额是否收齐，收齐应收款状态修改
//                if(receivable.getCost().compareTo(countMoney) == 0){
//                    receivable.setStatus(1);
//                    Student student = studentMapper.selectByPrimaryKey(receivable.getPkStudent());
//                    if(student.getIstype() != 1){
//                        student.setIstype(2);
//                        student.setModifier(receipt.getAuditor());
//                        student.setLasteditDate(new Date());
//                        studentMapper.updateByPrimaryKeySelective(student);
//                    }
//                }
//                receivable.setMoney(countMoney);
//                receivable.setModifier(tParams.getCurrentUser());
//                receivable.setLasteditDate(new Date());
//                receivableMapper.updateByPrimaryKeySelective(receivable);
//            }
//        }catch (Exception ex){
//            message = ex.getMessage();
//            return response.SAVE_FAIL(message);
//        }
//        if(iReuslt>0) {
//            return response;
//        }
//        return response.SAVE_FAIL(message);
//    }

    @Override
    public Response audit(Request<Receipt> tParams) {
        Response response = Response.newResponse();
        Receipt receipt = tParams.getData();


        if (receipt == null) {
            return response.PARAMS_ISNULL();
        }

        String primaryKey = receipt.getPkReceipt();
        int iReuslt = 1;
        String message = "";
        if (primaryKey != null && !primaryKey.equals("")) {
            try {
                Receipt byPrimaryKey = receiptMapper.selectByPrimaryKey(primaryKey);

                Response verification = CommonUtils.auditVerification(byPrimaryKey);
                if (verification == null) {
                    if (byPrimaryKey.getIsaudit() == 0) {
                        byPrimaryKey.setAuditor(receipt.getAuditor());
                        byPrimaryKey.setIsaudit(1);
                        byPrimaryKey.setAuditDate(new Date());
                        byPrimaryKey.setModifier(receipt.getAuditor());
                        byPrimaryKey.setLasteditDate(new Date());
                        receiptMapper.updateByPrimaryKeySelective(byPrimaryKey);

                        BigDecimal countMoney = receiptMapper.countMoney(byPrimaryKey);
//                        判断收款单已审核过的费用是否等于应收单应收费用，如果相同则反写应收单金额和状态
                        Receivable receivable = receivableMapper.selectByPrimaryKey(byPrimaryKey.getPkParent());
                        if (receivable.getCost().compareTo(countMoney) == 0) {
                            receivable.setStatus(1);
                            Student student = studentMapper.selectByPrimaryKey(receivable.getPkStudent());
                            if (student.getIstype() != 1) {
                                student.setIstype(2);
                                student.setModifier(receipt.getAuditor());
                                student.setLasteditDate(new Date());
                                studentMapper.updateByPrimaryKeySelective(student);
                            }

                        StudentSignup studentSignup = studentSignupMapper.selectByPrimaryKey(receivable.getPkParent());
                        studentSignup.setStatus(1);
                        studentSignup.setReviewStatus(3);
                        studentSignup.setModifier(receipt.getAuditor());
                        studentSignup.setLasteditDate(new Date());
                        studentSignupMapper.updateByPrimaryKeySelective(studentSignup);
                        }
                        receivable.setModifier(receipt.getAuditor());
                        receivable.setLasteditDate(new Date());
                        receivable.setMoney(countMoney);
                        receivableMapper.updateByPrimaryKeySelective(receivable);

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
    public Response submit(Request<Receipt> tParams) {
        Response response = Response.newResponse();
        Receipt receipt = tParams.getData();

        if (receipt == null) {
            return response.PARAMS_ISNULL();
        }

        String primaryKey = receipt.getPkReceipt();
        int iReuslt = 1;
        String message = "";
        if (primaryKey != null && !primaryKey.equals("")) {
            try {
                Receipt byPrimaryKey = receiptMapper.selectByPrimaryKey(primaryKey);
                Response verification = CommonUtils.submitVerification(byPrimaryKey);

                if (verification == null) {
                    if (byPrimaryKey.getIssubmit() == 0) {
                        byPrimaryKey.setIssubmit(1);
                        byPrimaryKey.setSubmitor(receipt.getSubmitor());
                        byPrimaryKey.setSubmitDate(new Date());
                        byPrimaryKey.setModifier(receipt.getSubmitor());
                        byPrimaryKey.setLasteditDate(new Date());
                        iReuslt = receiptMapper.updateByPrimaryKeySelective(byPrimaryKey);
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

    @Override
    public Response retreat(Request<Receipt> tParams) {
        Response response = Response.newResponse();
        Receipt receipt = tParams.getData();

        if (receipt == null) {
            return response.PARAMS_ISNULL();
        }

        String primaryKey = receipt.getPkReceipt();
        if (primaryKey != null && !primaryKey.equals("")) {
            try {
                Receipt byPrimaryKey = receiptMapper.selectByPrimaryKey(primaryKey);
                if (byPrimaryKey.getIsaudit() != null && byPrimaryKey.getIsaudit() == 1) {
                    response.setCode(1052);
                    response.setMessage("审核后，不能驳回");
                    return response;
                }
                if (byPrimaryKey.getIssubmit() == 1) {
                    byPrimaryKey.setIssubmit(0);
                    byPrimaryKey.setSubmitor(null);
                    byPrimaryKey.setSubmitDate(null);
                    receiptMapper.updateByPrimaryKey(byPrimaryKey);
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
    public Response findFroRefund(Request<Receipt> receiptRequest) {
        Response response = Response.newResponse();
        Receipt receipt = receiptRequest.getData();
        PageHelper.startPage(receipt.getPageNo(), receipt.getPageSize());
        List<Map<String,Object>> receiptList  = receiptMapper.selectFroRefund(receipt);
        PageInfo pageInfo = new PageInfo(receiptList);
        response.setTotal(pageInfo.getTotal());
//        if (receiptList.size() > 0) {
//            for (Receipt list : receiptList) {
//                utilsService.setExpenseItemKeyValue(list,list.getPkExpenseItem(),LinkEntity.ITEMS_ENTITY);
//            }
//        }
        response.setData(receiptList);
        return response;
    }
}
