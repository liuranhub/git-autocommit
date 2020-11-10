package com.liuran.autocommit;

import com.liuran.autocommit.config.Constant;
import com.liuran.autocommit.support.AutoCommitCollection;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.StatusCommand;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.lib.Ref;
import org.eclipse.jgit.merge.MergeStrategy;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Date;
import java.util.List;
import java.util.Set;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        System.setProperty("proxyHost", Constant.PROXY_HOST);
        System.setProperty("proxyPort",  Constant.PROXY_PORT);
        System.setProperty("proxyUser", Constant.GIT_USERNAME);
        System.setProperty("proxyPassword", Constant.PROXY_PASSWORD);

        SpringApplication.run(Application.class);

        AutoCommitCollection.init();
    }
}
