package com.wuxue.api.service;

import com.wuxue.api.interfaces.IDeleteService;
import com.wuxue.api.interfaces.IFindService;
import com.wuxue.api.interfaces.ISaveService;
import com.wuxue.model.QuestionsSubject;
import com.wuxue.model.SysMenu;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;

public interface QuestionsSubjectService extends ISaveService<QuestionsSubject>,IFindService<QuestionsSubject>,IDeleteService<String>{

}
