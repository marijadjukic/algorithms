package algorithms.chapter.graphalgorithms;

public class JohnsonAllPairsShortestPaths {

    private SingleSourceShortestPaths<Integer> sssp = new SingleSourceShortestPaths<>();

    public Integer[][] johnson(Graph<Integer> graph) {
        Vertex<Integer> source = new Vertex<>(0);
        source.setColor(GraphColors.BLACK);
        Graph<Integer> reweightedGraph = new Graph<>(graph);
        reweightedGraph.add(source);

        for (Vertex<Integer> vertex : graph.getGraphMap().keySet()) {
            reweightedGraph.add(source, vertex, 0);
        }

        if (!sssp.bellmanFord(reweightedGraph, source)) {
            return null;
        } else {
            for (Vertex<Integer> vertex : reweightedGraph.getGraphMap().keySet()) {
                vertex.setReweightedDistance(vertex.getDistance());
            }

            for (Graph.Edge edge : reweightedGraph.getEdges()) {
                edge.setWeight(edge.getWeight() + edge.getU().getReweightedDistance() - edge.getV().getReweightedDistance());
            }

            graph.remove(source);
            int n = graph.getGraphMap().size();
            Integer[][] allShortestPathsMatrix = new Integer[n][n];

            for (Vertex<Integer> u : graph.getGraphMap().keySet()) {
                sssp.dijkstra(graph, u);
                for (Vertex<Integer> v : graph.getGraphMap().keySet()) {
                    allShortestPathsMatrix[u.getValue() - 1][v.getValue() - 1] =
                            v.getDistance() + v.getReweightedDistance() - u.getReweightedDistance();
                }
            }
            return allShortestPathsMatrix;
        }
    }

}
