package com.wuxue.view.client.product;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.github.pagehelper.PageInfo;
import com.wuxue.model.ClassTime;
import com.wuxue.model.Schedule;
import com.wuxue.utils.contract.Response;
import com.wuxue.view.client.BaseClient;
import com.wuxue.view.enums.ActionEnum;
import com.wuxue.view.interfaces.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleClient extends BaseClient implements
        IFindClient<Schedule, Response<List<Schedule>>>, ISaveClient<Schedule, String>,
        IDeleteClient<String, Object>, IFindByPrimaryKeyClient<Schedule, Response<Schedule>> {


    /**
     * 所属页面名称
     *
     * @return
     */
    @Override
    protected String getPageName() {
        return "/schedule";
    }

    @Override
    protected String getModuleName() {
        return "/product";
    }

    /**
     * 删除
     *
     * @param pkId
     * @return
     */
    @Override
    public String delete(String pkId) {
        String responseXml = POST(getSendUrl(ActionEnum.DELETE), pkId);
//        Response<Object> response = JSON.parseObject(responseXml,new TypeReference<Response<Object>>(){});
        return responseXml;
    }

    /**
     * 根据主键查询
     *
     * @param schedule
     * @return
     */
    @Override
    public Response<Schedule> findByPrimaryKey(Schedule schedule) {
        String responseXml = POST(getSendUrl(ActionEnum.FIND), schedule);
        Response<Schedule> response = JSON.parseObject(responseXml, new TypeReference<Response<Schedule>>() {
        });
        return response;
    }

    /**
     * 根据主键查询详情  中航
     *
     * @param str
     * @return
     */
    public Response findByPrimarypk(String str) {
        String responseXml = POST(getSendUrl(ActionEnum.GETSTUDENT), str);
        Response<Schedule> response = JSON.parseObject(responseXml, new TypeReference<Response<Schedule>>() {
        });
        return response;
    }

    /**
     * 查询列表
     *
     * @param schedule
     * @return
     */
    @Override
    public Response<List<Schedule>> find(Schedule schedule) {
        String responseXml = POST(getSendUrl(ActionEnum.FIND), schedule);
        Response<List<Schedule>> response = JSON.parseObject(responseXml, new TypeReference<Response<List<Schedule>>>() {
        });
        return response;

    }

    public Response<List<Schedule>> selectBy(Schedule schedule) {
        String responseXml = POST(getSendUrl(ActionEnum.SELECTBY), schedule);
        Response<List<Schedule>> response = JSON.parseObject(responseXml, new TypeReference<Response<List<Schedule>>>() {
        });
        return response;

    }

    public String saveAll(List<Schedule> schedule) {
        String responseXml = POST(getSendUrl(ActionEnum.SAVEALL), schedule);
//        Response<Object> response = JSON.parseObject(responseXml, new TypeReference<Response<Object>>(){});
        return responseXml;
    }

    /**
     * 保存
     *
     * @param schedule
     * @return
     */
    @Override
    public String save(Schedule schedule) {
        String responseXml = POST(getSendUrl(ActionEnum.SAVE), schedule);
//        Response<Object> response = JSON.parseObject(responseXml, new TypeReference<Response<Object>>(){});
        return responseXml;
    }
    public String saveLoop(Schedule schedule) {
        String responseXml = POST(getSendUrl(ActionEnum.SAVELOOP), schedule);
//        Response<Object> response = JSON.parseObject(responseXml, new TypeReference<Response<Object>>(){});
        return responseXml;
    }


    public Response<List<ClassTime>> findClassSchodule(ClassTime schedule) {
        String responseXml = POST(getSendUrl(ActionEnum.FINDCLASSSCHODULE), schedule);
        Response<List<ClassTime>> response = JSON.parseObject(responseXml, new TypeReference<Response<List<ClassTime>>>() {
        });
        return response;
    }
}
