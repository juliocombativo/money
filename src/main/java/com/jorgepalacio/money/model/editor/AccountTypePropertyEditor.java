package com.jorgepalacio.money.model.editor;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;

import com.jorgepalacio.money.model.AccountType;
import com.jorgepalacio.money.service.AccountTypeService;

public class AccountTypePropertyEditor extends PropertyEditorSupport {
	private AccountTypeService service;
	
	@Autowired
	public void setService(AccountTypeService service) {
		this.service = service;
	}
	
	public void setAsText(String text) throws IllegalArgumentException {
		AccountType type = service.getAccountType(Long.parseLong(text));
		setValue(type);
	}
}
