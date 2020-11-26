package com.liuran.autocommit.support;

import com.liuran.autocommit.vos.Message;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.*;

@Component
public class AutoPushTimer {
    private static Timer timer = new Timer();

    private static List<Integer> PUSH_TIME = new ArrayList<>();

    private static Long DELAY_TIME ;

    @PostConstruct
    public void init(){
        PUSH_TIME.add(18);
        PUSH_TIME.add(22);

        DELAY_TIME = 0L;

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Calendar calendar = Calendar.getInstance();
                int hour = calendar.get(Calendar.HOUR_OF_DAY);
                if (PUSH_TIME.contains(hour)){
                    randomDelayTime();
                    Message msg = new Message();
                    msg.setMessage("commit time:" + calendar.getTime().toString());
                    try {
                        for (Project project : ProjectCollection.getAll().values()) {
                            project.push(msg);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }


            }
        }, 10 * 1000, 10 * 60 * 1000);
    }

    private static void randomDelayTime(){
        try {
            Thread.sleep(new Random().nextInt(30 * 60 * 1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
