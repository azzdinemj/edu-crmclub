package com.wuxue.api.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wuxue.api.mapper.SysAutocodeCounterMapper;
import com.wuxue.api.mapper.SysAutocodeMapper;
import com.wuxue.api.service.UtilsService;
import com.wuxue.model.SysAutocode;
import com.wuxue.api.service.SysAutoCodeService;
import com.wuxue.model.SysAutocodeCounter;
import com.wuxue.api.utils.GuidUtils;
import com.wuxue.utils.contract.LinkEntity;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@Service("sysAutocodeService")
public class SysAutoCodeServiceImpl implements SysAutoCodeService {
    @Autowired
    SysAutocodeMapper sysAutocodeMapper;

    @Autowired
    SysAutocodeCounterMapper sysAutocodeCounterMapper;

    @Autowired
    UtilsService utilsService;

    @Override
    public Response delete(Request<String> tParams) {
        Response response = Response.newResponse();
        String primaryKey = tParams.getData();
        if (primaryKey == null || primaryKey.equals("")) {
            return response.PARAMS_ISNULL();
        }
        int iReuslt = 1;
        String message = "";
        try {
            iReuslt = sysAutocodeMapper.deleteByPrimaryKey(primaryKey);
        } catch (Exception ex) {
            message = ex.getMessage();
            return response.DELETE_FAIL(message);
        }
        if (iReuslt > 0) {
            return response;
        }
        return response.DELETE_FAIL(message);
    }

    @Override
    public Response find(Request<SysAutocode> tParams) {
        Response response = Response.newResponse();
        SysAutocode sysAutocode = tParams.getData();

        if (sysAutocode == null) {
            return response.PARAMS_ISNULL();
        }
        String primaryKey = sysAutocode.getPkSysAutocode();
        if (primaryKey != null && !primaryKey.equals("")) {
            SysAutocode byPrimaryKey = sysAutocodeMapper.selectByPrimaryKey(primaryKey);
            utilsService.setSysUserKeyValue(byPrimaryKey,byPrimaryKey.getCreator(), LinkEntity.CREATOR_ENTITY);
            utilsService.setSysUserKeyValue(byPrimaryKey,byPrimaryKey.getModifier(),LinkEntity.MODIFIER_ENTITY);
            response.setData(byPrimaryKey);
        } else {
            PageHelper.startPage(sysAutocode.getPageNo(), sysAutocode.getPageSize());
            List<SysAutocode> autocodeList = sysAutocodeMapper.select(sysAutocode);
            PageInfo pageInfo = new PageInfo(autocodeList);
            response.setTotal(pageInfo.getTotal());
            if (autocodeList.size() > 0) {
                for (SysAutocode list : autocodeList) {
                    utilsService.setSysUserKeyValue(list, list.getCreator(), LinkEntity.CREATOR_ENTITY);
                    utilsService.setSysUserKeyValue(list, list.getModifier(), LinkEntity.MODIFIER_ENTITY);
                }
            }
            response.setData(autocodeList);
            //response.setTotal(sysAutocodeMapper.countBy(sysAutocode));

        }
        return response;
    }

    @Override
    public Response save(Request<SysAutocode> tParams) {
        Response response = Response.newResponse();
        SysAutocode sysAutocode = tParams.getData();

        if (sysAutocode == null) {
            return response.PARAMS_ISNULL();
        }
        String primaryKey = sysAutocode.getPkSysAutocode();
        SysAutocode select = null;
        if (primaryKey != null && !primaryKey.equals("")) {
            select = sysAutocodeMapper.selectByPrimaryKey(primaryKey);
        }
        int iReuslt = 1;
        String message = "";
        try {
            if (select != null) {
                sysAutocode.setLasteditDate(new Date());
                iReuslt = sysAutocodeMapper.updateByPrimaryKeySelective(sysAutocode);
            } else {
                sysAutocode.setPkSysAutocode(GuidUtils.getGuid());
                sysAutocode.setCreationDate(new Date());
                sysAutocode.setLasteditDate(new Date());
                sysAutocode.setIsvalid(1);
                iReuslt = sysAutocodeMapper.insertSelective(sysAutocode);
            }
        } catch (Exception ex) {
            message = ex.getMessage();
            return response.SAVE_FAIL(message);
        }
        if (iReuslt > 0) {
            return response;
        }
        return response.SAVE_FAIL(message);
    }

    @Override
    public Response getAutocode(Request<SysAutocode> tParams) {
        SysAutocode sysAutocode = tParams.getData();
        return getCode(sysAutocode);
    }

