package com.java.concurrent.chapter02;

import lombok.extern.slf4j.Slf4j;

/**
 * @ProjectName keep-learning
 * @Title TestCounterOop
 * @Description
 * @Author zhouhoulin
 * @Date 2022-09-16
 */
@Slf4j(topic = "logger.TestCounterOop")
public class TestCounterOop {
    public static void main(String[] args) throws InterruptedException {
        Room room = new Room();
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 5000; i++) {
                room.increment();
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 5000; i++) {
                room.decrement();
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();
        log.debug("counter: {}", room.get());
    }
}

class Room {
    private static int counter = 0;

    public void increment(){
        synchronized (this){
            counter ++;
        }
    }

    public void decrement(){
        synchronized (this){
            counter--;
        }
    }

    public int get(){
        synchronized (this){
            return counter;
        }
    }
}
