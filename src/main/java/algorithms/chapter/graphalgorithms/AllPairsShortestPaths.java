package algorithms.chapter.graphalgorithms;

public class AllPairsShortestPaths {

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
