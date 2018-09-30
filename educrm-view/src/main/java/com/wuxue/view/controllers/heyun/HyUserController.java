package com.wuxue.view.controllers.heyun;

import com.wuxue.model.HyUser;
import com.wuxue.utils.contract.Response;
import com.wuxue.view.client.heyun.HyUserClient;
import com.wuxue.view.controllers.BaseController;
import com.wuxue.view.interfaces.IQueryByPagingController;
import com.wuxue.view.interfaces.IQueryController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 *出行人
 */
@Controller
@RequestMapping("/heyun/hyUser")
public class HyUserController extends BaseController implements IQueryByPagingController<HyUser,Map<String,Object>>,IQueryController<HyUser,String> {

    @Autowired
    private HyUserClient hyUserClient;

    /**
     *
     * @param model
     * @return
     */
    @Override
    public String query(HttpServletRequest request, Model model, HyUser hyUser)  {
        return "/heyun/hyUserList";
    }

    @Override
    @ResponseBody
    public Map<String, Object> queryByPaging(HttpServletRequest request, HttpServletResponse response, HyUser hyUser, Integer sEcho, Integer start, Integer length) {
        hyUser.setPageNo((start/length)+1);
        hyUser.setPageSize(length);

        Response<List<HyUser>> resp=hyUserClient.find(hyUser);
        List<HyUser> data = resp.getData();
        if(data==null) {
            data = new ArrayList<>();
        }
        return convertToPaging(resp.getTotal(),resp.getTotal(),data);
    }

}
