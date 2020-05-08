package algorithms.chapter.graphalgorithms;

public class DepthFirstSearch<T extends Comparable<? super T>> {

    private int time;

    public void depthFirstSearch(Graph<T> graph) {
        for (Vertex<T> vertex : graph.getGraphMap().keySet()) {
            vertex.setColor(GraphColors.WHITE);
            vertex.setParent(null);
        }
        time = 0;

        for (Vertex<T> vertex : graph.getGraphMap().keySet()) {
            if (vertex.getColor() == GraphColors.WHITE) {
                visit(graph, vertex);
            }
        }
    }

    private void visit(Graph<T> graph, Vertex<T> vertex) {
        time++;
        vertex.setDiscoveryTime(time);
        vertex.setColor(GraphColors.GRAY);

        for (Vertex<T> edge : graph.getGraphMap().get(vertex)) {
            if (edge.getColor() == GraphColors.WHITE) {
                edge.setParent(vertex);
                visit(graph, edge);
            }
        }

        time++;
        vertex.setFinishingTime(time);
        vertex.setColor(GraphColors.BLACK);
    }
}
