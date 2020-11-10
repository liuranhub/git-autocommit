package com.liuran.autocommit;

import com.liuran.autocommit.config.Constant;
import com.liuran.autocommit.support.ProjectCollection;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        System.setProperty("proxyHost", Constant.PROXY_HOST);
        System.setProperty("proxyPort",  Constant.PROXY_PORT);
        System.setProperty("proxyUser", Constant.GIT_USERNAME);
        System.setProperty("proxyPassword", Constant.PROXY_PASSWORD);

        SpringApplication.run(Application.class);

        ProjectCollection.init();
    }
}
