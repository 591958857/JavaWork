package com.alex;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TestClassloader extends ClassLoader{

	private static byte[] decode(byte[] srcArray) {
		int arrayLength = srcArray.length;
		byte[] tmpArray = new byte[arrayLength];
		for(int i = 0; i<arrayLength; i++) {
			tmpArray[i] = (byte)(255 - srcArray[i]);
		}
		return tmpArray;
	}
	
	public void readFileByBytesForClass(String className, String fileName) throws IOException {
        File file = new File(fileName);
        InputStream in = null;
        in = new FileInputStream(file);
        int arrayLength = in.available();
    	byte[] byteArray = new byte[arrayLength];
    	in.read(byteArray);
    	in.close();
    	
    	byte[] targetByteArray = decode(byteArray);
    	
    	defineClass(className, targetByteArray, 0, targetByteArray.length);	
        
	}
	
	public void runClass(String className, String methodName, ClassLoader classLoader) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
	     Class<?> clazz = classLoader.loadClass(className);
	     Object instance = clazz.getDeclaredConstructor().newInstance();
	  
	     Method method = clazz.getMethod(methodName);
	     method.invoke(instance);
	}
	
	
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, IOException {
		TestClassloader classLoader = new TestClassloader();
		classLoader.readFileByBytesForClass("Hello","G:\\Java训练营\\week1\\作业相关\\作业相关\\Hello\\Hello.xlass");
		classLoader.runClass("Hello", "hello", classLoader);
	}

	
	
	
}
