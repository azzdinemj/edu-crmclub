package com.wuxue.api.service;

import com.wuxue.base.KeyValue;
import com.wuxue.base.Page;
import com.wuxue.model.*;
import com.wuxue.model.shuttle.NoticeRecord;

import java.security.Key;

/**
 * Created by Jamie on 2018/1/21.
 */
public interface UtilsService {
    void setSysUserKeyValue(Page object, String pkId, String linkEntityName);

    void setDepartmentKeyValue(Page object, String pkId, String linkEntityName);

    void setEmployeeKeyValue(Page object, String pkId, String linkEntityName);

    void setSysDictKeyValue(Page object, String pkId, String linkEntityName);

    void setDomainKeyValue(Page object, String pkId, String linkEntityName);

    void setClassRoomKeyValue(Page object, String pkId, String linkEntityName);

    void setClassTimeKeyValue(Page object, String pkId, String linkEntityName);

    void setLinkManKeyValue(Page object, String pkId, String linkEntityName);

    void setLinkManByKeyValue(Page object, String pkId, String linkEntityName);

    void setStudentKeyValue(Page object, String pkId, String linkEntityName);
    void setStudentValue(Page object, String pkId, String linkEntityName);

    void setStudentIdValue(Page object, String pkId, String linkEntityName);

    String getSysSetValue(String pkDomain, String pkSysSet);

    void setStudentScoreKeyValue(Page object, String pkClassinfo, String pkStudent, String studentScoreEntity);

    void setReceiptMoney(Page object, String pkReceivable, String receiptMoney);

    void setClassInfoKeyValue(Page object, String pkClassinfo, String classInfoEntity);

    void saveLog(Object oldObj, Object newObj, String tableName, int kind,String pkId, String operator);
    void setScoreCaptionKeyValue(Page object, String pkClassinfo, String scoreCaptionEntity);

    void setClassinfoScheduleStatus(Page object, String pkClassinfo, String classinfoScheduleStatus);

    void setEmployeeDataValue(Page object, String pkEmployee, String empEntity);

    void setRefundStatusKeyValue(Page object, String pkPayables, String refundStatus);

    void setDivisionKeyValue(Page object, String type, String divisionEntity);
    void setSetMeal(Page object, String type, String divisionEntity);
    void setExpenseItemKeyValue(Page object, String type, String divisionEntity);

    void setExaminationObject(JhExamination jhExamination, String object, String objectEntity);

    void setQuestionNum(JhExamination jhExamination, String pkExamination, String questionNumEntity);

    void setNumber(JhExamination jhExamination, String pkExamination, String numberEntity);

    void setMealKeyValue(Page object, String pkId, String linkEntityName);

    void setSchoolBusKeyValue(Page object, String pkSchoolBus, String schoolBusKindEntity);

    void setSchoolBusLineKeyValue(Page object, NoticeRecord noticeRecord, String schoolBusLineKindEntity);
}
