package com.jorgepalacio.money.controller;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.FactoryUtils;
import org.apache.commons.collections.list.LazyList;
import org.springframework.beans.PropertyEditorRegistrar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.mvc.SimpleFormController;

import com.jorgepalacio.money.model.Movement;
import com.jorgepalacio.money.model.MovementDetail;
import com.jorgepalacio.money.model.validation.MovementValidator;
import com.jorgepalacio.money.service.AccountService;
import com.jorgepalacio.money.service.MovementService;

public class RegisterController extends SimpleFormController {
	private AccountService accountService;
	private MovementService movementService;
	private PropertyEditorRegistrar registrar;
	
	public RegisterController() {
		setValidator(new MovementValidator());
	}
	
	@Override
	protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
		registrar.registerCustomEditors(binder);
	}
	
	@Autowired
	public void setMovementService(MovementService movementService) {
		this.movementService = movementService;
	}
	
	@Autowired
	public void setRegistrar(PropertyEditorRegistrar registrar) {
		this.registrar = registrar;
	}
	
	@Autowired
	public void setAccountService(AccountService accountService) {
		this.accountService = accountService;
	}
	
	@SuppressWarnings("unchecked")
	protected Object formBackingObject(HttpServletRequest request) throws Exception {
		Movement movement = (Movement) super.formBackingObject(request);
		
		if(movement.getDetails() == null) {
			movement.setDetails(LazyList.decorate(
					new ArrayList<MovementDetail>(), FactoryUtils.instantiateFactory(MovementDetail.class)));
			movement.getDetails().add(new MovementDetail());
		} else {
			movement.setDetails(LazyList.decorate(
					movement.getDetails(), FactoryUtils.instantiateFactory(MovementDetail.class)));
		}
		return movement;
	}
	
	@SuppressWarnings("unchecked")
	protected Map referenceData(HttpServletRequest request, Object command, Errors errors) throws Exception {
		LinkedHashMap<String, Object> data = new LinkedHashMap<String, Object>();
		
		data.put("lastMovements", movementService.lastMovements());
		data.put("accounts", accountService.all());
		
		return data;
	}
	
	@Override
	protected void doSubmitAction(Object command) throws Exception {
		movementService.registerMovement((Movement) command);
	}
}
