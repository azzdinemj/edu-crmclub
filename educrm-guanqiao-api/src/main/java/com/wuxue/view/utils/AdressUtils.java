package com.wuxue.view.utils;

import com.wuxue.model.TbAllarea;
import com.wuxue.utils.contract.Response;
import com.wuxue.view.client.system.TbAllareaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;
@Service
public class AdressUtils {

    @Autowired
    private TbAllareaClient tbAllareaClient;

    private List<TbAllarea> getAdress(Integer parent){
        TbAllarea tbAllarea = new TbAllarea();
        tbAllarea.setParent(parent);
        Response<List<TbAllarea>> listResponse = tbAllareaClient.find(tbAllarea);

        return listResponse.getData();
    }

    public void setModeAttribute(Model model, String name,Integer parent){
        if(model == null || name ==null || name.equals("")){
            return;
        }
        model.addAttribute(name,getAdress(parent));
    }


}
