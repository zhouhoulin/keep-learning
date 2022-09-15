package com.java.concurrent.pattern;

import lombok.extern.slf4j.Slf4j;

/**
 * 两阶段终止模式
 */
@Slf4j(topic = "logger.TestTwoPhaseTermination")
public class TestTwoPhaseTermination {
    public static void main(String[] args) throws InterruptedException {
        TPTInterrupt t = new TPTInterrupt();
        t.start();

        Thread.sleep(3500);
        log.debug("stop");
        t.stop();

//        TPTVolatile t = new TPTVolatile();
//        t.start();
//
//        Thread.sleep(3500);
//        log.debug("stop");
//        t.stop();
    }
}

@Slf4j(topic = "logger.TPTInterrupt")
class TPTInterrupt {
    private Thread thread;

    public void start(){
        thread = new Thread(() -> {
            while (true){
                Thread currentThread = Thread.currentThread();
                if (currentThread.isInterrupted()){
                    log.debug("料理后事");
                    break;
                }
                try {
                    Thread.sleep(1000);
                    log.debug("将结果保存");
                } catch (InterruptedException e) {
                    currentThread.interrupt();
                }
            }

        }, "监控线程");
        thread.start();

    }

    public void stop() {
        thread.interrupt();
    }
}

@Slf4j(topic = "logger.TPTVolatile")
class TPTVolatile {
    private Thread thread;
    private volatile boolean stop = false;

    public void start(){
        thread = new Thread(() -> {
            while(true) {
                Thread current = Thread.currentThread();
                if(stop) {
                    log.debug("料理后事");
                    break;
                }
                try {
                    Thread.sleep(1000);
                    log.debug("将结果保存");
                } catch (InterruptedException e) {
                }
            }
        },"监控线程");
        thread.start();
    }

    public void stop() {
        stop = true;
        thread.interrupt();
    }
}


