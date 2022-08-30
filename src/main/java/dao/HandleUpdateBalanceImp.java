package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

import exception.ApplicationException;
import pojo.AccountPojo;

public class HandleUpdateBalanceImp implements HandleUpdate {

	public AccountPojo performUpdate(AccountPojo accountPojo) throws ApplicationException {
		// TODO Auto-generated method stub
		
		
		String sqlString = "UPDATE account SET balance = ? WHERE id = ?";
		String accountString = "INSERT INTO bank_transaction(account_id,transaction_date,transaction_type,transaction_amount) VALUES(?,?,?,?);";
		Connection newConnection = null;
		try{
			try {
				newConnection = DBUtil.makeConnection();
			} catch (ApplicationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			newConnection.setAutoCommit(false);
			PreparedStatement preparedStatement = newConnection.prepareStatement(sqlString);
			preparedStatement.setDouble(1, accountPojo.getBalance()+accountPojo.getBalanceChangeAmount());
			preparedStatement.setInt(2, accountPojo.getId());
			int rowsAffected = preparedStatement.executeUpdate();
			preparedStatement = newConnection.prepareStatement(accountString);
			preparedStatement.setInt(1,accountPojo.getId());
			preparedStatement.setString(2,LocalDate.now().toString());
			preparedStatement.setString(3,accountPojo.getBalanceChangeAmount() > 0? "DEPOSIT" : "WITHDRAW");
			preparedStatement.setFloat(4,accountPojo.getBalanceChangeAmount());
			int transactionRowsAffected = preparedStatement.executeUpdate();
			newConnection.commit();
			if(rowsAffected > 0 && transactionRowsAffected > 0) {
				accountPojo.setBalance(accountPojo.getBalance()+accountPojo.getBalanceChangeAmount());
				return accountPojo;}
			
		}
		catch(SQLException e) {
			try {
				newConnection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				throw new ApplicationException("Database error please try again!");
			}
			throw new ApplicationException();
		}
		
		
		return null;
		
		
		
	}

}
