package com.jorgepalacio.money.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@NamedQueries({
	@NamedQuery(name="movement.byUser", query="SELECT m, m.details FROM Movement m WHERE m.client=?1 ORDER BY m.date DESC")
})
public class Movement {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date date;

	@ManyToOne
	private User client;
	
	private boolean expense;
	
	private boolean validated;
	
	@OneToMany(targetEntity=MovementDetail.class, mappedBy="movement",cascade={CascadeType.PERSIST})
	private List<MovementDetail> details;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public User getClient() {
		return client;
	}

	public void setClient(User client) {
		this.client = client;
	}

	public boolean isExpense() {
		return expense;
	}

	public void setExpense(boolean expense) {
		this.expense = expense;
	}

	public boolean isValidated() {
		return validated;
	}

	public void setValidated(boolean validated) {
		this.validated = validated;
	}
	
	public List<MovementDetail> getDetails() {
		return details;
	}
	
	public void setDetails(List<MovementDetail> details) {
		this.details = details;
	}
}
