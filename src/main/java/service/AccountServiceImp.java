package service;

import java.util.ArrayList;
import java.util.List;

import javax.xml.crypto.Data;

import dao.AccountDao;
import dao.DatabaseManager;
import dao.HandleUpdate;
import pojo.AccountPojo;
import pojo.TransactionPojo;

public class AccountServiceImp implements AccountService {

	AccountDao accountDao;
	
	public AccountServiceImp() {
		// TODO Auto-generated constructor stub
		accountDao = new DatabaseManager();
	}
	
	
	public List<AccountPojo> getAllAccount() {
		// TODO Auto-generated method stub
		List<AccountPojo> accountPojos = new ArrayList<AccountPojo>();
		accountPojos = accountDao.getAllAccount();
		return accountPojos;
	}

	public AccountPojo createAccount(AccountPojo accountPojo) {
		// TODO Auto-generated method stub
		accountPojo = accountDao.createAccount(accountPojo);
		return accountPojo;
	}

	public AccountPojo updateAccount(AccountPojo accountPojo, HandleUpdate handleUpdate) {
		// TODO Auto-generated method stub
		return accountDao.updateAccount(accountPojo, handleUpdate);
		
	}

	public void deleteAccount(AccountPojo accountPojo) {
		// TODO Auto-generated method stub
		accountDao.deleteAccount(accountPojo);
	}

	public AccountPojo getOneAccount(AccountPojo accountPojo) {
		// TODO Auto-generated method stub
		return accountDao.getOneAccount(accountPojo);
	}


	public List<TransactionPojo> getTransactions(AccountPojo accountPojo, int amountTransactions) {
		// TODO Auto-generated method stub
		List<TransactionPojo> transactionPojos = new ArrayList<TransactionPojo>();
		transactionPojos = accountDao.getTransactions(accountPojo, amountTransactions);
		return transactionPojos;
	}

}