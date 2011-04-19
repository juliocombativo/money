package com.jorgepalacio.money.tests.integration;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.jorgepalacio.money.service.AccountService;
import com.jorgepalacio.money.service.AccountTypeService;
import com.jorgepalacio.money.service.MovementService;
import com.jorgepalacio.money.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/applicationContext.xml"})
public class ServiceInjectionTest {
	@Autowired
	AccountService accountService;
	
	@Autowired
	AccountTypeService accountTypeService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	MovementService movementService;
	
	@Test
	public void accountService() {
		Assert.assertThat(accountService, CoreMatchers.notNullValue());
	}
	
	@Test
	public void accountTypeService() {
		Assert.assertThat(accountTypeService, CoreMatchers.notNullValue());
	}
	
	@Test
	public void userService() {
		Assert.assertThat(userService, CoreMatchers.notNullValue());
	}
	
	@Test
	public void movementService() {
		Assert.assertThat(movementService, CoreMatchers.notNullValue());
	}
}
