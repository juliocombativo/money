package com.jorgepalacio.money.tests.unit;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import junit.framework.TestCase;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Validator;

import com.jorgepalacio.money.model.Account;
import com.jorgepalacio.money.model.validation.AccountValidator;

public class AccountValidatorTest extends TestCase {
	public void testValidTypes() {
		AccountValidator validator = new AccountValidator();
		assertThat(validator.supports(null), is(false));
		assertThat(validator.supports(Object.class), is(false));
		assertThat(validator.supports(Account.class), is(true));
	}
	
	public void testValidator() {
		AccountValidator validator = new AccountValidator();
		
		doValidate(validator, accountType().get());
		
		doValidate(validator, accountType().withType(null).get(), "accountType.required");
		doValidate(validator, accountType().withClient(null).get(), "client.required");
		doValidate(validator, accountType().withName(null).get(), "name.required");
		doValidate(validator, accountType().withName("").get(), "name.required");
		
		doValidate(validator, accountType().withStartAmount(0).get());
		doValidate(validator, accountType().withStartAmount(100).get());
		doValidate(validator, accountType().withStartAmount(-1).get(), "startAmount.negative");
	}
	
	private AccountDsl accountType() {
		return new AccountDsl();
	}
	
	private void doValidate(Validator validator, Object o, String... expectedErrors) {
		BeanPropertyBindingResult errors = new BeanPropertyBindingResult(o, "validator");
		validator.validate(o, errors);
		
		if(expectedErrors.length > 0) {
			assertThat(errors.hasErrors(), is(true));
		} else {
			assertThat(errors.hasErrors(), is(false));
		}
		
		for(String error : expectedErrors) {
			boolean found = false;
			
			for(Object errorObject : errors.getAllErrors()) {
				DefaultMessageSourceResolvable errorMessage  = (DefaultMessageSourceResolvable) errorObject;
				if(errorMessage.getCode().equalsIgnoreCase(error)) {
					found = true;
				}
			}
			
			assertThat(found, is(true));
		}
	}
}
