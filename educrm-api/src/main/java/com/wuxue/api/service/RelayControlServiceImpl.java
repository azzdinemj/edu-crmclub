package com.wuxue.api.service;

import com.wuxue.api.mapper.DeliveryApplicationMapper;
import com.wuxue.base.KeyValue;
import com.wuxue.model.shuttle.DeliveryApplication;
import com.wuxue.utils.common.DataUtils;
import com.wuxue.utils.common.DateTimeUtils;
import com.wuxue.utils.contract.Light;
import com.wuxue.utils.contract.LinkEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("relayControlService")
public class RelayControlServiceImpl implements RelayControlService {

    @Autowired
    DeliveryApplicationMapper deliveryApplicationMapper;
    @Autowired
    private DeliveryApplicationService deliveryApplicationService;


    @Override
    public String getMessage(int messageType, String id) {
        //1 委托他人接送
        if (messageType == 1) {
            DeliveryApplication deliveryApplication = deliveryApplicationService.selectByKey(id);
            Map<String, Object> map = deliveryApplication.getMap();
            KeyValue linkMan = DataUtils.objectToObject(map.get(LinkEntity.LINKMAN_ENTITY), KeyValue.class);
            KeyValue entrustment = DataUtils.objectToObject(map.get(LinkEntity.ENTRUSTMEN_ENTITY), KeyValue.class);
            List<KeyValue> keyValues = DataUtils.objectToList(map.get(Light.STUDENT_LIST), KeyValue.class);
            String message = "";
            if (keyValues.size() > 0) {
                int i = 1;
                for (KeyValue keyValue : keyValues) {
                    if (i == keyValues.size()) {
                        message = keyValue.getCaption();
                    } else {
                        message = keyValue.getCaption() + ",";
                    }

                }
            }
            message = linkMan.getCaption() + "请您于" + DateTimeUtils.DateToString(deliveryApplication.getDeliveryDate()) + "接" + message + "回家";

            return message;

        }

        return null;
    }
}
