package presentation;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import dao.HandleGetOneAccount;
import dao.HandleGetOneCustomerImp;
import dao.HandleGetOneEmployeeImp;
import dao.HandleUpdate;
import dao.HandleUpdateBalanceImp;
import dao.HandleUpdatePasswordImp;
import exception.ApplicationException;
import pojo.AccountPojo;
import pojo.TransactionPojo;
import service.AccountService;
import service.AccountServiceImp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.BasicConfigurator;





//import org.apache.log4j.BasicConfigurator;  
//import org.apache.log4j.LogManager;  
//import org.apache.log4j.Logger;  




public class Project_Demo {

	private static final Logger logger = LoggerFactory.getLogger(Project_Demo.class); 
	private static ScannerHandler scannerHandler;
	private static AccountPojo accountPojo;
	private static AccountService accountServiceImp;
	private static HandleUpdate handleUpdate;
	private static OutputHandler outputHandler;
	private static float amountToWithdraw = 0f;
	private static void bla() {
		System.out.println("whoaaaaaaaa");
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//BasicConfigurator.configure();
		//logger.info("Starting the application!");
		scannerHandler = new ScannerHandler();
		outputHandler = new OutputHandler();
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

			choice = scannerHandler.getInputChar();
			// scanner.nextLine();
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
		scannerHandler.close();

	}

