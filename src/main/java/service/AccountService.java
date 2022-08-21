package service;

import java.util.List;

import pojo.AccountPojo;
import pojo.TransactionPojo;

public interface AccountService {
	//CRUD
	public List<AccountPojo> getAllAccount();
	
	public AccountPojo createAccount(AccountPojo accountPojo);
	
    AccountPojo updateAccount(AccountPojo accountPojo);
	
	public void deleteAccount(AccountPojo accountPojo);
	
	public AccountPojo getOneAccount(AccountPojo accountPojo);
	
	public List<TransactionPojo> getTransactions(AccountPojo accountPojo, int amountTransactions);
}