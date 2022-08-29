package dao;

import java.util.List;

import exception.ApplicationException;
import pojo.AccountPojo;
import pojo.TransactionPojo;

public interface AccountDao {
	public List<AccountPojo> getAllAccount() throws ApplicationException;
	
	public AccountPojo createAccount(AccountPojo accountPojo) throws ApplicationException;
	
    AccountPojo updateAccount(AccountPojo accountPojo, HandleUpdate handleUpdate) throws ApplicationException;
	
	public void deleteAccount(AccountPojo accountPojo) throws ApplicationException;
	
	public AccountPojo getOneAccount(AccountPojo accountPojo, HandleGetOneAccount handleGetOneAccount) throws ApplicationException;
	
	public List<TransactionPojo> getTransactions(AccountPojo accountPojo, int amountTransactions) throws ApplicationException;
	
	public AccountPojo transferToAccount(AccountPojo fromPojo, AccountPojo toPojo) throws ApplicationException;
}