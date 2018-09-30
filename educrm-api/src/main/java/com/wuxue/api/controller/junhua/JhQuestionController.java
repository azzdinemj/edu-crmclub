package com.wuxue.api.controller.junhua;

import com.wuxue.api.interfaces.IDeleteController;
import com.wuxue.api.interfaces.IFindController;
import com.wuxue.api.interfaces.ISaveController;
import com.wuxue.api.service.JhQuestionService;
import com.wuxue.model.JhQuestion;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping(value = "api/junhua/jhQuestion")
public class JhQuestionController implements IFindController<JhQuestion>,
ISaveController<JhQuestion>,IDeleteController<JhQuestion> {

	@Autowired
	private JhQuestionService JhQuestionService;

	@Override
	public Response Delete(@RequestBody Request<JhQuestion> request) {

		return JhQuestionService.delete(request);
	}

	@Override
	public Response Save(@RequestBody Request<JhQuestion> request) {

		return JhQuestionService.save(request);
	}

	@Override
	public Response Find(@RequestBody Request<JhQuestion> request) {
		
		return JhQuestionService.find(request);
	}

	@RequestMapping(value = "/findbyuser",method = RequestMethod.POST)
	public Response FindByUser(@RequestBody Request<JhQuestion> request) {

		return JhQuestionService.FindByUser(request);
	}

}
