package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

import exception.ApplicationException;
import pojo.AccountPojo;

public class HandleUpdatePasswordImp implements HandleUpdate {

	@Override
	public AccountPojo performUpdate(AccountPojo accountPojo) throws ApplicationException {
		// TODO Auto-generated method stub

		String sqlString = "UPDATE account SET password = ? WHERE id = ?";
		try {
			Connection newConnection = null;
			try {
				newConnection = DBUtil.makeConnection();
			} catch (ApplicationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			PreparedStatement preparedStatement = newConnection.prepareStatement(sqlString);
			preparedStatement.setString(1, accountPojo.getPassword());
			preparedStatement.setInt(2, accountPojo.getId());
			int rowsAffected = preparedStatement.executeUpdate();
			if (rowsAffected > 0) {
				return accountPojo;
			}
		} catch (SQLException e) {
			throw new ApplicationException();
		}

		return null;

	}

}
