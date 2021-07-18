package com.alex.concurrency;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

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
//CompletableFuture
public class C11 {
	 public static void main(String[] args) {
	        long start=System.currentTimeMillis();

        	CompletableFuture<Integer> cf = CompletableFuture.supplyAsync(()->{
        		return sum();
        	});
        	
          	CompletableFuture<Void> cf1 = cf.thenAccept( (result)->{
        		System.out.println("异步计算结果为："+result);
    	        System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");
        	});
		        	
          	try {
				cf1.get();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
          	
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
