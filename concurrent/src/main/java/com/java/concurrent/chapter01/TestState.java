package com.java.concurrent.chapter01;

import lombok.extern.slf4j.Slf4j;

/**
 * @ProjectName keep-learning
 * @Title TestState
 * @Description
 * @Author zhouhoulin
 * @Date 2022-09-14
 */
@Slf4j(topic = "logger.TestState")
public class TestState {

    public static void main(String[] args) {
        Thread t1 = new Thread("t1") {
            @Override
            public void run() {
                log.debug("NEW...");
            }
        };

        Thread t2 = new Thread("t2") {
            @Override
            public void run() {
                while (true){
                }
            }
        };
        t2.start();

        Thread t3 = new Thread("t3") {
            @Override
            public void run() {
                log.debug("running...");
            }
        };
        t3.start();

        Thread t4 = new Thread("t4") {
            @Override
            public void run() {
                synchronized (TestState.class){
                    try {
                        Thread.sleep(1000000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        t4.start();

        Thread t5 = new Thread("t5") {
            @Override
            public void run() {
                try {
                    t2.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        t5.start();

        Thread t6 = new Thread("t6") {
            @Override
            public void run() {
                synchronized (TestState.class){
                    try {
                        Thread.sleep(1000000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        t6.start();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        log.debug("t1.state is {}", t1.getState());
        log.debug("t2.state is {}", t2.getState());
        log.debug("t3.state is {}", t3.getState());
        log.debug("t4.state is {}", t4.getState());
        log.debug("t5.state is {}", t5.getState());
        log.debug("t6.state is {}", t6.getState());
    }
}
