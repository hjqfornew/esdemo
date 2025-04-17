package com.hjq.algorithm.graph;

import java.util.HashMap;
import java.util.HashSet;

public class Graph {
    private HashMap<Integer, GraphNode> nodes;
    private HashSet<Edge> edges;

    public Graph(HashMap<Integer, GraphNode> nodes, HashSet<Edge> edges) {
        this.nodes = nodes;
        this.edges = edges;
    }

    public HashMap<Integer, GraphNode> getNodes() {
        return nodes;
    }

    public void setNodes(HashMap<Integer, GraphNode> nodes) {
        this.nodes = nodes;
    }

    public HashSet<Edge> getEdges() {
        return edges;
    }

    public void setEdges(HashSet<Edge> edges) {
        this.edges = edges;
    }
}
