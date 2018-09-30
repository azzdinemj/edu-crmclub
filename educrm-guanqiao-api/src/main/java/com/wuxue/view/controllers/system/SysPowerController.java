package com.wuxue.view.controllers.system;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.wuxue.model.SysMenu;
import com.wuxue.model.SysPowerKey;
import com.wuxue.model.SysRole;
import com.wuxue.model.SysUser;
import com.wuxue.utils.common.DataUtils;
import com.wuxue.utils.contract.Light;
import com.wuxue.utils.contract.Response;
import com.wuxue.view.client.system.SysMenuClient;
import com.wuxue.view.client.system.SysRoleClient;
import com.wuxue.view.client.system.SysUserClient;
import com.wuxue.view.controllers.BaseController;
import com.wuxue.view.interfaces.IQueryController;
import com.wuxue.view.utils.SessionCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * Created by Jamie on 2018/1/11.
 */
@Controller
@RequestMapping(value = "/system/sysPower")
public class SysPowerController extends BaseController implements IQueryController<String,Response<List<SysMenu>>> {

    @Autowired
    private SysMenuClient sysMenuClient;

    @Autowired
    private SysUserClient sysUserClient;

    @Autowired
    private SysRoleClient sysRoleClient;
    @Override
    @ResponseBody
    public Response<List<SysMenu>> query(HttpServletRequest request, Model model, String s) {
        Response<List<SysMenu>> response = new Response<>();
        response.setData(SessionCache.getSysMenu());
        return  response;
    }
}
