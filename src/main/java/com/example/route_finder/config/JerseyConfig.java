package com.example.route_finder.config;

import javax.inject.Named;

import org.glassfish.jersey.server.ResourceConfig;

import com.example.route_finder.RouteFinderResource;

@Named
public class JerseyConfig extends ResourceConfig {

	public JerseyConfig() {
		registerEndPoints();
	}
	
	private void registerEndPoints() {
		register(RouteFinderResource.class);
	}
	
}