	private static void implementEmployeeMenu() {

		int choice;
		boolean isMenuDone = false;

		if (!isLoginSuccessful(false))
			return;
		System.out.println("WELCOME " + accountPojo.getFirstName());

		while (!isMenuDone) {

			System.out.println("EMPLOYEE MENU -----------------------");
			System.out.println("1. Register/Create a Customer");
			System.out.println("2. List all Customers");
			System.out.println("3. Logout");

			choice = scannerHandler.getInputChar();

			switch (choice) {
			case '1':
				System.out.println("1. Register/Create a Customer");
				accountPojo = new AccountPojo();
				System.out.println("first name");
				accountPojo.setFirstName(scannerHandler.getInputString());
				if(accountPojo.getFirstName().isEmpty()) {
					System.out.println("Can't be nothing, try again!");
					break;
				}
				System.out.println("last name");
				accountPojo.setLastName(scannerHandler.getInputString());
				if(accountPojo.getLastName().isEmpty()) {
					System.out.println("Can't be nothing, try again!");
					break;
				}
				System.out.println("enter userName");
				accountPojo.setUserName(scannerHandler.getInputString());
				if(accountPojo.getUserName().isEmpty()) {
					System.out.println("Can't be nothing, try again!");
					break;
				}
				System.out.println("enter password");
				accountPojo.setPassword(scannerHandler.getInputString());
				if(accountPojo.getPassword().isEmpty()) {
					System.out.println("Can't be nothing, try again!");
					break;
				}
				System.out.print("any money to put in now?");
				float amountFloat;
				try {
					amountFloat = scannerHandler.getInputFloat();
				} catch (NumberFormatException e) {
					System.out.println("Invalid input, please try again!");
					break;
				}
				accountPojo.setBalanceChangeAmount(amountFloat);
				try {
					accountPojo = accountServiceImp.createAccount(accountPojo);
				} catch (ApplicationException e) {
					// TODO Auto-generated catch block
					System.out.println(e.getMessage());
					break;
				}
				if (accountPojo != null) {
					System.out.println("Success! Account details are: " + accountPojo);
				} else {
					System.out.println("Sorry Was Unsuccessful");
				}
				break;
			case '2':
				System.out.println("2. List all Customers");
				List<AccountPojo> accountPojos = new ArrayList<AccountPojo>();
				try {
					accountPojos = accountServiceImp.getAllAccount();
				} catch (ApplicationException e) {
					// TODO Auto-generated catch block
					System.out.println(e.getMessage());
					break;
				}
				outputHandler.OutputAccountPojos(accountPojos);
				/*for (int i = 0; i < accountPojos.size(); i++) {
					System.out.println(accountPojos.get(i));
				}*/
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
		if (!isLoginSuccessful(true))
			return;
		System.out.println("WELCOME " + accountPojo.getFirstName());

		handleUpdate = new HandleUpdateBalanceImp();

		while (!isCustomerMenuDone) {
			System.out.println("CUSTOMER MENU -----------------------");
			System.out.println("1. View account details");
			System.out.println("2. Deposit");
			System.out.println("3. Withdraw");
			System.out.println("4. Transfer");
			System.out.println("5. View Transaction History");
			System.out.println("6. Close Account");
			System.out.println("7. Logout");
			int choice;
			choice = scannerHandler.getInputChar();

			switch (choice) {
			case '1':
				try {
					accountPojo = accountServiceImp.getOneAccount(accountPojo, new HandleGetOneCustomerImp());
				} catch (ApplicationException e) {
					// TODO Auto-generated catch block
					System.out.println(e.getMessage());
					break;
				}
				if (accountPojo != null) {
					System.out.println(accountPojo);
				} else {
					System.out.println("Error retrieving account");
				}
				break;
			case '2':
				System.out.println("How much do you want to deposit?");
				float amountFloat;
				try {
					amountFloat = scannerHandler.getInputFloat();
				} catch (NumberFormatException e) {
					System.out.println("Invalid input, please try again!");
					break;
				}
				
				try {
					accountPojo = accountServiceImp.getOneAccount(accountPojo, new HandleGetOneCustomerImp());
				} catch (ApplicationException e) {
					// TODO Auto-generated catch block
					System.out.println(e.getMessage());
					break;
				}
				accountPojo.setBalanceChangeAmount(amountFloat);
				handleUpdate = new HandleUpdateBalanceImp();
				try {
					accountPojo = accountServiceImp.updateAccount(accountPojo, handleUpdate);
				} catch (ApplicationException e) {
					// TODO Auto-generated catch block
					System.out.println(e.getMessage());
					break;
				}
				if (accountPojo != null) {
					System.out.println(accountPojo);
				} else {
					System.out.println("Error retrieving account");
				}
				break;
			case '3':
				if(!isAbleToWithdraw()) break;
				accountPojo.setBalanceChangeAmount(amountToWithdraw * -1);
				handleUpdate = new HandleUpdateBalanceImp();
				try {
					accountPojo = accountServiceImp.updateAccount(accountPojo, handleUpdate);
				} catch (ApplicationException e) {
					// TODO Auto-generated catch block
					System.out.println(e.getMessage());
					break;
				}
				if (accountPojo != null) {
					System.out.println(accountPojo);
				} else {
					System.out.println("Error retrieving account");
				}
				break;
				
			case '4':
				if(!isAbleToWithdraw()) break;
				accountPojo.setBalanceChangeAmount(amountToWithdraw * -1);
				System.out.println("Enter the user name of who you want to transfer the money to");
				String otherUserName = scannerHandler.getInputString();
				AccountPojo otherPojo = new AccountPojo();
				otherPojo.setUserName(otherUserName);
				try {
					otherPojo = accountServiceImp.getOneAccount(otherPojo, new HandleGetOneCustomerImp());
					if(otherPojo == null) {
						System.out.println("Had trouble finding the recipient, please try again later");
						break;
					}
					accountPojo = accountServiceImp.transferToAccount(accountPojo, otherPojo);
					if(accountPojo != null) {
						System.out.println("Success! The money has been transferred.");
					}
					else {
						System.out.println("Had trouble transferring the funds, please try again later.");
						break;
					}
				} catch (ApplicationException e) {
					// TODO Auto-generated catch block
					System.out.println(e.getMessage());
					break;
				}
				break;
				
			case '5':
				System.out.println("Viewing Transaction History");
				try {
					accountPojo = accountServiceImp.getOneAccount(accountPojo, new HandleGetOneCustomerImp());
				} catch (ApplicationException e) {
					// TODO Auto-generated catch block
					System.out.println(e.getMessage());
					break;
				}
				List<TransactionPojo> transactionPojos = new ArrayList<TransactionPojo>();
				System.out.println("Enter amount of transactions you want to see, for all enter 0");
				Integer amountTransactions = 0;
				try {
					amountTransactions = scannerHandler.getInputInt();
				} catch (NumberFormatException e) {
					System.out.println("Invalid Input, please try again!");
				}
				if(amountTransactions < 0) {
					System.out.println("Input is less than 0, we can't process this, please try again!");
					break;
				}
				try {
					transactionPojos = accountServiceImp.getTransactions(accountPojo, amountTransactions);
				} catch (ApplicationException e) {
					// TODO Auto-generated catch block
					System.out.println(e.getMessage());
					break;
				}
				for (int i = 0; i < transactionPojos.size(); i++) {
					System.out.println(transactionPojos.get(i));
				}
				break;
			case '6':
				try {
					accountPojo = accountServiceImp.getOneAccount(accountPojo, new HandleGetOneCustomerImp());
				} catch (ApplicationException e) {
					// TODO Auto-generated catch block
					System.out.println(e.getMessage());
					break;
				}
				System.out.println("Do you really want to close your account? type y if so");
				char willDelete = scannerHandler.getInputChar();
				if (willDelete != 'y' && willDelete != 'Y') {
					System.out.println("That's ok we knew you would stay with us, good ol Umbrella....");
					break;
				}
				System.out.println("No really do you seriously want to close your account? type y if so");
				willDelete = scannerHandler.getInputChar();
				if (willDelete != 'y' && willDelete != 'Y') {
					System.out.println(
							"That's ok we knew you would stay with us, good ol Umbrella.... scared us a sec though.");
					break;
				}
				try {
					accountServiceImp.deleteAccount(accountPojo);
				} catch (ApplicationException e) {
					// TODO Auto-generated catch block
					System.out.println(e.getMessage());
					break;
				}
				if(accountPojo!= null) {
				System.out.println("Your account was succesfully deleted, you will pay for- I mean have a good day!");
				isCustomerMenuDone = true;
				accountPojo = null;
				}
				else {
					System.out.println("Failed to delete, please try again later (or not).");
				}
				break;
			case '7':
				System.out.println("Logging out");
				isCustomerMenuDone = true;
				break;
			default:
				System.out.println("Invalid Input, please try again...");

			}

		}
	}

	private static boolean isLoginSuccessful(boolean isCustomer) {

		accountPojo = new AccountPojo();
		System.out.println("enter username");
		accountPojo.setUserName(scannerHandler.getInputString());
		System.out.println("Now Password");
		String attemptedPasswordString = scannerHandler.getInputString();
		HandleGetOneAccount handler = isCustomer ? new HandleGetOneCustomerImp() : new HandleGetOneEmployeeImp();
		try {
			accountPojo = accountServiceImp.getOneAccount(accountPojo, handler);
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			return false;
		}
		if (accountPojo == null) {
			System.out.println("Nothing found with user name and password input");
			return false;
		}
		boolean isNotEquals = !attemptedPasswordString.equals(accountPojo.getPassword());
		if (isNotEquals) {
			System.out.println("Incorrect Password");
			return false;
		}
		return true;
	}

	private static void implementPasswordReset() {
		accountPojo = new AccountPojo();
		System.out.println("Enter your Username");
		String user_name = scannerHandler.getInputString();
		accountPojo.setUserName(user_name);
		try {
			accountPojo = accountServiceImp.getOneAccount(accountPojo, new HandleGetOneCustomerImp());
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			return;
		}
		if (accountPojo == null) {
			System.out.println("User name wasn't found");
			return;
		}
		System.out.println("Enter your new password");
		String firstPasswordString = scannerHandler.getInputString();
		System.out.println("Enter it again for confirmation");
		String secondPasswordString = scannerHandler.getInputString();

		if (!firstPasswordString.equals(secondPasswordString)) {
			System.out.println("They didn't match, password did not change");
			return;
		}
		accountPojo.setPassword(firstPasswordString);
		handleUpdate = new HandleUpdatePasswordImp();
		try {
			accountServiceImp.updateAccount(accountPojo, handleUpdate);
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			return;
		}
		if (accountPojo != null) {
			System.out.println("Your password was changed successfully");
		} else {
			System.out.println("There was an error, your password did not change");
		}

	}
	
	private static boolean isAbleToWithdraw() {
		System.out.println("How much you want taken out?");
		try {
			amountToWithdraw = scannerHandler.getInputFloat();
		} catch (Exception e) {
			System.out.println("Invalid input, please try again!");
			return false;
		}
		try {
			accountPojo = accountServiceImp.getOneAccount(accountPojo, new HandleGetOneCustomerImp());
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			return false;
		}
		float currentBalanceWithdraw = accountPojo.getBalance();
		if (currentBalanceWithdraw - amountToWithdraw < 0) {
			System.out.println("You are withdrawing more than thats in the account...");
			return false;
		}
		return true;
	}
	

}