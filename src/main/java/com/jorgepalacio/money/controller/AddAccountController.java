package com.jorgepalacio.money.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.PropertyEditorRegistrar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.mvc.SimpleFormController;

import com.jorgepalacio.money.model.Account;
import com.jorgepalacio.money.model.validation.AccountValidator;
import com.jorgepalacio.money.service.AccountService;
import com.jorgepalacio.money.service.AccountTypeService;

public class AddAccountController extends SimpleFormController {
	private AccountTypeService accountTypeService;
	private AccountService accountService;
	private PropertyEditorRegistrar registrar;
	
	public AddAccountController() {
		setValidator(new AccountValidator());
	}
	
	@Override
	protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
		registrar.registerCustomEditors(binder);
	}
	
	@Autowired
	public void setRegistrar(PropertyEditorRegistrar registrar) {
		this.registrar = registrar;
	}
	
	@Autowired
	public void setAccountTypeService(AccountTypeService accountTypeService) {
		this.accountTypeService = accountTypeService;
	}
	
	@Autowired
	public void setAccountService(AccountService accountService) {
		this.accountService = accountService;
	}
	
	@SuppressWarnings("unchecked")
	protected Map referenceData(HttpServletRequest request, Object command, Errors errors) throws Exception {
		LinkedHashMap<String, Object> data = new LinkedHashMap<String, Object>();
		
		data.put("types", accountTypeService.all());
		
		return data;
	}
	
	@Override
	protected void doSubmitAction(Object command) throws Exception {
		accountService.createAccount((Account) command);
	}
}
