package com.id.test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Exchanger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class DateUtil {

    private DateUtil(){}
    
    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    
    private static ReentrantLock lock = new ReentrantLock();

//    public static void main(String[] args) throws InterruptedException {
//        ExecutorService executorService = Executors.newFixedThreadPool(500);
//        for (int i = 0; i < 500; i++) {
//            executorService.execute(new Runnable() {
//                @Override
//                public void run() {
//                    for (int i = 0; i < 1000000; i++) {
//                        try {
//                            DATE_FORMAT.parse("2014-01-01 00:00:00");
//                        } catch (ParseException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }
//            });
//        }
//        Thread.sleep(3000000);
//    }
    
    public static void main(String[] args) throws InterruptedException {
    	final Condition condition1 = lock.newCondition();
        final Condition condition2 = lock.newCondition();
        Thread thread1 = new Thread(new Runnable() {
            public void run() {
                lock.lock();
                try {
                    System.out.println("等待condition1被通知！");
                    condition1.await();
                    System.out.println("condition1已被通知，马上开始通知condition2！");
                    condition2.signal();
                    System.out.println("通知condition2完毕！");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } finally {
                    lock.unlock();
                }
            }
        });
        Thread thread2 = new Thread(new Runnable() {
            public void run() {
                lock.lock();
                try {
                    System.out.println("马上开始通知condition1！");
                    condition1.signal();
                    System.out.println("通知condition1完毕，等待condition2被通知！");
                    condition2.await();
                    System.out.println("condition2已被通知！");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } finally {
                    lock.unlock();
                }
            }
        });
        thread1.start();
        Thread.sleep(1000);
        thread2.start();
        
        final CountDownLatch countDownLatch = new CountDownLatch(10);
        for (int i = 0; i < 10; i++) {
            final int number = i + 1;
            Runnable runnable = new Runnable() {
                public void run() {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {}
                    System.out.println("执行任务[" + number + "]");
                    countDownLatch.countDown();
                    System.out.println("完成任务[" + number + "]");
                }
            };
            Thread thread = new Thread(runnable);
            thread.start();
        }
        System.out.println("主线程开始等待...");
        countDownLatch.await();
        System.out.println("主线程执行完毕...");
        
        
        final CyclicBarrier cyclicBarrier = new CyclicBarrier(10);
        for (int i = 0; i < 10; i++) {
            final int number = i + 1;
            Runnable runnable = new Runnable() {
                public void run() {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {}
                    System.out.println("等待执行任务[" + number + "]");
                    try {
                        cyclicBarrier.await();
                    } catch (InterruptedException e) {
                    } catch (BrokenBarrierException e) {
                    }
                    System.out.println("开始执行任务[" + number + "]");
                }
            };
            Thread thread = new Thread(runnable);
            thread.start();
        }
        
        
        final Exchanger<String> exchanger = new Exchanger<String>();
        Thread thread3 = new Thread(new Runnable() {
            public void run() {
                try {
                    System.out.println("线程1等待接受");
                    String content = exchanger.exchange("thread3");
                    System.out.println("线程1收到的为：" + content);
                } catch (InterruptedException e) {}
            }
        });
        Thread thread4 = new Thread(new Runnable() {
            public void run() {
                try {
                    System.out.println("线程2等待接受并沉睡3秒");
                    Thread.sleep(3000);
                    String content = exchanger.exchange("thread4");
                    System.out.println("线程2收到的为：" + content);
                } catch (InterruptedException e) {}
            }
        });
        thread3.start();
        thread4.start();
    }
    
}