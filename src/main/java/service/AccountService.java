package service;

import java.util.List;

import dao.HandleUpdate;
import pojo.AccountPojo;
import pojo.TransactionPojo;

public interface AccountService {
	//CRUD
	public List<AccountPojo> getAllAccount();
	
	public AccountPojo createAccount(AccountPojo accountPojo);
	
    AccountPojo updateAccount(AccountPojo accountPojo, HandleUpdate handleUpdate);
	
	public void deleteAccount(AccountPojo accountPojo);
	
	public AccountPojo getOneAccount(AccountPojo accountPojo);
	
	public List<TransactionPojo> getTransactions(AccountPojo accountPojo, int amountTransactions);
}