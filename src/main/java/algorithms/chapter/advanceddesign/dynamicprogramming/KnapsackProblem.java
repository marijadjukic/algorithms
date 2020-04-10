package algorithms.chapter.advanceddesign.dynamicprogramming;

import java.util.Arrays;

public class KnapsackProblem {

    public int knapsackProblemRecursive(int[] itemValues, int[] itemWeight, int sackWeight) {
        return knapsackProblemRecursive(itemValues,itemWeight,sackWeight,0,itemValues.length);
    }

    private int knapsackProblemRecursive(int[] itemValues, int[] itemWeight, int sackWeight, int start, int end) {
        if(end-start==0 || itemWeight[start]>sackWeight || sackWeight==0) {
            return 0;
        }
        int maxValue=0;

        for(int i=start; i<end; i++) {
            if(itemWeight[i]<=sackWeight) {
                int currentMaxValue = itemValues[i] + Math.max(
                        knapsackProblemRecursive(itemValues,itemWeight,sackWeight-itemWeight[i],start,i),
                        knapsackProblemRecursive(itemValues,itemWeight,sackWeight-itemWeight[i],i+1,end)
                );
                if(currentMaxValue > maxValue) {
                    maxValue = currentMaxValue;
                }
            }
        }
        return maxValue;
    }

    public int memoizedKnapsackProblem(int[] itemValues, int[] itemWeight, int sackWeight){
        int n = itemValues.length;
        Integer[][] memo = new Integer[n+1][sackWeight+1];
        return memoizedKnapsackProblemRecursive(memo, itemValues, itemWeight, sackWeight,0,n);
    }

    private int memoizedKnapsackProblemRecursive(Integer[][] memo, int[] itemValues, int[] itemWeight, int sackWeight, int start, int end){
        if(memo[start][sackWeight] != null) {
            return memo[start][sackWeight];
        }

        if(end-start==0 || itemWeight[start]>sackWeight || sackWeight==0){
            memo[start][sackWeight] = 0;
            return memo[start][sackWeight];
        }

        for(int i = start; i < end; i++) {
            if(itemWeight[i]<=sackWeight) {
                int currentMaxValue = itemValues[i] + Math.max(
                        memoizedKnapsackProblemRecursive(memo,itemValues,itemWeight,sackWeight-itemWeight[i],start,i),
                        memoizedKnapsackProblemRecursive(memo,itemValues,itemWeight,sackWeight-itemWeight[i],i+1,end)
                );
                if(memo[start][sackWeight] == null || currentMaxValue > memo[start][sackWeight]) {
                    memo[start][sackWeight] = currentMaxValue;
                }
            }
        }

        return memo[start][sackWeight];
    }

    public Integer[][] knapsackProblemBottomUp(int[] itemValues, int[] itemWeight, int sackWeight){
        int rows = itemValues.length;
        int columns = sackWeight + 1;
        Integer [][] matrix = new Integer[rows][columns];

        for(int i = 0; i < rows; i++) {
            matrix[i][0] = 0;
        }

        for(int j = 0; j <= sackWeight; j++) {
            if(itemWeight[0] <= j) {
                matrix[0][j] = itemValues[0];
            } else {
                matrix[0][j] = 0;
            }
        }

        for (int i = 1; i < rows; i++) {
            for (int j=1; j <= sackWeight; j++) {
                if (itemWeight[i]>j) {
                    matrix[i][j] = matrix[i-1][j];
                } else {
                    matrix[i][j] = Math.max(itemValues[i] + matrix[i - 1][j - itemWeight[i]], matrix[i - 1][j]);
                }
            }
        }

        return matrix;
    }

    public KnapsackProblemResultItem knapsackProblemBottomUpResult(int[] itemValues, int[] itemWeight, int sackWeight) {

        Integer[][] matrix = knapsackProblemBottomUp(itemValues, itemWeight,sackWeight);

        int row = itemValues.length-1;
        int column = sackWeight;

        KnapsackProblemResultItem knapsackProblemResultItem = new KnapsackProblemResultItem();
        knapsackProblemResultItem.setMaxItemValues(matrix[row][column]);

        int[] itemValuesResult = new int[itemValues.length];
        int[] itemWeightsResult = new int[itemWeight.length];
        int itemIndex = 0;

        while(row!=0) {
            int value = matrix[row][column];
            if(matrix[row-1][column] != value) {
                itemValuesResult[itemIndex] = itemValues[row];
                itemWeightsResult[itemIndex] = itemWeight[row];
                itemIndex++;
                column = column - itemWeight[row];
            }
            row--;
        }

        knapsackProblemResultItem.setItemValuesResult(removeZeros(itemValuesResult));
        knapsackProblemResultItem.setItemWeightsResult(removeZeros(itemWeightsResult));

        return knapsackProblemResultItem;
    }

    private int[] removeZeros(int[] array) {
        int zeroIndex = -1;
        do {
            zeroIndex++;
        } while (array[zeroIndex] != 0);

        return zeroIndex > 0 ? Arrays.copyOfRange(array,0,zeroIndex) : array;
    }

}
