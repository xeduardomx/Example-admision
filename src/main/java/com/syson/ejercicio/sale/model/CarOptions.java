package com.syson.ejercicio.sale.model;

/**
 *
 * @author Eduardo Mendoza
 */
public abstract class CarOptions implements Icar{
	private Icar car;

	public CarOptions(Icar car){
		setCar(car);
    }

	public Icar getCar() {
		return car;
	}

	private void setCar(Icar car) {
		this.car = car;
	}
}
