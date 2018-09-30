package com.wuxue.api.service;


import com.wuxue.api.interfaces.IDeleteService;
import com.wuxue.api.interfaces.IFindService;
import com.wuxue.api.interfaces.ISaveService;
import com.wuxue.model.ScheduleConstraint;

public interface ScheduleConstraintService extends ISaveService<ScheduleConstraint>,IFindService<ScheduleConstraint>,IDeleteService<Long> {
}
