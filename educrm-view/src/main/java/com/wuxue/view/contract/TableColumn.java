package com.wuxue.view.contract;

/**
 * Created by Jamie on 2018/4/2.
 */
public class TableColumn {
    private String sTitle;
    private String sTitleEng;
    private String data;
    private String render;

    public String getsTitle() {
        return sTitle;
    }

    public void setsTitle(String sTitle) {
        this.sTitle = sTitle;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getRender() {
        return render;
    }

    public void setRender(String render) {
        this.render = render;
    }

    public String getsTitleEng() {
        return sTitleEng;
    }

    public void setsTitleEng(String sTitleEng) {
        this.sTitleEng = sTitleEng;
    }
}
