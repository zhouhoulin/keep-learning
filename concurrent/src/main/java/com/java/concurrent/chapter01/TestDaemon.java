package com.java.concurrent.chapter01;

import lombok.extern.slf4j.Slf4j;

import static com.java.concurrent.util.Sleeper.sleep;

/**
 * @ProjectName keep-learning
 * @Title TestDaemon
 * @Description
 * @Author zhouhoulin
 * @Date 2022-09-14
 */
@Slf4j(topic = "logger.TestDaemon")
public class TestDaemon {

    public static void main(String[] args) {
        log.debug("运行开始...");
        Thread t1 = new Thread(() -> {
            log.debug("运行开始...");
            sleep(2);
            log.debug("运行结束...");
        }, "daemon");
        // 设置线程为守护线程
        // 任何非守护线程还在运行，守护线程将继续运行
        // 不存在非守护线程，程序也就结束退出，守护线程将会被杀死。
        t1.setDaemon(true);
        t1.start();
        sleep(1);
        log.debug("运行结束...");
    }
}
