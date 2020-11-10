package com.liuran.autocommit.controller;

import com.liuran.autocommit.support.AutoCommitCollection;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/autocommit")
public class CommitController {
    @PostMapping()
    public void commit(){
        AutoCommitCollection.get().autoPush();
    }
}
