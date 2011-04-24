package com.jorgepalacio.money.tests.integration;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit38.AbstractTransactionalJUnit38SpringContextTests;

import com.jorgepalacio.money.model.User;
import com.jorgepalacio.money.service.AccountService;
import com.jorgepalacio.money.service.UserService;
import com.jorgepalacio.money.tests.utils.SamePersistentFields;

@ContextConfiguration(locations="/applicationContext.xml")
public class AccountServiceTest extends AbstractTransactionalJUnit38SpringContextTests {
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private UserService userService;
	
	@PersistenceContext
	private EntityManager manager;
	
	public void testAdd() throws Exception {
		assertThat(accountService, is(not(nullValue())));
		assertThat(userService, is(not(nullValue())));
		assertThat(manager, is(not(nullValue())));
		
		User newUser = new User();
		newUser.setLogin("user");newUser.setEmail("nowhere@mailinator.com");
		newUser.setFullName("User"); newUser.setPassword("password");
		userService.createUser(newUser);
		
		User recoveredUser = userService.byLogin("user");
		assertThat(recoveredUser, is(not(nullValue())));
		assertThat(recoveredUser, SamePersistentFields.hasSameFieldsThat(newUser));
		
		newUser.setId(0);
		newUser.setLogin("kuki"); newUser.setEmail("llamadragon@mailinator.com");
		userService.createUser(newUser);
	}
}
