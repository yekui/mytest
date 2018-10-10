package com.common;

public class ThreadUtil {

    public static void join() {
        try {
            Thread.currentThread().join();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
