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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.syson.ejercicio.exception.ResourceNotFoundException;
import com.syson.ejercicio.model.Auto;
import com.syson.ejercicio.repository.AutoRepository;

@Component
@Path("/api/v1")
public class AutoResource {

 @Autowired
 private AutoRepository AutoRepository;

 @GET
 @Produces("application/json")
 @Path("/Autos")
 public List<Auto> getAllAutos() {
  return AutoRepository.findAll();
 }

 @GET
 @Produces("application/json")
 @Path("/Autos/{id}")
 public ResponseEntity<Auto> getAutoById(@PathParam(value = "id") Long AutoId) throws ResourceNotFoundException {
  Auto Auto = AutoRepository.findById(AutoId)
    .orElseThrow(() -> new ResourceNotFoundException("Auto not found :: " + AutoId));
  return ResponseEntity.ok().body(Auto);
 }

 @POST
 @Produces("application/json")
 @Consumes("application/json")
 @Path("/Autos")
 @PostMapping("/Autos")
 public Auto createAuto(Auto Auto) {
  return AutoRepository.save(Auto);
 }

 @PUT
 @Consumes("application/json")
 @Path("/Autos/{id}}")
 public ResponseEntity<Auto> updateAuto(@PathParam(value = "id") Long AutoId,
   @Valid @RequestBody Auto AutoDetails) throws ResourceNotFoundException {
  Auto Auto = AutoRepository.findById(AutoId)
    .orElseThrow(() -> new ResourceNotFoundException("Auto not found :: " + AutoId));

  Auto.setDescripcion(AutoDetails.getDescripcion());
  Auto.setCostoBase(AutoDetails.getCostoBase());
  final Auto updatedAuto = AutoRepository.save(Auto);
  return ResponseEntity.ok(updatedAuto);
 }

 @DELETE
 @Path("/Autos/{id}")
 public Map<String, Boolean> deleteAuto(@PathParam(value = "id") Long AutoId) throws ResourceNotFoundException {
  Auto Auto = AutoRepository.findById(AutoId)
    .orElseThrow(() -> new ResourceNotFoundException("Auto not found :: " + AutoId));

  AutoRepository.delete(Auto);
  Map<String, Boolean> response = new HashMap<>();
  response.put("deleted", Boolean.TRUE);
  return response;
 }
}
