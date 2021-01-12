package com.liuran.autocommit.xmind.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TreeNode {
    private String id;
    private String title;
    private TreeNodeChildren children;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public TreeNodeChildren getChildren() {
        return children;
    }

    public void setChildren(TreeNodeChildren children) {
        this.children = children;
    }
}
