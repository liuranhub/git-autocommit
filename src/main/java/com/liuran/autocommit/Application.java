package com.liuran.autocommit;

import com.liuran.autocommit.config.Constant;
import com.liuran.autocommit.support.ProjectCollection;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class Application extends SpringBootServletInitializer {
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        System.setProperty("proxyHost", Constant.PROXY_HOST);
        System.setProperty("proxyPort",  Constant.PROXY_PORT);
        System.setProperty("proxyUser", Constant.GIT_USERNAME);
        System.setProperty("proxyPassword", Constant.PROXY_PASSWORD);


        ProjectCollection.init();

        return builder.sources(Application.class);

    }
}
