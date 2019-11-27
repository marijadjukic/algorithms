package algorithms.chapter.one;

public class DivideAndConquer {

    public MaxSubarrayBean findMaximumSubarray(int[] array, int low, int high) {
        if (low==high) return new MaxSubarrayBean(low, high, array[low]);
        else {
            int mid = (low + high - 1)/2;
            MaxSubarrayBean leftMaxSubarray = findMaximumSubarray(array, low, mid);
            MaxSubarrayBean rightMaxSubarray = findMaximumSubarray(array, mid + 1, high);
            MaxSubarrayBean crossingMaxSubarray = findMaxCrossingSubarray(array, low, mid, high);
            if(leftMaxSubarray.getMaxSum() >= rightMaxSubarray.getMaxSum() 
                    && leftMaxSubarray.getMaxSum() >= crossingMaxSubarray.getMaxSum()) return leftMaxSubarray;
            else if(rightMaxSubarray.getMaxSum() >= leftMaxSubarray.getMaxSum() 
                    && rightMaxSubarray.getMaxSum() >= crossingMaxSubarray.getMaxSum()) return rightMaxSubarray;
            else return crossingMaxSubarray;
        }
    }

    private MaxSubarrayBean findMaxCrossingSubarray(int[] array, int low, int mid, int high) {
        int leftSum = Integer.MIN_VALUE;
        int rightSum = Integer.MIN_VALUE;
        int sum = 0;
        int maxLeft = mid;
        int maxRight = mid + 1;
        for(int i = mid; i>=low; i--) {
            sum = sum + array[i];
            if(sum > leftSum) {
                leftSum = sum;
                maxLeft = i;
            }
        }
        sum = 0;
        for(int j = mid + 1; j<= high; j++) {
            sum = sum + array[j];
            if(sum > rightSum) {
                rightSum = sum;
                maxRight = j;
            }
        }
        return new MaxSubarrayBean(maxLeft, maxRight, leftSum + rightSum);
    }

    // running time n^3
    public int[][] squareMatrixMultiply(int[][] A, int[][] B) {
        int n = A.length;
        int[][] C = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                C[i][j] = 0;
                for (int k = 0; k < n; k++)
                    C[i][j] = C[i][j] + A[i][k] * B[k][j];
            }
        }
        return C;
    }

    public int[][] squareMatrixMultiplyDAC(int[][] A, int[][] B) {
        int[][] C = new int[A.length][A.length];
        squareMatrixMultiplyRecursive(A, B, C,0, 0, 0, 0, A.length);
        return C;
    }

    // running time n^3
    public int[][] squareMatrixMultiplyRecursive(int[][] A, int[][] B, int[][] result, int aStart, int aEnd, int bStart, int bEnd, int n) {

        int[][] C = new int[n][n];
        int halfLength = n/2;

        if (n==1) {
            C[0][0] = A[aStart][aEnd]*B[bStart][bEnd];
        } else {
           copyToC(matrixSum(squareMatrixMultiplyRecursive(A,B,result,aStart,aEnd,bStart,bEnd,halfLength),
                            squareMatrixMultiplyRecursive(A,B,result,aStart,aEnd + halfLength,bStart + halfLength, bEnd, halfLength)),
                   result, aStart, bEnd, halfLength);
           copyToC(matrixSum(squareMatrixMultiplyRecursive(A,B,result,aStart,aEnd,bStart,bEnd + halfLength,halfLength),
                            squareMatrixMultiplyRecursive(A,B,result,aStart,aEnd + halfLength,bStart + halfLength, bEnd + halfLength, halfLength)),
                   result, aStart, bEnd + halfLength, halfLength);
           copyToC(matrixSum(squareMatrixMultiplyRecursive(A,B,result,aStart + halfLength, aEnd, bStart, bEnd, halfLength),
                            squareMatrixMultiplyRecursive(A,B,result,aStart + halfLength,aEnd + halfLength,bStart + halfLength, bEnd, halfLength)),
                   result, aStart + halfLength, bEnd, halfLength);
           copyToC(matrixSum(squareMatrixMultiplyRecursive(A,B,result,aStart + halfLength, aEnd, bStart, bEnd + halfLength,halfLength),
                            squareMatrixMultiplyRecursive(A,B,result,aStart + halfLength,aEnd + halfLength,bStart + halfLength, bEnd + halfLength, halfLength)),
                   result, aStart + halfLength, bEnd + halfLength, halfLength);
        }
        return C;
    }

    private int[][] matrixSum(int[][] A, int[][] B) {
        int n = A.length;
        int[][] C = new int[n][n];

        for (int i=0; i<n; i++) {
            for (int j=0; j<n; j++) {
                C[i][j] = A[i][j] + B[i][j];
            }
        }
        return C;
    }

    private void copyToC(int[][] copyFrom, int[][] copyTo, int cStart, int cEnd, int length) {
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                copyTo[cStart + i][cEnd + j] = copyTo[cStart + i][cEnd + j] + copyFrom[i][j];
            }
        }
    }

}