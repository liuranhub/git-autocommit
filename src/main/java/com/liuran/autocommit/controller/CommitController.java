package com.liuran.autocommit.controller;

import com.liuran.autocommit.support.GitProject;
import com.liuran.autocommit.support.ProjectCollection;
import com.liuran.autocommit.vos.Message;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/autocommit")
public class CommitController {
    @PostMapping()
    public void commit(@RequestBody Message message){
        ProjectCollection.get().push(message);
    }

    @GetMapping("status")
    public void status(){
        ((GitProject)ProjectCollection.get()).status();
    }
}
