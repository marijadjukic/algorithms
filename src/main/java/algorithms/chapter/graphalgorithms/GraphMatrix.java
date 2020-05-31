package algorithms.chapter.graphalgorithms;

import lombok.Getter;

public class GraphMatrix {

    @Getter
    private int[][] graphMatrix;

    @Getter
    private Integer[][] predecessorMatrix;

    public GraphMatrix(int size) {
        this.graphMatrix = new int[size][size];
        this.predecessorMatrix = new Integer[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (i != j) {
                    graphMatrix[i][j] = Integer.MAX_VALUE;
                }
            }
        }
    }

    public void addEdge(int vertex1, int vertex2, int weight) {
        this.graphMatrix[vertex1][vertex2] = weight;
    }

    public String printAllPairsShortestPaths(Integer[][] predecessorMatrix, int i, int j) {
        StringBuilder result = new StringBuilder();
        return printAllPairsShortestPathsRecursive(predecessorMatrix, i, j, result).toString().substring(0, result.length()-1);
    }

    private StringBuilder printAllPairsShortestPathsRecursive(Integer[][] predecessorMatrix, int i, int j, StringBuilder result) {
        if (i == j) {
            result.append(i + "-");
        } else if (predecessorMatrix[i][j] == null) {
            result = new StringBuilder("no path from " + i + " to " + j + " exists");
        } else {
            printAllPairsShortestPathsRecursive(predecessorMatrix, i, predecessorMatrix[i][j], result);
            result.append(j + "-");
        }
        return result;
    }

}
