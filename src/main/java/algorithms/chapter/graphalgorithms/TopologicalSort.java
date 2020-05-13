package algorithms.chapter.graphalgorithms;

import java.util.LinkedList;

public class TopologicalSort<T extends Comparable<? super T>> {

    private int time;

    public LinkedList<Vertex<T>> topologicalSort(Graph<T> graph) {
        LinkedList<Vertex<T>> verticesList = new LinkedList<>();
        time = 0;

        for (Vertex<T> vertex : graph.getGraphMap().keySet()) {
            if (vertex.getColor() == GraphColors.WHITE) {
                visit(graph, vertex, verticesList);
            }
        }
        return verticesList;
    }

    private void visit(Graph<T> graph, Vertex<T> vertex, LinkedList<Vertex<T>> verticesList) {
        time++;
        vertex.setDiscoveryTime(time);
        vertex.setColor(GraphColors.GRAY);

        for (Vertex<T> edge : graph.getGraphMap().get(vertex)) {
            if (edge.getColor() == GraphColors.WHITE) {
                edge.setParent(vertex);
                visit(graph, edge, verticesList);
            }
        }
        time++;
        vertex.setFinishingTime(time);
        vertex.setColor(GraphColors.BLACK);
        verticesList.addFirst(vertex);
    }

}
