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

public class DatabaseManager implements AccountDao {

	public static DatabaseManager databaseManager = null;

	public static DatabaseManager getInstance() {
		if (databaseManager == null) {
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

			while (resultSet.next()) {
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
		try {
			Connection newConnection = DBUtil.makeConnection();
			PreparedStatement preparedStatement = newConnection.prepareStatement(sqlString,
					PreparedStatement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, accountPojo.getFirstName());
			preparedStatement.setString(2, accountPojo.getLastName());
			preparedStatement.setString(3, accountPojo.getPassword());
			preparedStatement.setDouble(4, 0.0f);
			preparedStatement.setString(5, accountPojo.getUserName());

			int rowsUpdated = preparedStatement.executeUpdate();
			ResultSet resultSet = preparedStatement.getGeneratedKeys();
			while (resultSet.next()) {
				accountPojo.setId(resultSet.getInt("id"));
			}
			if (rowsUpdated > 0) {
				if(accountPojo.getBalanceChangeAmount() > 0) {
					accountPojo = updateAccount(accountPojo, new HandleUpdateBalanceImp());
				}
				return accountPojo;
			}
		} catch (SQLException e) {
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
		String sqlString = "DELETE FROM account WHERE id= ?;";
		try {
			Connection newConnection = DBUtil.makeConnection();
			PreparedStatement preparedStatement = newConnection.prepareCall(sqlString);
			preparedStatement.setInt(1, accountPojo.getId());
			int rowsAffected = preparedStatement.executeUpdate();
			if (rowsAffected > 0)
				accountPojo = null;
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public AccountPojo getOneAccount(AccountPojo accountPojo, HandleGetOneAccount handleGetOneAccount) {
		// TODO Auto-generated method stub
		return handleGetOneAccount.handleGetOneAccount(accountPojo);
	}

	@Override
	public List<TransactionPojo> getTransactions(AccountPojo accountPojo, int amountTransactions) {
		// TODO Auto-generated method stub
		try {
			Connection newConnection = DBUtil.makeConnection();
			Statement statement = newConnection.createStatement();
			ResultSet resultSet;
			if (amountTransactions > 0) {
				resultSet = statement.executeQuery("SELECT * FROM bank_transaction " + "WHERE account_id = "
						+ accountPojo.getId() + " ORDER BY transaction_id DESC LIMIT " + amountTransactions + ";");
			} else {

				resultSet = statement.executeQuery("SELECT * FROM bank_transaction " + "WHERE account_id = "
						+ accountPojo.getId() + " ORDER BY transaction_id DESC;");
			}
			List<TransactionPojo> transactionPojos = new ArrayList<TransactionPojo>();

			int i = 0;
			while (resultSet.next() && (i < amountTransactions || amountTransactions == 0)) {
				TransactionPojo temPojo = new TransactionPojo();
				temPojo.setAccountID(resultSet.getInt("account_id"));
				temPojo.setTransactionID(resultSet.getInt("transaction_id"));
				temPojo.setTransactionDate(resultSet.getString("transaction_date"));
				temPojo.setTransactionType(resultSet.getString("transaction_type"));
				temPojo.setTransactionAmount(resultSet.getDouble("transaction_amount"));
				transactionPojos.add(temPojo);
				i++;
			}
			return transactionPojos;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;

	}

}