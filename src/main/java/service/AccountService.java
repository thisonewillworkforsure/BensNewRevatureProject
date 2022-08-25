package service;

import java.util.List;

import dao.HandleGetOneAccount;
import dao.HandleUpdate;
import exception.ApplicationException;
import exception.ServiceException;
import pojo.AccountPojo;
import pojo.TransactionPojo;

public interface AccountService {
	//CRUD
	public List<AccountPojo> getAllAccount() throws ApplicationException;
	
	public AccountPojo createAccount(AccountPojo accountPojo) throws ApplicationException;
	
    AccountPojo updateAccount(AccountPojo accountPojo, HandleUpdate handleUpdate) throws ApplicationException;
	
	public void deleteAccount(AccountPojo accountPojo) throws ApplicationException;
	
	public AccountPojo getOneAccount(AccountPojo accountPojo, HandleGetOneAccount handleGetOneAccount) throws ApplicationException;
	
	public List<TransactionPojo> getTransactions(AccountPojo accountPojo, int amountTransactions) throws ApplicationException;
}