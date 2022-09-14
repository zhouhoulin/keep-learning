package com.java.concurrent.chapter01;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @ProjectName keep-learning
 * @Title TestThread03
 * @Description
 * @Author zhouhoulin
 * @Date 2022-09-14
 *
 * 实现Callable接口的方式
 * 1、解决java单继承的问题
 * 2、多个线程可以共享一个target
 * 3、访问线程名，Thread.currentThread.getName()
 * 4、可以获取线程执行结果的返回值
 */
@Slf4j(topic = "logger.TestThread03")
public class TestThread03 implements Callable<String> {
    @Override
    public String call(){
        log.debug(Thread.currentThread().getName() + " running...");
        return Thread.currentThread().getName() + " 线程返回结果。";
    }

    public static void main(String[] args) {
        FutureTask<String> task = new FutureTask<>(new TestThread03());
        Thread thread = new Thread(task);
        thread.start();
        log.debug(Thread.currentThread().getName() + " running...");
        try {
            String result = task.get();
            log.debug(result);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

    }
}
