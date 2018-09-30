package com.wuxue.api.service.impl;

import com.wuxue.api.mapper.NoticeRecordMapper;
import com.wuxue.api.mapper.SchoolbusLineMapper;
import com.wuxue.api.service.SysAutoCodeService;
import com.wuxue.api.service.junhwa.SchoolBusLineService;
import com.wuxue.api.utils.DateUtil;
import com.wuxue.api.utils.dozer.BeanMapperUtil;
import com.wuxue.model.shuttle.SchoolbusLine;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;
import com.wuxue.vo.SchoolBusLineVO;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service("schoolBusLineService")
public class SchoolBusLineServiceImpl extends BeanMapperUtil implements SchoolBusLineService {
    @Autowired
    private SchoolbusLineMapper schoolbusLineMapper;
    @Autowired
    private SysAutoCodeService sysAutoCodeService;

    @Autowired
    private NoticeRecordMapper noticeRecordMapper;

    @Override
    public Response findBusLineByBusIdAndDirection(SchoolbusLine schoolbusLine) {
        Response response = Response.newResponse();
        String schoolBusId = schoolbusLine.getSchoolbusId();
        if (null == schoolBusId || "".equals(schoolBusId)) {
            return response.PARAMS_ISNULL();
        }
        Byte direction = schoolbusLine.getDirection();
        if (null == direction || "".equals(direction) || 0 == direction) {
            return response.PARAMS_ISNULL();
        }

        List<SchoolbusLine> schoolbusLines = schoolbusLineMapper.findBusLineByBusIdAndDirection(schoolbusLine);

        if (CollectionUtils.isEmpty(schoolbusLines)) {
            response.setData(new ArrayList<>(0));
            return response;
        }
        List<String> indexNos = new ArrayList<>();
        for (SchoolbusLine busLine : schoolbusLines) {
            indexNos.add(String.valueOf(busLine.getIndexno()));
        }

        List<String> lineNumList = noticeRecordMapper.selectNoticeRecordByIndexNo(indexNos, DateUtil.getCurrentDayStartTime(), DateUtil.getCurrentDayEndTime());
        if (CollectionUtils.isNotEmpty(lineNumList)) {
            for (SchoolbusLine busLine : schoolbusLines) {
                if (lineNumList.contains(String.valueOf(busLine.getIndexno()))) {
                    busLine.setSendMessage(true);
                }
            }
        }
        SchoolbusLine schoolbusLineFirst = schoolbusLines.get(0);
        schoolbusLines.remove(0);
        schoolbusLines.add(schoolbusLineFirst);

        response.setData(toList(schoolbusLines, SchoolBusLineVO.class));
        response.setTotal(schoolbusLines.size());
        return response;
    }

    @Override
    public Response saveAll(Request<List<SchoolbusLine>> request) {

        Response response = Response.newResponse();
        List<SchoolbusLine> data = request.getData();
        if (data == null || data.size() == 0) {
            return response.PARAMS_ISNULL();
        }
        String message = "";
        try {
            if (data != null && data.size() > 0) {
                for (SchoolbusLine schoolbusLine : data) {

                    if (schoolbusLine.getIndexno() == null) {
                        schoolbusLine.setShortCode(sysAutoCodeService.getCode("busline"));
                        schoolbusLineMapper.insertSelective(schoolbusLine);
                    }
                    schoolbusLineMapper.updateByPrimaryKeySelective(schoolbusLine);

                }

            }

        } catch (Exception ex) {
            message = ex.getMessage();
            return response.SAVE_FAIL(message);
        }


        return response;
    }

    @Override
    public Response delete(Request<SchoolbusLine> request) {
        SchoolbusLine schoolbusLine = request.getData();
        Response response = Response.newResponse();
        String message = "";
        if (schoolbusLine == null) {
            return response.PARAMS_ISNULL();
        }

        try {
            SchoolbusLine select = schoolbusLineMapper.selectByPrimaryKey(schoolbusLine);
            if (schoolbusLine == null) {
                return response.DELETE_FAIL(message);
            }
            schoolbusLine.setIsvalid(0);
            schoolbusLineMapper.updateStationid(select);
            schoolbusLineMapper.updateByPrimaryKeySelective(schoolbusLine);
        } catch (Exception ex) {
            message = ex.getMessage();
            return response.DELETE_FAIL(message);
        }


        return response;
    }

