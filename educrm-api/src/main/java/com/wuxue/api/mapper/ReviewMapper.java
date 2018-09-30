package com.wuxue.api.mapper;

import com.wuxue.api.interfaces.*;
import com.wuxue.model.Review;

import java.util.List;

public interface ReviewMapper extends IInsertMapper<Review>,ICountMapper<Review,Integer>,
        IUpdateMapper<Review>,IDeleteByPrimaryKeyMapper<String>,
        ISelectByPrimaryKeyMapper<String,Review>,ISelectMapper<Review,List<Review>>{
}