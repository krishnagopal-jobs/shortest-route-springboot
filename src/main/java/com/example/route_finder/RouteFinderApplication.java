package com.example.route_finder;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class RouteFinderApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		// SpringApplication.run(RouteFinderApplication.class, args);
		new RouteFinderApplication().configure(new SpringApplicationBuilder(RouteFinderApplication.class)).run(args);
	}

}
