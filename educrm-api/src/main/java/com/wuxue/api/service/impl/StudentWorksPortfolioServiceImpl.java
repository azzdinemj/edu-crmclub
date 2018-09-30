package com.wuxue.api.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wuxue.api.mapper.StudentWorksPortfolioMapper;
import com.wuxue.api.service.UtilsService;
import com.wuxue.model.StudentWorksPortfolio;
import com.wuxue.api.service.StudentWorksPortfolioService;
import com.wuxue.api.utils.GuidUtils;
import com.wuxue.utils.contract.Light;
import com.wuxue.utils.contract.LinkEntity;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

//import com.wuxue.api.utils.GuidUtils;

@Service("studentWorksPortfolioService")
public class StudentWorksPortfolioServiceImpl implements StudentWorksPortfolioService{
    @Autowired
    StudentWorksPortfolioMapper studentWorksPortfolioMapper;
    @Autowired
    UtilsService utilsService;

    @Override
    public Response delete(Request<String> tParams) {
        Response response = Response.newResponse();
        String primaryKey = tParams.getData();
        if(primaryKey== null || primaryKey.equals("")){
            return  response.PARAMS_ISNULL();
        }
        int iReuslt = 1;
        String message= "";
        try {
            iReuslt=studentWorksPortfolioMapper.deleteByPrimaryKey(primaryKey);
        }catch (Exception ex){
            message = ex.getMessage();
            return response.DELETE_FAIL(message);
        }
        if(iReuslt>0) {
            return response;
        }
        return response.DELETE_FAIL(message);
    }

    @Override
    public Response find(Request<StudentWorksPortfolio> tParams) {
        Response response = Response.newResponse();
        StudentWorksPortfolio studentWorksPortfolio = tParams.getData();

        if(studentWorksPortfolio== null){
            return  response.PARAMS_ISNULL();
        }
        String primaryKey = studentWorksPortfolio.getPkStudentWorksPortfolio();
        if(primaryKey !=null && !primaryKey.equals("")){
            StudentWorksPortfolio byPrimaryKey = studentWorksPortfolioMapper.selectByPrimaryKey(primaryKey);
            utilsService.setSysUserKeyValue(byPrimaryKey,byPrimaryKey.getCreator(), LinkEntity.CREATOR_ENTITY);
            utilsService.setSysUserKeyValue(byPrimaryKey,byPrimaryKey.getModifier(),LinkEntity.MODIFIER_ENTITY);
            response.setData(byPrimaryKey);
        }else{
            PageHelper.startPage(studentWorksPortfolio.getPageNo(),studentWorksPortfolio.getPageSize());
            List<StudentWorksPortfolio> portfolioList = studentWorksPortfolioMapper.select(studentWorksPortfolio);
            PageInfo page = new PageInfo(portfolioList);
            response.setTotal(page.getTotal());
            if (portfolioList.size() > 0) {
                for (StudentWorksPortfolio list : portfolioList) {
                    utilsService.setSysUserKeyValue(list, list.getCreator(), LinkEntity.CREATOR_ENTITY);
                    utilsService.setSysUserKeyValue(list, list.getModifier(), LinkEntity.MODIFIER_ENTITY);
                    utilsService.setSysDictKeyValue(list,list.getGrade(),LinkEntity.GTADE_ENTITY);
                    utilsService.setSysDictKeyValue(list,list.getTypeWork(), Light.STUDENT_WOEKS_PORTFOLIO);
                }
            }
            response.setData(portfolioList);
            //response.setTotal(studentWorksPortfolioMapper.countBy(studentWorksPortfolio));

        }
        return response;
    }

    @Override
    public Response save(Request<StudentWorksPortfolio> tParams) {
        Response response = Response.newResponse();
        StudentWorksPortfolio studentWorksPortfolio = tParams.getData();

        if(studentWorksPortfolio== null){
            return  response.PARAMS_ISNULL();
        }
        String primaryKey = studentWorksPortfolio.getPkStudentWorksPortfolio();
        StudentWorksPortfolio select = null;
        if (primaryKey != null && !primaryKey.equals("")) {
            select = studentWorksPortfolioMapper.selectByPrimaryKey(primaryKey);
        }
        int iReuslt = 1;
        String message= "";
        try {
            if (select != null) {
                studentWorksPortfolio.setLasteditDate(new Date());
                iReuslt = studentWorksPortfolioMapper.updateByPrimaryKeySelective(studentWorksPortfolio);
            } else {
                studentWorksPortfolio.setPkStudentWorksPortfolio(GuidUtils.getGuid());
                studentWorksPortfolio.setCreationDate(new Date());
                studentWorksPortfolio.setLasteditDate(new Date());
                iReuslt = studentWorksPortfolioMapper.insertSelective(studentWorksPortfolio);
            }
        }catch (Exception ex){
            message = ex.getMessage();
            return response.SAVE_FAIL(message);
        }
        if(iReuslt>0) {
            return response;
        }
        return response.SAVE_FAIL(message);
    }
}
