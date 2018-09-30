package com.wuxue.view.client.classInfo;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.wuxue.base.KeyValue;
import com.wuxue.model.Classinfo;
import com.wuxue.model.ClassinfoStudent;
import com.wuxue.model.Schedule;
import com.wuxue.utils.contract.Response;
import com.wuxue.view.client.ClassInfoBaseClient;
import com.wuxue.view.enums.ActionEnum;
import com.wuxue.view.interfaces.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassInfoClient extends ClassInfoBaseClient
        implements IFindClient<Classinfo,Response<List<Classinfo>>>,ISaveClient<Classinfo,String>,
        IDeleteClient<String,Object>,IFindByPrimaryKeyClient<Classinfo,Response<Classinfo>>,
        ISubmitClient<Classinfo,String>,IAuditClient<Classinfo,String>{




    /**
     * 删除
     * @param pkId 主键
     * @return
     */
    @Override
    public String delete(String pkId) {
        String responseXml = POST(getSendUrl(ActionEnum.DELETE),pkId);
//        Response<Object> response = JSON.parseObject(responseXml,new TypeReference<Response<Object>>(){});
        return responseXml;
    }

    /**
     * 查询
     * @param classinfo
     * @return
     */
    @Override
    public Response<List<Classinfo>> find(Classinfo classinfo) {
        String responseXml = POST(getSendUrl(ActionEnum.FIND),classinfo);
        Response<List<Classinfo>> response = JSON.parseObject(responseXml,new TypeReference<Response<List<Classinfo>>>(){});
        return response;
    }

    public Response<List<Classinfo>> selectBy(Classinfo classinfo) {
        String responseXml = POST(getSendUrl(ActionEnum.SELECTBY),classinfo);
        Response<List<Classinfo>> response = JSON.parseObject(responseXml,new TypeReference<Response<List<Classinfo>>>(){});
        return response;
    }

    /**
     *保存
     * @param classinfo
     * @return
     */
    @Override
    public String save(Classinfo classinfo){
        String responseXml = POST(getSendUrl(ActionEnum.SAVE),classinfo);
        //Response<Object> response = JSON.parseObject(responseXml, new TypeReference<Response<Object>>(){});
        return responseXml;
    }
    public String saveForSche(String pkClassinfo){
        String responseXml = POST(getSendUrl(ActionEnum.SAVEFORSCHE),pkClassinfo);
        //Response<Object> response = JSON.parseObject(responseXml, new TypeReference<Response<Object>>(){});
        return responseXml;
    }
//    public String saveForSche(List<String> list){
//        String responseXml = POST(getSendUrl(ActionEnum.SAVEFORSCHE),list);
//        //Response<Object> response = JSON.parseObject(responseXml, new TypeReference<Response<Object>>(){});
//        return responseXml;
//    }
    public String updateStatus(Classinfo classinfo){
        String responseXml = POST(getSendUrl(ActionEnum.UPDATESTATUS),classinfo);
        //Response<Object> response = JSON.parseObject(responseXml, new TypeReference<Response<Object>>(){});
        return responseXml;
    }

    /**
     * 所属页面名称
     * @return
     */
    @Override
    protected String getPageName() {
        return "/classinfo";
    }

    /**
     * 主键查询
     * @param classinfo
     * @return
     */
    @Override
    public Response<Classinfo> findByPrimaryKey(Classinfo classinfo) {
        String responseXml = POST(getSendUrl(ActionEnum.FIND),classinfo);
        Response<Classinfo> response = JSON.parseObject(responseXml,new TypeReference<Response<Classinfo>>(){});
        return response;
    }

    @Override
    public String audit(Classinfo classinfo) {
        String responseXml = POST(getSendUrl(ActionEnum.AUDIT),classinfo);
        return responseXml;
    }

    @Override
    public String submit(Classinfo classinfo) {
        String responseXml = POST(getSendUrl(ActionEnum.SUBMIT),classinfo);
        return responseXml;
    }

    public Response<List<ClassinfoStudent>> findClasssStudentScores(ClassinfoStudent classinfoStudent) {
        String responseXml = POST(getSendUrl(ActionEnum.FINDCLASSSTUDENTSCORES),classinfoStudent);
        Response<List<ClassinfoStudent>> response = JSON.parseObject(responseXml,new TypeReference<Response<List<ClassinfoStudent>>>(){});
        return response;
    }
    public Response<List<Classinfo>> findClassForCourse(Classinfo classinfo) {
        String responseXml = POST(getSendUrl(ActionEnum.FINDCLASSFORCOURSE),classinfo);
        Response<List<Classinfo>> response = JSON.parseObject(responseXml,new TypeReference<Response<List<Classinfo>>>(){});
        return response;
    }

    public String selectiveCheck(Schedule schedule) {
        String responseXml = POST(getSendUrl(ActionEnum.SELECTIVECHECK),schedule);
//        Response response = JSON.parseObject(responseXml,new TypeReference<Response>(){});
        return responseXml;
    }
    /**
     *升学
     * @param classinfo
     * @return
     */

    public String goToSchool(Classinfo classinfo){
        String responseXml = POST(getSendUrl(ActionEnum.GOTOSCHOOL),classinfo);
        //Response<Object> response = JSON.parseObject(responseXml, new TypeReference<Response<Object>>(){});
        return responseXml;
    }

    public Response classOn(Classinfo classinfo) {

        String responseXml = POST(getSendUrl(ActionEnum.CLASSON),classinfo);
        Response<Classinfo> response = JSON.parseObject(responseXml,new TypeReference<Response<Classinfo>>(){});
        return response;
    }

    public Response<List<KeyValue>> getClassinfo(Classinfo classinfo) {
        String responseXml = POST(getSendUrl(ActionEnum.GETCLASSINFO),classinfo);
        Response<List<KeyValue>> response = JSON.parseObject(responseXml,new TypeReference<Response<List<KeyValue>>>(){});

        return response;
    }

    public String saveElective(Classinfo classinfo) {
        String responseXml = POST(getSendUrl(ActionEnum.SAVEELECTIVE),classinfo);
        //Response<Object> response = JSON.parseObject(responseXml, new TypeReference<Response<Object>>(){});
        return responseXml;
    }
}
