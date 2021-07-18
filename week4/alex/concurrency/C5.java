package com.alex.concurrency;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*
 * 1，callable
 * 2，线程池callable
 * 3，join
 * 4，wait notify
 * 5，锁
 * 6，自旋等待 Sleep
 * 7，信号量
 * 8，Countdownlatch
 * 9，Cyclicbarrier
 * 10，CompletableFuture
 * */
//锁（多种）
public class C5 {
	 static Object o1 = new Object();
	 private static  int  result;
	 final static Lock lock = new ReentrantLock();
	 final static Condition lockCondition  = lock.newCondition();
     
	 public static void main(String[] args) {
	        long start=System.currentTimeMillis();

	        Runnable cTask = new Runnable() {
				@Override
				public void run() {
					 lock.lock();
					 result = sum(); 
					 lockCondition.signal();
					 lock.unlock();
				}
	        };
	        
	     
	        Thread t = 	new Thread( cTask);
	        t.start();
	        lock.lock();
	        try {
				lockCondition.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
	        lock.unlock();
	        System.out.println("异步计算结果为："+result);
	        System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");
	        
	    }
	 
	 private static int sum() {
	        return fibo(36);
	    }
	    
	    private static int fibo(int a) {
	        if ( a < 2) 
	            return 1;
	        return fibo(a-1) + fibo(a-2);
	    }
}
