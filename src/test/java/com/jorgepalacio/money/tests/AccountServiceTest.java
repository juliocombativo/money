package com.jorgepalacio.money.tests;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;

import com.jorgepalacio.money.model.Account;
import com.jorgepalacio.money.service.AccountService;
import com.jorgepalacio.money.service.UserService;
import com.jorgepalacio.money.tests.utils.MapDaoTemplate;
import com.jorgepalacio.money.tests.utils.SetupConfiguration;

public class AccountServiceTest {
	private AccountService accountService = new AccountService();
	
	@Before
	public void setUp() {
		SetupConfiguration.load();
	}
	
	@Test
	public void add() {
		MapDaoTemplate<Account> dao = new MapDaoTemplate<Account>(Account.class);
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
