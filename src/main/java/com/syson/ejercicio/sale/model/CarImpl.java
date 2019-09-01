package com.syson.ejercicio.sale.model;

import com.syson.ejercicio.dao.Car;

public class CarImpl  implements Icar{
	private Car car;

	public CarImpl(Car car) {
        this.car=car;
    }

	@Override
	public double getPrice() {
		return car.getBaseCost();
	}
}
