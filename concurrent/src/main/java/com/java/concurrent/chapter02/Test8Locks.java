package com.java.concurrent.chapter02;

import lombok.extern.slf4j.Slf4j;

import static com.java.concurrent.util.Sleeper.sleep;

/**
 * 线程8锁
 */
@Slf4j(topic = "logger.Test8Locks")
public class Test8Locks {
    public static void main(String[] args) {
        Number n1 = new Number();
        Number n2 = new Number();
        new Thread(() -> {
            log.debug("begin");
            n1.a();
        }).start();
        new Thread(() -> {
            log.debug("begin");
            n2.b();
        }).start();
    }
}

@Slf4j(topic = "logger.Number")
class Number{
    public synchronized void a() {
        sleep(1);
        log.debug("1");
    }
    public synchronized void b() {
        log.debug("2");
    }
}
