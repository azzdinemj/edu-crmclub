package com.wuxue.api.service;


import com.wuxue.api.interfaces.IAuditService;
import com.wuxue.api.interfaces.IDeleteService;
import com.wuxue.api.interfaces.IFindService;
import com.wuxue.api.interfaces.ISaveService;
import com.wuxue.model.Review;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;

public interface ReviewService extends ISaveService<Review>,IFindService<Review>,IDeleteService<String>,IAuditService<Review> {
}
