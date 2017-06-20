package com.prashant.springservice.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.prashant.springservice.domain.User;
import com.prashant.springservice.utility.DBUtility;

public class UserService {
	
	private Connection connection;
	
	public UserService(){
		connection = DBUtility.getConnection();
	}
	
	public List<User> getAllUsers(){
		List<User> users = new ArrayList<User>();
		try{
			Statement statement =connection.createStatement();
			ResultSet rs = statement.executeQuery("select top 15 * from Prashant.dbo.tblUser ");
			while(rs.next()){
				User user = new User();
				user.setUserid(rs.getInt("userid"));
				user.setFirstName(rs.getString("firstname"));
				user.setLastName(rs.getString("lastname"));				
				user.setEmail(rs.getString("email"));
				users.add(user);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return users;
	}
	
	public User getUserById(int userId) {
		User user = new User();
		try {
			PreparedStatement preparedStatement = connection.
					prepareStatement("select * from Prashant.dbo.tblUser where userid=?");
			preparedStatement.setInt(1, userId);
			ResultSet rs = preparedStatement.executeQuery();
			
			if (rs.next()) {
				user.setUserid(rs.getInt("userid"));
				user.setFirstName(rs.getString("firstname"));
				user.setLastName(rs.getString("lastname"));
				
				user.setEmail(rs.getString("email"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

}
