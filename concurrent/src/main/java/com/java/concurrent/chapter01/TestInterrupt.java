package com.java.concurrent.chapter01;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.LockSupport;

import static com.java.concurrent.util.Sleeper.sleep;

@Slf4j(topic = "logger.TestInterrupt")
public class TestInterrupt {
    public static void main(String[] args) throws InterruptedException {
        test4();
    }
    private static void test4() {
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                log.debug("park...");
                LockSupport.park();
//                log.debug("打断状态：{}", Thread.currentThread().isInterrupted());
                log.debug("打断状态：{}", Thread.interrupted());
            }
        });
        t1.start();


        sleep(1);
        t1.interrupt();
    }
    private static void test3() {
        Thread t1 = new Thread(() -> {
            log.debug("park...");
            LockSupport.park();
            log.debug("unpark...");
            log.debug("打断状态：{}", Thread.currentThread().isInterrupted());
        }, "t1");
        t1.start();


        sleep(0.5);
        t1.interrupt();
    }
    private static void test2() throws InterruptedException {
        Thread t2 = new Thread(()->{
            while(true) {
                Thread current = Thread.currentThread();
                boolean interrupted = current.isInterrupted();
                if(interrupted) {
                    log.debug(" 打断状态: {}", interrupted);
                    break;
                }
            }
        }, "t2");
        t2.start();

        sleep(0.5);
        t2.interrupt();
    }
    private static void test1() throws InterruptedException {
        Thread t1 = new Thread(()->{
            sleep(1);
        }, "t1");
        t1.start();

        sleep(0.5);
        t1.interrupt();
        log.debug(" 打断状态: {}", t1.isInterrupted());
    }
}
