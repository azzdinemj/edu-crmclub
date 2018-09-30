package com.wuxue.api.service;


import com.wuxue.api.interfaces.IDeleteService;
import com.wuxue.api.interfaces.IFindService;
import com.wuxue.api.interfaces.ISaveService;
import com.wuxue.model.junhwa.Material;

public interface MaterialService extends ISaveService<Material>,IFindService<Material>,IDeleteService<String> {
}
