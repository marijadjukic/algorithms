package algorithms.chapter.graphalgorithms;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.*;

@NoArgsConstructor
public class Graph<T extends Comparable<? super T>> {

    /**
     * Graph map with keys representing vertices
     * and each vertex has adjacency list which represents edges
     */
    private Map<Vertex<T>, LinkedList<Vertex<T>>> graphMap = new HashMap<>();

    @Getter
    private List<Edge<T>> edges = new ArrayList<>();

    private int time;

    public void add(Vertex<T> vertex) {
        this.graphMap.put(vertex, new LinkedList<>());
    }

    public void add(Vertex<T> vertex, Vertex<T> edge) {
        Collection<Vertex<T>> edges = Arrays.asList(edge);
        this.add(vertex, edges);
    }

    public void add(Vertex<T> vertex, Vertex<T> edge, int edgeWeight) {
        this.add(vertex, edge);
        Edge<T> e = new Edge<>(vertex, edge, edgeWeight);
        edges.add(e);
    }

    public void add(Vertex<T> vertex, Collection<Vertex<T>> edges) {
        if (this.graphMap.keySet().contains(vertex)) {
            LinkedList<Vertex<T>> adjacencyList = this.graphMap.get(vertex);
            adjacencyList.addAll(edges);
        } else {
            this.graphMap.put(vertex, new LinkedList<>(edges));
        }
    }

    public void breathFirstSearch(Vertex<T> source) {
        source.setColor(GraphColors.GRAY);
        source.setDistance(0);
        source.setParent(null);

        Queue<Vertex<T>> queue = new ArrayDeque<>();
        queue.add(source);
        while(!queue.isEmpty()) {
            Vertex<T> u = queue.poll();
            for (Vertex<T> v : this.graphMap.get(u)) {
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

    public String getPath(Vertex<T> source, Vertex<T> end) {
        StringBuilder result = new StringBuilder();
        return printPathRecursive(source, end, result).toString().substring(0, result.length()-1);
    }

    private StringBuilder printPathRecursive(Vertex<T> source, Vertex<T> end, StringBuilder result) {
        if (source.compareTo(end) == 0) {
            result.append(source.getValue() + "-");
        } else if (end.getParent() == null) {
            result = new StringBuilder("no path from " + source.getValue() + " to " + end.getValue() + " exists");
        } else {
            printPathRecursive(source, end.getParent(), result);
            result.append(end.getValue() + "-");
        }
        return result;
    }

    public void depthFirstSearch(Vertex<T> vertex) {
        time = 0;
        visit(vertex);
    }

    private void visit(Vertex<T> vertex) {
        time++;
        vertex.setDiscoveryTime(time);
        vertex.setColor(GraphColors.GRAY);

        for (Vertex<T> edge : this.graphMap.get(vertex)) {
            if (edge.getColor() == GraphColors.WHITE) {
                edge.setParent(vertex);
                visit(edge);
            }
        }
        time++;
        vertex.setFinishingTime(time);
        vertex.setColor(GraphColors.BLACK);
    }

    public void depthFirstSearch() {
        time = 0;

        for (Vertex<T> vertex : this.graphMap.keySet()) {
            if (vertex.getColor() == GraphColors.WHITE) {
                visit(vertex);
            }
        }
    }

    public Map<Vertex<T>, LinkedList<Vertex<T>>> getGraphMap() {
        return this.graphMap;
    }

    public int getEdgeWeight(Vertex<T> u, Vertex<T> v) {
        Edge<T> edge = new Edge<>(u, v);
        for(Edge<T> e : this.edges) {
            if(e.equals(edge)) {
                return e.weight;
            }
        }
        return 0;
    }

    public class Edge<T extends Comparable<? super T>> {
        private Vertex<T> u;

        private Vertex<T> v;

        private int weight;

        public Edge(Vertex<T> u, Vertex<T> v) {
            this.u = u;
            this.v = v;
        }

        public Edge(Vertex<T> u, Vertex<T> v, int weight) {
            this.u = u;
            this.v = v;
            this.weight = weight;
        }

        public Vertex<T> getU() {
            return u;
        }

        public Vertex<T> getV() {
            return v;
        }

        public int getWeight() {
            return weight;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Edge<?> edge = (Edge<?>) o;
            return Objects.equals(u.getValue(), edge.u.getValue()) &&
                    Objects.equals(v.getValue(), edge.v.getValue());
        }

        @Override
        public int hashCode() {
            return Objects.hash(u.getValue(), v.getValue());
        }
    }
}
