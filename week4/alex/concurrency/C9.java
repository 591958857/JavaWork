package com.alex.concurrency;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

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
//Cyclicbarrier
public class C9 {
	 static Object o1 = new Object();
	 private static  int  result = 0;
	 private static CyclicBarrier barrier = new CyclicBarrier(2);
     
	 public static void main(String[] args) {
	        long start=System.currentTimeMillis();

	        Runnable cTask = new Runnable() {
				@Override
				public void run() {
						  result = sum(); 
						  try {
							  barrier.await();
						} catch (InterruptedException e) {
							e.printStackTrace();
						} catch (BrokenBarrierException e) {
							e.printStackTrace();
						}
				}
	        };
	        
        	Thread t = 	new Thread( cTask);
        	t.start();
        	
        	try {
				barrier.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (BrokenBarrierException e) {
				e.printStackTrace();
			}
         
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
