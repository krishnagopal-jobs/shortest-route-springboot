package com.example.route_finder.graph;

import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode
@ToString
public class Node {
    final private String name;


    public Node(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    

}