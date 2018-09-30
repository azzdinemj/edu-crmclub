package com.wuxue.view.utils;

import com.wuxue.model.SysDictValues;
import com.wuxue.utils.contract.Light;
import com.wuxue.utils.contract.Response;
import com.wuxue.view.client.system.SysDictValuesClient;
import com.wuxue.view.enums.SysDictEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

@Service
public class SysDictUtils {
    @Autowired
    private SysDictValuesClient sysDictValuesClient;

   /* private static SysDictUtils currentUtils;

    public static SysDictUtils getInstance() {
        if (currentUtils == null) {
            currentUtils = new SysDictUtils();
        }
        return currentUtils;
    }*/

    public List<SysDictValues> getSysDictValue(SysDictEnum dictEnum) {
        SysDictValues sysDictValues = new SysDictValues();
        sysDictValues.setPkSysDict(dictEnum.toString());
//        sysDictValues.setp
        Response<List<SysDictValues>> listResponse = sysDictValuesClient.find(sysDictValues);
        return listResponse.getData();
    }
    public List<SysDictValues> getSysDictValue(String string) {
        SysDictValues sysDictValues = new SysDictValues();
        sysDictValues.setPkSysDict(string);
        Response<List<SysDictValues>> listResponse = sysDictValuesClient.find(sysDictValues);
        return listResponse.getData();
    }
    public void setModeAttribute(Model model,String name,String pkSysDict){
        if(model == null || name ==null || name.equals("")){
            return;
        }
        model.addAttribute(name,getSysDictValue(pkSysDict));
    }


}