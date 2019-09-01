package com.syson.ejercicio.config;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import com.syson.ejercicio.controller.CarResource;
import com.syson.ejercicio.controller.OptionResource;
import com.syson.ejercicio.controller.SaleResources;
import com.syson.ejercicio.exception.GenericExceptionMapper;
import com.syson.ejercicio.exception.ServiceExceptionMapper;

import javax.annotation.PostConstruct;


import org.glassfish.jersey.server.wadl.internal.WadlResource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

import io.swagger.annotations.Api;
import io.swagger.jaxrs.config.BeanConfig;
import io.swagger.jaxrs.listing.ApiListingResource;
import io.swagger.jaxrs.listing.SwaggerSerializers;


/**
* Configuration class
* In this class the drivers must be registered
* They contain the Swagger configuration
* 
* @author Eduardo Mendoza
* 
*/
@Component
@ApplicationPath("/sysone")
public class JerseyConfiguration extends ResourceConfig {
	@Value("${spring.jersey.application-path:/sysone}")
	private String apiPath;
	
	public JerseyConfiguration() {
		register(CarResource.class);
		register(OptionResource.class);
		register(SaleResources.class);
		register(ServiceExceptionMapper.class);
		register(GenericExceptionMapper.class);
	}
	
	@PostConstruct
	public void init() {
		this.configureSwagger();
	}
	
	private void configureSwagger() {
		this.register(ApiListingResource.class);
		this.register(SwaggerSerializers.class);

		BeanConfig config = new BeanConfig();
		config.setTitle("Prueba admision sysone, Jersey, Swagger");
		config.setVersion("v1");
		config.setContact("Eduardo Mendoza");
		config.setSchemes(new String[] { "http" });
		config.setBasePath("/sysone");
		config.setScan();
		config.setResourcePackage("com.syson.ejercicio.controller");
		config.setPrettyPrint(true);
		config.setScan(true);
	}
}