    public String getSysAutocode(String key) {
        SysAutocode sysAutocode = new SysAutocode();
        if(key != null && !"".equals(key)){
            sysAutocode.setPkSysAutocode(key);
        }
        return getCode(sysAutocode).toString();
    }


    /**
     * 编码计数增加
     */
    @Override
    public Response updateCodeNum(SysAutocode sysAutocode) {
        //            查询编码规则主表
        List<SysAutocode> sysAutocodes = sysAutocodeMapper.select(sysAutocode);
        Response response = Response.newResponse();
        Object data = getCode(sysAutocode).getData();
        response.setData(data);
        if (sysAutocodes.size() > 0) {
//            查询编码规则字表获取次数
            SysAutocodeCounter sysAutocodeCounter = new SysAutocodeCounter();
            sysAutocodeCounter.setPkSysAutocode(sysAutocodes.get(0).getPkSysAutocode());
            sysAutocodeCounter.setDate(sysAutocodes.get(0).getKind());
            List<SysAutocodeCounter> counterList = sysAutocodeCounterMapper.select(sysAutocodeCounter);

//        存入数据
            if (counterList.size() < 1) {
                sysAutocodeCounter.setNum(0);
                sysAutocodeCounter.setPkSysAutocodeCounter(GuidUtils.getGuid());
                sysAutocodeCounter.setCreationDate(new Date());
                sysAutocodeCounter.setLasteditDate(new Date());
                sysAutocodeCounter.setCreator("Rogue");
                sysAutocodeCounter.setModifier("Rogue");
                sysAutocodeCounterMapper.insertSelective(sysAutocodeCounter);
            } else {
                Integer num = counterList.get(0).getNum() + 1;
                counterList.get(0).setNum(num);
                sysAutocodeCounterMapper.updateByPrimaryKeySelective(counterList.get(0));
            }
        }
        return response;
    }

    @Override
    public String getCode(String key){
        SysAutocode sysAutocode = new SysAutocode();
        sysAutocode.setPkSysAutocode(key);
        Response updateCodeNum = updateCodeNum(sysAutocode);
        if (updateCodeNum !=null && updateCodeNum.getData() != null) {
            return updateCodeNum.getData().toString();
        }
        return GuidUtils.getGuid();
    }


    /**
     * 拼接code
     */
    private Response getCode(SysAutocode sysAutocode) {
        Response response = Response.newResponse();

        if (sysAutocode != null) {
//            查询编码规则主表
            List<SysAutocode> sysAutocodes = sysAutocodeMapper.select(sysAutocode);

            if (sysAutocodes.size() > 0) {
//            查询编码规则字表获取次数
                SysAutocodeCounter sysAutocodeCounter = new SysAutocodeCounter();
                sysAutocodeCounter.setPkSysAutocode(sysAutocodes.get(0).getPkSysAutocode());
                sysAutocodeCounter.setDate(sysAutocodes.get(0).getKind());
                List<SysAutocodeCounter> counterList = sysAutocodeCounterMapper.select(sysAutocodeCounter);

//            拼接字段
                SimpleDateFormat sdf = new SimpleDateFormat(sysAutocodes.get(0).getKind());
                StringBuffer sb = new StringBuffer();
                sb.append(sysAutocodes.get(0).getPrefix());
                sb.append(sdf.format(new Date()));
                if (sysAutocodes.get(0).getIsline() != 0) {
                    sb.append("-");
                }
                Integer num = 1;
                if (counterList.size() < 1) {
                    sysAutocodeCounter.setNum(0);
                    sysAutocodeCounter.setPkSysAutocodeCounter(GuidUtils.getGuid());
                    sysAutocodeCounter.setCreationDate(new Date());
                    sysAutocodeCounter.setLasteditDate(new Date());
                    sysAutocodeCounter.setCreator("Rogue");
                    sysAutocodeCounter.setModifier("Rogue");
                    sysAutocodeCounterMapper.insertSelective(sysAutocodeCounter);
                } else {
                    num = counterList.get(0).getNum() + 1;
                }
                String format = String.format("%0" + sysAutocodes.get(0).getZeroWidth() + "d", num);
                sb.append(format);
                System.out.println(sb.toString());
                response.setData(sb);
            }else{
                return response.SYSAUTOCODE_ISNULL();
            }
        } else {
            return response.PARAMS_ISNULL();
        }
        return response;
    }
}
