package com.app.tests;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.app.runnables.CleaningProcessor;

public class TestSchedules {
	
	
	public static void main(String[] args) {
		
		ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
//		service.schedule(new CleaningProcessor(), 5, TimeUnit.SECONDS);
//		service.scheduleAtFixedRate(new CleaningProcessor(), 5, 4, TimeUnit.SECONDS);
		service.scheduleWithFixedDelay(new CleaningProcessor(), 5, 4, TimeUnit.SECONDS);

	}

}
