package com.wuxue.api.service;


import com.wuxue.api.interfaces.IAuditService;
import com.wuxue.api.interfaces.IDeleteService;
import com.wuxue.api.interfaces.IFindService;
import com.wuxue.api.interfaces.ISaveService;
import com.wuxue.model.Member;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;

public interface MemberService extends ISaveService<Member>,IFindService<Member>,IDeleteService<String>,IAuditService<Member> {
    /**
     * 官网申请试用
     * */
    Response applyForTrial(Request<Member> member);
}
