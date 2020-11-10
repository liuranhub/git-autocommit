package com.liuran.autocommit.support;

import com.liuran.autocommit.config.Constant;

public class ProjectCollection {

    private static Push project;

    public static Push get(){
        return project;
    }

    public static void init(){
        project = new GitProject(Constant.GIT_LOCALPATH, Constant.GIT_USERNAME, Constant.GIT_PASSWORD);
    }
}
