package com.syson.ejercicio.controller;

import java.util.ArrayList;
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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.syson.ejercicio.dao.Car;
import com.syson.ejercicio.dao.Option;
import com.syson.ejercicio.dao.Sale;
import com.syson.ejercicio.exception.ServiceException;
import com.syson.ejercicio.repository.CarRepository;
import com.syson.ejercicio.repository.OptionRepository;
import com.syson.ejercicio.repository.SaleRepository;
import com.syson.ejercicio.service.SaleService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * <p>
 * Sale controller, contains all http methods to insert, update, delete
 * </p>
 * 
 * @author Eduardo Mendoza
 */
@Api(value = "Api", produces = "application/json")
@Component
@Path("/api/v1")
public class SaleResources {

	@Autowired
	private CarRepository carRepository;

	@Autowired
	private SaleRepository saleRepository;

	@Autowired
	private OptionRepository optionRepository;

	@Autowired
	private SaleService saleService;

	/**
	 * <p>
	 * List Sales method
	 * </p>
	 * It must be invoked with the GET method, not receiving parameters
	 * 
	 * @param Null
	 * @return List of Cars
	 * @since 1.0
	 */
	@ApiOperation(value = "List Sales", response = SaleResources.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Resource found"),
			@ApiResponse(code = 404, message = "Resource not found") })
	@GET
	@Produces("application/json")
	@Path("/sales")
	public List<Sale> getAllSales() {
		return saleRepository.findAll();
	}

	/**
	 * <p>
	 * Find Sale By ID
	 * </p>
	 * Search for a specific database record<br>
	 * must be invoked by the GET method
	 * 
	 * @param ID
	 * @return Car
	 * @since 1.0
	 */
	@ApiOperation(value = "Get Sale by ID", response = SaleResources.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Resource found"),
			@ApiResponse(code = 404, message = "Resource not found") })
	@GET
	@Produces("application/json")
	@Path("/sales/{id}")
	public ResponseEntity<Sale> getSaleById(@PathParam(value = "id") Long saleId) throws ServiceException {
		Sale sale = saleRepository.findById(saleId).orElseThrow(
				() -> new ServiceException(HttpStatus.NOT_FOUND.value(), "Sale not found :: " + saleId, 1));

		Car car = carRepository.findById(sale.getCar().getId())
				.orElseThrow(() -> new ServiceException(HttpStatus.NOT_FOUND.value(),
						"Car not found :: " + sale.getCar().getId(), 1));
		sale.setCar(car);
		return ResponseEntity.ok().body(sale);
	}

	/**
	 * <p>
	 * Create and calculate Sale
	 * </p>
	 * Create a new car<br>
	 * must be invited by the POST method
	 * 
	 * @param body request, example: { "car": { "id": 1 }, "options":
	 *             ["AA","AB","LL"] }
	 * @return Car
	 * @since 1.0
	 */
	@ApiOperation(value = "Create and calculate new Sale", response = SaleResources.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Resource found"),
			@ApiResponse(code = 404, message = "Resource not found") })
	@POST
	@Produces("application/json")
	@Consumes("application/json")
	@Path("/sales")
	@PostMapping("/sales")
	public Sale calculateAndcreateSale(Sale sale) throws ServiceException {

		Car car = carRepository.findById(sale.getCar().getId())
				.orElseThrow(() -> new ServiceException(HttpStatus.NOT_FOUND.value(),
						"Car not found :: " + sale.getCar().getId(), 1));

		ArrayList<Option> listOptions = optionRepository.findListSc(sale.getOptions());
		sale.setTotalPrice(saleService.calculateTotalCarPrice(car, listOptions));
		sale.setCar(car);
		return saleRepository.save(sale);
	}

	/**
	 * <p>
	 * Modify and re3calculate Sale
	 * </p>
	 * Modify specific sale<br>
	 * must be invited by the PUT method
	 * 
	 * @param body request, example: { "car": { "id": 1 }, "options":
	 *             ["AA","AB","LL"] }
	 * @return Car
	 * @since 1.0
	 */
	@ApiOperation(value = "Edit Sales", response = SaleResources.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Resource found"),
			@ApiResponse(code = 404, message = "Resource not found") })
	@PUT
	@Produces("application/json")
	@Consumes("application/json")
	@Path("/sales/{id}")
	public ResponseEntity<Sale> updateSale(@PathParam(value = "id") Long saleId, @Valid @RequestBody Sale saleDetails)
			throws ServiceException {
		Sale sale = saleRepository.findById(saleId).orElseThrow(
				() -> new ServiceException(HttpStatus.NOT_FOUND.value(), "Sale not found :: " + saleId, 1));
		sale.setCar(saleDetails.getCar());
		sale.setOptions(saleDetails.getOptions());

		Car car = carRepository.findById(sale.getCar().getId())
				.orElseThrow(() -> new ServiceException(HttpStatus.NOT_FOUND.value(),
						"Car not found :: " + sale.getCar().getId(), 1));

		sale.setCar(car);
		ArrayList<Option> listOptions = optionRepository.findListSc(sale.getOptions());
		sale.setTotalPrice(saleService.calculateTotalCarPrice(sale.getCar(), listOptions));
		// sale.setTotalPrice(saleDetails.getTotalPrice());
		final Sale updatedCar = saleRepository.save(sale);
		return ResponseEntity.ok(updatedCar);
	}

	/**
	 * <p>
	 * Delete sale
	 * </p>
	 * Delete specific sale<br>
	 * must be invited by the DELETE method
	 * 
	 * @param id
	 * @return { "deleted": true }
	 * 
	 * */
	@ApiOperation(value = "Delete Sales", response = SaleResources.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Resource found"),
			@ApiResponse(code = 404, message = "Resource not found") })
	@DELETE
	@Path("/sales/{id}")
	@Produces("application/json")
	public Map<String, Boolean> deleteSale(@PathParam(value = "id") Long saleId) throws ServiceException {
		Sale sale = saleRepository.findById(saleId).orElseThrow(
				() -> new ServiceException(HttpStatus.NOT_FOUND.value(), "Sale not found :: " + saleId, 1));

		saleRepository.delete(sale);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}
