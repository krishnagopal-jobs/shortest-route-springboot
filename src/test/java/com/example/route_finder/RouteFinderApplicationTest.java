package com.example.route_finder;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.route_finder.graph.RouteFinderService;

import mockit.Injectable;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = RouteFinderApplication.class, webEnvironment = WebEnvironment.DEFINED_PORT)
class RouteFinderApplicationTest {


	@Injectable
	private RouteFinderService shortestRouteFinderService;
	
	
	private static ServicesClient servicesClient;
	
	@BeforeAll
    public static void setup() throws IOException {
        servicesClient = new ServicesClient();
    }

	@DisplayName("Integration testing different requests.")
    @ParameterizedTest(name = "{index}. Does route exist between {0} and {1} : {2}")
    @CsvSource({
    	"Boston,Newark,yes",
    	"Boston,Philadelphia,yes",
    	"Philadelphia,Albany,no"
    })
    public void testGetStatus(String origin, String destination, String output) {

        String response = servicesClient.getServicesResponse(origin, destination);

        assertEquals(output, response);
    }
	

}
