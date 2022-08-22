package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

import pojo.AccountPojo;

public class HandleUpdatePasswordImp implements HandleUpdate {

	@Override
	public AccountPojo performUpdate(AccountPojo accountPojo) {
		// TODO Auto-generated method stub

		String sqlString = "UPDATE account SET password = ? WHERE id = ?";
		try {
			Connection newConnection = DBUtil.makeConnection();
			PreparedStatement preparedStatement = newConnection.prepareStatement(sqlString);
			preparedStatement.setString(1, accountPojo.getPassword());
			preparedStatement.setInt(2, accountPojo.getId());
			int rowsAffected = preparedStatement.executeUpdate();
			if (rowsAffected > 0) {
				return accountPojo;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;

	}

}
