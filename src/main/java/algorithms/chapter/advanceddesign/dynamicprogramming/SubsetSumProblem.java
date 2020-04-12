package algorithms.chapter.advanceddesign.dynamicprogramming;

import java.util.Arrays;

public class SubsetSumProblem {

    public boolean subsetSumProblemRecursive(int[] set, int sum) {
        return subsetSumProblemRecursive(set, sum, set.length);
    }

    private boolean subsetSumProblemRecursive(int[] set, int sum, int n) {

        if(sum == 0) {
            return true;
        }
        if(sum < 0 || (n == 0 && sum != 0)) {
            return false;
        }

        boolean isSubsetSum = subsetSumProblemRecursive(set,sum-set[n-1], n-1) ||
                subsetSumProblemRecursive(set,sum, n-1);

        return isSubsetSum;
    }


    public boolean subsetSumProblemRecursiveSolution2(int[] set, int sum) {
        return subsetSumProblemRecursiveSolution2(set, sum, 0);
    }

    private boolean subsetSumProblemRecursiveSolution2(int[] set, int sum, int start) {

        if(sum == 0) {
            return true;
        }
        if(sum < 0 || (start == set.length-1 && sum != 0)) {
            return false;
        }

        boolean isSubsetSum = false;
        for (int i = start; i<set.length; i++) {
            isSubsetSum = isSubsetSum || subsetSumProblemRecursiveSolution2(set, sum - set[i], i++);
        }

        return isSubsetSum;
    }

    public boolean memoizedSubsetSumProblem(int[] set, int sum) {
        Boolean[][] memo = new Boolean[set.length+1][sum+1];
        return  memoizedSubsetSumProblemRecursive(memo, set, sum, set.length);
    }

    private boolean memoizedSubsetSumProblemRecursive(Boolean[][] memo, int[] set, int sum, int n) {
        if (sum>=0 && memo[n][sum] != null) {
            return memo[n][sum];
        }

        if(sum==0) {
            memo[n][sum] = true;
            return memo[n][sum];
        }

        if (sum < 0 || (n == 0 && sum != 0)) {
            if(sum < 0) {
                return false;
            } else {
                memo[n][sum] = false;
                return memo[n][sum];
            }
        }

        boolean isSubsetSum = memoizedSubsetSumProblemRecursive(memo, set,sum-set[n-1], n-1) ||
                memoizedSubsetSumProblemRecursive(memo, set, sum, n-1);

        if(memo[n][sum] == null || isSubsetSum) {
            memo[n][sum] = isSubsetSum;
        }

        return memo[n][sum];
    }

    public boolean[][] subsetSumProblemBottomUp(int[] set, int sum) {
        int row = set.length;
        int column = sum + 1;

        boolean[][] result = new boolean[row][column];

        for(int i=0; i<row; i++) {
            result[i][0] = true;
        }

        for(int j=1; j<=sum; j++) {
            if(j == set[0]) {
                result[0][j] = true;
            } else {
                result[0][j] = false;
            }
        }

        for(int i=1; i<row; i++) {
            for(int j=1; j<=sum; j++) {
                if(result[i-1][j] || set[i]>j) {
                    result[i][j] = result[i-1][j];
                } else {
                    result[i][j] = result[i-1][j-set[i]];
                }
            }
        }

        return result;
    }

    public int[] subsetSumProblemBottomUpResult(int[] set, int sum) {
        int[] subset = new int[set.length];
        int subsetIndex = 0;

        int row = set.length - 1;
        int column = sum;

        boolean[][] subsetSumMatrix = subsetSumProblemBottomUp(set, sum);

        while(column != 0) {
            boolean value = subsetSumMatrix[row][column];
            if(value != subsetSumMatrix[row-1][column]) {
                subset[subsetIndex] = set[row];
                subsetIndex++;
                column = column - set[row];
            }
            row--;

        }
        return removeZeros(subset);
    }

    private int[] removeZeros(int[] array) {
        int zeroIndex = -1;
        do {
            zeroIndex++;
        } while (array[zeroIndex] != 0);

        return zeroIndex > 0 ? Arrays.copyOfRange(array,0,zeroIndex) : array;
    }

}
