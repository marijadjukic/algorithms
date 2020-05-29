package algorithms.chapter.graphalgorithms;

import java.util.*;

public class SingleSourceShortestPaths<T extends Comparable<? super T>> {

    private TopologicalSort<T> topologicalSort = new TopologicalSort<>();

    public boolean bellmanFord(Graph<T> graph, Vertex<T> source) {
        this.initSingleSource(graph, source);
        for(int i=1; i <= graph.getGraphMap().keySet().size()-1; i++) {
            for(Graph.Edge edge : graph.getEdges()) {
                int edgeWeight = edge.getWeight();
                this.relax(edge.getU(), edge.getV(), edgeWeight);
            }
        }
        for(Graph.Edge edge : graph.getEdges()) {
            if(edge.getV().getDistance() > edge.getU().getDistance() + edge.getWeight()) {
                return false;
            }
        }
        return true;
    }

    public void dagShortestPaths(Graph<T> graph, Vertex<T> source) {
        LinkedList<Vertex<T>> sortedVertices = topologicalSort.topologicalSort(graph);
        this.initSingleSource(graph, source);

        for(Vertex<T> u : sortedVertices) {
            for(Vertex<T> v : graph.getGraphMap().get(u)) {
                int edgeWeight = graph.getEdgeWeight(u, v);
                this.relax(u, v, edgeWeight);
            }
        }
    }

    public Set<Vertex<T>> dijkstra(Graph<T> graph, Vertex<T> source) {
        this.initSingleSource(graph, source);
        Set<Vertex<T>> set = new HashSet<>();

        PriorityQueue<Vertex<T>> queue = new PriorityQueue<>(Comparator.comparingInt(Vertex::getDistance));
        queue.addAll(graph.getGraphMap().keySet());

        while(!queue.isEmpty()) {
            Vertex<T> u = queue.poll();
            set.add(u);
            for(Vertex<T> v : graph.getGraphMap().get(u)) {
                int edgeWeight = graph.getEdgeWeight(u, v);
                this.relax(u, v, edgeWeight);
            }
        }
        return set;
    }

    private void initSingleSource(Graph<T> graph, Vertex<T> source) {
        for(Vertex<T> vertex : graph.getGraphMap().keySet()) {
            vertex.setDistance(Integer.MAX_VALUE);
            vertex.setParent(null);
        }
        source.setDistance(0);
    }

    private void relax(Vertex<T> u, Vertex<T> v, int edgeWeight) {

        if(u.getDistance() != Integer.MAX_VALUE && v.getDistance() > u.getDistance() + edgeWeight) {
            v.setDistance(u.getDistance() + edgeWeight);
            v.setParent(u);
        }
    }

}
