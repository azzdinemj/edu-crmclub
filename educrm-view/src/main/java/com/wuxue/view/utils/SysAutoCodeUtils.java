package com.wuxue.view.utils;

import com.wuxue.model.SysAutocode;
import com.wuxue.model.SysSet;
import com.wuxue.utils.contract.Response;
import com.wuxue.view.client.system.SysAutcodeClient;
import com.wuxue.view.client.system.SysSetClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysAutoCodeUtils {

    @Autowired
    private SysAutcodeClient sysAutcodeClient;
    @Autowired
    private SysSetClient sysSetClient;


    public String getSysAutocode(String pkSysAutoCode){
        SysAutocode sysAutocode = new SysAutocode();
        sysAutocode.setPkDomain(SessionCache.getPkdomain());
        sysAutocode.setPkSysAutocode(pkSysAutoCode);
        String code = sysAutcodeClient.getCode(sysAutocode);

        return code;

    }

    /**
     * 获取编码规则全局变量
     * @return
     */
    public String getPKSysSet(String pkSysSet,String pkDomain){
        SysSet sysSet = new SysSet();
        sysSet.setPkSysSet(pkSysSet);
        sysSet.setPkDomain(pkDomain);
        String value = "";
        Response<SysSet> byPrimaryKey = sysSetClient.findByPrimaryKey(sysSet);
        SysSet data = byPrimaryKey.getData();
        if (data != null && data.getPkSysSet() != null){
            value = data.getValue();
        }
        return value;
    }

}
