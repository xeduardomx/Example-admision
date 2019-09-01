package com.syson.ejercicio.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.syson.ejercicio.dao.Car;
import com.syson.ejercicio.dao.Option;
import com.syson.ejercicio.exception.ServiceException;
import com.syson.ejercicio.repository.CarRepository;
import com.syson.ejercicio.sale.model.AddOptions;
import com.syson.ejercicio.sale.model.CarImpl;
import com.syson.ejercicio.sale.model.Icar;

@Service
public class SaleService {
	@Autowired
	private CarRepository carRepository;
	public double calculateTotalCarPrice(Car car, ArrayList<Option> ListOptions) throws ServiceException {
		Icar iCar = new CarImpl(car);
		System.out.println("iCar.getPrice(): " + iCar.getPrice());
		iCar = new AddOptions(iCar, ListOptions);
		return iCar.getPrice();
	}
}
