package com.wuxue.view.controllers.student;

import com.google.gson.Gson;
import com.wuxue.model.junhwa.StudentNutritionAnalysis;
import com.wuxue.utils.contract.Response;
import com.wuxue.view.client.student.StudentTrophicAnalysisClient;
import com.wuxue.view.controllers.BaseController;
import com.wuxue.view.interfaces.IQueryController;
import com.wuxue.view.utils.DateTimeUtils;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 营养分析
 */
@Controller
@RequestMapping(value = "/student/trophicAnalysis")
public class StudentTrophicAnalysisController extends BaseController
        implements IQueryController<StudentNutritionAnalysis, String> {

    @Autowired
    private StudentTrophicAnalysisClient studentTrophicAnalysisClient;

    @InitBinder("studentNutritionAnalysis")
    public void initBinder(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("stu.");
    }

    @Override
    public String query(HttpServletRequest request, Model model, StudentNutritionAnalysis studentNutritionAnalysis) {

        String studentName = request.getParameter("studentName");
        model.addAttribute("studentName",studentName);
        model.addAttribute("yearAndMonth",String.format("%s-%s",DateTimeUtils.getCurrentYear(),DateTimeUtils.getLastMonth()));
        return "/canteen/trophicanalysis";
    }

    //
    @ResponseBody
    @RequestMapping(value = "/findList", method = RequestMethod.POST)
    public String selectList(HttpServletRequest request, Model model, StudentNutritionAnalysis studentNutritionAnalysis) {
        studentNutritionAnalysis.setPkStudent("201820909861061752");
        Response<List<StudentNutritionAnalysis>> listResponse = studentTrophicAnalysisClient.find(studentNutritionAnalysis);
        List<StudentNutritionAnalysis> data = listResponse.getData();
        List<ValueStyle> valueStyles = new ArrayList<>();
        List<String> nutritionNames = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(data)) {
            for (StudentNutritionAnalysis nutritionAnalysis : data) {
//                realmounts.add(nutritionAnalysis.getRealmount());
                ValueStyle valueStyle = new ValueStyle();
                valueStyle.value(nutritionAnalysis.getRealmount());
                ItemStyle itemStyle = new ItemStyle();
                Normal normal = new Normal();
                Boolean hight = nutritionAnalysis.getRealmount().compareTo(nutritionAnalysis.getStandmountHigh()) > 0;
                Boolean low = nutritionAnalysis.getRealmount().compareTo(nutritionAnalysis.getStandmountLow()) < 0;
                if (hight || low) {
                    normal.color("#FF4E00");
                } else {
                    normal.color("#1D92E3");
                }
                itemStyle.normal(normal);
                valueStyle.itemStyle(itemStyle);
                valueStyles.add(valueStyle);
                nutritionNames.add(String.format("%s(%s)",nutritionAnalysis.getNutrition().getNutritionName(),nutritionAnalysis.getNutrition().getUnit()));
            }
        } else {
            String[] strs = new String[]{"钙", "铁", "锌", "硒", "维生素"};
            for (int i = 0; i < 5; i++) {
                ValueStyle valueStyle = new ValueStyle();
                valueStyle.value(BigDecimal.valueOf(0));
                valueStyles.add(valueStyle);
                nutritionNames.add(strs[i]);
            }
        }
        Option option = new Option();
        option.tooltip(new Tooltip());
        CategoryAxis categoryAxis = new CategoryAxis();
        categoryAxis.data(nutritionNames);
        categoryAxis.type("category");
        option.xAxis(categoryAxis);
        ValueAxis valueAxis = new ValueAxis();
        valueAxis.type("value");
        option.yAxis(valueAxis);

        Bar bar = new Bar();
        bar.data(valueStyles);
        bar.type("bar");
        bar.barWidth("25");
        List<Bar> bars = new ArrayList<>();
        bars.add(bar);
        option.series(bars);


        Gson gson = new Gson();
        String json = gson.toJson(option);
        return json;
    }
//
//    /**
//     * 柱状图 图表option 的封装  测试用例--路段人流数据
//     *
//     * @return
//     */
//    @RequestMapping(value = "/bar", method = RequestMethod.POST)
//    @ResponseBody
//    public String bar() {
//        //路段
//        String[] roads = {"长江路", "长河路", "长安路", "西湖路", "建安路"};
//        //路段数据
//        int[] datas = {223, 153, 643, 234, 453};
//
//        Option option = new Option();
//
//        option.tooltip(new Tooltip());
//        //6.设置x轴数据
//        CategoryAxis categoryAxis = new CategoryAxis();
////        categoryAxis.data(roads);
//        categoryAxis.type("category");
//        option.xAxis(categoryAxis);
//
//        ValueAxis valueAxis = new ValueAxis();
//        valueAxis.type("value");
//        option.yAxis(valueAxis);
//
//        //8.填充柱状图数据
//        Bar bar = new Bar();
////        bar.data(datas);
//        bar.type("bar");
//        bar.barWidth("40%");
//        List<Bar> bars = new ArrayList<>();
//        bars.add(bar);
//        //装入数据表中
//        option.series(bars);
//        //option进行json格式处理
//        Gson gson = new Gson();
//        String result = gson.toJson(option);
//        return result;
//    }

    class Option {
        Legend legend;
        CategoryAxis xAxis;
        ValueAxis yAxis;
        List<Bar> series;
        Tooltip tooltip;

        public void tooltip(Tooltip tooltip) {
            this.tooltip = tooltip;
        }

        public void series(List<Bar> series) {
            this.series = series;
        }

        public void legend(Legend legend) {
            this.legend = legend;
        }

        public void xAxis(CategoryAxis xAxis) {
            this.xAxis = xAxis;
        }


        public void yAxis(ValueAxis yAxis) {
            this.yAxis = yAxis;
        }

    }

    class Legend {
        List<String> data;

        public void data(List<String> data) {
            this.data = data;
        }
    }

    class Tooltip {

    }

    class CategoryAxis {
        String type;
        List<String> data;

        public void type(String type) {
            this.type = type;
        }

        public void data(List<String> data) {
            this.data = data;
        }
    }

    class ValueAxis {
        String type;

        public void type(String type) {
            this.type = type;
        }
    }

    class Bar {
        List<ValueStyle> data;
        String type;
        String barWidth;


        public void barWidth(String barWidth) {
            this.barWidth = barWidth;
        }

        public void type(String type) {
            this.type = type;
        }

        public void data(List<ValueStyle> data) {
            this.data = data;
        }
    }

    class ValueStyle {
        BigDecimal value;
        ItemStyle itemStyle;

        public void value(BigDecimal value) {
            this.value = value;
        }

        public void itemStyle(ItemStyle itemStyle) {
            this.itemStyle = itemStyle;
        }
    }

    class ItemStyle {
        Normal normal;

        public void normal(Normal normal) {
            this.normal = normal;
        }
    }

    class Normal {
        String color;

        public void color(String color) {
            this.color = color;
        }
    }

}
