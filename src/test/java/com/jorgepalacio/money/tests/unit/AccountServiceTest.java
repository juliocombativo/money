package com.jorgepalacio.money.tests.unit;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import junit.framework.TestCase;

import org.mockito.Matchers;
import org.mockito.Mockito;

import com.jorgepalacio.money.model.Account;
import com.jorgepalacio.money.service.AccountService;
import com.jorgepalacio.money.service.UserService;
import com.jorgepalacio.money.tests.utils.MapDaoTemplate;

public class AccountServiceTest extends TestCase {
	private AccountService accountService = new AccountService();
	
	@SuppressWarnings("unchecked")
	public void testAdd() {
		MapDaoTemplate<Account> dao = mock(MapDaoTemplate.class);
		accountService.setAccountDao(dao);
		
		UserService user = mock(UserService.class);
		doReturn(null).when(user).getCurrentUser();
		accountService.setUserService(user);
		
		Account account = new Account();
		account.setName("Juan Lopez");
		account.setAccountType(null);
		accountService.createAccount(new Account());
		verify(dao, times(1)).add(Matchers.any(Account.class));
		verify(user, times(1)).getCurrentUser();
	}
}
