package com.example.route_finder;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.route_finder.graph.Node;
import com.example.route_finder.graph.ShortestRouteFinderService;

import mockit.Expectations;
import mockit.Injectable;
import mockit.Tested;

class RouteFinderResourceTest {

	@Tested
	private RouteFinderResource routeFinderResource;
	
	@Injectable
	private ShortestRouteFinderService shortestRouteFinderService;
	
	
	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void testConnectionFound() {
		
		String origin = "origin";
		String destination = "destination";
		
		new Expectations() {{
			shortestRouteFinderService.findTheShortestPath(new Node(origin), new Node(destination));
			this.times=2;
			this.returns(List.of(new Node(origin), new Node("Rajnikanth"), new Node(destination)), null);
		}};
		
		String result = routeFinderResource.connectionFound(origin, destination);
		assertEquals("yes", result);
		assertEquals("no", routeFinderResource.connectionFound(origin, destination));
	}

	@Test
	void testGetPath() {
		
		String origin = "origin";
		String destination = "destination";
		
		new Expectations() {{
			shortestRouteFinderService.findTheShortestPath(new Node(origin), new Node(destination));
			this.times=2;
			this.returns(List.of(new Node(origin), new Node("Rajnikanth"), new Node(destination)), null);
		}};
		
		String result = routeFinderResource.getPath(origin, destination);
		assertEquals(List.of(new Node(origin), new Node("Rajnikanth"), new Node(destination)).stream().map(Node::getName).collect(Collectors.joining(" &rarr; ")), result);
		assertEquals("(Path doesn't exist) or (Origin and destination are the same)", routeFinderResource.getPath(origin, destination));
	}

}
