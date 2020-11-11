package com.liuran.autocommit.config;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Constant {
    public static List<ProjectConfig> DEFAULT_PROJECT_LIST;
    public static ProxyConfig PROXY;

    static {
        try {
            Properties properties = PropertiesLoaderUtils.loadProperties(
                    new ClassPathResource("application.properties"));
            String username = properties.getProperty("git.username");
            String password = properties.getProperty("git.password");

            String projectPath = properties.getProperty("git.project");
            DEFAULT_PROJECT_LIST = toProject(projectPath, username, password);

            PROXY = new ProxyConfig();
            PROXY.setHost(properties.getProperty("proxy.host"));
            PROXY.setPort(properties.getProperty("proxy.port"));
            PROXY.setUsername(properties.getProperty("proxy.username"));
            PROXY.setPassword(properties.getProperty("proxy.password"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static List<ProjectConfig> toProject(String projectPath, String username, String password){
        List<ProjectConfig> result = new ArrayList<>();
        for (String path : projectPath.split(",")){
            String name = path.substring(path.lastIndexOf("\\") + 1);
            ProjectConfig p = new ProjectConfig();
            p.setName(name);
            p.setPath(path);

            p.setGitUsername(username);
            p.setGitPassword(password);

            result.add(p);
        }
        return result;
    }
}
