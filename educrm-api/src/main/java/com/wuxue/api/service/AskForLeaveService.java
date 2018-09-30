package com.wuxue.api.service;

import com.wuxue.api.interfaces.IFindService;
import com.wuxue.api.interfaces.ISaveService;
import com.wuxue.model.AskForLeave;
import com.wuxue.utils.contract.Response;

import java.util.List;

public interface AskForLeaveService extends ISaveService<AskForLeave>,IFindService<AskForLeave>{

    List<AskForLeave> findByPar(AskForLeave askForLeave);
    AskForLeave findByKey(String pkAskForLeave);

    Response deleteByLeaveId(String pkAskForLeave);

    Response applyLeaveSubmit(AskForLeave askForLeave,List<String> studentList);
}
