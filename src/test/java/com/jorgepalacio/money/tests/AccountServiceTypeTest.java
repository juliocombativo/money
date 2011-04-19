package com.jorgepalacio.money.tests;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import static org.junit.Assert.assertThat;
import static com.jorgepalacio.money.tests.utils.SamePersistentFields.hasSameFieldsThat;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;

import com.jorgepalacio.money.model.AccountType;
import com.jorgepalacio.money.service.AccountTypeService;
import com.jorgepalacio.money.tests.utils.MapDaoTemplate;
import com.jorgepalacio.money.tests.utils.SetupConfiguration;

public class AccountServiceTypeTest {
	private AccountTypeService accountService = new AccountTypeService();
	
	@Before
	public void setUp() {
		SetupConfiguration.load();
	}
	
	@Test
	public void add() {
		MapDaoTemplate<AccountType> dao = new MapDaoTemplate<AccountType>(AccountType.class);
		accountService.setAccountTypeDao(dao);
		
		AccountType type = new AccountType();
		type.setName("Credit Card");
		type.setAsset(false);
		
		accountService.createAccountType(type);
		assertThat(type.getId(), is(not((long) 0)));
		
		AccountType newType = dao.byId(type.getId());
		assertThat(newType, is(not(nullValue())));
		assertThat(newType, hasSameFieldsThat(type));
	}
}
