package algorithms.chapter.graphalgorithms;

public class AllPairsShortestPaths {

    /**
     * Returns a matrix with computed all pairs shortest paths for a given graph
     * in O(n4) time where n is the number of rows in matrix with edge weights
     * @param graph matrix representation of the graph
     * @return int[n][n]
     */
    public int[][] slowAllPairsShortestPaths(GraphMatrix graph) {
        int[][] weightsMatrix = graph.getGraphMatrix();
        int n = weightsMatrix.length;

        int[][] shortestPathsWeights = weightsMatrix;

        for (int m = 1; m < n - 1; m++) {
            int[][] current = shortestPathsWeights;
            int[][] shortestPathsWeightsExtended = this.extendedShortestPaths(current, weightsMatrix);
            shortestPathsWeights = shortestPathsWeightsExtended;
        }
        return shortestPathsWeights;
    }

    /**
     * Returns a matrix with computed all pairs shortest paths for a given graph
     * in O(n3 lgn) time where n is the number of rows in matrix with edge weights
     * @param graph matrix representation of the graph
     * @return int[n][n]
     */
    public int[][] fasterAllPairsShortestPaths(GraphMatrix graph) {
        int[][] weightsMatrix = graph.getGraphMatrix();
        int n = weightsMatrix.length;

        int[][] shortestPathsWeights = weightsMatrix;
        int m = 1;
        while (m < n - 1) {
            int[][] current = shortestPathsWeights;
            int[][] shortestPathsWeightsExtended = this.extendedShortestPaths(current, current);
            shortestPathsWeights = shortestPathsWeightsExtended;
            m = 2*m;
        }
        return shortestPathsWeights;
    }

    private int[][] extendedShortestPaths(int[][] shortestPathsWeights, int[][] weightsMatrix) {
        int n = shortestPathsWeights.length;

        int[][] shortestPathsWeightsExtended = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                shortestPathsWeightsExtended[i][j] = Integer.MAX_VALUE;
                for (int k = 0; k < n; k++) {
                    int weight = (shortestPathsWeights[i][k] == Integer.MAX_VALUE || weightsMatrix[k][j] == Integer.MAX_VALUE)
                            ? Integer.MAX_VALUE
                            : shortestPathsWeights[i][k] + weightsMatrix[k][j];
                    shortestPathsWeightsExtended[i][j] = Math.min(
                            shortestPathsWeightsExtended[i][j],
                            weight);
                }
            }
        }
        return shortestPathsWeightsExtended;
    }

}
