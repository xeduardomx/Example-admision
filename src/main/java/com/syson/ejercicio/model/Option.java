package com.syson.ejercicio.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "option",
		uniqueConstraints = {
	        @UniqueConstraint(columnNames = "description"),
            @UniqueConstraint(columnNames = "short_cut")
		}
)
public class Option {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	 
	@Column(name = "description", nullable = false)
	private String description;
	 
	@Column(name = "price_option", nullable = false)
	private double priceOption;

	@Column(name = "short_cut", nullable = false, length = 3)
	private String shortCut;

	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPriceOption() {
		return priceOption;
	}

	public void setPriceOption(double priceOption) {
		this.priceOption = priceOption;
	}

	public String getShortCut() {
		return shortCut;
	}

	public void setShortCut(String shortCut) {
		this.shortCut = shortCut;
	}
	
}
