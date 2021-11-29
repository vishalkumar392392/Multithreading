package com.app.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.app.beans.User;

public class UserDao {
	
	public int saveUser(User user ) {
		
		int rows = 0;
		Connection connection = DbConnection.getConnection();
		PreparedStatement statement;
		try {
			statement = connection.prepareStatement("insert into user values(?,?,?)");
			statement.setInt(1, user.getId());
			statement.setString(2, user.getName());
			statement.setString(3, user.getEmailAddress());
			rows = statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return rows;
	}

}
