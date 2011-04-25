package com.jorgepalacio.money.model.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.jorgepalacio.money.model.Account;

public class AccountValidator implements Validator {
	@SuppressWarnings("unchecked")
	public boolean supports(Class clazz) {
		if(clazz == null)
			return false;
		return Account.class.isAssignableFrom(clazz);
	}
	
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmpty(errors, "name", "name.required", "Name is required.");
		ValidationUtils.rejectIfEmpty(errors, "accountType", "accountType.required", "Account type is required.");
		
		Account account = (Account) target;
		if(account.getStartAmount() < 0) {
			errors.rejectValue("startAmount", "startAmount.negative", "Start amount must be a non negative value");
		}
	}
}
