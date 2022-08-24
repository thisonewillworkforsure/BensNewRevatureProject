package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import pojo.AccountPojo;

public class HandleGetOneEmployeeImp implements HandleGetOneAccount {

	@Override
	public AccountPojo handleGetOneAccount(AccountPojo accountPojo) {
		String sqlString = "SELECT * FROM emp_account WHERE user_name= ?";
		try{
			Connection newConnection = DBUtil.makeConnection();
			PreparedStatement preparedStatement = newConnection.prepareCall(sqlString);
			preparedStatement.setString(1, accountPojo.getUserName());
			ResultSet resultSet = preparedStatement.executeQuery();
			boolean isnull = true;
			while(resultSet.next()) {
				isnull = false;
				accountPojo.setId(resultSet.getInt("id"));
				accountPojo.setFirstName(resultSet.getString("first_name"));
				accountPojo.setLastName(resultSet.getString("last_name"));
				accountPojo.setUserName(resultSet.getString("user_name"));
				accountPojo.setPassword(resultSet.getString("password"));
			}
			if(isnull) return null;
			return accountPojo;
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		
		return null;
	}

}
