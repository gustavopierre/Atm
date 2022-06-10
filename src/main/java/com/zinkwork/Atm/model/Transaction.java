package com.zinkwork.Atm.model;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "tb_transaction")
public class Transaction implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
	private Instant momment;
	private Double value;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "atmmachine_id")
	private AtmMachine atmMachine;

	@ManyToOne
	@JoinColumn(name = "account_id")
	private Account account;

	public Transaction() {
	}

	public Transaction(Long id, Double value, AtmMachine atmMachine, Account account) {
		super();
		this.id = id;
		this.momment = Instant.now();
		this.value = value;
		this.atmMachine = atmMachine;
		this.account = account;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Instant getMomment() {
		return momment;
	}

	public void setMomment(Instant momment) {
		this.momment = momment;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	public AtmMachine getAtmMachine() {
		return atmMachine;
	}

	public void setAtmMachine(AtmMachine atmMachine) {
		this.atmMachine = atmMachine;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
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
		Transaction other = (Transaction) obj;
		return Objects.equals(id, other.id);
	}

}
