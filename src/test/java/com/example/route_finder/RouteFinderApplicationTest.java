package com.example.route_finder;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;

import org.glassfish.jersey.test.JerseyTest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.route_finder.graph.ShortestRouteFinderService;

import lombok.extern.slf4j.XSlf4j;
import mockit.Injectable;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = RouteFinderApplication.class, webEnvironment = WebEnvironment.DEFINED_PORT)
class RouteFinderApplicationTest {


	@Injectable
	private ShortestRouteFinderService shortestRouteFinderService;
	
	
	private static ServicesClient servicesClient;
	
	@BeforeAll
    public static void setup() throws IOException {
        servicesClient = new ServicesClient();
    }

    @Test
    public void testGetStatus() {

        String status = servicesClient.getServicesStatus();

        assertEquals("yes", status);
    }
	

}
