package com.example.route_finder;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import com.example.route_finder.graph.Node;
import com.example.route_finder.graph.ShortestRouteFinderService;

@Path("/")
@Named
public class RouteFinderResource {

	@Inject
	private ShortestRouteFinderService shortestRouteFinderService;

	@GET
	@Path("/connected")
	public String connectionFound(@QueryParam("origin") String origin, @QueryParam("destination") String destination) {

		List<Node> path = shortestRouteFinderService.findTheShortestPath(new Node(origin), new Node(destination));

		return Objects.nonNull(path) ? "yes" : "no";

	}

	@GET
	@Path("/path")
	public String getPath(@QueryParam("origin") String origin, @QueryParam("destination") String destination) {
		List<Node> path = shortestRouteFinderService.findTheShortestPath(new Node(origin), new Node(destination));
		if (Objects.nonNull(path)) {
			return path.stream().map(Node::getName).collect(Collectors.joining(" &rarr; "));
		}
		return "(Path doesn't exist) or (Origin and destination are the same)";
	}

}
