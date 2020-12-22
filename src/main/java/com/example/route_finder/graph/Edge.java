package com.example.route_finder.graph;

import java.util.Optional;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@ToString
@EqualsAndHashCode
public class Edge {

	@Getter
	private final Node origin; 
	@Getter
	private final Node destination;
	@Getter
	private final Integer weightage;
	
	public Edge(Node origin, Node destination, Integer weightage) {
		this.origin = origin;
		this.destination = destination;
		this.weightage = Optional.ofNullable(weightage).filter(x -> x >= 0).orElse(Integer.valueOf(1));
	}

}
