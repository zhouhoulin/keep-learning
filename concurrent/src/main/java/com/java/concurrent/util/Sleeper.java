package com.java.concurrent.util;

import java.util.concurrent.TimeUnit;

/**
 * @ProjectName keep-learning
 * @Title Sleeper
 * @Description
 * @Author zhouhoulin
 * @Date 2022-09-14
 */
public class Sleeper {

    public static void sleep(int i){
        try {
            TimeUnit.SECONDS.sleep(i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void sleep(double i){
        try {
            TimeUnit.MILLISECONDS.sleep((int) (i * 1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
