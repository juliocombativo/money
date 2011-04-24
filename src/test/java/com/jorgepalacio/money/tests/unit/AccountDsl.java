/**
 * 
 */
package com.jorgepalacio.money.tests.unit;

import com.jorgepalacio.money.model.Account;
import com.jorgepalacio.money.model.AccountType;
import com.jorgepalacio.money.model.User;

class AccountDsl {
	Account account = new Account();
	
	public AccountDsl() {
		account.setName("Account");
		account.setId(1);
		account.setCurrentAmount(100.0);
		account.setStartAmount(100.0);
		
		AccountType type = new AccountType();
		type.setName("Account Type");
		type.setAsset(true);
		type.setId(1);
		
		account.setAccountType(type);
		
		User user = new User();
		account.setClient(user);
	}
	
	public AccountDsl withName(String name) {
		account.setName(name); return this;
	}
	
	public AccountDsl withId(long id) {
		account.setId(id); return this;
	}
	
	public AccountDsl withCurrentAmount(long amount) {
		account.setCurrentAmount(amount); return this;
	}
	
	public AccountDsl withStartAmount(long amount) {
		account.setStartAmount(amount); return this;
	}
	
	public AccountDsl withType(AccountType type) {
		account.setAccountType(type); return this;
	}
	
	public AccountDsl withClient(User user) {
		account.setClient(user); return this;
	}
	
	public Account get() {
		return account;
	}
}