package com.jorgepalacio.money.model.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.jorgepalacio.money.model.AccountType;

public class AccountTypeValidator implements Validator {

	@SuppressWarnings("unchecked")
	public boolean supports(Class clazz) {
		return AccountType.class.isAssignableFrom(clazz);
	}

	public void validate(Object target, Errors errors) {
		AccountType accountType = (AccountType) target;
		if(accountType.getName() == null || accountType.getName().trim().length() == 0) {
			errors.rejectValue("name", "name.required", "Name is required.");
		}
	}

}
