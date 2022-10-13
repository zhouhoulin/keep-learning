package com.java.concurrent.test;

import lombok.extern.slf4j.Slf4j;

import static com.java.concurrent.util.Sleeper.sleep;

@Slf4j(topic = "logger.Test32")
public class Test32 {
    // 易变
    static boolean run = true;

    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(()->{
            while(true){
                    if(!run) {
                        break;
                    }
            }
        });
        t.start();

        sleep(1);
            run = false; // 线程t不会如预想的停下来
    }
}
