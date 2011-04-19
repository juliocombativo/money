package com.jorgepalacio.money.model.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.jorgepalacio.money.model.MovementDetail;

public class MovementDetailValidator implements Validator {

	@SuppressWarnings("unchecked")
	public boolean supports(Class clazz) {
		return MovementDetail.class.isAssignableFrom(clazz);
	}

	public void validate(Object target, Errors errors) {
		MovementDetail detail = (MovementDetail) target;
		if(detail.getAccount() == null) {
			errors.rejectValue("account", "account.required", "Account is required.");
		}
		if(detail.getAmount() <= 0) {
			errors.rejectValue("amount", "amount.positive", "Amount must be a positive number.");
		}
	}

}
