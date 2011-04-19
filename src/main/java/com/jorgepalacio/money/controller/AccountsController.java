package com.jorgepalacio.money.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;

import com.jorgepalacio.money.service.AccountService;

@Controller
public class AccountsController {
	private AccountService accountService;
	
	@RequestMapping("/accounts/mine")
	public String myAccounts(Model model, WebRequest request) {
		model.addAttribute("accounts", accountService.byUser(request.getUserPrincipal()));
		return "myAccounts";
	}
	
	@RequestMapping("/balance")
	public String addAccount(Model model) {
		model.addAttribute("assets", accountService.assets());
		model.addAttribute("debts", accountService.debts());
		return "balance";
	}
	
	@Autowired
	public void setAccountService(AccountService accountService) {
		this.accountService = accountService;
	}
}
