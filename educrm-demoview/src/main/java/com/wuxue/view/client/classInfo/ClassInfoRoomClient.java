package com.wuxue.view.client.classInfo;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.wuxue.model.ClassinfoRoom;
import com.wuxue.utils.contract.Response;
import com.wuxue.view.client.ClassInfoBaseClient;
import com.wuxue.view.enums.ActionEnum;
import com.wuxue.view.interfaces.IDeleteClient;
import com.wuxue.view.interfaces.IFindByPrimaryKeyClient;
import com.wuxue.view.interfaces.IFindClient;
import com.wuxue.view.interfaces.ISaveClient;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassInfoRoomClient extends ClassInfoBaseClient
        implements IFindClient<ClassinfoRoom,Response<List<ClassinfoRoom>>>,ISaveClient<ClassinfoRoom,String>,
        IDeleteClient<ClassinfoRoom,Object>,IFindByPrimaryKeyClient<ClassinfoRoom,Response<ClassinfoRoom>> {




    /**
     * 删除
     * @return
     */
    @Override
    public String delete(ClassinfoRoom classinfoRoom) {
        String responseXml = POST(getSendUrl(ActionEnum.DELETE),classinfoRoom);
//        Response<Object> response = JSON.parseObject(responseXml,new TypeReference<Response<Object>>(){});
        return responseXml;
    }

    /**
     * 查询
     * @param classinfoRoom
     * @return
     */
    @Override
    public Response<List<ClassinfoRoom>> find(ClassinfoRoom classinfoRoom) {
        String responseXml = POST(getSendUrl(ActionEnum.FIND),classinfoRoom);
        Response<List<ClassinfoRoom>> response = JSON.parseObject(responseXml,new TypeReference<Response<List<ClassinfoRoom>>>(){});
        return response;
    }

    /**
     *      班级老师
     * @param classinfoRoom
     * @return
     */

    public Response<List<ClassinfoRoom>> findTeacher(ClassinfoRoom classinfoRoom) {
        String responseXml = POST(getSendUrl(ActionEnum.FINDTEACHER),classinfoRoom);
        Response<List<ClassinfoRoom>> response = JSON.parseObject(responseXml,new TypeReference<Response<List<ClassinfoRoom>>>(){});
        return response;
    }

    public Response<List<ClassinfoRoom>> findClassinfoRoom(ClassinfoRoom classinfoRoom) {
        String responseXml = POST(getSendUrl(ActionEnum.FINDCLASSINFOROOM),classinfoRoom);
        Response<List<ClassinfoRoom>> response = JSON.parseObject(responseXml,new TypeReference<Response<List<ClassinfoRoom>>>(){});
        return response;
    }

    /**
     *保存
     * @param classinfoRoom
     * @return
     */
    @Override
    public String save(ClassinfoRoom classinfoRoom){
        String responseXml = POST(getSendUrl(ActionEnum.SAVE),classinfoRoom);
        //Response<Object> response = JSON.parseObject(responseXml, new TypeReference<Response<Object>>(){});
        return responseXml;
    }

    public String saveAll(ClassinfoRoom classinfoRoom){
        String responseXml = POST(getSendUrl(ActionEnum.SAVEALL),classinfoRoom);
        //Response<Object> response = JSON.parseObject(responseXml, new TypeReference<Response<Object>>(){});
        return responseXml;
    }
    /**
     * 所属页面名称
     * @return
     */
    @Override
    protected String getPageName() {
        return "/classinfoRoom";
    }

    /**
     * 主键查询
     * @param classinfoRoom
     * @return
     */
    @Override
    public Response<ClassinfoRoom> findByPrimaryKey(ClassinfoRoom classinfoRoom) {
        String responseXml = POST(getSendUrl(ActionEnum.FIND),classinfoRoom);
        Response<ClassinfoRoom> response = JSON.parseObject(responseXml,new TypeReference<Response<ClassinfoRoom>>(){});
        return response;
    }

}
