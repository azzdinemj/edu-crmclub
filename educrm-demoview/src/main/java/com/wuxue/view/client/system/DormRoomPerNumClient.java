package com.wuxue.view.client.system;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.wuxue.model.DormRoomPerNum;
import com.wuxue.utils.contract.Response;
import com.wuxue.view.client.SystemBaseClient;
import com.wuxue.view.enums.ActionEnum;
import com.wuxue.view.interfaces.IFindClient;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DormRoomPerNumClient extends SystemBaseClient implements
        IFindClient<DormRoomPerNum,Response<List<DormRoomPerNum>>>
         {


    /**
     * 所属页面名称
     * @return
     */
    @Override
    protected String getPageName() {
        return "/dormroompernum";
    }





    /**
     * 查询列表
     * @param dormRoomPerNum
     * @return
     */
    @Override
    public Response<List<DormRoomPerNum>> find(DormRoomPerNum dormRoomPerNum) {
        String responseXml = POST(getSendUrl(ActionEnum.FIND),dormRoomPerNum);
        Response<List<DormRoomPerNum>> response = JSON.parseObject(responseXml,new TypeReference<Response<List<DormRoomPerNum>>>(){});
        return response;

    }


}
