package algorithms.chapter.advanceddatastructures;

public class GraphComponents<T extends Comparable<? super T>> {

    private DisjointSet<T> disjointSet = new DisjointSet<>();

    public void connectedComponents(Graph<T> graph) {
        for (T vertex : graph.getVertices()) {
            disjointSet.makeSet(vertex);
        }
        for (Edge edge : graph.getEdges()) {
            if (disjointSet.findSet((T) edge.getXVertex()).compareTo(disjointSet.findSet((T) edge.getYVertex())) != 0) {
                disjointSet.union((T)edge.getXVertex(), (T) edge.getYVertex());
            }
        }
    }

    public boolean isSameComponent(T u, T v) {
        return disjointSet.findSet(u).compareTo(disjointSet.findSet(v)) == 0;
    }

    public DisjointSet<T> getDisjointSet() {
        return this.disjointSet;
    }

}
