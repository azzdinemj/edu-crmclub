package com.wuxue.api.service;


import com.wuxue.api.interfaces.IDeleteService;
import com.wuxue.api.interfaces.IFindService;
import com.wuxue.api.interfaces.ISaveService;
import com.wuxue.model.DivisionGrade;

public interface DivisionGradeService extends ISaveService<DivisionGrade>,IFindService<DivisionGrade>,IDeleteService<Integer> {

}
