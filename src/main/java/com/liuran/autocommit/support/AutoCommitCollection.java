package com.liuran.autocommit.support;

import com.liuran.autocommit.config.Constant;

public class AutoCommitCollection {

    private static AutoCommit project;

    public static AutoCommit get(){
        return project;
    }

    public static void init(){
        project = new AutoCommitGitProject(Constant.GIT_LOCALPATH, Constant.GIT_USERNAME, Constant.GIT_PASSWORD);
    }
}
