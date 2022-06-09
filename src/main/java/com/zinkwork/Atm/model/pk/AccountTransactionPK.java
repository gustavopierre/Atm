package com.zinkwork.Atm.model.pk;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.zinkwork.Atm.model.Account;
import com.zinkwork.Atm.model.AtmMachine;

@Embeddable
public class AccountTransactionPK  implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@ManyToOne
	@JoinColumn(name = "account_id")
	private Account account;
	
	@ManyToOne
	@JoinColumn(name = "atmmachine_id")
	private AtmMachine atmMachine;
	
	public Account getAccount() {
		return account;
	}
	public void setAccount(Account account) {
		this.account = account;
	}
	public AtmMachine getAtmMachine() {
		return atmMachine;
	}
	public void setAtmMachine(AtmMachine atmMachine) {
		this.atmMachine = atmMachine;
	}
	@Override
	public int hashCode() {
		return Objects.hash(account, atmMachine);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AccountTransactionPK other = (AccountTransactionPK) obj;
		return Objects.equals(account, other.account) && Objects.equals(atmMachine, other.atmMachine);
	}
	
	
}
