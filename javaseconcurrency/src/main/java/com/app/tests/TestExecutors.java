package com.app.tests;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.app.dao.UserDao;
import com.app.runnables.UserProcessor;

public class TestExecutors {

	public static void main(String[] args) throws InterruptedException, ExecutionException {

//		ExecutorService service = Executors.newSingleThreadExecutor();
		ExecutorService service = Executors.newFixedThreadPool(3);

		List<String> users = getUsersFromFile(
				"C:\\Users\\vpalla\\Downloads\\Ex_Files_Java_EE_Concurrency\\Exercise Files\\Chapter3\\03_04\\begin\\new_users.txt");
		UserDao userDao = new UserDao();
		for(String user :users) {
			service.submit(new UserProcessor(user, userDao));
//			Future<Integer> future = service.submit(new UserProcessor(user, userDao));
//			System.out.println("Result of the operation is: "+future.get());
		}
		
		service.shutdown();
		System.out.println("Main execution is over...");
		
	}

	public static List<String> getUsersFromFile(String filename) {

		List<String> users = new ArrayList<>();
		try (BufferedReader reader = new BufferedReader(new FileReader(new File(filename)))) {
			String line = null;
			while ((line = reader.readLine()) != null) {
				users.add(line);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return users;

	}
}
