package algorithms.chapter.graphalgorithms;

import algorithms.chapter.advanceddatastructures.DisjointSet;
import algorithms.chapter.advanceddatastructures.Edge;
import algorithms.chapter.advanceddatastructures.Graph;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class MinimumSpanningTree<T extends Comparable<? super T>> {

    private DisjointSet<T> disjointSet = new DisjointSet<>();

    public Set<Edge<T>> kruskalMinimumSpanningTree(Graph<T> graph) {
        Set<Edge<T>> forest = new HashSet<>();

        for (T vertex : graph.getVertices()) {
            disjointSet.makeSet(vertex);
        }

        graph.getEdges().sort(Comparator.comparingInt(Edge::getWeight));

        for(Edge<T> edge : graph.getEdges()) {
            if (disjointSet.findSet((T) edge.getXVertex()).compareTo(disjointSet.findSet((T) edge.getYVertex())) != 0) {
                forest.add(edge);
                disjointSet.union(edge.getXVertex(), edge.getYVertex());
            }
        }

        return forest;
    }

    public int getSpanningTreeWeight(Set<Edge<T>> spanningTree) {
        int spanningTreeWeight = 0;
        for(Edge<T> edge : spanningTree) {
            spanningTreeWeight += edge.getWeight();
        }
        return spanningTreeWeight;
    }

}
