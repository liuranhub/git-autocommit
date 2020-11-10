package com.liuran.autocommit.support;

import com.liuran.autocommit.vos.Message;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.*;

@Component
public class AutoPushTimer {
    private static Timer timer = new Timer();

    private static List<Integer> PUSH_TIME = new ArrayList<>();

    @PostConstruct
    public void init(){
        PUSH_TIME.add(18);
        PUSH_TIME.add(23);

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Calendar calendar = Calendar.getInstance();
                int hour = calendar.get(Calendar.HOUR_OF_DAY);
                if (PUSH_TIME.contains(hour)){
                    Message msg = new Message();
                    msg.setMessage("auto commit time:" + calendar.getTime().toString());
                    try {
                        ProjectCollection.get().push(msg);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }, 10 * 1000, 10 * 60 * 1000);
    }
}
