/**
 * <br>项目名: csvo
 * <br>文件名: TestSingleton.java
 * <br>Copyright 2016 北京壹平台科技有限公司
 */
package com.id.tools;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/** 
 * <br>类 名: TestSingleton 
 * <br>描 述: 描述类完成的主要功能 
 * <br>作 者: yufenghe 
 * <br>创 建： 2016年4月27日 
 * <br>版 本：v1.0.0 
 * <br>
 * <br>历 史: (版本) 作者 时间 注释
 */
public class TestSingleton {
	public static void main(String[] args) throws InterruptedException {
        
        final Set<String> set = Collections.synchronizedSet(new HashSet<String>());
        final CountDownLatch cdl = new CountDownLatch(1000);
        final CyclicBarrier barrier = new CyclicBarrier(1000);
        ExecutorService executor = Executors.newCachedThreadPool();
        for (int i = 0; i < 1000; i++) {
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                    	barrier.await();
                        Singleton singleton = Singleton.getInstance();
                        set.add(singleton.toString());
                        cdl.countDown();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
         
         
//        Thread.sleep(1000);
        cdl.await();
//        Thread.sleep(1000);
         
        System.out.println("一共有" + set.size() + "个实例");
        for (String str : set) {
            System.out.println(str);
        }
         
        executor.shutdown();
         
    }
 
}
