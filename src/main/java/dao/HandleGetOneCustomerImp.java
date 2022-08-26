package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import exception.ApplicationException;
import pojo.AccountPojo;

public class HandleGetOneCustomerImp implements HandleGetOneAccount {

	public AccountPojo handleGetOneAccount(AccountPojo accountPojo) throws ApplicationException {

		String sqlString = "SELECT * FROM account WHERE user_name= ?";
		try {
			Connection newConnection = null;
			try {
				newConnection = DBUtil.makeConnection();
			} catch (ApplicationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			PreparedStatement preparedStatement = newConnection.prepareCall(sqlString);
			preparedStatement.setString(1, accountPojo.getUserName());
			ResultSet resultSet = preparedStatement.executeQuery();
			boolean isnull = true;
			while (resultSet.next()) {
				isnull = false;
				accountPojo.setId(resultSet.getInt("id"));
				accountPojo.setBalance(resultSet.getFloat("balance"));
				accountPojo.setFirstName(resultSet.getString("first_name"));
				accountPojo.setLastName(resultSet.getString("last_name"));
				accountPojo.setUserName(resultSet.getString("user_name"));
				accountPojo.setPassword(resultSet.getString("password"));
			}
			if (isnull)
				return null;
			return accountPojo;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;

	}

}
