package com.wuxue.view.utils;

import com.wuxue.model.StudentSpecialty;
import com.wuxue.utils.common.DataUtils;
import com.wuxue.utils.contract.Light;
import org.springframework.ui.Model;

import java.util.List;

public class LinkingUtils {
    public static <T> void setModeAttribute(Model model, String name, Object object, Class<T> clazz) {
        if (model == null || name == null || name.equals("") || object == null) {
            return;
        }
        List<T> list = DataUtils.objectToList(object, clazz);
        model.addAttribute(name, list);
    }
}
