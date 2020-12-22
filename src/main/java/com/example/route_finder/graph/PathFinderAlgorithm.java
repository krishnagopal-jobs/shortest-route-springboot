package com.example.route_finder.graph;

import java.util.LinkedList;

/**
 * The implementing classes of this interface provides the shortest path from interface.
 * 
 * @author Owner
 *
 */
public interface PathFinderAlgorithm {

	/*
	 * This method returns the path from the source to the selected target and
	 * NULL if no path exists
	 */
	LinkedList<Node> getPath(Node destination);

}