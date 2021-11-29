package com.app.runnables;

import java.util.StringTokenizer;
import java.util.concurrent.Callable;

import com.app.beans.User;
import com.app.dao.UserDao;

public class UserProcessor implements Callable<Integer> {

	private String userRecord;
	
	private UserDao userDao;
	
	public UserProcessor(String userRecord, UserDao userDao) {
		super();
		this.userRecord = userRecord;
		this.userDao = userDao;
	}

	@Override
	public Integer call() throws Exception {
		int rows = 0;
		System.out.println(Thread.currentThread().getName()+ "processing record for: "+ userRecord);
		StringTokenizer stringTokenizer = new StringTokenizer(userRecord,",");
		User user = null;
		while(stringTokenizer.hasMoreTokens()) {
			user = new User();
			user.setEmailAddress(stringTokenizer.nextToken());
			user.setName(stringTokenizer.nextToken());
			user.setId(Integer.valueOf(stringTokenizer.nextToken()));
			rows = userDao.saveUser(user);
		}
		return rows;
	}

}
