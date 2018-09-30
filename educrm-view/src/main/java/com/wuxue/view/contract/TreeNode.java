package com.wuxue.view.contract;

import java.util.List;

/**
 * Created by Jamie on 2018/1/25.
 */
public class TreeNode {
    private String id;
    private String pId;
    private String name;
    private boolean open;
    private boolean checked;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getpId() {
        return pId;
    }

    public void setpId(String pid) {
        this.pId = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public List<TreeNode> getChildren() {
        return children;
    }

    public void setChildren(List<TreeNode> children) {
        this.children = children;
    }

    private List<TreeNode> children;
}
