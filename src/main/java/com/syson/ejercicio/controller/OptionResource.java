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

import com.syson.ejercicio.exception.ServiceException;
import com.syson.ejercicio.model.Option;
import com.syson.ejercicio.repository.OptionRepository;

@Component
@Path("/api/v1")
public class OptionResource {

	@Autowired
	private OptionRepository optionRepository;

	@GET
	@Produces("application/json")
	@Path("/options")
	public List<Option> getAllOptions() throws SQLException {
		return optionRepository.findAll();
	}

	@GET
	@Produces("application/json")
	@Path("/options/{id}")
	public ResponseEntity<Option> getOptionById(@PathParam(value = "id") Long optionId) throws ServiceException {
		Option option = optionRepository.findById(optionId)
				.orElseThrow(() -> new ServiceException(HttpStatus.NOT_FOUND.value(), "Option not found :: " + optionId, 1));
		return ResponseEntity.ok().body(option);
	}
	
	@POST
	@Produces("application/json")
	@Consumes("application/json")
	@Path("/options")
	@PostMapping("/options")
	public Option createOption(Option option) {
		return optionRepository.save(option);
	}

	@PUT
	@Produces("application/json")
	@Consumes("application/json")
	@Path("/options/{id}")
	public ResponseEntity<Option> updateOption(@PathParam(value = "id") Long optionId, @Valid @RequestBody Option optionDetails)
			throws ServiceException {
		Option option = optionRepository.findById(optionId)
				.orElseThrow(() -> new ServiceException(HttpStatus.NOT_FOUND.value(), "Option not found :: " + optionId, 1));
		option.setDescription(optionDetails.getDescription());
		option.setPriceOption(optionDetails.getPriceOption());
		option.setShortCut(optionDetails.getShortCut());
		final Option updatedOption = optionRepository.save(option);
		return ResponseEntity.ok(updatedOption);
	}

	@DELETE
	@Path("/options/{id}")
	@Produces("application/json")
	public Map<String, Boolean> deleteOption(@PathParam(value = "id") Long optionId) throws ServiceException {
		Option option = optionRepository.findById(optionId)
				.orElseThrow(() -> new ServiceException(HttpStatus.NOT_FOUND.value(), "Option not found :: " + optionId, 1));

		optionRepository.delete(option);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}