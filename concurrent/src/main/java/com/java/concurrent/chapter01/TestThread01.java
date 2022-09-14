package com.java.concurrent.chapter01;

import lombok.extern.slf4j.Slf4j;

/**
 * @ProjectName keep-learning
 * @Title TestThread01
 * @Description
 * @Author zhouhoulin
 * @Date 2022-09-14
 *
 * 继承方式
 * 1、访问线程名，可以直接使用this.getName()
 */
@Slf4j(topic = "logger.TestThread01")
public class TestThread01 extends Thread{

    @Override
    public void run() {
        log.debug(this.getName() + " running...");
    }

    public static void main(String[] args) {
        TestThread01 thread01 = new TestThread01();
        thread01.start();
        log.debug(Thread.currentThread().getName() + " running...");
    }
}
