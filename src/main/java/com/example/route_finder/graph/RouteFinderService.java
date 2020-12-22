package com.example.route_finder.graph;

import java.util.List;

public interface RouteFinderService {

	List<Node> findTheShortestPath(Node origin, Node destination);

}