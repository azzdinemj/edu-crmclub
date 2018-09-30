package com.wuxue.view.client.course;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.github.pagehelper.PageInfo;
import com.wuxue.model.Course;
import com.wuxue.model.CourseLessonChapter;
import com.wuxue.utils.contract.Response;
import com.wuxue.view.client.CourseBaseClient;
import com.wuxue.view.enums.ActionEnum;
import com.wuxue.view.interfaces.IDeleteClient;
import com.wuxue.view.interfaces.IFindByPrimaryKeyClient;
import com.wuxue.view.interfaces.IFindClient;
import com.wuxue.view.interfaces.ISaveClient;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @Description:  课程章节
 * 访问api服务类
* @author wanghao
* @date  13:42 2018/3/13
* @version V1.0
*/
@Service
public class CourseLessonChapterClient extends CourseBaseClient implements IDeleteClient<String,String>,
        IFindClient<CourseLessonChapter,Response<PageInfo<CourseLessonChapter>>>,ISaveClient<CourseLessonChapter,String> {


    /**
     * 所属页面
     * @return
     */
    @Override
    protected String getPageName() { return "/courseLessonChapter"; }


    /**
     * 查询列表
     * @param courseLessonChapter
     * @return
     */
    @Override
    public Response find(CourseLessonChapter courseLessonChapter) {
        String responseXml = POST(getSendUrl(ActionEnum.FIND),courseLessonChapter);
        Response<List<CourseLessonChapter>> response = JSON.parseObject(responseXml,new TypeReference<Response<List<CourseLessonChapter>>>(){});
        return response;
    }

    /**
     * 查询单条
     * @param courseLessonChapter
     * @return
     */
    public Response findbuPk(CourseLessonChapter courseLessonChapter) {
        String responseXml = POST(getSendUrl(ActionEnum.FIND),courseLessonChapter);
        Response response = JSON.parseObject(responseXml,new TypeReference<Response>(){});
        return response;
    }

    @Override
    public String save(CourseLessonChapter courseLessonChapter) {
        String responseXml = POST(getSendUrl(ActionEnum.SAVE),courseLessonChapter);
     //   Response response = JSON.parseObject(responseXml,new TypeReference<Response>(){});
        return responseXml;
    }

    /**
     * sort排序
     * @param id
     * @return
     */
    public String updatesort(String id){
        String responseXml = POST(getSendUrl(ActionEnum.UPDATESORT),id);
        return responseXml;
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


}

