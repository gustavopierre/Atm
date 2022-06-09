package com.zinkwork.Atm.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tb_atmmachine")
public class AtmMachine implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Integer quantityNote50;
	private Integer quantityNote20;
	private Integer quantityNote10;
	private Integer quantityNote5;

	public AtmMachine() {
	}

	public AtmMachine(Long id, Integer quantityNote50, Integer quantityNote20, Integer quantityNote10,
			Integer quantityNote5) {
		super();
		this.id = id;
		this.quantityNote50 = quantityNote50;
		this.quantityNote20 = quantityNote20;
		this.quantityNote10 = quantityNote10;
		this.quantityNote5 = quantityNote5;
	}

	public Long getId() {
		return id;
	}

	public Integer getQuantityNote50() {
		return quantityNote50;
	}

	public void updateQuantityNote50(Integer quantityNote50) {
		this.quantityNote50 += quantityNote50;
	}

	public Integer getQuantityNote20() {
		return quantityNote20;
	}

	public void updateQuantityNote20(Integer quantityNote20) {
		this.quantityNote20 += quantityNote20;
	}

	public Integer getQuantityNote10() {
		return quantityNote10;
	}

	public void updateQuantityNote10(Integer quantityNote10) {
		this.quantityNote10 += quantityNote10;
	}

	public Integer getQuantityNote5() {
		return quantityNote5;
	}

	public void updateQuantityNote5(Integer quantityNote5) {
		this.quantityNote5 += quantityNote5;
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
		AtmMachine other = (AtmMachine) obj;
		return Objects.equals(id, other.id);
	}

	public Double total() {
		return quantityNote5 * 5.0 + quantityNote10 * 10.0 + quantityNote20 * 20.0 + quantityNote50 * 50.0;
	}

}
