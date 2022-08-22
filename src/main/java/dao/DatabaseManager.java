package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLClientInfoException;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import pojo.AccountPojo;
import pojo.TransactionPojo;


public class DatabaseManager implements AccountDao{
	
	public static DatabaseManager databaseManager = null;
	
	public static DatabaseManager getInstance() {
		if(databaseManager == null) {
			databaseManager = new DatabaseManager();
		}
		return databaseManager;
	}
	
		
	public List<AccountPojo> getAllAccount() {
		
		try {
			Connection newConnection = DBUtil.makeConnection();
			Statement statement = newConnection.createStatement();
			
			ResultSet resultSet = statement.executeQuery("SELECT * FROM account;");
			List<AccountPojo> accountPojos = new ArrayList<AccountPojo>();
			
			while(resultSet.next()) {
				AccountPojo temPojo = new AccountPojo();
				temPojo.setId(resultSet.getInt("id"));
				temPojo.setFirstName(resultSet.getString("first_name"));
				temPojo.setLastName(resultSet.getString("last_name"));
				temPojo.setPassword(resultSet.getString("password"));
				temPojo.setUserName(resultSet.getString("user_name"));
				temPojo.setBalance(resultSet.getFloat("balance"));
				accountPojos.add(temPojo);
			}
			return accountPojos;
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	public AccountPojo createAccount(AccountPojo accountPojo) {
		
		String sqlString = "INSERT INTO account(first_name,last_name,password,balance,user_name) VALUES(?,?,?,?,?);";
		try{
			Connection newConnection = DBUtil.makeConnection();
			PreparedStatement preparedStatement = newConnection.prepareStatement(sqlString,PreparedStatement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, accountPojo.getFirstName());
			preparedStatement.setString(2, accountPojo.getLastName());
			preparedStatement.setString(3, accountPojo.getPassword());
			preparedStatement.setDouble(4, accountPojo.getBalance());
			preparedStatement.setString(5, accountPojo.getUserName());
			
			int rowsUpdated = preparedStatement.executeUpdate();
			ResultSet resultSet = preparedStatement.getGeneratedKeys();
			while(resultSet.next()) {
				accountPojo.setId(resultSet.getInt("id"));
			}
			if(rowsUpdated > 0) return accountPojo;
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	public AccountPojo updateAccount(AccountPojo accountPojo, HandleUpdate handleUpdate) {
		// TODO Auto-generated method stub
		return handleUpdate.performUpdate(accountPojo);
	}
	
	
	
	
	

	public void deleteAccount(AccountPojo accountPojo) {
		// TODO Auto-generated method stub
		
	}
	
	public AccountPojo getOneAccount(AccountPojo accountPojo) {
		// TODO Auto-generated method stub
		
		String sqlString = "SELECT * FROM account WHERE user_name= ?";
		try{
			Connection newConnection = DBUtil.makeConnection();
			PreparedStatement preparedStatement = newConnection.prepareCall(sqlString);
			preparedStatement.setString(1, accountPojo.getUserName());
			ResultSet resultSet = preparedStatement.executeQuery();
			boolean isnull = true;
			while(resultSet.next()) {
				isnull = false;
				accountPojo.setId(resultSet.getInt("id"));
				accountPojo.setBalance(resultSet.getFloat("balance"));
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


	@Override
	public List<TransactionPojo> getTransactions(AccountPojo accountPojo, int amountTransactions) {
		// TODO Auto-generated method stub
		try {
			Connection newConnection = DBUtil.makeConnection();
			Statement statement = newConnection.createStatement();
			
			ResultSet resultSet = statement.executeQuery("SELECT * FROM bank_transaction;");
			List<TransactionPojo> transactionPojos = new ArrayList<TransactionPojo>();
			
			while(resultSet.next()) {
				TransactionPojo temPojo = new TransactionPojo();
				temPojo.setAccountID(resultSet.getInt("account_id"));
				temPojo.setTransactionID(resultSet.getInt("transaction_id"));
				temPojo.setTransactionDate(resultSet.getString("transaction_date"));
				temPojo.setTransactionType(resultSet.getString("transaction_type"));
				temPojo.setTransactionAmount(resultSet.getDouble("transaction_amount"));
				transactionPojos.add(temPojo);
			}
			return transactionPojos;
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
		
		
		
		
	}
	
}