package com.jorgepalacio.money.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;

import com.jorgepalacio.money.model.User;
import com.jorgepalacio.money.model.validation.UserEditValidator;
import com.jorgepalacio.money.model.validation.UserValidator;
import com.jorgepalacio.money.service.UserService;

@Controller
public class UserController {
	private UserService service;
	
	@RequestMapping("/index")
	public String home() {
		return "redirect:/accounts/mine";
	}
	
	@Autowired
	public void setService(UserService service) {
		this.service = service;
	}
	
	@RequestMapping("/user/plain_registration")
	public String crudeRegistration(User user) {
		return "normalRegister";
	}
	
	@RequestMapping("/users")
	public String myAccounts(Model model) {
		model.addAttribute("users", service.all());
		return "userList";
	}
	
	@RequestMapping("/user/add")
	public String addAccountType(Model model, User user, BindingResult result) {
		new UserValidator().validate(user, result);
		if(!result.hasErrors()) {
			service.createUser(user);
			return "redirect:/users.html";
		}
		
		model.addAttribute("command", user);
		return "addUser";
	}
	
	@RequestMapping("/user/edit/*")
	public String editAccountType(Model model, User account, BindingResult result, WebRequest request) {
		String requestUri = request.getDescription(false);
		Long id = Long.parseLong(requestUri.substring(requestUri.lastIndexOf('/') + 1).replaceAll(".html", ""));
		
		User original = service.getUser(id);
		if(original == null) {
			return "redirect:/users.html";
		}
		
		new UserEditValidator().validate(account, result);
		if(!result.hasErrors()) {
			service.updateUser(account);
			return "redirect:/users.html";
		}
		
		model.addAttribute("command", original);
		return "editUser";
	}
}
