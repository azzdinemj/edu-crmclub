package com.wuxue.view.client.student;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.github.pagehelper.PageInfo;
import com.wuxue.model.Member;
import com.wuxue.utils.contract.Response;
import com.wuxue.view.client.StudentBaseClient;
import com.wuxue.view.enums.ActionEnum;
import com.wuxue.view.interfaces.IAuditClient;
import com.wuxue.view.interfaces.IDeleteClient;
import com.wuxue.view.interfaces.IFindByPrimaryKeyClient;
import com.wuxue.view.interfaces.ISaveClient;
import org.springframework.stereotype.Service;

/**
* @Description:  预约
 * 访问api服务类
* @author Rogue
* @date  13:42 2018/3/23
* @version V1.0
*/
@Service
public class MemberClient extends StudentBaseClient implements
        IDeleteClient<String,String>,ISaveClient<Member,String>,IFindByPrimaryKeyClient<Member,Response<Member>>,IAuditClient<Member,Response<Member>> {

    /**
     * 查询
     * @param member
     * @return
     */
    public Response<PageInfo<Member>> find(Member member) {
        String responseXml = POST(getSendUrl(ActionEnum.FIND),member);
        Response<PageInfo<Member>> response = JSON.parseObject(responseXml,new TypeReference<Response<PageInfo<Member>>>(){});
        return response;
    }

    @Override
    public Response<Member> findByPrimaryKey(Member member) {
        String responseXml = POST(getSendUrl(ActionEnum.FIND),member);
        Response<Member> response = JSON.parseObject(responseXml,new TypeReference<Response<Member>>(){});
        return response;
    }
    /**
     * 删除
     * @param s
     * @return
     */
    @Override
    public String delete(String s) {
        String responseXml = POST(getSendUrl(ActionEnum.DELETE),s);
      //  Response response = JSON.parseObject(responseXml,new TypeReference<Response>(){});
        return responseXml;
    }

    @Override
    public String save(Member member) {
        String responseXml = POST(getSendUrl(ActionEnum.SAVE),member);
        return responseXml;
    }



    /**
     * 所属页面
     * @return
     */
    @Override
    protected String getPageName() { return "/member"; }

    @Override
    public Response audit(Member member) {
        String responseXml = POST(getSendUrl(ActionEnum.AUDIT),member);
        Response<Member> response = JSON.parseObject(responseXml,new TypeReference<Response<Member>>(){});
        return response;
    }
}

