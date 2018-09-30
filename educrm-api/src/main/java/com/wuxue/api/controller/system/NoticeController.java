package com.wuxue.api.controller.system;

import com.wuxue.api.interfaces.IDeleteController;
import com.wuxue.api.interfaces.IFindController;
import com.wuxue.api.interfaces.ISaveController;
import com.wuxue.api.service.NoticeService;
import com.wuxue.model.Notice;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Rogue on 2018/01/02.
 */
@RestController
@RequestMapping(value = "api/system/notice")
public class NoticeController implements IFindController<Notice>,
        ISaveController<Notice>,IDeleteController<String> {
    @Autowired
    private NoticeService noticeService;

    @Override
    public Response Find(@RequestBody Request<Notice> notice) {
        return noticeService.find(notice);
    }

    @Override
    public Response Save(@RequestBody Request<Notice> notice) {
        return noticeService.save(notice);
    }
    @Override

    public Response Delete(@RequestBody Request<String> notice) {
        return noticeService.delete(notice);

    }

    @RequestMapping(value = "/findAll",method = RequestMethod.POST)
    public Response findAll(@RequestBody Request<Notice> notice) {
        return noticeService.findAll(notice);
    }
}
