package com.vishal.multithreading.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.vishal.multithreading.entity.User;
import com.vishal.multithreading.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	Logger logger = LoggerFactory.getLogger(UserService.class);

	@Async
	public CompletableFuture<List<User>> saveUser(MultipartFile file) {
		long start = System.currentTimeMillis();
		List<User> users = parseCsvFile(file);
		logger.info("saving list of users {} and thread name is : -> {}", users.size(),
				Thread.currentThread().getName());
		List<User> savedUsers = userRepository.saveAll(users);
		long end = System.currentTimeMillis();
		logger.info("Time taken {}", (end - start));
		return CompletableFuture.completedFuture(savedUsers);

	}

	@Async
	public CompletableFuture<List<User>> getAllUsers() {

		logger.info("Thread name : {}", Thread.currentThread().getName());
		return CompletableFuture.completedFuture(userRepository.findAll());
	}

	@Async
	public CompletableFuture<List<User>> getUsers() {

		List<User> users = new ArrayList<>();

		CompletableFuture<List<User>> allUsers1 = getAllUsers();
		CompletableFuture<List<User>> allUsers2 = getAllUsers();
		CompletableFuture<List<User>> allUsers3 = getAllUsers();

		try {
			users.addAll(allUsers1.get());
			users.addAll(allUsers2.get());
			users.addAll(allUsers3.get());
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}

		return CompletableFuture.completedFuture(users);

	}

	private List<User> parseCsvFile(final MultipartFile file) {

		List<User> users = new ArrayList<>();

		try (BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()))) {

			String line = null;

			while ((line = br.readLine()) != null) {
				String[] data = line.split(",");
				User user = new User();
				user.setName(data[0]);
				user.setEmail(data[1]);
				users.add(user);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return users;

	}

	
	@Async
	public void test(String name) {
		userRepository.findAll();
		System.out.println(name+"  "+LocalDate.now());
	}
}
