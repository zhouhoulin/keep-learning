package com.java.concurrent.chapter03;

public class Initialize {

    private boolean initialized = false;

    public void init() {

        synchronized (this) {
            if (initialized) {
                return;
            }
            doInit();
            initialized = true;
        }
    }

    private void doInit() {

    }
}
