package com.liuran.autocommit.xmind.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TreeNodeChildren {
    private List<TreeNode> attached;
    private List<TreeNode> detached;

    public List<TreeNode> getAttached() {
        return attached;
    }

    public void setAttached(List<TreeNode> attached) {
        this.attached = attached;
    }

    public List<TreeNode> getDetached() {
        return detached;
    }

    public void setDetached(List<TreeNode> detached) {
        this.detached = detached;
    }
}
