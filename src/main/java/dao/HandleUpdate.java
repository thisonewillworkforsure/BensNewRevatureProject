package dao;

import exception.ApplicationException;
import pojo.AccountPojo;

public interface HandleUpdate {

	public AccountPojo performUpdate(AccountPojo accountPojo) throws ApplicationException;
	
}
