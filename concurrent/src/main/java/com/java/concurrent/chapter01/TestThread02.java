package com.java.concurrent.chapter01;

import lombok.extern.slf4j.Slf4j;

/**
 * @ProjectName keep-learning
 * @Title TestThread02
 * @Description
 * @Author zhouhoulin
 * @Date 2022-09-14
 *
 * 实现接口的方式
 * 1、解决java单继承的问题
 * 2、多个线程可以共享一个target
 * 3、访问线程名，Thread.currentThread.getName()
 */
@Slf4j(topic = "logger.TestThread02")
public class TestThread02 implements Runnable{

    private int target = 5;

    @Override
    public synchronized void run() {
        log.debug(Thread.currentThread().getName() + " running...");
        while (target > 0){
            log.debug("target:" + target);
            target--;
        }
    }

    public static void main(String[] args) {
        TestThread02 testThread01 = new TestThread02();
        TestThread02 testThread02 = new TestThread02();
        TestThread02 testThread03 = new TestThread02();
        // 测试共享target
        // Thread-0
        Thread thread01 = new Thread(testThread01);
        // Thread-1
        Thread thread02 = new Thread(testThread01);
        // Thread-2
        Thread thread03 = new Thread(testThread01);
        // 打印结果，其他两个线程没有进入循环，说明知道第一个线程把target已经达到0
        thread01.start();
        thread02.start();
        thread03.start();
        log.debug(Thread.currentThread().getName() + " running...");
    }
}
