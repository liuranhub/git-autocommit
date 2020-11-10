package com.liuran.autocommit.config;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.Properties;

public class Constant {
    public static String GIT_LOCALPATH;
    public static String GIT_USERNAME;
    public static String GIT_PASSWORD;
    public static String PROXY_HOST;
    public static String PROXY_PORT;
    public static String PROXY_USERNAME;
    public static String PROXY_PASSWORD;
    static {
        try {
            Properties properties = PropertiesLoaderUtils.loadProperties(
                    new ClassPathResource("application.properties"));
            GIT_LOCALPATH = properties.getProperty("git.localpath");
            GIT_USERNAME = properties.getProperty("git.username");
            GIT_PASSWORD = properties.getProperty("git.password");

            PROXY_HOST = properties.getProperty("proxy.host");
            PROXY_PORT = properties.getProperty("proxy.port");
            PROXY_USERNAME = properties.getProperty("proxy.username");
            PROXY_PASSWORD = properties.getProperty("proxy.password");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
