import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.nullable;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

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
import exception.ApplicationException;
import pojo.AccountPojo;
import service.AccountService;
import service.AccountServiceImp;


@ExtendWith(MockitoExtension.class)
public class ServiceImpTest {

	@InjectMocks
	AccountServiceImp accountServiceImp;

	@Mock
	DatabaseManager accountDao;
	@Test
	public void testGetOneAccountPojo() {

		AccountPojo accountPojo = new AccountPojo();
		accountPojo.setUserName("cd");
		accountPojo.setPassword("cd");
		AccountService serviceImp = new AccountServiceImp();
		int expectedID = 4;
		try {
			accountPojo = serviceImp.getOneAccount(accountPojo, new HandleGetOneCustomerImp());
			assertEquals(expectedID, accountPojo.getId());
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			assertEquals(expectedID, -1);
		} catch (NullPointerException e) {
			assertEquals(expectedID, -1);
		}
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
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		AccountPojo actualPojo = null;
		
		
		try {
			// the bookService declared at class level is used here
			actualPojo = accountServiceImp.getOneAccount(dummyPojo,bla); // here when i call a methodof the service layer, it in turn call a method of dao
														// according to unit testing the method that we are testing should be tested in isolation
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertEquals(dummyPojo.getId(), actualPojo.getId());	
	}
	

	
	
}
