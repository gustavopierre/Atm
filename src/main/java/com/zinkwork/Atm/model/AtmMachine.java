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

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "tb_atmmachine")
public class AtmMachine implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Integer quantityNote50;
	private Integer quantityNote20;
	private Integer quantityNote10;
	private Integer quantityNote5;

	@JsonIgnore
	@OneToMany(mappedBy = "atmMachine")
	private List<Transaction> transactions = new ArrayList<>();

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

	public List<Transaction> getTransactions() {
		return transactions;
	}

	public void setQuantityNote50(Integer quantityNote50) {
		this.quantityNote50 = quantityNote50;
	}

	public void setQuantityNote20(Integer quantityNote20) {
		this.quantityNote20 = quantityNote20;
	}

	public void setQuantityNote10(Integer quantityNote10) {
		this.quantityNote10 = quantityNote10;
	}

	public void setQuantityNote5(Integer quantityNote5) {
		this.quantityNote5 = quantityNote5;
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

	public Double getTotal() {
		return quantityNote5 * 5.0 + quantityNote10 * 10.0 + quantityNote20 * 20.0 + quantityNote50 * 50.0;
	}

	public boolean checkValueATM(Double value) {
		Double remaining = value;
		int quantity;
		int notes[][] = {{50, quantityNote50}, {20, quantityNote20}, {10, quantityNote10}, {5, quantityNote5}};
		
		if (remaining <= getTotal()){
			for(int i = 0; i < 4; i++) {
				quantity = (int)(remaining/notes[i][0]);
				if (quantity > notes[i][1]) {
					quantity = notes[i][1];
				}
				remaining -= quantity * notes[i][0];
			}
			
			if (remaining == 0) {
				return true;
			}
		}
		return false;
	}
	
	public int[][] quantityNotes(double value){
		int quantity[][] = {{50, 0}, {20, 0}, {10, 0}, {5, 0}};
		int notes[][] = {{50, quantityNote50}, {20, quantityNote20}, {10, quantityNote10}, {5, quantityNote5}};
		double remaining = value;
		
		for(int i = 0; i < 4; i++) {
			quantity[i][1] = (int)(remaining/quantity[i][0]);
			if (quantity[i][1] > notes[i][1]) {
				quantity[i][1] = notes[i][1];
			}
			remaining -= quantity[i][1] * quantity[i][0];
		}
			
		return quantity;
	}
	
}
