package com.jorgepalacio.money.model.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.jorgepalacio.money.model.User;

public class UserValidator implements Validator {

	@SuppressWarnings("unchecked")
	public boolean supports(Class clazz) {
		return User.class.isAssignableFrom(clazz);
	}

	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "fullName", "fullName.required", "Fullname is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "email.required", "email is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "login", "login.required", "Login is required.");
		
		User user = (User) target;
		if(user.getPassword() != null || user.getRepeatPassword() != null) {
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "password.required", "Password is required.");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "repeatPassword", "repeatPassword.required", "Please retype the user password.");
			
			if((user.getPassword() != null && !user.getPassword().equals(user.getRepeatPassword())) ||
			   (user.getRepeatPassword() != null && ! user.getRepeatPassword().equals(user.getPassword()))) {
				errors.reject("passwords.differ", "Passwords must match.");
			}
		}
	}

}
