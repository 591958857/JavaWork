package com.alex.concurrency;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

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
//CompletableFuture
public class C11 {
	 public static void main(String[] args) {
	        long start=System.currentTimeMillis();

        	CompletableFuture<Integer> cf = CompletableFuture.supplyAsync(()->{
        		return sum();
        	});
        	
          	CompletableFuture<Void> cf1 = cf.thenAccept( (result)->{
        		System.out.println("�첽������Ϊ��"+result);
    	        System.out.println("ʹ��ʱ�䣺"+ (System.currentTimeMillis()-start) + " ms");
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
