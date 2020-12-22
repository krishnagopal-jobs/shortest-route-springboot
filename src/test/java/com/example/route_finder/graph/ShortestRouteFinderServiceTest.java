package com.example.route_finder.graph;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.LinkedList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import mockit.Expectations;
import mockit.Injectable;
import mockit.Mocked;
import mockit.Tested;

class ShortestRouteFinderServiceTest {

	@Tested
	private ShortestRouteFinderService shortestRouteFinderService;

	@Injectable
	private File inputFile = Paths.get("src/test/resources/route-test-file.txt").toFile();

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void testInit() throws IOException {

		new Expectations() {
			{
				inputFile.toPath();
				this.times = 1;
				this.result = Paths.get("src/test/resources/route-test-file.txt");
			}
		};

		shortestRouteFinderService.init();

		Graph graph = shortestRouteFinderService.getGraph();

		assertEquals(8, graph.getEdges().size());
		assertEquals(6, graph.getNodes().size());

		assertEquals(
				"[Edge(origin=Node(name=New York), destination=Node(name=Boston), weightage=1), Edge(origin=Node(name=Boston), destination=Node(name=New York), weightage=1), Edge(origin=Node(name=Newark), destination=Node(name=Philadelphia), weightage=1), Edge(origin=Node(name=Philadelphia), destination=Node(name=Newark), weightage=1), Edge(origin=Node(name=Newark), destination=Node(name=Boston), weightage=1), Edge(origin=Node(name=Boston), destination=Node(name=Newark), weightage=1), Edge(origin=Node(name=Trenton), destination=Node(name=Albany), weightage=1), Edge(origin=Node(name=Albany), destination=Node(name=Trenton), weightage=1)]",
				graph.getEdges().toString());
		assertEquals(
				"[Node(name=New York), Node(name=Boston), Node(name=Newark), Node(name=Philadelphia), Node(name=Trenton), Node(name=Albany)]",
				graph.getNodes().toString());

	}

	@Test
	void testInitEmptyFile() throws IOException {

		new Expectations() {
			{
				inputFile.toPath();
				this.times = 1;
				this.result = Paths.get("src/test/resources/route-test-empty-file.txt");
			}
		};

		try {
			shortestRouteFinderService.init();
		} catch (MalformedInputFileException e) {
			assertEquals(
					"MalformedInputFileException(inputFilePath=src/test/resources/route-test-empty-file.txt, line=Brooklyn)",
					e.toString().replaceAll("\\\\", "/"));
		}

	}
	
	@Test
	void testFindTheShortestPath(@Mocked ShortestPathFinderAlgorithm algorithm, @Mocked Graph graph, @Mocked Node origin, @Mocked Node destination) {
		
		LinkedList<Node> nodes = new LinkedList<Node>();
		nodes.add(origin);
		nodes.add(new Node("SmartTown"));
		nodes.add(destination);
		
		new Expectations() {{
			ShortestPathFinderAlgorithm shortAlgorithm = new ShortestPathFinderAlgorithm((Graph) any, (Node) any);
			shortAlgorithm.getPath((Node) any);
			this.times = 1;
			this.result = nodes;
		}};
		
		assertEquals(nodes, shortestRouteFinderService.findTheShortestPath(origin, destination));
		
	}

}
