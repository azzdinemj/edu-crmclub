package com.wuxue.api.service;

import com.wuxue.api.interfaces.IDeleteService;
import com.wuxue.api.interfaces.IFindService;
import com.wuxue.api.interfaces.ISaveService;
import com.wuxue.model.QuestionsItem;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;

public interface QuestionsItemService extends IDeleteService<QuestionsItem>,ISaveService<QuestionsItem>,IFindService<QuestionsItem>{
}
