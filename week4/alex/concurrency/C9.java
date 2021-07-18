package com.alex.concurrency;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

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
