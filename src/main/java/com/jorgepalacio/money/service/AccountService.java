package com.jorgepalacio.money.service;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.jorgepalacio.money.dao.impl.JpaDaoTemplate;
import com.jorgepalacio.money.model.Account;

public class AccountService {
	private UserService userService;
	JpaDaoTemplate<Account> accountDao;

	@Autowired
	public void setAccountDao(
			@Qualifier("accountDao") JpaDaoTemplate<Account> accountDao) {
		this.accountDao = accountDao;
	}

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@Transactional
	public void createAccount(final Account account) {
		account.setClient(userService.getCurrentUser());
		account.setCurrentAmount(account.getStartAmount());

		accountDao.add(account);
	}

	public List<Account> all() {
		return accountDao.all();
	}

	public List<Account> byUser(Principal userPrincipal) {
		String login = userPrincipal.getName();
		return accountDao.byParams(
				"SELECT a FROM Account a WHERE a.client.login=?1", login);
	}

	public List<Account> assets() {
		return accountDao.byNamedQuery("account.balance.asset", userService
				.getCurrentUser());
	}

	public List<Account> debts() {
		return accountDao.byNamedQuery("account.balance.debt", userService
				.getCurrentUser());
	}

	public Account account(long id) {
		return accountDao.byId(id);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void save(final Account account) {
		accountDao.edit(account);
	}
}
