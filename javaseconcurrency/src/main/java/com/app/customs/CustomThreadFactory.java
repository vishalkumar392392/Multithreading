package com.app.customs;

import java.util.concurrent.ThreadFactory;

public class CustomThreadFactory implements ThreadFactory {

	 int counter = 0;
	
	@Override
	public Thread newThread(Runnable r) {

		Thread thread = new Thread(r);
		thread.setName("Custom Thread :"+ (++counter));
		return thread;
	}

}
