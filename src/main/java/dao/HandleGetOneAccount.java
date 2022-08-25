package dao;

import exception.ApplicationException;
import pojo.AccountPojo;

public interface HandleGetOneAccount {

	public AccountPojo handleGetOneAccount(AccountPojo accountPojo) throws ApplicationException;
}
