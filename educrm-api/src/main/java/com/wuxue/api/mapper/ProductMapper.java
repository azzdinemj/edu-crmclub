package com.wuxue.api.mapper;

import com.wuxue.model.Product;
import com.wuxue.api.interfaces.*;
import java.util.List;
public interface ProductMapper extends IInsertMapper<Product>,ICountMapper<Product,Integer>,
IUpdateMapper<Product>,IDeleteByPrimaryKeyMapper<String>,
ISelectByPrimaryKeyMapper<String,Product>,ISelectMapper<Product,List<Product>> {
    void updateByPrimaryKey(Product byPrimaryKey);
    List<Product> selectBy(Product tParams);
}
