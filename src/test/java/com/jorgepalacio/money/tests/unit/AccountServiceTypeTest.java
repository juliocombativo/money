package com.jorgepalacio.money.tests.unit;

import static com.jorgepalacio.money.tests.utils.SamePersistentFields.hasSameFieldsThat;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import junit.framework.TestCase;

import com.jorgepalacio.money.model.AccountType;
import com.jorgepalacio.money.service.AccountTypeService;
import com.jorgepalacio.money.tests.utils.MapDaoTemplate;
import com.jorgepalacio.money.tests.utils.SetupConfiguration;

public class AccountServiceTypeTest extends TestCase {
	private AccountTypeService accountService = new AccountTypeService();
	
	public void setUp() {
		SetupConfiguration.load();
	}
	
	public void testAdd() {
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
