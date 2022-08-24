package dao;

import java.util.List;

import pojo.AccountPojo;
import pojo.TransactionPojo;

public interface AccountDao {
	public List<AccountPojo> getAllAccount();
	
	public AccountPojo createAccount(AccountPojo accountPojo);
	
    AccountPojo updateAccount(AccountPojo accountPojo, HandleUpdate handleUpdate);
	
	public void deleteAccount(AccountPojo accountPojo);
	
	public AccountPojo getOneAccount(AccountPojo accountPojo, HandleGetOneAccount handleGetOneAccount);
	
	public List<TransactionPojo> getTransactions(AccountPojo accountPojo, int amountTransactions);
}