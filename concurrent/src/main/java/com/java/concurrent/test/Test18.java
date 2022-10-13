package com.java.concurrent.test;

import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "logger.Test18")
public class Test18 {
    static final Object lock = new Object();
    public static void main(String[] args) {

        synchronized (lock) {
            try {
                lock.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
