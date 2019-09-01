package com.syson.ejercicio.config;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import com.syson.ejercicio.controller.CarResource;
import com.syson.ejercicio.controller.OptionResource;
import com.syson.ejercicio.controller.SaleResources;
import com.syson.ejercicio.exception.GenericExceptionMapper;
import com.syson.ejercicio.exception.ServiceExceptionMapper;


@Component
@ApplicationPath("/sysone")
public class JerseyConfiguration extends ResourceConfig {
	public JerseyConfiguration() {
		 register(CarResource.class);
		 register(OptionResource.class);
		 register(SaleResources.class);
		 register(ServiceExceptionMapper.class);
	     register(GenericExceptionMapper.class);
	}
}