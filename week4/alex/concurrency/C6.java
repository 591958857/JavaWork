package com.alex.concurrency;

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
//自旋等待
public class C6 {
	 static Object o1 = new Object();
	 private static  int  result = 0;
	 
     
	 public static void main(String[] args) {
	        long start=System.currentTimeMillis();

	        Runnable cTask = new Runnable() {
				@Override
				public void run() {
					 result = sum(); 
				}
	        };
	        
	     
	        Thread t = 	new Thread( cTask);
	        t.start();
	        
	        while(true) {
	        	if(result != 0) {
	        		break;
	        	}
	        	try {
					Thread.sleep( (long) 0.01);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
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
