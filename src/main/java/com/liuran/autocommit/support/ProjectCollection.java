package com.liuran.autocommit.support;

import com.liuran.autocommit.config.Constant;
import com.liuran.autocommit.config.ProjectConfig;

import java.util.HashMap;
import java.util.Map;

public class ProjectCollection {

    private static Map<String,Project> projectMap = new HashMap<>();

    public static Project get(String name){
        return projectMap.get(name);
    }

    public static Map<String,Project> getAll(){
        return projectMap;
    }

    public static void init(){
        for (ProjectConfig config : Constant.DEFAULT_PROJECT_LIST) {
            Project project = new GitProject(config.getPath(), config.getGitUsername(), config.getGitPassword());
            projectMap.put(config.getName(), project);
        }
    }
}
