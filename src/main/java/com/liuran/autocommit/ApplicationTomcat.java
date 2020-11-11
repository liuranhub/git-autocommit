package com.liuran.autocommit;

import com.liuran.autocommit.config.Constant;
import com.liuran.autocommit.support.ProjectCollection;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class ApplicationTomcat extends SpringBootServletInitializer {
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        Constant.PROXY.init();
        ProjectCollection.init();
        return builder.sources(ApplicationTomcat.class);
    }
}
