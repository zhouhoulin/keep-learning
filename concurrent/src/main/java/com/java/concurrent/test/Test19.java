package com.java.concurrent.test;

import com.java.concurrent.util.Sleeper;
import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "logger.Test19")
public class Test19 {

    static final Object lock = new Object();
    public static void main(String[] args) {
        new Thread(() -> {
            synchronized (lock) {
                log.debug("获得锁");
                try {
//                    Thread.sleep(20000);
                    lock.wait(20000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "t1").start();

        Sleeper.sleep(1);
        synchronized (lock) {
            log.debug("获得锁");
        }
    }
}
