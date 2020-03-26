package algorithms.chapter.advanceddesign.dynamicprogramming;

public class MatrixChainMultiplication {

    public MatrixChainItem[][] matrixChainOrderRecursive(int[] p) {
        int n = p.length - 1;
        MatrixChainItem[][] matrix = new MatrixChainItem[n][n];
        return matrixChainOrderRecursive(matrix, p, 0, n-1);
    }

    private MatrixChainItem[][] matrixChainOrderRecursive(MatrixChainItem[][] matrix, int[] p, int i, int j) {
        if(i==j){
            matrix[i][i] = new MatrixChainItem(0);
            return matrix;
        }

        matrix[i][j] = new MatrixChainItem(Integer.MAX_VALUE);
        for(int k=i; k<j; k++) {
            int q = matrixChainOrderRecursive(matrix,p,i,k)[i][k].getMultiplicationCost() + matrixChainOrderRecursive(matrix,p,k+1,j)[k + 1][j].getMultiplicationCost() + p[i] * p[k + 1] * p[j + 1];
            if (q < matrix[i][j].getMultiplicationCost()) {
                matrix[i][j].setMultiplicationCost(q);
                matrix[i][j].setOptimalCostIndex(k);
            }
        }
        return matrix;
    }

    public MatrixChainItem[][] matrixChainOrderMemoized(int[] p) {
        int n = p.length - 1;
        MatrixChainItem[][] matrix = new MatrixChainItem[n][n];
        return matrixChainOrderMemoizedRecursive(matrix, p, 0, n-1);
    }

    private MatrixChainItem[][] matrixChainOrderMemoizedRecursive(MatrixChainItem[][] matrix, int[] p, int i, int j) {
        if(matrix[i][j] != null) {
            return matrix;
        }
        if(i==j){
            matrix[i][i] = new MatrixChainItem(0);
        } else {
            matrix[i][j] = new MatrixChainItem(Integer.MAX_VALUE);
            for (int k = i; k < j; k++) {
                int q = matrixChainOrderRecursive(matrix, p, i, k)[i][k].getMultiplicationCost() + matrixChainOrderRecursive(matrix, p, k + 1, j)[k + 1][j].getMultiplicationCost() + p[i] * p[k + 1] * p[j + 1];
                if (q < matrix[i][j].getMultiplicationCost()) {
                    matrix[i][j].setMultiplicationCost(q);
                    matrix[i][j].setOptimalCostIndex(k);
                }
            }
        }
        return matrix;
    }

    public MatrixChainItem[][] matrixChainOrderBottomUp(int[] p) {
        int n = p.length - 1;
        MatrixChainItem[][] matrix = new MatrixChainItem[n][n];

        for(int i=0; i<n; i++) {
            matrix[i][i] = new MatrixChainItem(0);
        }

        for(int l=2; l<=n; l++) {
            for(int i=0; i<n-l+1; i++) {
                int j = i + l - 1;
                matrix[i][j] = new MatrixChainItem(Integer.MAX_VALUE);
                for(int k=i; k<j; k++) {
                    int q = matrix[i][k].getMultiplicationCost() + matrix[k+1][j].getMultiplicationCost() + p[i]*p[k+1]*p[j+1];
                    if(q<matrix[i][j].getMultiplicationCost()) {
                        matrix[i][j].setMultiplicationCost(q);
                        matrix[i][j].setOptimalCostIndex(k);
                    }
                }
            }
        }
        return matrix;
    }

    public String getOptimalParenthesizeRecursiveMultiplication(int[] p) {
        MatrixChainItem[][] matrix = matrixChainOrderRecursive(p);
        return getOptimalParenthesizeRecursive(matrix, 0, p.length-2, "");
    }

    public String getOptimalParenthesizeMemoizedMultiplication(int[] p) {
        MatrixChainItem[][] matrix = matrixChainOrderMemoized(p);
        return getOptimalParenthesizeRecursive(matrix, 0, p.length-2, "");
    }

    public String getOptimalParenthesizeBottomUpMultiplication(int[] p) {
        MatrixChainItem[][] matrix = matrixChainOrderBottomUp(p);
        return getOptimalParenthesizeRecursive(matrix, 0, p.length-2, "");
    }

    private String getOptimalParenthesizeRecursive(MatrixChainItem[][] matrix, int i, int j, String solution) {
        if(i==j) {
            solution = "A" + i;
        } else {
            solution = "("
                    + getOptimalParenthesizeRecursive(matrix, i, matrix[i][j].getOptimalCostIndex(),solution)
                    + getOptimalParenthesizeRecursive(matrix, matrix[i][j].getOptimalCostIndex() + 1, j, solution)
                    + ")";
        }
        return solution;
    }

}
