package com.hugo.servlet;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Create by Hugo Liao
 * Email: i@1800g.net
 * 2018/4/1 2:35
 */

public class LongRunningProcess {

    public void run(){
        try {
            int mills = ThreadLocalRandom.current().nextInt(5000);
            String currentThread = Thread.currentThread().getName();
            System.out.println(currentThread+" sleep of "+mills+" milliseconds.");
            Thread.sleep(mills);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
