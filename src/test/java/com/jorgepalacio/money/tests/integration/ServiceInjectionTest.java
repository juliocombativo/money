package com.jorgepalacio.money.tests.integration;

import static org.hamcrest.MatcherAssert.assertThat;

import org.hamcrest.CoreMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit38.AbstractTransactionalJUnit38SpringContextTests;

import com.jorgepalacio.money.service.AccountService;
import com.jorgepalacio.money.service.AccountTypeService;
import com.jorgepalacio.money.service.MovementService;
import com.jorgepalacio.money.service.UserService;

@ContextConfiguration(locations="/applicationContext.xml")
public class ServiceInjectionTest extends AbstractTransactionalJUnit38SpringContextTests {
	@Autowired
	AccountService accountService;
	
	@Autowired
	AccountTypeService accountTypeService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	MovementService movementService;
	
	public void testAccountService() {
		assertThat(accountService, CoreMatchers.notNullValue());
	}
	
	public void testAccountTypeService() {
		assertThat(accountTypeService, CoreMatchers.notNullValue());
	}
	
	public void testUserService() {
		assertThat(userService, CoreMatchers.notNullValue());
	}
	
	public void testMovementService() {
		assertThat(movementService, CoreMatchers.notNullValue());
	}
}
