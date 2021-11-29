package com.app.tests;

import com.app.runnables.AppThreads;

public class TestThreads {
	
	public static void main(String[] args) {
		
		AppThreads thread1 = new AppThreads();
		AppThreads thread2 = new AppThreads();
		AppThreads thread3 = new AppThreads();
		
		thread1.start();
		thread2.start();
		thread3.start();
	}

}
