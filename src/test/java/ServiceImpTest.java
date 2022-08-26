import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import dao.HandleGetOneCustomerImp;
import exception.ApplicationException;
import pojo.AccountPojo;
import service.AccountService;
import service.AccountServiceImp;


@ExtendWith(MockitoExtension.class)
public class ServiceImpTest {

	@Mock
	AccountPojo accountPojo;
	
	
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

		AccountPojo dummyPojo;
		dummyPojo = Mockito.mock(AccountPojo.class);
		AccountPojo accountPojo = new AccountPojo();
		dummyPojo.setUserName("cd");
		dummyPojo.setPassword("cd");
		dummyPojo.setId(4);
		AccountService serviceImp = new AccountServiceImp();
		int expectedID = 4;
		try {
			//doReturn(fooBar).when(bar).getFoo()
			doReturn(dummyPojo).when(serviceImp.getOneAccount(dummyPojo, new HandleGetOneCustomerImp()));
			//when(serviceImp.getOneAccount(dummyPojo, new HandleGetOneCustomerImp())).thenReturn(dummyPojo);
			dummyPojo = serviceImp.getOneAccount(dummyPojo, new HandleGetOneCustomerImp());
			assertEquals(expectedID, dummyPojo.getId());
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			assertEquals(expectedID, -1);
		} catch (NullPointerException e) {
			assertEquals(expectedID, -1);
		}
	}
	
	public void testy() {
		
		
	}
	
	
	
	
	
	
	
	
	/*
	
	@Test
	public void testReturnNullGetOneAccountPojo() {

		AccountPojo accountPojo = new AccountPojo();
		accountPojo.setUserName("cde");
		accountPojo.setPassword("cd");
		AccountService serviceImp = new AccountServiceImp();
		try {
			accountPojo = serviceImp.getOneAccount(accountPojo, new HandleGetOneCustomerImp());
			assertEquals(null, accountPojo);
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			assertEquals(0, -1);
		} catch (NullPointerException e) {
			assertEquals(0, -1);
		}
	}
	
	@Test
	public void testCreateOneAccountPojo() {

		AccountPojo accountPojo = new AccountPojo();
		accountPojo.setUserName("testy");
		accountPojo.setPassword("testerson");
		accountPojo.setBalanceChangeAmount(500);
		accountPojo.setFirstName("testy");
		accountPojo.setLastName("testerson");
		
		AccountService serviceImp = new AccountServiceImp();
		try {
			accountPojo = serviceImp.createAccount(accountPojo);
			assertEquals(null, accountPojo);
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			assertEquals(0, -1);
		} catch (NullPointerException e) {
			assertEquals(0, -1);
		}
	}
	*/
	
	
	
}
