package com.java.concurrent.pattern;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.List;

import static com.java.concurrent.pattern.Downloader.download;

/**
 * @ProjectName keep-learning
 * @Title TestGuardedObject
 * @Description
 * @Author zhouhoulin
 * @Date 2022-09-15
 */
@Slf4j(topic = "logger.TestGuardedObject")
public class TestGuardedObject {
    public static void main(String[] args) {
        GuardedObject guardedObject = new GuardedObject();
        new Thread(() -> {
            List<String> response = null;
            try {
                response = download();
            } catch (IOException e) {
                e.printStackTrace();
            }
            log.debug("download complete...");
            guardedObject.complete(response);
        }).start();

        log.debug("waiting...");
        Object response = guardedObject.get();
        log.debug("get response: [{}] lines", ((List<String>) response).size());

    }

}

class GuardedObject{
    private Object response;
    private final Object lock = new Object();

    public Object get(){
        synchronized (lock){
            // 条件不满足则等待
            if (response == null){
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return response;
        }
    }

    public void complete(Object response){
        synchronized (lock){
            // 条件满足，通知等待线程
            this.response = response;
            lock.notifyAll();
        }
    }
}
