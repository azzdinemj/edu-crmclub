package com.wuxue.api.service;


import com.wuxue.api.interfaces.IDeleteService;
import com.wuxue.api.interfaces.IFindService;
import com.wuxue.api.interfaces.ISaveService;
import com.wuxue.model.StudentTestPlansScores;

public interface StudentTestPlansScoresService extends ISaveService<StudentTestPlansScores>,IFindService<StudentTestPlansScores>,IDeleteService<String> {
}
