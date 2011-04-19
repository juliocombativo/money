package com.jorgepalacio.money.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;

import com.jorgepalacio.money.model.AccountType;
import com.jorgepalacio.money.model.validation.AccountTypeValidator;
import com.jorgepalacio.money.service.AccountTypeService;

@Controller
public class AccountTypeController {
	private AccountTypeService service;
	
	@RequestMapping("/accounttype")
	public String myAccounts(Model model) {
		model.addAttribute("types", service.all());
		return "accountTypes";
	}
	
	@RequestMapping("/accounttype/add")
	public String addAccountType(Model model, AccountType account, BindingResult result) {
		new AccountTypeValidator().validate(account, result);
		if(!result.hasErrors()) {
			service.createAccountType(account);
			return "redirect:/accounttype.html";
		}
		
		model.addAttribute("command", account);
		return "addAccountType";
	}
	
	@RequestMapping("/accounttype/edit/*")
	public String editAccountType(Model model, AccountType account, BindingResult result, WebRequest request) {
		String requestUri = request.getDescription(false);
		Long id = Long.parseLong(requestUri.substring(requestUri.lastIndexOf('/') + 1).replaceAll(".html", ""));
		
		AccountType original = service.getAccountType(id);
		if(original == null) {
			return "redirect:/accounttype.html";
		}
		
		new AccountTypeValidator().validate(account, result);
		if(!result.hasErrors()) {
			service.updateAccountType(account);
			return "redirect:/accounttype.html";
		}
		
		model.addAttribute("command", original);
		return "editAccountType";
	}
	
	@Autowired
	public void setService(AccountTypeService service) {
		this.service = service;
	}
}
