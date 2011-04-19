package com.jorgepalacio.money.model.editor;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;

import com.jorgepalacio.money.model.Account;
import com.jorgepalacio.money.service.AccountService;

public class AccountPropertyEditor extends PropertyEditorSupport {
	private AccountService service;
	
	@Autowired
	public void setService(AccountService service) {
		this.service = service;
	}
	
	public void setAsText(String text) throws IllegalArgumentException {
		Account account = service.account(Long.parseLong(text));
		setValue(account);
	}
}
