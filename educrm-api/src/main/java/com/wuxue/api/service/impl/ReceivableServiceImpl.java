package com.wuxue.api.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wuxue.api.mapper.*;
import com.wuxue.api.service.SysAutoCodeService;
import com.wuxue.api.service.UtilsService;
import com.wuxue.api.utils.CommonUtils;
import com.wuxue.model.*;
import com.wuxue.api.service.ReceivableService;
import com.wuxue.api.utils.GuidUtils;
import com.wuxue.utils.common.DataUtils;
import com.wuxue.utils.contract.Light;
import com.wuxue.utils.contract.LinkEntity;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

//import com.wuxue.api.utils.GuidUtils;

@Service("receivableService")
public class ReceivableServiceImpl implements ReceivableService {
    @Autowired
    ReceivableMapper receivableMapper;
    @Autowired
    SysSetMapper sysSetMapper;
    @Autowired
    UtilsService utilsService;
    @Autowired
    TaskMapper taskMapper;
    @Autowired
    SysAutoCodeService sysAutoCodeService;
    @Autowired
    ReceiptMapper receiptMapper;
    @Autowired
    StudentSignupMapper studentSignupMapper;
    @Autowired
    StudentSignupDetailsMapper studentSignupDetailsMapper;
    @Autowired
    ClassinfoStudentMapper classinfoStudentMapper;

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
            iReuslt = receivableMapper.deleteByPrimaryKey(primaryKey);
        } catch (Exception ex) {
            message = ex.getMessage();
            return response.SAVE_FAIL(message);
        }
        if (iReuslt > 0) {
            return response;
        }
        return response.DELETE_FAIL(message);
    }

    @Override
    public Response find(Request<Receivable> tParams) {
        Response response = Response.newResponse();
        Receivable receivable = tParams.getData();

        if (receivable == null) {
            return response.PARAMS_ISNULL();
        }
        String primaryKey = receivable.getPkReceivable();
        if (primaryKey != null && !primaryKey.equals("")) {
            Receivable byPrimaryKey = receivableMapper.selectByPrimaryKey(primaryKey);
            utilsService.setSysUserKeyValue(byPrimaryKey, byPrimaryKey.getCreator(), LinkEntity.CREATOR_ENTITY);
            utilsService.setSysUserKeyValue(byPrimaryKey, byPrimaryKey.getModifier(), LinkEntity.MODIFIER_ENTITY);
            utilsService.setSysUserKeyValue(byPrimaryKey, byPrimaryKey.getSubmitor(), LinkEntity.SUBMITOR_ENTITY);
            utilsService.setSysUserKeyValue(byPrimaryKey, byPrimaryKey.getAuditor(), LinkEntity.AUDITOR_ENTITY);
            utilsService.setSysUserKeyValue(byPrimaryKey, byPrimaryKey.getCancel(), LinkEntity.CANCEL_ENTITY);
            utilsService.setEmployeeKeyValue(byPrimaryKey, byPrimaryKey.getPkSysUser(), LinkEntity.EMP_ENTITY);
            utilsService.setReceiptMoney(byPrimaryKey, byPrimaryKey.getPkReceivable(), LinkEntity.RECEIPT_MONEY);
            response.setData(byPrimaryKey);
        } else {
            PageHelper.startPage(receivable.getPageNo(), receivable.getPageSize());
            List<Receivable> receivableList = receivableMapper.select(receivable);
            PageInfo pageInfo = new PageInfo(receivableList);
            response.setTotal(pageInfo.getTotal());
            if (receivableList.size() > 0) {
                for (Receivable list : receivableList) {
                    utilsService.setSysUserKeyValue(list, list.getCreator(), LinkEntity.CREATOR_ENTITY);
                    utilsService.setSysUserKeyValue(list, list.getModifier(), LinkEntity.MODIFIER_ENTITY);
                    utilsService.setSysUserKeyValue(list, list.getSubmitor(), LinkEntity.SUBMITOR_ENTITY);
                    utilsService.setSysUserKeyValue(list, list.getAuditor(), LinkEntity.AUDITOR_ENTITY);
                    utilsService.setSysUserKeyValue(list, list.getCancel(), LinkEntity.CANCEL_ENTITY);
                    utilsService.setSysDictKeyValue(list, list.getSchoolYear(), LinkEntity.SCHOOL_YEAR_ENTITY);
                    utilsService.setEmployeeKeyValue(list, list.getPkSysUser(), LinkEntity.EMP_ENTITY);
                    utilsService.setStudentKeyValue(list, list.getPkStudent(), LinkEntity.STUDENT_ENTITY);
                }
            }

            response.setData(receivableList);
            //response.setTotal(receivableMapper.countBy(receivable));

        }
        return response;
    }

    @Override
    public Response save(Request<Receivable> tParams) {
        Response response = Response.newResponse();
        Receivable receivable = tParams.getData();

        if (receivable == null) {
            return response.PARAMS_ISNULL();
        }
        String primaryKey = receivable.getPkReceivable();
        Receivable select = null;
        if (primaryKey != null && !primaryKey.equals("")) {
            select = receivableMapper.selectByPrimaryKey(primaryKey);
        }
        int iReuslt = 1;
        String message = "";
        try {
            if (select != null) {
                receivable.setModifier(tParams.getCurrentUser());
                receivable.setLasteditDate(new Date());
                iReuslt = receivableMapper.updateByPrimaryKeySelective(receivable);
            } else {
//                receivable.setPkReceivable(GuidUtils.getGuid());
                receivable.setModifier(tParams.getCurrentUser());
                receivable.setCode(sysAutoCodeService.getCode("receivable"));
                receivable.setCreator(tParams.getCurrentUser());
                receivable.setCurrency("CNY");
                receivable.setCreationDate(new Date());
                receivable.setLasteditDate(new Date());
                iReuslt = receivableMapper.insertSelective(receivable);

                //任务
                Task task = new Task();
                task.setPkTask(GuidUtils.getGuid());
                task.setIsdel(1);
                task.setContent("费用审核");
                // 0财务 1其他
                task.setType(0);
                task.setCreator(tParams.getCurrentUser());
                task.setModifier(tParams.getCurrentUser());
                task.setCreationDate(new Date());
                task.setLasteditDate(new Date());
                taskMapper.insertSelective(task);
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
    public Response audit(Request<Receivable> tParams) {
        Response response = Response.newResponse();
        Receivable receivable = tParams.getData();

        if (receivable == null) {
            return response.PARAMS_ISNULL();
        }

        String primaryKey = receivable.getPkReceivable();
        int iReuslt = 1;
        String message = "";
        if (primaryKey != null && !primaryKey.equals("")) {
            try {
                Receivable byPrimaryKey = receivableMapper.selectByPrimaryKey(primaryKey);
                Response verification = CommonUtils.auditVerification(byPrimaryKey);

                if (verification == null) {
                    if (byPrimaryKey.getIsaudit() == null || byPrimaryKey.getIsaudit() == 0) {
                        iReuslt = receivableMapper.updateByPrimaryKeySelective(receivable);
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
    public Response cancel(Request<Receivable> tParams) {
        Response response = Response.newResponse();
        Receivable receivable = tParams.getData();

        if (receivable == null) {
            return response.PARAMS_ISNULL();
        }

        String primaryKey = receivable.getPkReceivable();
        String message = "";
        try {
            Receivable byPrimaryKey = receivableMapper.selectByPrimaryKey(primaryKey);
            byPrimaryKey.setStatus(2);
            byPrimaryKey.setLasteditDate(new Date());
            byPrimaryKey.setModifier(tParams.getCurrentUser());
            receivableMapper.updateByPrimaryKeySelective(byPrimaryKey);

            if (byPrimaryKey.getPkParent() != null) {
                StudentSignup studentSignup = studentSignupMapper.selectByPrimaryKey(byPrimaryKey.getPkParent());
                studentSignup.setIssubmit(0);
                studentSignup.setReviewStatus(4);
                studentSignup.setAuditOpinion(receivable.getAuditOpinion());
                studentSignup.setModifier(tParams.getCurrentUser());
                studentSignup.setLasteditDate(new Date());
                studentSignupMapper.updateByPrimaryKeySelective(studentSignup);

                if(studentSignup.getPkClassinfo() != null) {
                    ClassinfoStudent classinfoStudent = new ClassinfoStudent();
                    classinfoStudent.setPkStudent(studentSignup.getPkStudent());
                    classinfoStudent.setPkClassinfo(studentSignup.getPkClassinfo());
                    classinfoStudentMapper.deleteByPrimaryKey(classinfoStudent);
                }
            }
        } catch (Exception ex) {
            message = ex.getMessage();
            return response.SAVE_FAIL(message);
        }
        return response;
    }

    @Override
    public Response submit(Request<Receivable> tParams) {
        Response response = Response.newResponse();
        Receivable receivable = tParams.getData();

        if (receivable == null) {
            return response.PARAMS_ISNULL();
        }

        String primaryKey = receivable.getPkReceivable();
        int iReuslt = 1;
        String message = "";
        if (primaryKey != null && !primaryKey.equals("")) {
            try {
                Receivable byPrimaryKey = receivableMapper.selectByPrimaryKey(primaryKey);
                Response verification = CommonUtils.submitVerification(byPrimaryKey);
//                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

                if (verification == null) {
                    if (byPrimaryKey.getSubmitor() == null || "".equals(byPrimaryKey.getSubmitor())) {
                        iReuslt = receivableMapper.updateByPrimaryKeySelective(receivable);
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
    public Response saveAudit(Request<Receivable> tParams) {
        Response response = Response.newResponse();
        Receivable receivable = tParams.getData();

        if (receivable == null) {
            return response.PARAMS_ISNULL();
        }
        String primaryKey = receivable.getPkReceivable();
        Receivable select = null;
        if (primaryKey != null && !primaryKey.equals("")) {
            select = receivableMapper.selectByPrimaryKey(primaryKey);
        }
        String message = "";
        try {
            if (select != null) {
                receivable.setModifier(tParams.getCurrentUser());
                receivable.setLasteditDate(new Date());
                receivableMapper.updateByPrimaryKeySelective(receivable);
            } else {
//                receivable.setPkReceivable(GuidUtils.getGuid());
                receivable.setModifier(tParams.getCurrentUser());
                receivable.setCode(sysAutoCodeService.getCode("receivable"));
                receivable.setCreator(tParams.getCurrentUser());
                if (receivable.getCost() != null) {
                    receivable.setMoney(receivable.getCost());
                    receivable.setStatus(1);
                }
                receivable.setCurrency("CNY");
                receivable.setCreationDate(new Date());
                receivable.setLasteditDate(new Date());
                receivableMapper.insertSelective(receivable);

                Receipt receipt = new Receipt();
                receipt.setCost(receivable.getMoney());
                receipt.setPkReceipt(GuidUtils.getGuid());
                receipt.setPkStudent(receivable.getPkStudent());
                receipt.setPkDomain(tParams.getCurrendDomain());
                receipt.setPkParent(receivable.getPkReceivable());
                receipt.setCode(sysAutoCodeService.getCode("receiptcode"));
                receipt.setMoney(BigDecimal.valueOf(0));
                receipt.setCreator(tParams.getCurrentUser());
                receipt.setModifier(tParams.getCurrentUser());
                receipt.setDate(new Date());
                receipt.setCreationDate(new Date());
                receipt.setLasteditDate(new Date());
                receiptMapper.insertSelective(receipt);
            }
        } catch (Exception ex) {
            message = ex.getMessage();
            return response.SAVE_FAIL(message);
        }
        return response;
    }

    @Override
    public Response saveAll(Request<Receivable> tParams) {
        Response response = Response.newResponse();
        Receivable receivable = tParams.getData();

        if (receivable == null) {
            return response.PARAMS_ISNULL();
        }

        String message = "";
        try {
            //        建立虚拟报名
            StudentSignup studentSignup = new StudentSignup();
            studentSignup.setPkDomain(tParams.getCurrendDomain());
            studentSignup.setPkStudentSignup(GuidUtils.getGuid());
            studentSignup.setCode(GuidUtils.getGuid());
            studentSignup.setCreator(tParams.getCurrentUser());
            studentSignup.setPkEmployee(tParams.getCurrentUser());
            studentSignup.setPkStudent(receivable.getPkStudent());
            studentSignup.setStatus(9);
            studentSignup.setCreationDate(new Date());
            studentSignup.setModifier(tParams.getCurrentUser());
            studentSignup.setLasteditDate(new Date());
            studentSignupMapper.insertSelective(studentSignup);

            //        保存应收款
            receivable.setPkParent(studentSignup.getPkStudentSignup());
            receivable.setModifier(tParams.getCurrentUser());
            receivable.setCode(sysAutoCodeService.getCode("receivable"));
            receivable.setCreator(tParams.getCurrentUser());
            if (receivable.getCost() != null) {
                receivable.setMoney(receivable.getCost());
                receivable.setStatus(1);
            }
            receivable.setCurrency("CNY");
            receivable.setCreationDate(new Date());
            receivable.setLasteditDate(new Date());
            receivableMapper.insertSelective(receivable);


            //            获取费用明细
            Map<String, Object> map = receivable.getMap();
            if (map != null) {
                List<StudentSignupDetails> studentSignupDetailsList = DataUtils.objectToList(map.get(Light.STUDENT_SIGNUP_DETAILS), StudentSignupDetails.class);
                if (studentSignupDetailsList != null && studentSignupDetailsList.size() > 0) {
                    for (StudentSignupDetails studentSignupDetails : studentSignupDetailsList) {
                        if (studentSignupDetails.getPkStudentSignupDetails() == null || studentSignup.getPkStudentSignup().equals("")) {
                            studentSignupDetails.setPkStudentSignupDetails(GuidUtils.getGuid());
                        }
                        studentSignupDetails.setPkDomain(studentSignup.getPkDomain());
                        studentSignupDetails.setPkStudentSignup(studentSignup.getPkStudentSignup());
                        studentSignupDetailsMapper.insertSelective(studentSignupDetails);
                        //        同时审核应收款
                        Receipt receipt = new Receipt();
                        receipt.setCost(studentSignupDetails.getMoney());
                        receipt.setPkExpenseItem(studentSignupDetails.getPkExpenseItem());
                        receipt.setPkReceipt(GuidUtils.getGuid());
                        receipt.setPkStudent(receivable.getPkStudent());
                        receipt.setPkDomain(tParams.getCurrendDomain());
                        receipt.setPkParent(receivable.getPkReceivable());
                        receipt.setCode(sysAutoCodeService.getCode("receiptcode"));
                        receipt.setMoney(BigDecimal.valueOf(0));
                        receipt.setCreator(tParams.getCurrentUser());
                        receipt.setModifier(tParams.getCurrentUser());
                        receipt.setDate(new Date());
                        receipt.setCreationDate(new Date());
                        receipt.setLasteditDate(new Date());
                        receiptMapper.insertSelective(receipt);
                    }
                }
            }
        } catch (Exception ex) {
            message = ex.getMessage();
            return response.SAVE_FAIL(message);
        }
        return response;
    }


//    @Override
//    public Response saveAll(Request<Receivable> tParams) {
//        Response response = Response.newResponse();
//        Receivable receivable = tParams.getData();
//
//        if (receivable == null) {
//            return response.PARAMS_ISNULL();
//        }
//
//        String message = "";
//        try {
//    //        建立虚拟报名
//            StudentSignup studentSignup = new StudentSignup();
//            studentSignup.setPkDomain(tParams.getCurrendDomain());
//            studentSignup.setPkStudentSignup(GuidUtils.getGuid());
//            studentSignup.setCode(GuidUtils.getGuid());
//            studentSignup.setCreator(tParams.getCurrentUser());
//            studentSignup.setPkEmployee(tParams.getCurrentUser());
//            studentSignup.setPkStudent(receivable.getPkStudent());
//            studentSignup.setStatus(9);
//            studentSignup.setCreationDate(new Date());
//            studentSignup.setModifier(tParams.getCurrentUser());
//            studentSignup.setLasteditDate(new Date());
//            studentSignupMapper.insertSelective(studentSignup);
//
//    //        保存应收款
//            receivable.setPkParent(studentSignup.getPkStudentSignup());
//            receivable.setModifier(tParams.getCurrentUser());
//            receivable.setCode(sysAutoCodeService.getCode("receivable"));
//            receivable.setCreator(tParams.getCurrentUser());
//            if (receivable.getCost() != null) {
//                receivable.setMoney(receivable.getCost());
//                receivable.setStatus(1);
//            }
//            receivable.setCurrency("CNY");
//            receivable.setCreationDate(new Date());
//            receivable.setLasteditDate(new Date());
//            receivableMapper.insertSelective(receivable);
//
//    //        同时审核应收款
//            Receipt receipt = new Receipt();
//            receipt.setCost(receivable.getMoney());
//            receipt.setPkReceipt(GuidUtils.getGuid());
//            receipt.setPkStudent(receivable.getPkStudent());
//            receipt.setPkDomain(tParams.getCurrendDomain());
//            receipt.setPkParent(receivable.getPkReceivable());
//            receipt.setCode(sysAutoCodeService.getCode("receiptcode"));
//            receipt.setMoney(BigDecimal.valueOf(0));
//            receipt.setCreator(tParams.getCurrentUser());
//            receipt.setModifier(tParams.getCurrentUser());
//            receipt.setDate(new Date());
//            receipt.setCreationDate(new Date());
//            receipt.setLasteditDate(new Date());
//            receiptMapper.insertSelective(receipt);
//
//    //            获取费用明细
//            Map<String, Object> map = receivable.getMap();
//            if (map != null) {
//                List<StudentSignupDetails> studentSignupDetailsList = DataUtils.objectToList(map.get(Light.STUDENT_SIGNUP_DETAILS), StudentSignupDetails.class);
//                if (studentSignupDetailsList != null && studentSignupDetailsList.size() > 0) {
//                    for (StudentSignupDetails studentSignupDetails : studentSignupDetailsList) {
//                        if (studentSignupDetails.getPkStudentSignupDetails() == null || studentSignup.getPkStudentSignup().equals("")) {
//                            studentSignupDetails.setPkStudentSignupDetails(GuidUtils.getGuid());
//                        }
//                        studentSignupDetails.setPkDomain(studentSignup.getPkDomain());
//                        studentSignupDetails.setPkStudentSignup(studentSignup.getPkStudentSignup());
//                        studentSignupDetailsMapper.insertSelective(studentSignupDetails);
//                    }
//                }
//            }
//        } catch (Exception ex) {
//            message = ex.getMessage();
//            return response.SAVE_FAIL(message);
//        }
//        return response;
//    }
}
