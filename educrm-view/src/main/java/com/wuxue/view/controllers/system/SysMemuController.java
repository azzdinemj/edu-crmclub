package com.wuxue.view.controllers.system;

import com.wuxue.model.SysMenu;
import com.wuxue.utils.contract.Response;
import com.wuxue.view.client.system.SysMenuClient;
import com.wuxue.view.interfaces.IQueryController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping(value = "/system/sysMenu")
public class SysMemuController  implements IQueryController<SysMenu, Response<List<SysMenu>>> {

    @Autowired
    private SysMenuClient sysMenuClient;

    @Override
    //@RequestMapping(value = "/query",method = RequestMethod.POST)
    @ResponseBody
    public Response<List<SysMenu>> query(HttpServletRequest request, Model model, SysMenu sysMenu) {
        return sysMenuClient.find(sysMenu);
    }
}
