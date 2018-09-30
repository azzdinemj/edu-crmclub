package com.wuxue.view.utils;

import com.wuxue.model.SysAutocode;
import com.wuxue.view.client.system.SysAutcodeClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysAutoCodeUtils {

    @Autowired
    private SysAutcodeClient sysAutcodeClient;


    public String getSysAutocode(String pkSysAutoCode){
        SysAutocode sysAutocode = new SysAutocode();
        sysAutocode.setPkDomain(SessionCache.getPkdomain());
        sysAutocode.setPkSysAutocode(pkSysAutoCode);
        String code = sysAutcodeClient.getCode(sysAutocode);

        return code;

    }



}
