package com.wuxue.view.client.student;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.wuxue.model.StudentWorksPortfolio;
import com.wuxue.model.StudentWorksPortfolio;
import com.wuxue.utils.contract.Response;
import com.wuxue.view.client.StudentBaseClient;
import com.wuxue.view.enums.ActionEnum;
import com.wuxue.view.interfaces.IDeleteClient;
import com.wuxue.view.interfaces.IFindByPrimaryKeyClient;
import com.wuxue.view.interfaces.IFindClient;
import com.wuxue.view.interfaces.ISaveClient;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentWorksPortfolioClient extends StudentBaseClient implements
        IFindClient<StudentWorksPortfolio,Response<List<StudentWorksPortfolio>>>,ISaveClient<StudentWorksPortfolio,String>,
        IDeleteClient<String,Object>,IFindByPrimaryKeyClient<StudentWorksPortfolio,Response<StudentWorksPortfolio>> {


    /**
     * 所属页面名称
     * @return
     */
    @Override
    protected String getPageName() {
        return "/studentWorksPortfolio";
    }

    /**
     * 删除
     * @param s
     * @return
     */
    @Override
    public String delete(String s) {
        String responseXml = POST(getSendUrl(ActionEnum.DELETE),s);
//        Response<Object> response = JSON.parseObject(responseXml,new TypeReference<Response<Object>>(){});
        return responseXml;
    }

    /**
     * 根据主键查询
     * @param studentWorksPortfolio
     * @return
     */
    @Override
    public Response<StudentWorksPortfolio> findByPrimaryKey(StudentWorksPortfolio studentWorksPortfolio) {
        String responseXml = POST(getSendUrl(ActionEnum.FIND),studentWorksPortfolio);
        Response<StudentWorksPortfolio> response = JSON.parseObject(responseXml,new TypeReference<Response<StudentWorksPortfolio>>(){});
        return response;
    }

    /**
     * 查询列表
     * @param studentWorksPortfolio
     * @return
     */
    @Override
    public Response<List<StudentWorksPortfolio>> find(StudentWorksPortfolio studentWorksPortfolio) {
        String responseXml = POST(getSendUrl(ActionEnum.FIND),studentWorksPortfolio);
        Response<List<StudentWorksPortfolio>> response = JSON.parseObject(responseXml,new TypeReference<Response<List<StudentWorksPortfolio>>>(){});
        return response;

    }

    /**
     *保存
     * @param studentWorksPortfolio
     * @return
     */
    @Override
    public String save(StudentWorksPortfolio studentWorksPortfolio){
        String responseXml = POST(getSendUrl(ActionEnum.SAVE),studentWorksPortfolio);
       /* Response<Object> response = JSON.parseObject(responseXml, new TypeReference<Response<Object>>(){});*/
        return responseXml;
    }
}
