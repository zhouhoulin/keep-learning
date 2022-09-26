package com.java.concurrent.chapter02.exercise;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Vector;

/**
 * @ProjectName keep-learning
 * @Title ExerciseSell
 * @Description
 * @Author zhouhoulin
 * @Date 2022-09-16
 */
@Slf4j(topic = "logger.ExerciseSell")
public class ExerciseSell {
    public static void main(String[] args) throws InterruptedException {
        //模拟多人买票
        TicketWindow ticketWindow = new TicketWindow(1000);
        List<Thread> threadList = new ArrayList<>();
        List<Integer> amountList = new Vector<>();
        for (int i = 0; i < 100; i++) {
            Thread thread = new Thread(() -> {
                int amount = ticketWindow.sell(random(5));
                amountList.add(amount);
            });
            threadList.add(thread);
            thread.start();
        }

        for (Thread thread : threadList) {
            thread.join();
        }

        log.debug("卖出的票数：{}", amountList.stream().mapToInt(i -> i).sum());
        log.debug("余票：{}", ticketWindow.getCount());
    }

    //Random 线程安全
    static Random random = new Random();
    //随机 1~5
    public static int random(int amount){
        return random.nextInt(amount) + 1;
    }

}
//售票窗口
class TicketWindow{
    private int count;

    public TicketWindow(int count) {
        this.count = count;
    }

    // 获取余票数量
    public int getCount() {
        return count;
    }

    //售票
    public synchronized int sell(int amount){
        if (count >= amount){
            count -= amount;
            return amount;
        }else {
            return 0;
        }

    }
}
