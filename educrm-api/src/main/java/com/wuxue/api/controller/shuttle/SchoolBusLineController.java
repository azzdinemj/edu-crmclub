package com.wuxue.api.controller.shuttle;

import com.wuxue.api.interfaces.IDeleteController;
import com.wuxue.api.interfaces.IFindController;
import com.wuxue.api.interfaces.ISaveController;
import com.wuxue.api.service.junhwa.SchoolBusLineService;
import com.wuxue.model.shuttle.SchoolbusLine;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 校车路线
 *
 * @author tly
 * @data 2018/08/03
 */
@RestController
@RequestMapping(value = "api/shuttle/schoolBusLine")
public class SchoolBusLineController implements ISaveController<SchoolbusLine>,IDeleteController<SchoolbusLine>,IFindController<SchoolbusLine> {
    @Autowired
    private SchoolBusLineService schoolBusLineService;

    @RequestMapping(value = "/findBusLine" ,method = RequestMethod.POST)
    public Response findBusLine(@RequestBody  Request<SchoolbusLine> request) {
        SchoolbusLine schoolbusLine = request.getData();
        return schoolBusLineService.findBusLineByBusIdAndDirection(schoolbusLine);
    }

    @RequestMapping(value = "/saveall",method = RequestMethod.POST)
    public Response saveAll(@RequestBody Request<List<SchoolbusLine>> request) {
        return schoolBusLineService.saveAll(request);
    }

    @Override
    public Response Delete(@RequestBody Request<SchoolbusLine> request) {
        return schoolBusLineService.delete(request);
    }

    @Override
    public Response Save(@RequestBody Request<SchoolbusLine> request) {
        return schoolBusLineService.save(request);
    }

    @Override
    public Response Find(@RequestBody Request<SchoolbusLine> request) {

        return schoolBusLineService.find(request);
    }
    @RequestMapping(value = "/findbybusid",method = RequestMethod.POST)
    public Response findByBusId(@RequestBody Request<SchoolbusLine> request) {

        return schoolBusLineService.findByBusId(request);
    }
    @RequestMapping(value = "/reverselines",method = RequestMethod.POST)
    public Response reverseLines(@RequestBody Request<SchoolbusLine> request) {
        return schoolBusLineService.reverseLines(request);
    }
}
