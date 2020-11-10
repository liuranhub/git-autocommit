package com.liuran.autocommit.support;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.merge.MergeStrategy;
import org.eclipse.jgit.transport.CredentialsProvider;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;

import java.io.File;
import java.io.IOException;
import java.util.Date;

public class AutoCommitGitProject implements AutoCommit {
    private Git project;
    private final CredentialsProvider credentialsProvider;
    // 0:空闲，1：提交中
    private int status;

    public AutoCommitGitProject(String localPath, String userName, String password) {
        credentialsProvider = new UsernamePasswordCredentialsProvider(userName, password);
        try {
            project = Git.open(new File(localPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void autoPush() {
        add();
        commit();
        pull();
        push();
    }

    private void push(){
        try {
            project.push().setCredentialsProvider(credentialsProvider).call();
        } catch (GitAPIException e) {
            e.printStackTrace();
        }
    }

    private void pull(){
        try {
            project.pull().setStrategy(MergeStrategy.OURS).call();
        } catch (GitAPIException e) {
            e.printStackTrace();
        }
    }

    private void commit() {
        try {
            project.commit().setMessage(new Date().toString()).call();
        } catch (GitAPIException e) {
            e.printStackTrace();
        }
    }

    private void add() {
        try {
            project.add().addFilepattern(".").call();
        } catch (GitAPIException e) {
            e.printStackTrace();
        }
    }
}
