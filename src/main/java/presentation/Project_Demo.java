package presentation;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import dao.HandleUpdate;
import dao.HandleUpdateBalanceImp;
import dao.HandleUpdatePasswordImp;
import pojo.AccountPojo;
import pojo.TransactionPojo;
import service.AccountService;
import service.AccountServiceImp;

public class Project_Demo {

		
	private static Scanner scanner;
	private static AccountPojo accountPojo;
	private static AccountService accountServiceImp;
	private static HandleUpdate handleUpdate;
	private static void bla() {
		System.out.println("whoaaaaaaaa");
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		bla();
		scanner = new Scanner(System.in);
		accountServiceImp = new AccountServiceImp();
		System.out.println("Hello and Welcome to Umbrella Corporation's Console Banking!");
		boolean isDone = false;
		char choice = '0';
	   
		while (!isDone) {
			System.out.println("MAIN MENU ------------------");
			System.out.println("1. Login as an Employee");
			System.out.println("2. Login as a Customer");
			System.out.println("3. Forgot Password?");
			System.out.println("4. Exit");

			choice = scanner.next().charAt(0);

			switch (choice) {
			case '1':
				implementEmployeeMenu();
				break; // break for case1 - employee

			case '2':

				implementCustomerMenu();

			
				break; // break for case 2 - customer

			case '3':
				implementPasswordReset();
				break;
				
			case '4':
				System.out.println("Have a nice day!");
				isDone = true;
				break;
				
				
			default:
				System.out.println("Invalid Input, please try again...");

			}
		}
		scanner.close();

	}
	
	private static void implementEmployeeMenu() {
		
		int choice;
		boolean isMenuDone = false;
		while (!isMenuDone) {

			System.out.println("EMPLOYEE MENU -----------------------");
			System.out.println("1. Register/Create a Customer");
			System.out.println("2. List all Customers");
			System.out.println("3. Logout");

			choice = scanner.next().charAt(0);

			switch (choice) {
			case '1':
				System.out.println("1. Register/Create a Customer");
				accountPojo = new AccountPojo();
				System.out.println("first name");
				accountPojo.setFirstName(scanner.next());
				System.out.println("last name");
				accountPojo.setLastName(scanner.next());
				System.out.println("enter userName");
				accountPojo.setUserName(scanner.next());
				System.out.println("enter password");
				accountPojo.setPassword(scanner.next());
				System.out.print("any money to put in now?");
				accountPojo.setBalance(scanner.nextFloat());
				accountPojo = accountServiceImp.createAccount(accountPojo);
				if (accountPojo != null) {
					System.out.println("Success! Account details are: " + accountPojo);
				} else {
					System.out.println("Sorry Was Unsuccessful");
				}
				isMenuDone = true;
				break;
			case '2':
				System.out.println("2. List all Customers");
				List<AccountPojo> accountPojos = new ArrayList<AccountPojo>();
				accountPojos = accountServiceImp.getAllAccount();
				for (int i = 0; i < accountPojos.size(); i++) {
					System.out.println(accountPojos.get(i));
				}
				break;
			case '3':
				System.out.println("3. Logging out");
				isMenuDone = true;
				break;
			default:
				System.out.println("Invalid Input, please try again...");
			}
		}
		
		
	}
	
	private static void implementCustomerMenu() {
		boolean isCustomerMenuDone = false;

		accountPojo = new AccountPojo();
		System.out.println("enter username");
		accountPojo.setUserName(scanner.next());
		System.out.println("Now Password");
		String attemptedPasswordString = scanner.next();
		accountPojo = accountServiceImp.getOneAccount(accountPojo);
		if(accountPojo == null) {
			System.out.println("Nothing found with user name and password input");
			return;
		}
		boolean isNotEquals = !attemptedPasswordString.equals(accountPojo.getPassword());
		if(isNotEquals) {
			System.out.println("Incorrect Password");
			return;
		}
		System.out.println("WELCOME " + accountPojo.getFirstName());

		handleUpdate = new HandleUpdateBalanceImp();
		
		while (!isCustomerMenuDone) {
			System.out.println("CUSTOMER MENU -----------------------");
			System.out.println("1. View account details");
			System.out.println("2. Deposit");
			System.out.println("3. Withdraw");
			System.out.println("4. View Transaction History");
			System.out.println("5. Logout");
			int choice;
			choice = scanner.next().charAt(0);

			switch (choice) {
			case '1':
				accountPojo = accountServiceImp.getOneAccount(accountPojo);
				if (accountPojo != null) {
					System.out.println(accountPojo);
				} else {
					System.out.println("Error retrieving account");
				}
				break;
			case '2':
				System.out.println("How much do you want to deposit?");
				float amountFloat = scanner.nextFloat();
				accountPojo = accountServiceImp.getOneAccount(accountPojo);
				accountPojo.setBalanceChangeAmount(amountFloat);
				handleUpdate = new HandleUpdateBalanceImp();
				accountPojo = accountServiceImp.updateAccount(accountPojo, handleUpdate);
				if (accountPojo != null) {
					System.out.println(accountPojo);
				} else {
					System.out.println("Error retrieving account");
				}
				break;
			case '3':
				System.out.println("How much you want taken out?");
				float amountFloatWithdraw = scanner.nextFloat();
				accountPojo = accountServiceImp.getOneAccount(accountPojo);
				float currentBalanceWithdraw = accountPojo.getBalance();
				if(currentBalanceWithdraw - amountFloatWithdraw < 0) {
					System.out.println("You are withdrawing more than thats in the account...");
					break;
				}
				accountPojo.setBalanceChangeAmount(amountFloatWithdraw * -1);
				handleUpdate = new HandleUpdateBalanceImp();
				accountPojo = accountServiceImp.updateAccount(accountPojo, handleUpdate);
				if (accountPojo != null) {
					System.out.println(accountPojo);
				} else {
					System.out.println("Error retrieving account");
				}
				break;
			case '4':
				System.out.println("Viewing Transaction History");
				accountPojo = accountServiceImp.getOneAccount(accountPojo);
				List<TransactionPojo> transactionPojos = new ArrayList<TransactionPojo>();
				transactionPojos = accountServiceImp.getTransactions(accountPojo, 1);
				for(int i = 0; i < transactionPojos.size();i++) {
					System.out.println(transactionPojos.get(i));
				}
				break;
			case '5':
				System.out.println("Logging out");
				isCustomerMenuDone = true;
				break;
			default:
				System.out.println("Invalid Input, please try again...");
			}

		}
	}
	
	
	private static void implementPasswordReset() {
		accountPojo = new AccountPojo();
		System.out.println("Enter your Username");
		String user_name = scanner.next();
		accountPojo.setUserName(user_name);
		accountPojo = accountServiceImp.getOneAccount(accountPojo);
		if(accountPojo == null) {
			System.out.println("User name wasn't found");
			return;
		}
		System.out.println("Enter your new password");
		String firstPasswordString = scanner.next();
		System.out.println("Enter it again for confirmation");
		String secondPasswordString = scanner.next();
		
		if(!firstPasswordString.equals(secondPasswordString)) {
			System.out.println("They didn't match, password did not change");
			return;
		}
		accountPojo.setPassword(firstPasswordString);
		handleUpdate = new HandleUpdatePasswordImp();
		accountServiceImp.updateAccount(accountPojo, handleUpdate);
		if(accountPojo != null) {
			System.out.println("Your password was changed successfully");
		}
		else {
			System.out.println("There was an error, your password did not change");
		}
		
		
	}
	
	
	
}