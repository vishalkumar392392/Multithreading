package com.app.tests;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import com.app.runnables.LoggingProcessor;

public class TestOtherApis {
	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		
		ExecutorService service = Executors.newFixedThreadPool(3);
		List<Callable<Boolean>> list = new ArrayList<>();
		list.add(new LoggingProcessor());
		list.add(new LoggingProcessor());
		list.add(new LoggingProcessor());
		list.add(new LoggingProcessor());
		list.add(new LoggingProcessor());
		list.add(new LoggingProcessor());
		list.add(new LoggingProcessor());
		list.add(new LoggingProcessor());
		
		List<Future<Boolean>> invokeAll = service.invokeAll(list);
		
		for(Future<Boolean> futures : invokeAll) {
			
			System.out.println(futures.get());
			
		}
		
		service.shutdown();
		System.out.println("Is service shutdown: "+service.awaitTermination(10, TimeUnit.SECONDS));
		
	}

}
