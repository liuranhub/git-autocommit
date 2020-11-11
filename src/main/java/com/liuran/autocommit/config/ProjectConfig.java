package com.liuran.autocommit.config;

public class ProjectConfig {
    private String name;
    private String path;
    private String gitUsername;
    private String gitPassword;

    public void setName(String name) {
        this.name = name;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getGitUsername() {
        return gitUsername;
    }

    public void setGitUsername(String gitUsername) {
        this.gitUsername = gitUsername;
    }

    public String getGitPassword() {
        return gitPassword;
    }

    public void setGitPassword(String gitPassword) {
        this.gitPassword = gitPassword;
    }

    public String getName() {
        return name;
    }

    public String getPath() {
        return path;
    }
}
