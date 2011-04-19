package com.jorgepalacio.money.model.editor;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.PropertyEditorRegistrar;
import org.springframework.beans.PropertyEditorRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;

import com.jorgepalacio.money.model.Account;
import com.jorgepalacio.money.model.AccountType;

public class CustomPropertyEditorRegistrar implements PropertyEditorRegistrar {
	private AccountTypePropertyEditor accountTypeEditor;
	private AccountPropertyEditor accountEditor;
	
	@Autowired
	public void setAccountTypeEditor(AccountTypePropertyEditor accountTypeEditor) {
		this.accountTypeEditor = accountTypeEditor;
	}
	
	@Autowired
	public void setAccountEditor(AccountPropertyEditor accountEditor) {
		this.accountEditor = accountEditor;
	}
	
	public void registerCustomEditors(PropertyEditorRegistry registry) {
		registry.registerCustomEditor(AccountType.class, accountTypeEditor);
		registry.registerCustomEditor(Account.class, accountEditor);
		registry.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("dd/MM/yyyy"), false));
	}
}
