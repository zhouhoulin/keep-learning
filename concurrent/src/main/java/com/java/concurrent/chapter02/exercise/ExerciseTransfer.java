package com.java.concurrent.chapter02.exercise;

import lombok.extern.slf4j.Slf4j;

import java.util.Random;

/**
 * @ProjectName keep-learning
 * @Title ExerciseTransfer
 * @Description
 * @Author zhouhoulin
 * @Date 2022-09-16
 */
@Slf4j(topic = "logger.ExerciseTransfer")
public class ExerciseTransfer {

    public static void main(String[] args) throws InterruptedException {
        Account a = new Account(1000);
        Account b = new Account(1000);

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                a.transfer(b, randomAmount());
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                b.transfer(a, randomAmount());
            }
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        log.debug("a账户余额：{}，b账户余额：{}", a.getMoney(), b.getMoney());
        log.debug("账户总余额：{}", (a.getMoney() + b.getMoney()));

    }

    static Random random = new Random();

    public static int randomAmount() {
        return random.nextInt(100) + 1;
    }

}

class Account{
    private int money;

    public Account(int money) {
        this.money = money;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    // 转账
    public void transfer(Account target, int money){
        synchronized (Account.class){
            if (this.getMoney() >= money){
                this.setMoney(this.getMoney() - money);
                target.setMoney(target.getMoney() + money);
            }
        }
    }
}
