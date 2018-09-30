package com.wuxue.api.service;


import com.wuxue.model.Linkman;
import com.wuxue.model.Student;
import com.wuxue.model.shuttle.DeliveryApplication;
import com.wuxue.utils.contract.Response;

import java.util.Date;
import java.util.List;

public interface DeliveryApplicationService {
    /**
     *  家长名下孩子特别申请集合
     * @param deliveryApplication
     * @return
     */
    List<DeliveryApplication> findApply(DeliveryApplication deliveryApplication);

    /**
     * 提交申请
     * @param studentList
     * @param deliveryApplication
     * @return
     */
    Response applyIssubmit(List<String> studentList, Date date, DeliveryApplication deliveryApplication);

    /**
     * 详情
     * @param pkDelivery
     * @return
     */
    DeliveryApplication selectByKey(String pkDelivery);

    /**
     * 审核
     * @param deliveryApplication
     * @return
     */
    Response audit(DeliveryApplication deliveryApplication);

    /**
     * 提交申请(临时接送)
     * @param linkman,studentList,date
     * @param studentList
     * @param deliveryApplication
     * @return
     */
    Response applyIssubmitTemp(Linkman linkman, List<Student> studentList, Date date, DeliveryApplication deliveryApplication);

    Response delete(String pkDelivery);
}
