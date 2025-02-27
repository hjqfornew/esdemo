package com.hjq.algorithm.graph;

import java.util.List;

public class GraphNode {
    private Integer value;
    private Integer in;
    private Integer out;
    private List<GraphNode> nextNodes;
    private List<Edge> nextEdges;

    public GraphNode(Integer value, Integer in, Integer out, List<GraphNode> nextNodes, List<Edge> nextEdges) {
        this.value = value;
        this.in = in;
        this.out = out;
        this.nextNodes = nextNodes;
        this.nextEdges = nextEdges;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Integer getIn() {
        return in;
    }

    public void setIn(Integer in) {
        this.in = in;
    }

    public Integer getOut() {
        return out;
    }

    public void setOut(Integer out) {
        this.out = out;
    }

    public List<GraphNode> getNextNodes() {
        return nextNodes;
    }

    public void setNextNodes(List<GraphNode> nextNodes) {
        this.nextNodes = nextNodes;
    }

    public List<Edge> getNextEdges() {
        return nextEdges;
    }

    public void setNextEdges(List<Edge> nextEdges) {
        this.nextEdges = nextEdges;
    }
}
