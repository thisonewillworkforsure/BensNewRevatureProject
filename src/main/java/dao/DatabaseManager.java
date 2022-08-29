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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import exception.ApplicationException;
import pojo.AccountPojo;
import pojo.TransactionPojo;
import presentation.Project_Demo;

public class DatabaseManager implements AccountDao {

	public static DatabaseManager databaseManager = null;
	private static final Logger logger = LoggerFactory.getLogger(Project_Demo.class); 
	public static DatabaseManager getInstance() {
		if (databaseManager == null) {
			databaseManager = new DatabaseManager();
		}
		return databaseManager;
	}

	public List<AccountPojo> getAllAccount() throws ApplicationException {
		logger.info("Invoking getAllAccount in dao layer");
		try {
			Connection newConnection = DBUtil.makeConnection();
			Statement statement = newConnection.createStatement();

			String queryString = "SELECT account.id,account.first_name,account.last_name,account.password,account.balance,account.user_name FROM account LEFT OUTER JOIN closed_account ON closed_account.account_id = account.id WHERE closed_account.closed_date IS NULL;";
			String actualString = "SELECT * FROM account";
			
			
			
			ResultSet resultSet = statement.executeQuery(queryString);
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
			throw new ApplicationException("There was an sql exception, please try again");
		}
	}

	public AccountPojo createAccount(AccountPojo accountPojo) throws ApplicationException {
		logger.info("Invoking createAccount in dao layer");
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
			throw new ApplicationException("Failed to create an account, please try again another time.");
		}
		return null;
	}

	public AccountPojo updateAccount(AccountPojo accountPojo, HandleUpdate handleUpdate) throws ApplicationException {
		logger.info("Invoking updateAccount in dao layer");
		// TODO Auto-generated method stub
		return handleUpdate.performUpdate(accountPojo);
	}
		
	public void deleteAccount(AccountPojo accountPojo) throws ApplicationException {
		logger.info("Invoking deleteAccount in dao layer");
		// TODO Auto-generated method stub
		String sqlString = "INSERT INTO closed_account(account_id, closed_date) VALUES(?,?);";
		try {
			Connection newConnection = DBUtil.makeConnection();
			PreparedStatement preparedStatement = newConnection.prepareCall(sqlString);
			preparedStatement.setInt(1, accountPojo.getId());
			preparedStatement.setString(2, LocalDate.now().toString());
			int rowsAffected = preparedStatement.executeUpdate();
			if (rowsAffected > 0)
				accountPojo = null;
		} catch (SQLException e) {
			throw new ApplicationException("Failed to delete account, please try again another time");
		}

	}
	

	public AccountPojo getOneAccount(AccountPojo accountPojo, HandleGetOneAccount handleGetOneAccount) throws ApplicationException {
		logger.info("Invoking getOneAccount in dao layer");
		// TODO Auto-generated method stub
		try {
			return handleGetOneAccount.handleGetOneAccount(accountPojo);
		} catch (ApplicationException e) {
			throw e;
		}
		
	}

	public List<TransactionPojo> getTransactions(AccountPojo accountPojo, int amountTransactions) throws ApplicationException {
		logger.info("Invoking getTransactions in dao layer");
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
			throw new ApplicationException("Failed to get transactions, please try again another time");
		}

	}

	public AccountPojo transferToAccount(AccountPojo fromPojo, AccountPojo toPojo) throws ApplicationException {
		try {
			float balanceAmt = fromPojo.getBalanceChangeAmount();
			fromPojo = updateAccount(fromPojo, new HandleUpdateBalanceImp());
			toPojo.setBalanceChangeAmount(balanceAmt * -1);
			toPojo = updateAccount(toPojo, new HandleUpdateBalanceImp());
			if(fromPojo == null || toPojo == null) fromPojo = null;
			return fromPojo;
		} catch (ApplicationException e) {
			throw e;
		}
		
	}

}