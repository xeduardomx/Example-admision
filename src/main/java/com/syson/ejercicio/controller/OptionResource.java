package com.syson.ejercicio.controller;

import java.sql.SQLException;
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

import com.syson.ejercicio.dao.Option;
import com.syson.ejercicio.exception.ServiceException;
import com.syson.ejercicio.repository.OptionRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * <p>
 * Option controller, contains all http methods to insert, update, delete
 * </p>
 * 
 * @author Eduardo Mendoza
 */
@Api(value = "Api", produces = "application/json")
@Component
@Path("/api/v1")
public class OptionResource {

	/**
	 * <p>
	 * List Options method
	 * </p>
	 * It must be invoked with the GET method, not receiving parameters
	 * 
	 * @param Null
	 * @return List of Options
	 * @since 1.0
	 */
	@Autowired
	private OptionRepository optionRepository;

	@ApiOperation(value = "List options", response = OptionResource.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Resource found"),
			@ApiResponse(code = 404, message = "Resource not found") })
	@GET
	@Produces("application/json")
	@Path("/options")
	public List<Option> getAllOptions() throws SQLException {
		return optionRepository.findAll();
	}

	/**
	 * <p>
	 * Find Option By ID
	 * </p>
	 * Search for a specific database record<br>
	 * must be invoked by the GET method
	 * 
	 * @param ID
	 * @return Car
	 * @since 1.0
	 */
	@ApiOperation(value = "Get option By ID", response = OptionResource.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Resource found"),
			@ApiResponse(code = 404, message = "Resource not found") })
	@GET
	@Produces("application/json")
	@Path("/options/{id}")
	public ResponseEntity<Option> getOptionById(@PathParam(value = "id") Long optionId) throws ServiceException {
		Option option = optionRepository.findById(optionId).orElseThrow(
				() -> new ServiceException(HttpStatus.NOT_FOUND.value(), "Option not found :: " + optionId, 1));
		return ResponseEntity.ok().body(option);
	}

	/**
	 * <p>
	 * Create Option
	 * </p>
	 * Create a new option<br>
	 * must be invited by the POST method
	 * 
	 * @param body request, example: { "description": "Sistema de Frenos ABS",
	 *             "priceOption": 14000.0, "shortCut": "ABS" }
	 * @return Option
	 * @since 1.0
	 */
	@ApiOperation(value = "Create new option", response = OptionResource.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Resource found"),
			@ApiResponse(code = 404, message = "Resource not found") })
	@POST
	@Produces("application/json")
	@Consumes("application/json")
	@Path("/options")
	@PostMapping("/options")
	public Option createOption(Option option) {
		return optionRepository.save(option);
	}

	/**
	 * <p>
	 * Modify car
	 * </p>
	 * Modify specific option<br>
	 * must be invited by the DELETE method
	 * 
	 * @param body request, example: { "description": "Sistema de Frenos ABS",
	 *             "priceOption": 14000.0, "shortCut": "ABS" }
	 * @return Option
	 * @since 1.0
	 */
	@ApiOperation(value = "Edit option by ID", response = OptionResource.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Resource found"),
			@ApiResponse(code = 404, message = "Resource not found") })
	@PUT
	@Produces("application/json")
	@Consumes("application/json")
	@Path("/options/{id}")
	public ResponseEntity<Option> updateOption(@PathParam(value = "id") Long optionId,
			@Valid @RequestBody Option optionDetails) throws ServiceException {
		Option option = optionRepository.findById(optionId).orElseThrow(
				() -> new ServiceException(HttpStatus.NOT_FOUND.value(), "Option not found :: " + optionId, 1));
		option.setDescription(optionDetails.getDescription());
		option.setPriceOption(optionDetails.getPriceOption());
		option.setShortCut(optionDetails.getShortCut());
		final Option updatedOption = optionRepository.save(option);
		return ResponseEntity.ok(updatedOption);
	}

	
	/**
	 * <p>
	 * Delete option
	 * </p>
	 * Delete specific option<br>
	 * must be invited by the PUT method
	 * 
	 * @param id
	 * @return { "deleted": true }
	 * 
	 * */
	@ApiOperation(value = "Delete Option", response = OptionResource.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Resource found"),
			@ApiResponse(code = 404, message = "Resource not found") })
	@DELETE
	@Path("/options/{id}")
	@Produces("application/json")
	public Map<String, Boolean> deleteOption(@PathParam(value = "id") Long optionId) throws ServiceException {
		Option option = optionRepository.findById(optionId).orElseThrow(
				() -> new ServiceException(HttpStatus.NOT_FOUND.value(), "Option not found :: " + optionId, 1));

		optionRepository.delete(option);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}
