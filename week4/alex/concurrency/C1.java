package com.alex.concurrency;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

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
//callable FutureTask
public class C1 {
	 public static void main(String[] args) {
	        long start=System.currentTimeMillis();

	        Callable<Integer> cTask = new Callable<Integer>() {
				@Override
				public Integer call() throws Exception {
					return sum(); 
				}
	        };
	        FutureTask<Integer> fTask = new FutureTask<Integer>(cTask);
	        new Thread(fTask).start();
	        
	        int result = 0;
			try {
				result = fTask.get();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
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
