package com.jorgepalacio.money.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({
	@NamedQuery(name="account.balance", query="SELECT a FROM Account a WHERE a.client=?1 AND a.currentAmount > 0"),
	@NamedQuery(name="account.balance.asset", query="SELECT a FROM Account a WHERE a.client=?1 AND a.currentAmount <> 0 AND a.accountType.asset = TRUE"),
	@NamedQuery(name="account.balance.debt", query="SELECT a FROM Account a WHERE a.client=?1 AND a.currentAmount <> 0 AND a.accountType.asset = FALSE")
})
public class Account {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	@Column(nullable=false)
	private String name;
	
	@ManyToOne
	private User client;
	
	@ManyToOne
	private AccountType accountType;
	
	private double startAmount;
	
	private double currentAmount;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public AccountType getAccountType() {
		return accountType;
	}
	
	public void setAccountType(AccountType accountType) {
		this.accountType = accountType;
	}
	
	public User getClient() {
		return client;
	}
	
	public void setClient(User client) {
		this.client = client;
	}
	
	public void setStartAmount(double startAmount) {
		this.startAmount = startAmount;
	}
	
	public double getStartAmount() {
		return startAmount;
	}
	
	public void setCurrentAmount(double currentAmount) {
		this.currentAmount = currentAmount;
	}
	
	public double getCurrentAmount() {
		return currentAmount;
	}
}
