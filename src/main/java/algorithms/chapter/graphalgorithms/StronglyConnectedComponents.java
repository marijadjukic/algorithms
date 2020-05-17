package algorithms.chapter.graphalgorithms;

import java.util.*;

public class StronglyConnectedComponents<T extends Comparable<? super T>> {

    private int time;

    public List<String> getStronglyConnectedComponents(Graph<T> graph) {
        time = 0;

        Stack<Vertex<T>> stack = new Stack<>();
        List<String> stronglyConnectedComponents = new ArrayList<>();

        for (Vertex<T> vertex : graph.getGraphMap().keySet()) {
            if (vertex.getColor() == GraphColors.WHITE) {
                visit(graph, vertex, stack);
            }
        }

        Graph<T> tGraph = transposeGraph(graph);

        for (Vertex<T> vertex : graph.getGraphMap().keySet()) {
            vertex.setColor(GraphColors.WHITE);
        }

        while(stack.empty() == false) {
            Vertex<T> vertex = stack.pop();

            if(vertex.getColor() == GraphColors.WHITE) {
                StringBuilder component = new StringBuilder();
                buildStronglyConnectedComponent(tGraph, vertex, component);
                stronglyConnectedComponents.add(component.substring(0, component.length()-1));
            }
        }
        return stronglyConnectedComponents;
    }

    private void visit(Graph<T> graph, Vertex<T> vertex, Stack<Vertex<T>> stack) {
        time++;
        vertex.setDiscoveryTime(time);
        vertex.setColor(GraphColors.GRAY);

        for (Vertex<T> edge : graph.getGraphMap().get(vertex)) {
            if (edge.getColor() == GraphColors.WHITE) {
                edge.setParent(vertex);
                visit(graph, edge, stack);
            }
        }
        time++;
        vertex.setFinishingTime(time);
        vertex.setColor(GraphColors.BLACK);
        stack.push(vertex);
    }

    private Graph<T> transposeGraph(Graph<T> graph) {
        Graph<T> tGraph = new Graph<>();
        Map<Vertex<T>, LinkedList<Vertex<T>>> graphMap = graph.getGraphMap();

        for (Vertex<T> vertex : graphMap.keySet()) {
            Iterator<Vertex<T>> edgesIterator = graphMap.get(vertex).listIterator();

            while (edgesIterator.hasNext()) {
                tGraph.add(edgesIterator.next(), vertex);
            }
        }

        return tGraph;
    }

    private void buildStronglyConnectedComponent(Graph<T> graph, Vertex<T> vertex, StringBuilder stronglyConnectedComponent) {
        vertex.setColor(GraphColors.GRAY);
        stronglyConnectedComponent.append(vertex.getValue() + "-");

        Vertex<T> nextVertex;
        Map<Vertex<T>, LinkedList<Vertex<T>>> graphMap = graph.getGraphMap();
        Iterator<Vertex<T>> edges = graphMap.get(vertex).iterator();
        while (edges.hasNext()) {
            nextVertex = edges.next();
            if(nextVertex.getColor() == GraphColors.WHITE) {
                buildStronglyConnectedComponent(graph, nextVertex, stronglyConnectedComponent);
            }
        }
        vertex.setColor(GraphColors.BLACK);
    }

}
