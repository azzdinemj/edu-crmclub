package com.wuxue.api.service.junhwa;

import com.wuxue.model.shuttle.SchoolbusLine;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;

import java.util.List;

/**
 * 校车行车路线
 * @author tly
 * @date 2018-08-03
 */
public interface SchoolBusLineService {
    /**
     * 根据校车id方向查询行车路线
     * @param schoolbusLine#schoolBusId 校车id
     * @param schoolbusLine#direction 方向 1：上学路线。2，放学路线
     * @return 站点集合
     */
    Response findBusLineByBusIdAndDirection( SchoolbusLine schoolbusLine );

    Response saveAll(Request<List<SchoolbusLine>> request);

    Response delete(Request<SchoolbusLine> request);

    Response save(Request<SchoolbusLine> request);

    Response find(Request<SchoolbusLine> request);

    Response findByBusId(Request<SchoolbusLine> request);

    Response reverseLines(Request<SchoolbusLine> request);
}
