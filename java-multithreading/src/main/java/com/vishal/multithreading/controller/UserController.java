package com.vishal.multithreading.controller;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.vishal.multithreading.entity.User;
import com.vishal.multithreading.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping(value = "/users", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity saveUsers(@RequestParam(value = "files") MultipartFile[] files) {
		System.out.println("post method entered");

		for (MultipartFile file : files) {
			CompletableFuture<List<User>> saveUser = userService.saveUser(file);
		}
		System.out.println("post method existed");
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@GetMapping("/users")
	public CompletableFuture<ResponseEntity> findAllUsers() {
		return userService.getAllUsers().thenApply(ResponseEntity::ok);
	}

	@GetMapping("/getUsers")
	public ResponseEntity getUsers() {
		System.out.println("get method entered");

		userService.getAllUsers();
		System.out.println("get method existed");

		return ResponseEntity.status(HttpStatus.OK).build();

	}

	@GetMapping("/testAsync")
	public String testAsync() {
		System.out.println("get method entered"+LocalDate.now());
		userService.test("testing");
		System.out.println("get method existed"+LocalDate.now());

		return "testing";
	}

}
