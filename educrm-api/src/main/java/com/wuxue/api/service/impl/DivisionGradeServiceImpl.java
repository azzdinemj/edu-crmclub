package com.wuxue.api.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wuxue.api.mapper.DivisionGradeMapper;
import com.wuxue.api.mapper.SysDictValuesMapper;
import com.wuxue.api.service.DivisionGradeService;
import com.wuxue.api.service.UtilsService;
import com.wuxue.model.DivisionGrade;
import com.wuxue.model.SysDictValues;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("divisionGradeService")
public class DivisionGradeServiceImpl implements DivisionGradeService {

    @Autowired
    private DivisionGradeMapper divisionGradeMapper;
    @Autowired
    private UtilsService utilsService;
    @Autowired
    private SysDictValuesMapper sysDictValuesMapper;

    @Override
    public Response delete(Request<Integer> tParams) {
        Response response = Response.newResponse();

        Integer primaryKey = tParams.getData();
        if (primaryKey == null || "".equals(primaryKey)) {
            return response.PARAMS_ISNULL();
        }
        String message = "";
        try {
            DivisionGrade divisionGrade = divisionGradeMapper.selectByPrimaryKey(primaryKey);
            if (divisionGrade != null) {
                divisionGradeMapper.deleteByPrimaryKey(primaryKey);
            }


        } catch (Exception ex) {
            message = ex.getMessage();
            return response.DELETE_FAIL(message);
        }


        return response;
    }

    @Override
    public Response find(Request<DivisionGrade> tParams) {
        Response response = Response.newResponse();
        DivisionGrade divisionGrade = tParams.getData();
        if (divisionGrade == null) {
            return response.PARAMS_ISNULL();
        }
        Integer primaryKey = divisionGrade.getPkDivisionGrade();
        if (primaryKey != null && !"".equals(primaryKey)) {
            DivisionGrade divisionGrade1 = divisionGradeMapper.selectByPrimaryKey(primaryKey);
            response.setData(divisionGrade1);
        } else {
            PageHelper.startPage(divisionGrade.getPageNo(), divisionGrade.getPageSize());
            List<DivisionGrade> list = divisionGradeMapper.select(divisionGrade);
            if (list.size() >0){
                for (DivisionGrade grade : list) {
                    SysDictValues sysDictValues = sysDictValuesMapper.selectByPrimaryKey(grade.getDivisionId());
                    if (sysDictValues != null){
                        grade.setDivisionName(sysDictValues.getCaption());
                    }
                }

            }
            PageInfo page = new PageInfo(list);
            response.setTotal(page.getTotal());
            response.setData(list);

        }


        return response;
    }

    @Override
    public Response save(Request<DivisionGrade> tParams) {
        Response response = Response.newResponse();
        DivisionGrade divisionGrade = tParams.getData();
        if (divisionGrade == null) {
            return response.PARAMS_ISNULL();
        }
        Integer primaryKey = divisionGrade.getPkDivisionGrade();
        String message = "";
        DivisionGrade select = null;
//        divisionGradeMapper.countBy()
        if (primaryKey != null && !"".equals(primaryKey)) {
            select = divisionGradeMapper.selectByPrimaryKey(primaryKey);
        }

        try {
            if (select != null) {
                divisionGrade.setLasteditDate(new Date());
                divisionGradeMapper.updateByPrimaryKeySelective(divisionGrade);
            } else {
                divisionGrade.setCreationDate(new Date());
                divisionGrade.setLasteditDate(new Date());
                divisionGradeMapper.insertSelective(divisionGrade);
            }

        } catch (Exception ex) {
            message = ex.getMessage();
            return response.SAVE_FAIL(message);
        }

        return response;
    }
}
