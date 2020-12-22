package com.example.route_finder;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.ClientConfig;

public class ServicesClient {

	private Client client;

	public ServicesClient() {
		client = initJerseyClient();
	}

	private Client initJerseyClient() {
		ClientConfig jerseyClientConfig = new ClientConfig();
		return ClientBuilder.newClient(jerseyClientConfig);
	}

	/**
	 * Returns OK if services are running
	 * 
	 * @return service status
	 */
	public String getServicesResponse(String origin, String destination) {

		try {
			Thread.sleep(10000l);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		Response respone = client.target("http://localhost:8080").path("connected").queryParam("origin", origin)
				.queryParam("destination", destination).request(MediaType.TEXT_PLAIN_TYPE).get();

		return respone.readEntity(String.class);
	}
}