package com.example.route_finder.graph.shortest_path_algorithm;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Value;

import com.example.route_finder.graph.Edge;
import com.example.route_finder.graph.Graph;
import com.example.route_finder.graph.MalformedInputFileException;
import com.example.route_finder.graph.Node;
import com.example.route_finder.graph.PathFinderAlgorithm;
import com.example.route_finder.graph.RouteFinderService;

import lombok.Getter;

@ShortestPath
@Named
public class ShortestRouteFinderService implements RouteFinderService {

	@Value("${input_file}")
	private File inputFile;

	@Getter
	private Graph graph;

	@PostConstruct
	public void init() throws IOException {
		Path filePath = inputFile.toPath();

		List<Edge> edges = new ArrayList<>();
		Set<Node> nodes = new LinkedHashSet<>();
		graph = new Graph(nodes, edges);

		try (BufferedReader bufferedReader = Files.newBufferedReader(filePath);) {
			bufferedReader.lines().forEach(line -> {
				Set<Node> lineNodes = Arrays.asList(line.split(",")).stream().map(String::trim).map(Node::new)
						.collect(Collectors.toSet());
				if (lineNodes.size() != 2) {
					throw new MalformedInputFileException(filePath, line);
				}

				nodes.addAll(lineNodes);

				List<Node> nodeList = List.copyOf(lineNodes);

				Edge edge1 = new Edge(nodeList.get(0), nodeList.get(1), 1);
				Edge edge2 = new Edge(nodeList.get(1), nodeList.get(0), 1);
				edges.add(edge1);
				edges.add(edge2);

			});
		}

	}

	@Override
	public List<Node> findTheShortestPath(Node origin, Node destination) {

		PathFinderAlgorithm algorithm = new ShortestPathFinderAlgorithm(graph, origin);

		return algorithm.getPath(destination);

	}

}
