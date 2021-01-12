package com.liuran.autocommit.xmind.vos;

import java.util.*;

public class ProjectVo {

    private String category;
    private List<TreeNodeVo> nodes = new LinkedList<>();
    private List<RelationVo> relations = new LinkedList<>();
    private Set<String> exitRelations = new HashSet<>();

    private Map<String, TreeNodeVo> nodeIndex = new HashMap<>();
    private Map<String, TreeNodeVo> mergeIndex = new HashMap<>();

    public void setCategory(String category) {
        this.category = category;
    }

    public void putAllMergeIndex(Map<String, TreeNodeVo> mergeIndex) {
        this.mergeIndex.putAll(mergeIndex);
    }

    public void putAllNodeIndex(Map<String, TreeNodeVo> nodeIndex) {
        this.nodeIndex.putAll(nodeIndex);
    }

    public String getCategory() {
        return category;
    }

    public Map<String, TreeNodeVo> getMergeIndex() {
        return mergeIndex;
    }

    public Map<String, TreeNodeVo> getNodeIndex() {
        return nodeIndex;
    }

    public List<TreeNodeVo> getNodes() {
        return nodes;
    }

    public void setNodes(List<TreeNodeVo> nodes) {
        this.nodes = nodes;
    }

    public List<RelationVo> getRelations() {
        return relations;
    }

    public void setRelations(List<RelationVo> relations) {
        this.relations = relations;
    }
}
