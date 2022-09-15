package com.java.concurrent.chapter01;

import com.java.concurrent.Constants;
import com.java.concurrent.util.FileReader;
import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "logger.TestStart")
public class TestStart {
    public static void main(String[] args) {
        Thread t1 = new Thread("t1") {
            @Override
            public void run() {
                log.debug(Thread.currentThread().getName());
                FileReader.read(Constants.MP4_FULL_PATH);
            }
        };

        t1.start();
        log.debug("do other things ...");
    }
}
