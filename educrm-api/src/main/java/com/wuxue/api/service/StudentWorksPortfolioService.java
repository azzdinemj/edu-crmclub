package com.wuxue.api.service;


import com.wuxue.api.interfaces.IDeleteService;
import com.wuxue.api.interfaces.IFindService;
import com.wuxue.api.interfaces.ISaveService;
import com.wuxue.model.StudentWorksPortfolio;

public interface StudentWorksPortfolioService extends ISaveService<StudentWorksPortfolio>,IFindService<StudentWorksPortfolio>,IDeleteService<String> {
}
