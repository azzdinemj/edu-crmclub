package com.wuxue.api.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wuxue.api.mapper.DropExpenseRecordMapper;
import com.wuxue.api.mapper.PayablesMapper;
import com.wuxue.api.mapper.ReceivableMapper;
import com.wuxue.api.mapper.StudentMapper;
import com.wuxue.api.service.DropExpenseRecordService;
import com.wuxue.api.service.SysAutoCodeService;
import com.wuxue.api.utils.GuidUtils;
import com.wuxue.model.DropExpenseRecord;
import com.wuxue.model.Payables;
import com.wuxue.model.Receivable;
import com.wuxue.model.Student;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.Date;
import java.util.List;

@Service("dropExpenseRecordService")
public class DropExpenseRecordServiceImpl implements DropExpenseRecordService {

    @Autowired
    private DropExpenseRecordMapper dropExpenseRecordMapper;
    @Autowired
    private SysAutoCodeService sysAutoCodeService;
    @Autowired
    private ReceivableMapper receivableMapper;
    @Autowired
    private PayablesMapper payablesMapper;
    @Autowired
    private StudentMapper studentMapper;

    @Override
    public Response delete(Request<Long> tParams) {
        Response response = Response.newResponse();

        Long primaryKey = tParams.getData();
        if (primaryKey == null || "".equals(primaryKey)) {
            return response.PARAMS_ISNULL();
        }
        String message = "";
        try {
            DropExpenseRecord dropExpenseRecord = dropExpenseRecordMapper.selectByPrimaryKey(primaryKey);
            if (dropExpenseRecord != null) {
                dropExpenseRecordMapper.updateByPrimaryKeySelective(dropExpenseRecord);
            }


        } catch (Exception ex) {
            message = ex.getMessage();
            return response.DELETE_FAIL(message);
        }


        return response.DELETE_FAIL(message);
    }

    @Override
    public Response find(Request<DropExpenseRecord> tParams) {
        Response response = Response.newResponse();
        DropExpenseRecord dropExpenseRecord = tParams.getData();
        if (dropExpenseRecord == null) {
            return response.PARAMS_ISNULL();
        }
        Long primaryKey = dropExpenseRecord.getPkDropExpenseRecord();
        if (primaryKey != null && !"".equals(primaryKey)) {
            DropExpenseRecord dropExpenseRecord1 = dropExpenseRecordMapper.selectByPrimaryKey(primaryKey);
            response.setData(dropExpenseRecord1);
        } else {
            PageHelper.startPage(dropExpenseRecord.getPageNo(), dropExpenseRecord.getPageSize());
            List<DropExpenseRecord> list = dropExpenseRecordMapper.select(dropExpenseRecord);
            PageInfo page = new PageInfo(list);
            response.setTotal(page.getTotal());
            response.setData(list);

        }


        return response;
    }

    @Override
    @Transactional
    public Response save(Request<DropExpenseRecord> tParams) {
        Response response = Response.newResponse();
        DropExpenseRecord dropExpenseRecord = tParams.getData();
        if (dropExpenseRecord == null) {
            return response.PARAMS_ISNULL();
        }
        Long primaryKey = dropExpenseRecord.getPkDropExpenseRecord();
        String message = "";
        DropExpenseRecord select = null;
        if (primaryKey != null && !"".equals(primaryKey)) {
            select = dropExpenseRecordMapper.selectByPrimaryKey(primaryKey);
        }

        try {
            if (select != null) {
                dropExpenseRecord.setLasteditDate(new Date());
                dropExpenseRecordMapper.updateByPrimaryKeySelective(dropExpenseRecord);
            } else {
                String businessId = "";
                Integer businessType = dropExpenseRecord.getBusinessType();
                if (businessType ==0){
//                    生成应收单
                    Receivable receivable = new Receivable();
                    receivable.setPkReceivable(GuidUtils.getGuid());
                    businessId=receivable.getPkReceivable();
                    receivable.setPkDomain(tParams.getCurrendDomain());
                    receivable.setPkStudent(dropExpenseRecord.getPkStudent());
                    receivable.setCode(sysAutoCodeService.getCode("receivable"));
                    receivable.setDate(new Date());
                    receivable.setCost(dropExpenseRecord.getBusinessCost());
                    receivable.setCreator(tParams.getCurrentUser());
                    receivable.setCreationDate(new Date());
                    receivable.setModifier(tParams.getCurrentUser());
                    receivable.setLasteditDate(new Date());
                    receivable.setStatus(0);
                    receivable.setCurrency("CNY");
                    receivable.setNotes("退学补缴费用");
                    receivable.setSchoolYear(dropExpenseRecord.getSchoolYear());
                    receivableMapper.insertSelective(receivable);
                    Receivable oldreceivable = new Receivable();
                    oldreceivable.setPkStudent(receivable.getPkStudent());
                    oldreceivable.setSchoolYear(receivable.getSchoolYear());
                    oldreceivable.setStatus(0);
                    receivableMapper.updateoldReceivable(receivable);



                }else if (businessType == 1){
                    Payables payables = new Payables();
                    payables.setPkDomain(tParams.getCurrendDomain());
                    payables.setPkPayables(GuidUtils.getGuid());
                    businessId = payables.getPkPayables();
                    payables.setCode(sysAutoCodeService.getCode("payablestcode"));
                    payables.setPkStudent(dropExpenseRecord.getPkStudent());
                    payables.setCost(dropExpenseRecord.getBusinessCost());
                    payables.setDate(new Date());
                    payables.setNotes("退学退费");
                    payables.setCreator(tParams.getCurrentUser());
                    payables.setCreationDate(new Date());
                    payables.setModifier(tParams.getCurrentUser());
                    payables.setLasteditDate(new Date());
                    payables.setStatus(0);
                    payablesMapper.insertSelective(payables);

                }
                dropExpenseRecord.setBusinessId(businessId);
                dropExpenseRecord.setCreator(tParams.getCurrentUser());
                dropExpenseRecord.setModifier(tParams.getCurrentUser());
                dropExpenseRecord.setCreationDate(new Date());
                dropExpenseRecord.setLasteditDate(new Date());
                dropExpenseRecordMapper.insertSelective(dropExpenseRecord);

                Student student = studentMapper.selectByPrimaryKey(dropExpenseRecord.getPkStudent());
                if (student != null){
                    student.setIstype(4);
                    studentMapper.updateByPrimaryKey(student);
                }

            }
        } catch (Exception ex) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            message = ex.getMessage();
            return response.SAVE_FAIL(message);
        }

        return response;
    }
}
