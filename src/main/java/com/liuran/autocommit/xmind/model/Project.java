package com.liuran.autocommit.xmind.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Project {
    private String id;
    private String title;
    private TreeNode rootTopic;
    private List<Relation> relationships;

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

    public TreeNode getRootTopic() {
        return rootTopic;
    }

    public void setRootTopic(TreeNode rootTopic) {
        this.rootTopic = rootTopic;
    }

    public List<Relation> getRelationships() {
        return relationships;
    }

    public void setRelationships(List<Relation> relationships) {
        this.relationships = relationships;
    }
}
