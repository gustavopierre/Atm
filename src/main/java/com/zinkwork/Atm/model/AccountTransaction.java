package com.zinkwork.Atm.model;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.zinkwork.Atm.model.pk.AccountTransactionPK;

@Entity
@Table(name = "tb_account_transaction")
public class AccountTransaction implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private AccountTransactionPK id = new AccountTransactionPK();

	private Instant date;
	private Double value;

	public AccountTransaction() {
	}

	public AccountTransaction(Account account, AtmMachine atmMachine, Double value) {
		super();
		id.setAccount(account);
		id.setAtmMachine(atmMachine);
		this.date = Instant.now();
		this.value = value;
	}

	public Account getAccount() {
		return id.getAccount();
	}

	public void setAccount(Account account) {
		id.setAccount(account);
	}

	public AtmMachine getAtmMachine() {
		return id.getAtmMachine();
	}

	public void setAtmMachine(AtmMachine atmMachine) {
		id.setAtmMachine(atmMachine);
	}

	public Instant getInstant() {
		return date;
	}

	/*public void setInstant(Instant date) {
		this.date = date;
	}*/

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
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
		AccountTransaction other = (AccountTransaction) obj;
		return Objects.equals(id, other.id);
	}

}
