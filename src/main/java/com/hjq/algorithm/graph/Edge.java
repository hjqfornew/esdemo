package com.hjq.algorithm.graph;

public class Edge {
    private Integer value;
    private GraphNode from;
    private GraphNode to;

    public Edge(Integer value, GraphNode from, GraphNode to) {
        this.value = value;
        this.from = from;
        this.to = to;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public GraphNode getFrom() {
        return from;
    }

    public void setFrom(GraphNode from) {
        this.from = from;
    }

    public GraphNode getTo() {
        return to;
    }

    public void setTo(GraphNode to) {
        this.to = to;
    }
}
