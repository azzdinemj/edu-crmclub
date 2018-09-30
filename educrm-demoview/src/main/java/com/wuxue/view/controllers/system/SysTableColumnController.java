package com.wuxue.view.controllers.system;

import com.wuxue.model.SysTableColumn;
import com.wuxue.model.SysTableColumns;
import com.wuxue.utils.common.DataUtils;
import com.wuxue.utils.contract.Light;
import com.wuxue.utils.contract.Response;
import com.wuxue.view.client.system.SysTableColumnClient;
import com.wuxue.view.contract.TableColumn;
import com.wuxue.view.controllers.BaseController;
import com.wuxue.view.interfaces.IQueryController;
import com.wuxue.view.utils.SessionCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jamie on 2018/4/1.
 */
@Controller
@RequestMapping(value = "/system/sysTableColumn")
public class SysTableColumnController extends BaseController implements IQueryController<SysTableColumn, List<TableColumn>> {
    @Autowired
    SysTableColumnClient sysTableColumnClient;

    @Override
    @ResponseBody
    public List<TableColumn> query(HttpServletRequest request, Model model, SysTableColumn sysTableColumn)  {
        List<TableColumn> list = new ArrayList<>();
        Response<SysTableColumn> response = sysTableColumnClient.findByPrimaryKey(sysTableColumn);
        if (response != null && response.getCode() == 0 && response.getData() != null && response.getData().getMap().get(Light.CHILD) != null) {
            List<SysTableColumns> sysTableColumnsList = DataUtils.objectToList(response.getData().getMap().get(Light.CHILD), SysTableColumns.class);
            if(sysTableColumnsList !=null){
                for(SysTableColumns sysTableColumns : sysTableColumnsList){
                    TableColumn tableColumn = new TableColumn();
                    if(SessionCache.getLoginVersion().equals("English")){
                        tableColumn.setsTitle(sysTableColumns.getCaptionEng());
                    }else {
                        tableColumn.setsTitle(sysTableColumns.getCaption());
                    }
                    tableColumn.setData(sysTableColumns.getData());
                    tableColumn.setRender(sysTableColumns.getRender());
                    list.add(tableColumn);
                }
            }
        }
        return list;
    }
}
