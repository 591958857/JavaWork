package com.alex.concurrency;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*
 * 1��callable
 * 2���̳߳�callable
 * 3��join
 * 4��wait notify
 * 5����
 * 6�������ȴ� Sleep
 * 7���ź���
 * 8��Countdownlatch
 * 9��Cyclicbarrier
 * 10��CompletableFuture
 * */
//�������֣�
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
	        System.out.println("�첽������Ϊ��"+result);
	        System.out.println("ʹ��ʱ�䣺"+ (System.currentTimeMillis()-start) + " ms");
	        
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
