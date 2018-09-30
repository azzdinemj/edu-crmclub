package com.wuxue.view.client.student;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.wuxue.model.junhwa.StudentNutritionAnalysis;
import com.wuxue.utils.contract.Response;
import com.wuxue.view.client.JunhuaBaseClient;
import com.wuxue.view.enums.ActionEnum;
import com.wuxue.view.interfaces.IFindClient;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 营养分析
 */
@Service
public class StudentTrophicAnalysisClient extends JunhuaBaseClient implements IFindClient<StudentNutritionAnalysis,Response<List<StudentNutritionAnalysis>>>{

    @Override
    protected String getPageName() {
        return "/analysis";
    }
    @Override
    public Response<List<StudentNutritionAnalysis>> find(StudentNutritionAnalysis studentNutritionAnalysis) {
        String responseXml = POST(getSendUrl(ActionEnum.GETNUTRITIONANALYSIS),studentNutritionAnalysis);
        Response<List<StudentNutritionAnalysis>> response = JSON.parseObject(responseXml,new TypeReference<Response<List<StudentNutritionAnalysis>>>(){});
        return response;
    }


}
