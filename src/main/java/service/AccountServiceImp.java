package service;

import java.util.ArrayList;
import java.util.List;

import javax.xml.crypto.Data;

import dao.AccountDao;
import dao.DatabaseManager;
import dao.HandleGetOneAccount;
import dao.HandleUpdate;
import exception.ApplicationException;
import pojo.AccountPojo;
import pojo.TransactionPojo;

public class AccountServiceImp implements AccountService {

	AccountDao accountDao;
	
	public AccountServiceImp() {
		// TODO Auto-generated constructor stub
		accountDao = new DatabaseManager();
	}
	
	
	public List<AccountPojo> getAllAccount() throws ApplicationException {
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
		// TODO Auto-generated method stub
		try {
			return accountDao.updateAccount(accountPojo, handleUpdate);
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			throw e;
		}
	}

	public void deleteAccount(AccountPojo accountPojo) throws ApplicationException {
		// TODO Auto-generated method stub
		try {
			accountDao.deleteAccount(accountPojo);
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			throw e;
		}
	}

	public AccountPojo getOneAccount(AccountPojo accountPojo, HandleGetOneAccount handleGetOneAccount) throws ApplicationException {
		// TODO Auto-generated method stub
		try {
			return accountDao.getOneAccount(accountPojo, handleGetOneAccount);
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			throw e;
		}
	}


	public List<TransactionPojo> getTransactions(AccountPojo accountPojo, int amountTransactions) throws ApplicationException {
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

}