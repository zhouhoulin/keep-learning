package com.java.concurrent.chapter02;

import lombok.extern.slf4j.Slf4j;

/**
 * @ProjectName keep-learning
 * @Title TestCounterSync
 * @Description
 * @Author zhouhoulin
 * @Date 2022-09-16
 */
@Slf4j(topic = "logger.TestCounterSync")
public class TestCounterSync {

    static int counter = 0;
    static final Object room = new Object();

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            synchronized (room){
                for (int i = 0; i < 5000; i++) {
                    counter++;
                }
            }
        });

        Thread t2 = new Thread(() -> {
            synchronized (room){
                for (int i = 0; i < 5000; i++) {
                    counter--;
                }
            }
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();
        log.debug("counter = {}", counter);
    }

}
