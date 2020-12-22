package com.example.route_finder.graph;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

class ShortestPathFinderAlgorithmTest {

	private Set<Node> nodes;
	private List<Edge> edges;
	
	private List<Node> nodesList;

	@Test
	public void testExcute() {
		nodes = new LinkedHashSet<Node>();
		edges = new ArrayList<Edge>();
		for (int i = 0; i < 11; i++) {
			Node location = new Node("Node_" + i);
			nodes.add(location);
		}

		nodesList = List.copyOf(nodes);
		
		addLane(0, 1, 85);
		addLane(0, 2, 217);
		addLane(0, 4, 173);
		addLane(2, 6, 186);
		addLane(2, 7, 103);
		addLane(3, 7, 183);
		addLane(5, 8, 250);
		addLane(8, 9, 84);
		addLane(7, 9, 167);
		addLane(4, 9, 502);
		addLane(9, 10, 40);
		addLane(1, 10, 600);

		// Lets check from location Loc_1 to Loc_10
		Graph graph = new Graph(nodes, edges);
		
		ShortestPathFinderAlgorithm shortestPathFinderAlgorithm = new ShortestPathFinderAlgorithm(graph, nodesList.get(0));
		LinkedList<Node> path = shortestPathFinderAlgorithm.getPath(nodesList.get(10));

		assertNotNull(path);
		assertTrue(path.size() > 0);

		System.out.println("Path : " + path.stream().map(Object::toString).collect(Collectors.joining(" -> ", "[", "]")));

	}

	private void addLane(int sourceLocNo, int destLocNo, int duration) {
		Edge lane = new Edge(nodesList.get(sourceLocNo), nodesList.get(destLocNo), duration);
		edges.add(lane);
	}

}
