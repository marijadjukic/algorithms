package algorithms.chapter.graphalgorithms;

public class FloydWarshall {

    public int[][] floydWarshallRecursive(GraphMatrix graph) {
        int n = graph.getGraphMatrix().length;
        int[][] distanceMatrix = graph.getGraphMatrix();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                distanceMatrix = floydWarshallRecursive(graph, distanceMatrix, i, j, n);
            }
        }
        return distanceMatrix;
    }

    private int[][] floydWarshallRecursive(GraphMatrix graph, int[][] distanceMatrix, int i, int j, int k) {
        if (k == 0) {
            distanceMatrix[i][j] = graph.getGraphMatrix()[i][j];
            return distanceMatrix;
        }

        int direct = floydWarshallRecursive(graph, distanceMatrix, i, j, k-1)[i][j];
        int left = floydWarshallRecursive(graph, distanceMatrix, i, k-1, k-1)[i][k-1];
        int right = floydWarshallRecursive(graph, distanceMatrix, k-1, j, k-1)[k-1][j];
        int sum = (left == Integer.MAX_VALUE || right == Integer.MAX_VALUE) ? Integer.MAX_VALUE : left + right;

        distanceMatrix[i][j] = Math.min(distanceMatrix[i][j], Math.min(direct, sum));

        return distanceMatrix;
    }

    public int[][] floydWarshallBottomUp(GraphMatrix graph) {
        int[][] weightsMatrix = graph.getGraphMatrix();
        int n = weightsMatrix.length;

        int[][] shortestPathsWeights = weightsMatrix;
        Integer[][] shortestPathsPredecessor = this.initPredecessorMatrix(weightsMatrix, n);

        for (int k = 0; k < n; k++) {
            int[][] prevIteration = shortestPathsWeights;
            int[][] shortestPathsWeightsExtended = new int[n][n];
            Integer[][] shortestPathsPredecessorExtended = new Integer[n][n];
            Integer[][] prevPredecessor = shortestPathsPredecessor;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    int directPath = prevIteration[i][j];
                    int leftPath = prevIteration[i][k];
                    int rightPath = prevIteration[k][j];
                    int sum = (leftPath == Integer.MAX_VALUE || rightPath == Integer.MAX_VALUE)
                            ? Integer.MAX_VALUE
                            : leftPath + rightPath;
                    shortestPathsWeightsExtended[i][j] = Math.min(directPath, sum);

                    if (directPath <= sum) {
                        shortestPathsPredecessorExtended[i][j] = prevPredecessor[i][j];
                    } else {
                        shortestPathsPredecessorExtended[i][j] = prevPredecessor[k][j];
                    }
                }
            }
            shortestPathsWeights = shortestPathsWeightsExtended;
            shortestPathsPredecessor = shortestPathsPredecessorExtended;
        }

        graph.setPredecessorMatrix(shortestPathsPredecessor);
        return shortestPathsWeights;
    }

    public Integer[][] floydWarshallAllPathsMatrix(GraphMatrix graph) {
        this.floydWarshallBottomUp(graph);
        return graph.getPredecessorMatrix();
    }

    private Integer[][] initPredecessorMatrix(int[][] weightsMatrix, int size) {
        Integer[][] shortestPathsPredecessor = new Integer[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if(i != j && weightsMatrix[i][j] != Integer.MAX_VALUE) {
                    shortestPathsPredecessor[i][j] = i;
                }
            }
        }
        return shortestPathsPredecessor;
    }

    public int[][] transitiveClosure(GraphMatrix graph) {
        int n = graph.getGraphMatrix().length;
        int[][] transitiveClosure = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if(i == j || graph.getGraphMatrix()[i][j] != Integer.MAX_VALUE) {
                    transitiveClosure[i][j] = 1;
                } else {
                    transitiveClosure[i][j] = 0;
                }
            }
        }

        for (int k = 0; k < n; k++) {
            int[][] prevIteration = transitiveClosure;
            int[][] transitiveClosureExtended = new int[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    transitiveClosureExtended[i][j] = prevIteration[i][j] | (prevIteration[i][k] & prevIteration[k][j]);
                }
            }
            transitiveClosure = transitiveClosureExtended;
        }

        return transitiveClosure;
    }

}
