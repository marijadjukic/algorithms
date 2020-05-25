package algorithms.chapter.graphalgorithms;

import algorithms.chapter.advanceddatastructures.Edge;
import algorithms.chapter.advanceddatastructures.Graph;

public class SingleSourceShortestPaths<T extends Comparable<? super T>> {

    public void initSingleSource(Graph<Vertex<T>> graph, Vertex<T> source) {
        for(Vertex<T> vertex : graph.getVertices()) {
            if(vertex.compareTo(source) != 0) {
                vertex.setDistance(Integer.MAX_VALUE);
                vertex.setParent(null);
            }
        }
        source.setDistance(0);
    }

    public void relax(Edge<Vertex<T>> edge) {
        if(edge.getYVertex().getDistance() > edge.getXVertex().getDistance() + edge.getWeight()) {
            edge.getYVertex().setDistance(edge.getXVertex().getDistance() + edge.getWeight());
            edge.getYVertex().setParent(edge.getXVertex());
        }
    }

    public boolean bellmanFord(Graph<Vertex<T>> graph, Vertex<T> source) {
        this.initSingleSource(graph, source);
        for(int i=1; i <= graph.getVertices().size()-1; i++) {
            for(Edge<Vertex<T>> edge : graph.getEdges()) {
                this.relax(edge);
            }
        }
        for(Edge<Vertex<T>> edge : graph.getEdges()) {
            if(edge.getYVertex().getDistance() > edge.getXVertex().getDistance() + edge.getWeight()) {
                return false;
            }
        }
        return true;
    }


}
