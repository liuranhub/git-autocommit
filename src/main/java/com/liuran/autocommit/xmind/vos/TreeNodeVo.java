package com.liuran.autocommit.xmind.vos;

import com.liuran.autocommit.xmind.ChineseToFirstCharUtils;

import java.util.ArrayList;
import java.util.List;

public class TreeNodeVo {
    private String id;
    private String name;
    private String mergeUniqueId;
    private String category;
    private TreeNodeVo parent;
    private List<TreeNodeVo> nodes = new ArrayList<>();

    public TreeNodeVo(TreeNodeVo parent, String id, String name, String category){
        this.id = id;
        this.parent = parent;
        this.name = name;
        this.category = category;
        this.mergeUniqueId =  ChineseToFirstCharUtils.getFirstSpell(name).toUpperCase();
//        this.mergeUniqueId =  name;
        if (parent != null) {
            this.mergeUniqueId = parent.getMergeUniqueId() + "_" + mergeUniqueId;
        }
    }

    public String getMergeUniqueId() {
        return mergeUniqueId;
    }

    public void addChild(TreeNodeVo node){
        nodes.add(node);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getParent() {
        if (parent != null){
            return parent.getMergeUniqueId();
        }
        return null;
    }

    public void setParent(TreeNodeVo parent) {
        this.parent = parent;
    }

    public List<TreeNodeVo> getNodes() {
        return nodes;
    }

    public void setNodes(List<TreeNodeVo> nodes) {
        this.nodes = nodes;
    }
}
