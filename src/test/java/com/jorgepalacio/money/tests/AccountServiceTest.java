package com.jorgepalacio.money.tests;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;

import com.jorgepalacio.money.dao.impl.JpaDaoTemplate;
import com.jorgepalacio.money.model.Account;
import com.jorgepalacio.money.service.AccountService;
import com.jorgepalacio.money.service.UserService;
import com.jorgepalacio.money.tests.utils.SetupConfiguration;

public class AccountServiceTest {
	private AccountService accountService = new AccountService();
	
	@Before
	public void setUp() {
		SetupConfiguration.load();
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void add() {
		JpaDaoTemplate<Account> dao = mock(JpaDaoTemplate.class);
		doNothing().when(dao).add(Matchers.any(Account.class));
		accountService.setAccountDao(dao);
		
		UserService user = mock(UserService.class);
		accountService.setUserService(user);
		
		accountService.createAccount(new Account());
		verify(dao, times(1)).add(Matchers.any(Account.class));
		verify(user, times(1)).getCurrentUser();
	}
}
