package com.wuxue.view.contract;

import java.util.List;

/**
 * Created by Jamie on 2018/1/24.
 */
public class TreeGridModel<T> {

    public TreeGridModel() {
        this.headerHeight = 40;
        this.indentation = 20;
    }

    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRenderTo() {
        return renderTo;
    }

    public void setRenderTo(String renderTo) {
        this.renderTo = renderTo;
    }

    public String getHeaderAlign() {
        return headerAlign;
    }

    public void setHeaderAlign(String headerAlign) {
        this.headerAlign = headerAlign;
    }

    public int getHeaderHeight() {
        return headerHeight;
    }

    public void setHeaderHeight(int headerHeight) {
        this.headerHeight = headerHeight;
    }

    public String getDataAlign() {
        return dataAlign;
    }

    public void setDataAlign(String dataAlign) {
        this.dataAlign = dataAlign;
    }

    public int getIndentation() {
        return indentation;
    }

    public void setIndentation(int indentation) {
        this.indentation = indentation;
    }

    public String getFolderOpenIcon() {
        return folderOpenIcon;
    }

    public void setFolderOpenIcon(String folderOpenIcon) {
        this.folderOpenIcon = folderOpenIcon;
    }

    public String getFolderCloseIcon() {
        return folderCloseIcon;
    }

    public void setFolderCloseIcon(String folderCloseIcon) {
        this.folderCloseIcon = folderCloseIcon;
    }

    public String getDefaultLeafIcon() {
        return defaultLeafIcon;
    }

    public void setDefaultLeafIcon(String defaultLeafIcon) {
        this.defaultLeafIcon = defaultLeafIcon;
    }

    public String getHoverRowBackground() {
        return hoverRowBackground;
    }

    public void setHoverRowBackground(String hoverRowBackground) {
        this.hoverRowBackground = hoverRowBackground;
    }

    public String getFolderColumnIndex() {
        return folderColumnIndex;
    }

    public void setFolderColumnIndex(String folderColumnIndex) {
        this.folderColumnIndex = folderColumnIndex;
    }

    public String getItemClick() {
        return itemClick;
    }

    public void setItemClick(String itemClick) {
        this.itemClick = itemClick;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public List<TreeGridColumn> getColumns() {
        return columns;
    }

    public void setColumns(List<TreeGridColumn> columns) {
        this.columns = columns;
    }

    private String renderTo; //div
    private String headerAlign;
    private int headerHeight;
    private String dataAlign;
    private int indentation;
    private String folderOpenIcon;//: "../../images/folderOpen.png",
    private String folderCloseIcon;//: "../../images/folderClose.png",
    private String defaultLeafIcon;//: "../../images/defaultLeaf.gif",
    private String hoverRowBackground;//: "false",
    private String folderColumnIndex;//: "1",
    private String itemClick;//: "itemClickEvent",
    private List<T> data;
    private List<TreeGridColumn> columns;
}
