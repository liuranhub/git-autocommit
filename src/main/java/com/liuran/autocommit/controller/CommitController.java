package com.liuran.autocommit.controller;

import com.liuran.autocommit.support.GitProject;
import com.liuran.autocommit.support.ProjectCollection;
import com.liuran.autocommit.vos.Message;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/autocommit")
public class CommitController {
    @PostMapping(value = "{projectName}")
    public void commit(@PathVariable String projectName, @RequestBody Message message){
        ProjectCollection.get(projectName).push(message);
    }
}
