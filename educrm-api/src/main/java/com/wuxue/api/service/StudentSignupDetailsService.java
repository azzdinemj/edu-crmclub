package com.wuxue.api.service;


import com.wuxue.api.interfaces.IDeleteService;
import com.wuxue.api.interfaces.IFindService;
import com.wuxue.api.interfaces.ISaveService;
import com.wuxue.api.interfaces.ISubmitService;
import com.wuxue.model.StudentSignup;
import com.wuxue.model.StudentSignupDetails;

public interface StudentSignupDetailsService extends ISaveService<StudentSignupDetails>,
        IFindService<StudentSignupDetails>,IDeleteService<String>{
}
