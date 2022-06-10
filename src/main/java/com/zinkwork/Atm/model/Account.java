package com.zinkwork.Atm.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tb_account")
public class Account implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long number;
	private Long pin;
	private Double balance;
	private Double overdraft;

	@OneToMany(mappedBy = "account")
	private List<Transaction> transactions = new ArrayList<>();

	public Account() {
	}

	public Account(Long id, Long number, Long pin, Double balance, Double overdraft) {
		super();
		this.id = id;
		this.number = number;
		this.pin = pin;
		this.balance = balance;
		this.overdraft = overdraft;
	}

	public Long getPin() {
		return pin;
	}

	public void setPin(Long pin) {
		this.pin = pin;
	}

	public Double getOverdraft() {
		return overdraft;
	}

	public void setOverdraft(Double overdraft) {
		this.overdraft = overdraft;
	}

	public Long getId() {
		return id;
	}

	public Long getNumber() {
		return number;
	}

	public Double getBalance() {
		return balance;
	}

	public void updateBalance(Double value) {
		balance += value;
	}

	public List<Transaction> getTransactions() {
		return transactions;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Account other = (Account) obj;
		return Objects.equals(id, other.id);
	}

}
