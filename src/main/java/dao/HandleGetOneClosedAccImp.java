package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import exception.ApplicationException;
import pojo.AccountPojo;

public class HandleGetOneClosedAccImp implements HandleGetOneAccount {

	public AccountPojo handleGetOneAccount(AccountPojo accountPojo) throws ApplicationException {
		// TODO Auto-generated method stub
		String sqlString = "SELECT * FROM closed_account WHERE account_id= ?";
		try {
			Connection newConnection = null;
			try {
				newConnection = DBUtil.makeConnection();
			} catch (ApplicationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			PreparedStatement preparedStatement = newConnection.prepareCall(sqlString);
			preparedStatement.setInt(1, accountPojo.getId());
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				accountPojo.setId(resultSet.getInt("account_id"));
				return accountPojo;
			}
			else {
				return null;
			}
		} catch (SQLException e) {
			throw new ApplicationException("Unable to get Account");
		}

	}

}
