package com.java.concurrent.test;

import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "logger.Test2")
public class Test2 {
    public static void main(String[] args) {
        Runnable r = () -> {log.debug("running");};

        Thread t = new Thread(r, "t2");

        t.start();
    }
}
