package com.wuxue.api.controller.junhua;

import com.wuxue.api.interfaces.IDeleteController;
import com.wuxue.api.interfaces.IFindController;
import com.wuxue.api.interfaces.ISaveController;
import com.wuxue.api.service.JhAnswerService;
import com.wuxue.api.service.JhExaminationService;
import com.wuxue.model.JhAnswer;
import com.wuxue.model.JhExamination;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


@RestController
@RequestMapping(value = "api/junhua/examination")
public class JhExaminationController implements IFindController<JhExamination>,
ISaveController<JhExamination>,IDeleteController<JhExamination> {

	@Autowired
	private JhExaminationService jhExaminationService;

	protected Map<String,Object> convertToPaging(long total, long displayTotal, Object data){
		Map map = new HashMap<String, Object>();
		map.put("aaData", data);
		//页数信息配置
		map.put("sEcho", null);
		map.put("iTotalRecords", total);
		map.put("iTotalDisplayRecords",displayTotal);
		return map;
	}

	@Override
	public Response Delete(@RequestBody Request<JhExamination> request) {

		return jhExaminationService.delete(request);
	}

	@Override
	public Response Save(@RequestBody Request<JhExamination> request) {

		return jhExaminationService.save(request);
	}

	@Override
	public Response Find(@RequestBody Request<JhExamination> request) {
		
		return jhExaminationService.find(request);
	}


	@RequestMapping("queryByPk")
	@ResponseBody
	public Response queryByPk(HttpServletRequest request,JhExamination jhExamination){
		Request request1=new Request();
		request1.setData(jhExamination);
	    return jhExaminationService.find(request1);
	}

	/**
	 *
	 * @param request
	 * @param response
	 * @param jhExamination
	 * @param sEcho
	 * @param start
	 * @param length
	 * @return
	 */
	@RequestMapping("queryByPaging")
	@ResponseBody
	public Map<String, Object> queryByPaging(HttpServletRequest request, HttpServletResponse response, JhExamination jhExamination, Integer sEcho, Integer start, Integer length) {
		jhExamination.setPageNo((start/length)+1);
		jhExamination.setPageSize(length);

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

//		jhExamination.setStartTime(sdf.parse(jhExamination.getStartTimes())); //时间范围
//		jhExamination.setEndTime(sdf.parse(jhExamination.getEndTimes()));     //时间范围

//		String dateTime = request.getParameter("dateTime");
//		if (dateTime != null && !"".equals(dateTime)) {
//			String substring = dateTime.substring(0, 10);
//			String substring1 = dateTime.substring(12);
//			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
//			try {
//				Date strTime = simpleDateFormat.parse(substring);
//				Date endTime = simpleDateFormat.parse(substring1);
//				jhExamination.setFromDate(strTime);
//				jhExamination.setToDate(endTime);
//			} catch (ParseException e) {
//				e.printStackTrace();
//			}
//		}

		Request request1=new Request();
		request1.setData(jhExamination);
		Response<List<JhExamination>> listResponse =jhExaminationService.find(request1);
		List<JhExamination> data = listResponse.getData();
		if(data==null) {
			data = new ArrayList<>();
		}
		return convertToPaging(listResponse.getTotal(),listResponse.getTotal(),data);
	}


	@RequestMapping(value = "/statistics",method = RequestMethod.POST)
	public Response statistics(@RequestBody Request<JhExamination> request){
		return jhExaminationService.statistics(request);
	}


}
