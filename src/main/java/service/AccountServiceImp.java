package service;

import java.util.ArrayList;
import java.util.List;

import javax.xml.crypto.Data;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dao.AccountDao;
import dao.DatabaseManager;
import dao.HandleGetOneAccount;
import dao.HandleGetOneClosedAccImp;
import dao.HandleUpdate;
import exception.ApplicationException;
import pojo.AccountPojo;
import pojo.TransactionPojo;
import presentation.Project_Demo;

public class AccountServiceImp implements AccountService {

	AccountDao accountDao;
	private static final Logger logger = LoggerFactory.getLogger(Project_Demo.class); 
	
	public AccountServiceImp() {
		// TODO Auto-generated constructor stub
		accountDao = new DatabaseManager();
	}
	
	
	public List<AccountPojo> getAllAccount() throws ApplicationException {
		//logger.info("Invoking getAllAccount");
		// TODO Auto-generated method stub
		List<AccountPojo> accountPojos = new ArrayList<AccountPojo>();
		try {
			accountPojos = accountDao.getAllAccount();
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			throw e;
		}
		return accountPojos;
	}

	public AccountPojo createAccount(AccountPojo accountPojo) throws ApplicationException {
		//logger.info("Invoking createAccount");
		// TODO Auto-generated method stub
		try {
			accountPojo = accountDao.createAccount(accountPojo);
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			throw e;
		}
		return accountPojo;
	}

	public AccountPojo updateAccount(AccountPojo accountPojo, HandleUpdate handleUpdate) throws ApplicationException {
		//logger.info("Invoking updateAccount");
		// TODO Auto-generated method stub
		try {
			return accountDao.updateAccount(accountPojo, handleUpdate);
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			throw e;
		}
	}

	public void deleteAccount(AccountPojo accountPojo) throws ApplicationException {
		//logger.info("Invoking deleteAccount");
		// TODO Auto-generated method stub
		try {
			accountDao.deleteAccount(accountPojo);
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			throw e;
		}
	}
	
	public AccountPojo getOneAccount(AccountPojo accountPojo, HandleGetOneAccount handleGetOneAccount) throws ApplicationException {
		//logger.info("Invoking getOneAccount");
		AccountPojo possiblePojo = null;
		try {
			possiblePojo = accountDao.getOneAccount(accountPojo, handleGetOneAccount);
			if(possiblePojo == null) return null;
			if(isAccountOpen(possiblePojo)) return possiblePojo;
			else {
				throw new ApplicationException("Sorry Acount is closed");
			}
			
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			throw e;
		}
	}


	public List<TransactionPojo> getTransactions(AccountPojo accountPojo, int amountTransactions) throws ApplicationException {
		//logger.info("Invoking getTransactions");
		// TODO Auto-generated method stub
		List<TransactionPojo> transactionPojos = new ArrayList<TransactionPojo>();
		try {
			transactionPojos = accountDao.getTransactions(accountPojo, amountTransactions);
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			throw e;
		}
		return transactionPojos;
	}
	
	private boolean isAccountOpen(AccountPojo accountPojo) throws ApplicationException {
		try {
			if(accountDao.getOneAccount(accountPojo, new HandleGetOneClosedAccImp()) != null) {
				return false;
			}
			else {
				return true;
			}
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			throw e;
		}
	}


	public AccountPojo transferToAccount(AccountPojo fromPojo, AccountPojo toPojo) throws ApplicationException {
		try {
			return accountDao.transferToAccount(fromPojo, toPojo);
		} catch (ApplicationException e) {
			throw e;
		}
	}
	
	

}