package com.example.route_finder.graph;

import java.util.List;
import java.util.Set;

public class Graph {
    private final Set<Node> nodes;
    private final List<Edge> edges;

    public Graph(Set<Node> nodes, List<Edge> edges) {
        this.nodes = nodes;
        this.edges = edges;
    }

    public Set<Node> getNodes() {
        return nodes;
    }

    public List<Edge> getEdges() {
        return edges;
    }

}
