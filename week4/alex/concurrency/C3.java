package com.alex.concurrency;

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
//join
public class C3 {
	 private static int result;
	
	 public static void main(String[] args) {
	        long start=System.currentTimeMillis();

	        Runnable cTask = new Runnable() {
				@Override
				public void run() {
					result = sum(); 
				}
	        };

	        try {
	        	Thread t = 	new Thread( cTask);
	        	t.start();
	        	t.join();
			} catch (InterruptedException e) {
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