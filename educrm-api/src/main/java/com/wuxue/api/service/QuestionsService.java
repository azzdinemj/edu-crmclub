package com.wuxue.api.service;


import com.wuxue.api.interfaces.IAuditService;
import com.wuxue.api.interfaces.IDeleteService;
import com.wuxue.api.interfaces.IFindService;
import com.wuxue.api.interfaces.ISaveService;
import com.wuxue.model.Questions;

public interface QuestionsService extends ISaveService<Questions>,IFindService<Questions>,IDeleteService<String>,IAuditService<Questions> {
}
