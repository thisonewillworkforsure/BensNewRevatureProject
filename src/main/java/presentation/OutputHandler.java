package presentation;

import java.util.List;

import pojo.AccountPojo;

public class OutputHandler {
	
	public void OutputAccountPojos(List<AccountPojo> accountPojos) {
		int maxIdLength = -1, maxFirstNameLength = -1, maxLastNameLength = -1, maxUserNameLength =-1,maxPasswordLength =-1,maxBalanceLength=-1;
		
		for(AccountPojo accountPojo : accountPojos) {
			String idLength = ((Integer)accountPojo.getId()).toString();
			if(idLength.length() > maxIdLength) maxIdLength = idLength.length();
			
			if(accountPojo.getFirstName().length() > maxFirstNameLength) maxFirstNameLength = accountPojo.getFirstName().length();		
			
			if(accountPojo.getLastName().length() > maxLastNameLength) maxLastNameLength = accountPojo.getLastName().length();
			
			if(accountPojo.getUserName().length() > maxUserNameLength) maxUserNameLength = accountPojo.getUserName().length();
			
			if(accountPojo.getPassword().length() > maxPasswordLength) maxPasswordLength = accountPojo.getPassword().length();
			
			String balanceLength = ((Float)accountPojo.getBalance()).toString();
			if(balanceLength.length() > maxIdLength) maxIdLength = balanceLength.length();
		}
		
		for(AccountPojo accountPojo : accountPojos) {
			String idLength = ((Integer)accountPojo.getId()).toString();
			String balanceLength = ((Float)accountPojo.getBalance()).toString();
			System.out.println("id=" + accountPojo.getId() + " ".repeat(maxIdLength-idLength.length()) + " " +
			"First Name: " + accountPojo.getFirstName() + " ".repeat(maxFirstNameLength-accountPojo.getFirstName().length()) + " " +
			"Last Name: " + accountPojo.getLastName() + " ".repeat(maxLastNameLength-accountPojo.getLastName().length()) + " " +
			"User Name: " + accountPojo.getUserName() + " ".repeat(maxUserNameLength-accountPojo.getUserName().length()) + " " +
			"Password: " + accountPojo.getPassword() + " ".repeat(maxPasswordLength-accountPojo.getPassword().length()) + " "  +
			"Balance: " + accountPojo.getBalance() /*+ " ".repeat(maxBalanceLength-balanceLength.length())*/);
			
		}
	}
	
	
}
