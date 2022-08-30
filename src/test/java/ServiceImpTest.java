import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.nullable;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.BDDMockito.Then;
import org.mockito.junit.jupiter.MockitoExtension;

import dao.AccountDao;
import dao.DatabaseManager;
import dao.HandleGetOneCustomerImp;
import dao.HandleUpdateBalanceImp;
import dao.HandleUpdatePasswordImp;
import exception.ApplicationException;
import pojo.AccountPojo;
import pojo.TransactionPojo;
import service.AccountService;
import service.AccountServiceImp;


@ExtendWith(MockitoExtension.class)
public class ServiceImpTest {

	@InjectMocks
	AccountServiceImp accountServiceImp;

	@Mock
	DatabaseManager accountDao;
	
	@Test
	public void testUpdateAccountWithMockito() {

		AccountPojo dummyPojo = new AccountPojo();
		dummyPojo.setUserName("cd");
		dummyPojo.setPassword("cd");
		dummyPojo.setId(4);
		HandleUpdateBalanceImp imp = new HandleUpdateBalanceImp();
		try {
			Mockito.lenient().when(accountServiceImp.updateAccount(dummyPojo,imp)).thenReturn(dummyPojo);
			
		} catch (ApplicationException e1) {
			e1.printStackTrace();
		}
		
		AccountPojo actualPojo = null;
		
		
		try {
			actualPojo = accountServiceImp.updateAccount(dummyPojo,imp);
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
		
		assertEquals(dummyPojo.getId(), actualPojo.getId());	
	}
	
	
	@Test
	public void testCreateOneAccountPojoWithMockito() {

		AccountPojo dummyPojo = new AccountPojo();
		dummyPojo.setUserName("cd");
		dummyPojo.setPassword("cd");
		dummyPojo.setId(4);
		HandleGetOneCustomerImp bla = new HandleGetOneCustomerImp();
		try {
			Mockito.lenient().when(accountServiceImp.createAccount(dummyPojo)).thenReturn(dummyPojo);
			
		} catch (ApplicationException e1) {
			e1.printStackTrace();
		}
		
		AccountPojo actualPojo = null;
		
		
		try {
			actualPojo = accountServiceImp.createAccount(dummyPojo); 
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
		
		assertEquals(dummyPojo.getId(), actualPojo.getId());	
	}
	
	
	@Test
	public void testGetOneAccountPojoWithMockito() {

		AccountPojo dummyPojo = new AccountPojo();
		dummyPojo.setUserName("cd");
		dummyPojo.setPassword("cd");
		dummyPojo.setId(4);
		HandleGetOneCustomerImp bla = new HandleGetOneCustomerImp();
		try {
			Mockito.lenient().when(accountServiceImp.getOneAccount(dummyPojo, bla)).thenReturn(dummyPojo);
			
		} catch (ApplicationException e1) {
			e1.printStackTrace();
		}
		
		AccountPojo actualPojo = null;
		
		
		try {
			actualPojo = accountServiceImp.getOneAccount(dummyPojo,bla); 
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
		
		assertEquals(dummyPojo.getId(), actualPojo.getId());	
	}
	
	@Test
	public void testGetAllAccountWithMockito() {

		List<AccountPojo> pojos = new ArrayList<AccountPojo>();
		AccountPojo dummyPojo = new AccountPojo();
		dummyPojo.setUserName("cd");
		dummyPojo.setPassword("cd");
		dummyPojo.setId(4);
		pojos.add(dummyPojo);
		HandleGetOneCustomerImp bla = new HandleGetOneCustomerImp();
		try {
			Mockito.lenient().when(accountServiceImp.getAllAccount()).thenReturn(pojos);
			
		} catch (ApplicationException e1) {
			e1.printStackTrace();
		}
		
		List<AccountPojo> actualPojo = new ArrayList<AccountPojo>();
		
		
		try {
			actualPojo = accountServiceImp.getAllAccount(); 
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
		
		assertEquals(actualPojo.size(), pojos.size());	
	}
	
	
	@Test
	public void testGetAllTransactionsWithMockito() {

		List<TransactionPojo> pojos = new ArrayList<TransactionPojo>();
		AccountPojo dummyPojo = new AccountPojo();
		dummyPojo.setId(4);
		
		TransactionPojo transactionPojo = new TransactionPojo();
		transactionPojo.setAccountID(3);
		pojos.add(transactionPojo);
		
		
		try {
			Mockito.lenient().when(accountServiceImp.getTransactions(dummyPojo,1)).thenReturn(pojos);
			
		} catch (ApplicationException e1) {
			e1.printStackTrace();
		}
		
		List<TransactionPojo> actualPojos = new ArrayList<TransactionPojo>();
		
		
		try {
			actualPojos = accountServiceImp.getTransactions(dummyPojo,1); 
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
		
		assertEquals(actualPojos.size(), pojos.size());	
	}
	
	
	@Test
	public void testTransferToAccountWithMockito() {

		AccountPojo dummyPojo = new AccountPojo();
		dummyPojo.setUserName("cd");
		dummyPojo.setPassword("cd");
		dummyPojo.setId(4);
		AccountPojo secondPojo = new AccountPojo();
		secondPojo.setId(7);
		secondPojo.setUserName("abc");
		secondPojo.setPassword("abc");
		
		
		try {
			Mockito.lenient().when(accountServiceImp.transferToAccount(dummyPojo, secondPojo)).thenReturn(dummyPojo);
			
		} catch (ApplicationException e1) {
			e1.printStackTrace();
		}
		
		AccountPojo actualPojo = null;
		
		
		try {
			actualPojo = accountServiceImp.transferToAccount(dummyPojo,secondPojo); 
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
		
		assertEquals(dummyPojo.getId(), actualPojo.getId());	
	}
	
	
	
	

	
	
}
