package algorithms.chapter.graphalgorithms;

import java.util.ArrayDeque;
import java.util.Queue;

public class BreadthFirstSearch<T extends Comparable<? super T>> {

    public void breathFirstSearch(Graph<T> graph, Vertex<T> source) {
        for(Vertex<T> vertex : graph.getGraphMap().keySet()) {
            if(vertex.compareTo(source) != 0) {
                vertex.setColor(GraphColors.WHITE);
                vertex.setDistance(Integer.MAX_VALUE);
                vertex.setParent(null);
            }
        }
        source.setColor(GraphColors.GRAY);
        source.setDistance(0);
        source.setParent(null);

        Queue<Vertex<T>> queue = new ArrayDeque<>();
        queue.add(source);
        while(!queue.isEmpty()) {
            Vertex<T> u = queue.poll();
            for (Vertex<T> v : graph.getGraphMap().get(u)) {
                if(v.getColor() == GraphColors.WHITE) {
                    v.setColor(GraphColors.GRAY);
                    v.setDistance(u.getDistance() + 1);
                    v.setParent(u);
                    queue.add(v);
                }
            }
            u.setColor(GraphColors.BLACK);
        }
    }

    public String printPath(Graph<T> graph, Vertex<T> source, Vertex<T> end) {
        StringBuilder result = new StringBuilder();
        return printPathRecursive(graph, source, end, result).toString().substring(0, result.length()-1);
    }

    private StringBuilder printPathRecursive(Graph<T> graph, Vertex<T> source, Vertex<T> end, StringBuilder result) {
       if (source.compareTo(end) == 0) {
           result.append(source.getValue() + "-");
       } else if (end.getParent() == null) {
           result = new StringBuilder("no path from " + source.getValue() + " to " + end.getValue() + " exists");
       } else {
           printPathRecursive(graph, source, end.getParent(), result);
           result.append(end.getValue() + "-");
       }
       return result;
    }

}
