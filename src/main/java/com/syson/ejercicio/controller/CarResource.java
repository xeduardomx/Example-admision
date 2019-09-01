package com.syson.ejercicio.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import io.swagger.annotations.*;

import com.syson.ejercicio.dao.Car;
import com.syson.ejercicio.exception.ServiceException;
import com.syson.ejercicio.repository.CarRepository;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;

@Api(value = "Api", produces = "application/json")
@Component
@Path("/api/v1")
public class CarResource {

	@Autowired
	private CarRepository carRepository;

	@ApiOperation(
			value = "List cars", response = CarResource.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Resource found"),
			@ApiResponse(code = 404, message = "Resource not found") })
	@GET
	@Produces("application/json")
	@Path("/cars")
	public List<Car> getAllCars() {
		return carRepository.findAll();
	}

	@GET
	@Produces("application/json")
	@Path("/cars/{id}")
	@ApiOperation(
			value = "Get car By ID", response = CarResource.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Resource found"),
			@ApiResponse(code = 404, message = "Resource not found") })
	public ResponseEntity<Car> getCarById(@PathParam(value = "id") Long carId) throws ServiceException {
		Car car = carRepository.findById(carId)
				.orElseThrow(() -> new ServiceException(HttpStatus.NOT_FOUND.value(), "Car not found :: " + carId, 1));
		return ResponseEntity.ok().body(car);
	}

	@POST
	@Produces("application/json")
	@Consumes("application/json")
	@ApiOperation(
			value = "Create new car", response = CarResource.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Resource found"),
			@ApiResponse(code = 404, message = "Resource not found") })
	@Path("/cars")
	@PostMapping("/cars")
	public Car createCar(Car car) {
		return carRepository.save(car);
	}

	@PUT
	@Produces("application/json")
	@Consumes("application/json")
	@ApiOperation(
			value = "Edit a car byd ID", response = CarResource.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Resource found"),
			@ApiResponse(code = 404, message = "Resource not found") })
	@Path("/cars/{id}")
	public ResponseEntity<Car> updateCar(@PathParam(value = "id") Long carId, @Valid @RequestBody Car carDetails)
			throws ServiceException {
		Car car = carRepository.findById(carId)
				.orElseThrow(() -> new ServiceException(HttpStatus.NOT_FOUND.value(), "Car not found :: " + carId, 1));
		car.setName(carDetails.getName());
		car.setBaseCost(carDetails.getBaseCost());
		final Car updatedCar = carRepository.save(car);
		return ResponseEntity.ok(updatedCar);
	}

	@DELETE
	@Path("/cars/{id}")
	@Produces("application/json")
	@ApiOperation(
			value = "Delete a car", response = CarResource.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Resource found"),
			@ApiResponse(code = 404, message = "Resource not found") })
	public Map<String, Boolean> deleteCar(@PathParam(value = "id") Long carId) throws ServiceException {
		Car car = carRepository.findById(carId)
				.orElseThrow(() -> new ServiceException(HttpStatus.NOT_FOUND.value(), "Car not found :: " + carId, 1));

		carRepository.delete(car);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}
