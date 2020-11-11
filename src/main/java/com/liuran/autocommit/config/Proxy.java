package com.liuran.autocommit.config;

public class Proxy {
    private String host;
    private String port;
    private String username;
    private String password;

    public Proxy(String host, String port, String username, String password) {
        this.host = host;
        this.port = port;
        this.username = username;
        this.password = password;
    }

    public void init(){
        System.setProperty("proxyHost", host);
        System.setProperty("proxyPort",  port);
        System.setProperty("proxyUser", username);
        System.setProperty("proxyPassword", password);
    }
}
