package com.javedrpi.webfluxdemo.service;

/**
 * @author Javed Ameen Shaikh
 * @website https://www.javedrpi.com
 */

public class SleepUtil {
    public static void sleepSeconds(int seconds){
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
