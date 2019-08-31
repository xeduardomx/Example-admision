package com.syson.ejercicio.config;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import com.syson.ejercicio.controller.AutoResource;

@Component
@ApplicationPath("/boot-jersey")
public class JerseyConfiguration extends ResourceConfig {
	public JerseyConfiguration() {
		 register(AutoResource.class);
	}
}