package com.syson.ejercicio.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
import com.syson.ejercicio.model.Auto;
import com.syson.ejercicio.repository.AutoRepository;

@Component
@Path("/api/v1")
public class AutoResource {

	@Autowired
	private AutoRepository autoRepository;

	@GET
	@Produces("application/json")
	@Path("/autos")
	public List<Auto> getAllAutos() {
		return autoRepository.findAll();
	}

	@GET
	@Produces("application/json")
	@Path("/autos/{id}")
	public ResponseEntity<Auto> getAutoById(@PathParam(value = "id") Long autoId) throws ServiceException {
		Auto auto = autoRepository.findById(autoId)
				.orElseThrow(() -> new ServiceException(HttpStatus.NOT_FOUND.value(), "Auto not found :: " + autoId, 1));
		return ResponseEntity.ok().body(auto);
	}
	
	@POST
	@Produces("application/json")
	@Consumes("application/json")
	@Path("/autos")
	@PostMapping("/autos")
	public Auto createAuto(Auto auto) {
		return autoRepository.save(auto);
	}

	@PUT
	@Produces("application/json")
	@Consumes("application/json")
	@Path("/autos/{id}")
	public ResponseEntity<Auto> updateAuto(@PathParam(value = "id") Long autoId, @Valid @RequestBody Auto autoDetails)
			throws ServiceException {
		Auto auto = autoRepository.findById(autoId)
				.orElseThrow(() -> new ServiceException(HttpStatus.NOT_FOUND.value(), "Auto not found :: " + autoId, 1));
		auto.setDescripcion(autoDetails.getDescripcion());
		auto.setCostoBase(autoDetails.getCostoBase());
		final Auto updatedAuto = autoRepository.save(auto);
		return ResponseEntity.ok(updatedAuto);
	}

	@DELETE
	@Path("/autos/{id}")
	@Produces("application/json")
	public Map<String, Boolean> deleteAuto(@PathParam(value = "id") Long autoId) throws ServiceException {
		Auto auto = autoRepository.findById(autoId)
				.orElseThrow(() -> new ServiceException(HttpStatus.NOT_FOUND.value(), "Auto not found :: " + autoId, 1));

		autoRepository.delete(auto);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}
