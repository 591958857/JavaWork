package com.alex;

public class TestByteCode {
	
	private int i = 10;
	private long l = 100;
	private float f = 1000.00f;
	private double d = 10000.00;
	
	private String str = "test";
	private Object obj;
	
	
	public static void fun(String param1) {
		String str1 = "hello ";
		str1 = str1 + param1;
		System.out.println(str1);
	}
	
	public double fun1(int param1, String param2) {
		if(param1>1) {
			return (i+l)*f;
		}
		
		if(param1<1) {
			return d/(l-i);
		}
		
		if(!str.equals(param2)) {
			obj = new Object();
			if(!obj.toString().equals(param2)) {
				for(int j = 0; j<i; j++) {
					d = d / 10;
				}		
			}
		}		
		
		return d;
	}
	
	public static void main(String args[]) {
		fun("world");

		TestByteCode testByteCode = new TestByteCode();
		testByteCode.fun1(0,"test0");
		testByteCode.fun1(1,"test1");
		testByteCode.fun1(2,"test2");
	}

}
