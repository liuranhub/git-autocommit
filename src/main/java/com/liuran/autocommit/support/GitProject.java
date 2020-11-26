package com.liuran.autocommit.support;

import com.liuran.autocommit.vos.Message;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.RmCommand;
import org.eclipse.jgit.api.Status;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.merge.MergeStrategy;
import org.eclipse.jgit.transport.CredentialsProvider;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.Set;

public class GitProject implements Project {

    private static final Logger LOG = LoggerFactory.getLogger(GitProject.class);

    private Git git;
    private final CredentialsProvider credentialsProvider;

    public GitProject(String localPath, String userName, String password) {
        credentialsProvider = new UsernamePasswordCredentialsProvider(userName, password);
        try {
            git = Git.open(new File(localPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public synchronized void push(Message message){
        long startTime = System.currentTimeMillis();

        add();
        boolean needPush;
        if (needPush = needCommit()) {
            commitLocal(message.getMessage());
        }
        pullFromOrigin();
        // TODO merge后需要重新提交
        add();
        if (needCommit()) {
            commitLocal("pull and merge , time : " + Calendar.getInstance().getTime());
        }

        if (needPush) {
            pushToOrigin();
        }
        LOG.info("total time : " + (System.currentTimeMillis() - startTime));
    }

    public synchronized void pull(Message message){

    }

    private boolean needCommit(){
        try {
            Status status = git.status().call();
            if (status.hasUncommittedChanges()){
                return true;
            }
        } catch (GitAPIException e) {
            e.printStackTrace();
        }
        return false;
    }

    private void pushToOrigin(){
        try {
            git.push().setCredentialsProvider(credentialsProvider).call();
            LOG.info("push");
        } catch (GitAPIException e) {
            e.printStackTrace();
        }
    }

    private void pullFromOrigin(){
        try {
            git.pull().call();
            LOG.info("pull");
        } catch (GitAPIException e) {
            e.printStackTrace();
        }
    }

    private void commitLocal(String message) {
        try {
            git.commit().setMessage(message).call();
            LOG.info("commit");
        } catch (GitAPIException e) {
            e.printStackTrace();
        }
    }

    private void add() {
        try {
            git.add().addFilepattern(".").call();
            LOG.info("add");
        } catch (GitAPIException e) {
            e.printStackTrace();
        }


        RmCommand rm = git.rm();
        Set<String> missing = null;
        try {
            // 处理手动删除的文件
            missing = git.status().call().getMissing();
            for(String m : missing){
                //setCached(false) 否则一次无法删除多个文件
                rm.addFilepattern(m).setCached(false).call();
            }
        } catch (GitAPIException e) {
            e.printStackTrace();
        }
    }
}
