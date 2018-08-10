/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hongdian.plat.term.thread;

import com.hongdian.plat.term.thread.entity.ShareCore;
import com.hongdian.plat.term.thread.entity.SharedObj;

/**
 *
 * @author win 10
 */
public class ThreadLocalTest {

    public static SharedObj obj = new SharedObj();

    public static void main(String[] args) {

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                ShareCore sc = new ShareCore("abc");
                obj.set(sc);
                ShareCore core = obj.get();
                System.out.println(Thread.currentThread().getName() + " : " + core.getAaa());
            }
        });
        thread1.setName("线程1");
        thread1.start();

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                ShareCore sc1 = new ShareCore("def");
                obj.set(sc1);
                ShareCore core1 = obj.get();
                System.out.println(Thread.currentThread().getName() + " : " + core1.getAaa());
            }
        });
        thread1.setName("线程2");
        thread2.start();
        
        ShareCore core2 = obj.get();
        System.out.println(Thread.currentThread().getName() + " : " + core2.getAaa());
        
    }

}
