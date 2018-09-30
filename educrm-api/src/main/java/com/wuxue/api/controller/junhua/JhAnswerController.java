package com.wuxue.api.controller.junhua;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.wuxue.api.interfaces.IDeleteController;
import com.wuxue.api.interfaces.IFindController;
import com.wuxue.api.interfaces.ISaveController;
import com.wuxue.api.service.JhAnswerService;
import com.wuxue.api.service.JhAnswerdetailService;
import com.wuxue.api.service.TalkCloudRoomService;
import com.wuxue.model.*;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


@RestController
@RequestMapping(value = "api/junhua/answer")
public class JhAnswerController implements IFindController<JhAnswer>,
ISaveController<JhAnswer>,IDeleteController<JhAnswer> {

	@Autowired
	private JhAnswerService jhAnswerService;

	@Autowired
	private JhAnswerdetailService jhAnswerdetailService;

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
	public Response Delete(@RequestBody Request<JhAnswer> request) {

		return jhAnswerService.delete(request);
	}

	@Override
	public Response Save(@RequestBody Request<JhAnswer> request) {

		return jhAnswerService.save(request);
	}

	@Override
	public Response Find(@RequestBody Request<JhAnswer> request) {
		
		return jhAnswerService.find(request);
	}

	@RequestMapping(value = "/queryanswerlist",method = RequestMethod.POST)
	public Response queryAnswerList(@RequestBody Request<JhAnswer> request) {
		return jhAnswerService.queryAnswerList(request);
	}

	/**
	 *
	 * @param request
	 * @return
	 */
	@RequestMapping("saveAnswer")
	@ResponseBody
	public Response SaveAnswer(@RequestBody String request) {
		Response response=Response.newResponse();
		//数据JSON字符串
		JSONObject jsonObject = JSON.parseObject(request);

		JhAnswer jhAnswer = JSON.parseObject(request, new TypeReference<JhAnswer>() {});
		Request request1=new Request();
		request1.setData(jhAnswer);
		Response response1=jhAnswerService.save(request1);
		if(response1.getCode()==0){
			Object jsonObjectUser=jsonObject.get("answerdetail");
			List<JhAnswerdetail> jhAnswerdetails = JSON.parseObject(jsonObjectUser.toString(),new TypeReference<List<JhAnswerdetail>>(){});
			for (JhAnswerdetail jh:jhAnswerdetails) {
				jh.setPkExamination(jhAnswer.getPkExamination());
				jh.setPkAnswer(response1.getData().toString());
				Request request2=new Request();
				request2.setData(jh);
				jhAnswerdetailService.save(request2);
			}
             return  response;
		}

        return response.SAVE_FAIL("失败");
	}

	/**
	 *  根据条件查找出
	 * @param request
	 * @param response
	 * @param schedule
	 * @param sEcho
	 * @param start
	 * @param length
	 * @return
	 */
//	@RequestMapping("queryByPaging")
//	@ResponseBody
//	public Map<String, Object> queryByPaging(HttpServletRequest request, HttpServletResponse response, Schedule schedule, Integer sEcho, Integer start, Integer length) {
//		schedule.setPageNo((start/length)+1);
//		schedule.setPageSize(length);
//
//		String dateTime = request.getParameter("dateTime");
//		if (dateTime != null && !"".equals(dateTime)) {
//			String substring = dateTime.substring(0, 10);
//			String substring1 = dateTime.substring(12);
//			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
//			try {
//				Date strTime = simpleDateFormat.parse(substring);
//				Date endTime = simpleDateFormat.parse(substring1);
//				schedule.setFromDate(strTime);
//				schedule.setToDate(endTime);
//			} catch (ParseException e) {
//				e.printStackTrace();
//			}
//		}
//
//		Response<List<Schedule>> listResponse = scheduleClient.findguanqiao(schedule);
//		List<Schedule> data = listResponse.getData();
//		if(data==null) {
//			data = new ArrayList<>();
//		}
//		return convertToPaging(listResponse.getTotal(),listResponse.getTotal(),data);
//	}
//


}
