package com.java.concurrent.chapter01;

import java.util.HashMap;
import java.util.Map;

public class TestCpu {
    public static void main(String[] args) {
        new Thread(() -> {
            while (true) {
                /*try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }*/
            }
        }).start();
    }
}
