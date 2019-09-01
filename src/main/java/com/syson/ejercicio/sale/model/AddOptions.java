package com.syson.ejercicio.sale.model;

import java.util.ArrayList;

import com.syson.ejercicio.dao.Option;

public class AddOptions extends CarOptions {

	private double price = 0;
	ArrayList<Option> listOptions = new ArrayList<Option>();

	public AddOptions(Icar car, ArrayList<Option> listOptions) {
		super(car);
		this.listOptions = listOptions;
	}

	@Override
	public double getPrice() {
		listOptions.forEach((n) -> price = price + n.getPriceOption());

		return getCar().getPrice() + price;
	}
}
