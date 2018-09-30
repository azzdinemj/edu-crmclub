package com.wuxue.api.service;


import com.wuxue.api.interfaces.IDeleteService;
import com.wuxue.api.interfaces.IFindService;
import com.wuxue.api.interfaces.ISaveService;
import com.wuxue.model.StudentSpecialty;

public interface StudentSpecialtyService extends ISaveService<StudentSpecialty>,IFindService<StudentSpecialty>,IDeleteService<String> {
}
