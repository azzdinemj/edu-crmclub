package com.wuxue.view.client.system;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.wuxue.model.TbAllarea;
import com.wuxue.model.TbAllarea;
import com.wuxue.utils.contract.Response;
import com.wuxue.view.client.SystemBaseClient;
import com.wuxue.view.enums.ActionEnum;
import com.wuxue.view.interfaces.IDeleteClient;
import com.wuxue.view.interfaces.IFindByPrimaryKeyClient;
import com.wuxue.view.interfaces.IFindClient;
import com.wuxue.view.interfaces.ISaveClient;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TbAllareaClient extends SystemBaseClient
        implements IFindClient<TbAllarea,Response<List<TbAllarea>>>,ISaveClient<TbAllarea,String>,
        IDeleteClient<Integer,Object>,IFindByPrimaryKeyClient<TbAllarea,Response<TbAllarea>> {




    /**
     * 删除
     * @param pkId 主键
     * @return
     */
    @Override
    public String delete(Integer pkId) {
        String responseXml = POST(getSendUrl(ActionEnum.DELETE),pkId);
//        Response<Object> response = JSON.parseObject(responseXml,new TypeReference<Response<Object>>(){});
        return responseXml;
    }

    /**
     * 查询
     * @param tbAllarea
     * @return
     */
    @Override
    public Response<List<TbAllarea>> find(TbAllarea tbAllarea) {
        String responseXml = POST(getSendUrl(ActionEnum.FIND),tbAllarea);
        Response<List<TbAllarea>> response = JSON.parseObject(responseXml,new TypeReference<Response<List<TbAllarea>>>(){});
        return response;
    }

    /**
     *保存
     * @param tbAllarea
     * @return
     */
    @Override
    public String save(TbAllarea tbAllarea){
        String responseXml = POST(getSendUrl(ActionEnum.SAVE),tbAllarea);
        //Response<Object> response = JSON.parseObject(responseXml, new TypeReference<Response<Object>>(){});
        return responseXml;
    }

    /**
     * 所属页面名称
     * @return
     */
    @Override
    protected String getPageName() {
        return "/tballarea";
    }

    /**
     * 主键查询
     * @param tbAllarea
     * @return
     */
    @Override
    public Response<TbAllarea> findByPrimaryKey(TbAllarea tbAllarea) {
        String responseXml = POST(getSendUrl(ActionEnum.FIND),tbAllarea);
        Response<TbAllarea> response = JSON.parseObject(responseXml,new TypeReference<Response<TbAllarea>>(){});
        return response;
    }
}
