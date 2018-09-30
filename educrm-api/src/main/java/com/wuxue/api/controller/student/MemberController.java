package com.wuxue.api.controller.student;

import com.wuxue.api.interfaces.IAuditController;
import com.wuxue.api.interfaces.IDeleteController;
import com.wuxue.api.interfaces.IFindController;
import com.wuxue.api.interfaces.ISaveController;
import com.wuxue.api.service.MemberService;
import com.wuxue.model.Member;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Rogue on 2018/03/23.
 */
@RestController
@RequestMapping(value = "api/student/member")
public class MemberController implements IFindController<Member>,
        ISaveController<Member>,IDeleteController<String>,IAuditController<Member> {
    @Autowired
    private MemberService memberService;

    @Override
    public Response Find(@RequestBody Request<Member> member) {
        return memberService.find(member);
    }

    @Override
    public Response Save(@RequestBody Request<Member> member) {
        return memberService.save(member);
    }
    @Override

    public Response Delete(@RequestBody Request<String> member) {
        return memberService.delete(member);

    }

    @Override
    public Response Audit(@RequestBody Request<Member> member) {
        return memberService.audit(member);
    }

    @RequestMapping(value = "/applyForTrial",method = RequestMethod.POST)
    public Response applyForTrial(@RequestBody Request<Member> member) {
        return memberService.applyForTrial(member);
    }
}
