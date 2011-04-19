package com.jorgepalacio.money.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Transactional;

import com.jorgepalacio.money.dao.impl.JpaDaoTemplate;
import com.jorgepalacio.money.model.AccountType;

public class AccountTypeService {
	JpaDaoTemplate<AccountType> accountTypeDao;

	@Autowired
	public void setAccountTypeDao(
			@Qualifier("accountTypeDao") JpaDaoTemplate<AccountType> accountTypeDao) {
		this.accountTypeDao = accountTypeDao;
	}

	@Transactional
	public void createAccountType(final AccountType account) {
		accountTypeDao.add(account);
	}

	public List<AccountType> all() {
		return accountTypeDao.all();
	}

	public AccountType getAccountType(Long id) {
		return accountTypeDao.byId(id);
	}

	@Transactional
	public void updateAccountType(final AccountType account) {
		AccountType type = getAccountType(account.getId());
		type.setAsset(account.isAsset());
		type.setName(account.getName());

		accountTypeDao.edit(type);
	}
}
