package com.jorgepalacio.money.model.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.jorgepalacio.money.model.Movement;
import com.jorgepalacio.money.model.MovementDetail;

public class MovementValidator implements Validator {

	@SuppressWarnings("unchecked")
	public boolean supports(Class clazz) {
		return Movement.class.isAssignableFrom(clazz);
	}

	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmpty(errors, "date", "date.required", "Date is required.");
		ValidationUtils.rejectIfEmpty(errors, "details", "details.required", "At least one account is required.");
		
		Movement movement = (Movement) target;
		for(MovementDetail detail : movement.getDetails()) {
			ValidationUtils.invokeValidator(new MovementDetailValidator(), detail, errors);
		}
	}

}
