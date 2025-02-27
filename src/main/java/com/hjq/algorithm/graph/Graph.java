package com.hjq.algorithm.graph;

import java.util.HashMap;
import java.util.HashSet;

public class Graph {
    private HashMap<Integer, GraphNode> nodeHashMap;
    private HashSet<Edge> edgeHashSet;

    public Graph(HashMap<Integer, GraphNode> nodeHashMap, HashSet<Edge> edgeHashSet) {
        this.nodeHashMap = nodeHashMap;
        this.edgeHashSet = edgeHashSet;
    }

    public HashMap<Integer, GraphNode> getNodeHashMap() {
        return nodeHashMap;
    }

    public void setNodeHashMap(HashMap<Integer, GraphNode> nodeHashMap) {
        this.nodeHashMap = nodeHashMap;
    }

    public HashSet<Edge> getEdgeHashSet() {
        return edgeHashSet;
    }

    public void setEdgeHashSet(HashSet<Edge> edgeHashSet) {
        this.edgeHashSet = edgeHashSet;
    }
}