    @Override
    public Response save(Request<SchoolbusLine> request) {
        Response response = Response.newResponse();
        SchoolbusLine data = request.getData();
        String message = "";
        if (data == null) {
            return response.PARAMS_ISNULL();
        }
        Long indexno = data.getIndexno();

        try {


            SchoolbusLine select = schoolbusLineMapper.selectById(indexno);
            if (select != null) {
                if (data.getStationid() != select.getStationid()) {
                    schoolbusLineMapper.updateAddStationid(data);
                }
                schoolbusLineMapper.updateIndexnoSelective(data);
            } else {
                SchoolbusLine select1 = schoolbusLineMapper.selectByPrimaryKey(data);
                if (select1 != null) {
                    select1.setIsvalid(1);
                    schoolbusLineMapper.updateAddStationid(select1);
                    schoolbusLineMapper.updateByPrimaryKeySelective(select1);
                } else {
                    data.setShortCode(sysAutoCodeService.getCode("busline"));
                    data.setIsvalid(1);
                    schoolbusLineMapper.updateAddStationid(data);
                    schoolbusLineMapper.insertSelective(data);
                }


            }

        } catch (Exception ex) {
            message = ex.getMessage();
            return response.SAVE_FAIL(message);
        }


        return response;
    }

    @Override
    public Response find(Request<SchoolbusLine> request) {
        Response response = Response.newResponse();
        SchoolbusLine data = request.getData();
        if (data == null) {
            return response.PARAMS_ISNULL();
        }
        SchoolbusLine select = schoolbusLineMapper.selectByPrimaryKey(data);
        response.setData(select);
        return response;
    }

    @Override
    public Response findByBusId(Request<SchoolbusLine> request) {
        Response response = Response.newResponse();
        SchoolbusLine data = request.getData();
        if (data == null) {
            return response.PARAMS_ISNULL();
        }
        String schoolbusId = data.getSchoolbusId();
        if (schoolbusId == null || "".equals(schoolbusId)) {
            return response.PARAMS_ISNULL();
        }
        List<SchoolbusLine> list = schoolbusLineMapper.findByBusId(data);
        if (list != null && list.size() > 0) {
            SchoolbusLine schoolbusLineFirst = list.get(0);
            if (schoolbusLineFirst.getStationid() ==-1){
                list.remove(0);
                list.add(schoolbusLineFirst);
            }

        }

        response.setData(list);
        return response;
    }

    @Override
    public Response reverseLines(Request<SchoolbusLine> request) {

        SchoolbusLine data = request.getData();
        Response response = Response.newResponse();
        String message = "";
        if (data == null) {
            return response.PARAMS_ISNULL();
        }
        String schoolbusId = data.getSchoolbusId();
        if (schoolbusId == null || "".equals(schoolbusId)) {
            return response.PARAMS_ISNULL();
        }
        try {
            data.setDirection(new Byte("2"));
            List<SchoolbusLine> select = schoolbusLineMapper.findByBusId(data);
            if (select == null || select.size() == 0) {
                return response.PARAMS_ISNULL();
            }
            SchoolbusLine schoolbusLine1 = new SchoolbusLine();
            schoolbusLine1.setSchoolbusId(data.getSchoolbusId());
            schoolbusLine1.setDirection(new Byte("1"));
            schoolbusLineMapper.deleteByBusId(schoolbusLine1);
            for (int i = 0; i < select.size(); i++) {
                SchoolbusLine schoolbusLine = select.get(i);
                if (schoolbusLine.getStationid() == 0) {
                    schoolbusLine.setStationid(new Byte("-1"));
                } else if (schoolbusLine.getStationid() == -1) {
                    schoolbusLine.setStationid(new Byte("0"));
                } else {
                    schoolbusLine.setStationid(new Byte("" + (select.size() - i)));
                }
                schoolbusLine.setDirection(new Byte("1"));

                schoolbusLine.setIndexno(null);
                schoolbusLine.setShortCode(sysAutoCodeService.getCode("busline"));
                schoolbusLine.setIsvalid(1);
                schoolbusLineMapper.insertSelective(schoolbusLine);

            }

        } catch (Exception ex) {
            message = ex.getMessage();
            response.SAVE_FAIL(message);
        }


        return response;
    }
}
