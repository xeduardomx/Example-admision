package com.syson.ejercicio.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "autos")
public class Auto {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	 
	@Column(name = "descripcion", nullable = false)
	private String descripcion;
	 
	@Column(name = "costo_base", nullable = false)
	private double costoBase;


	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public double getCostoBase() {
		return costoBase;
	}

	public void setCostoBase(double costoBase) {
		this.costoBase = costoBase;
	}

	
	
}
