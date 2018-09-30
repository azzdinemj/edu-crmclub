package com.wuxue.api.service;


import com.wuxue.api.interfaces.IDeleteService;
import com.wuxue.api.interfaces.IFindService;
import com.wuxue.api.interfaces.ISaveService;
import com.wuxue.model.Linkman;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;

public interface LinkmanService extends ISaveService<Linkman>,IFindService<Linkman>,IDeleteService<String> {
    /**
     * 获取家长绑定学生
     *
     * @param linkman*/
    Response selectStudentList(Linkman linkman);

    /**
     * 找出学生的联系人们
     * @param request --studentId 学生主键
     * @return
     */
    Response getStudentLinkMansInfo(Request<String> request);

    /**
     * 根据孩子信息获取家长
     *
     * @param linkman*/
    Response findByStudent(Request<Linkman> linkman);
}
