package algorithms.chapter.advanceddesign.dynamicprogramming;

import algorithms.util.ArraysUtil;

import java.util.Arrays;

public class CoinChangeProblem {

    public int coinChangeRecursive(int[] coins, int total) {
        int result = coinChangeRecursive(coins, total, 0, coins.length);
        return result == Integer.MAX_VALUE ? 0 : result;
    }

    private int coinChangeRecursive(int[] coins, int total, int start, int end) {
        if (total < 0) {
            return Integer.MAX_VALUE;
        }

        if (total == 0) {
            return 0;
        }

        int minNumCoinsUsed = Integer.MAX_VALUE;

        for(int i = start; i < end; i++) {
            int recurrence = coinChangeRecursive(coins, total - coins[i], i, end);
            int currentNumCoinsUsed = (recurrence == Integer.MAX_VALUE) ? Integer.MAX_VALUE : 1 + recurrence;

            if (currentNumCoinsUsed < minNumCoinsUsed) {
                minNumCoinsUsed = currentNumCoinsUsed;
            }
        }

        return minNumCoinsUsed;
    }

    public int coinChangeTotalNumSolutionRecursive(int[] coins, int total) {
        return coinChangeTotalNumSolutionRecursive(coins, total,0, coins.length);
    }

    private int coinChangeTotalNumSolutionRecursive(int[] coins, int total, int start, int end) {
        if (total < 0) {
            return 0;
        }

        if (total == 0) {
            return 1;
        }

        int totalNumSolution = 0;

        for(int i = start; i < end; i++) {
            totalNumSolution += coinChangeTotalNumSolutionRecursive(coins, total - coins[i], i, end);

        }

        return totalNumSolution;
    }

    public int memoizedCoinChange(int[] coins, int total) {
        Integer[][] memo = new Integer[coins.length][total+1];
        return memoizedCoinChangeRecursive(memo, coins, total, 0, coins.length);
    }

    private int memoizedCoinChangeRecursive(Integer[][] memo, int[] coins, int total, int start, int end) {
        if(total >= 0 && memo[start][total] != null) {
            return memo[start][total];
        }

        if (total < 0) {
            return Integer.MAX_VALUE;

        }

        if (total == 0) {
            memo[start][total] = 0;
            return memo[start][total];
        }

        for(int i = start; i < end; i++) {
            int recurrence = memoizedCoinChangeRecursive(memo, coins, total - coins[i], i, end);
            int currentNumCoinsUsed = (recurrence == Integer.MAX_VALUE) ? Integer.MAX_VALUE : 1 + recurrence;
            if (memo[start][total] == null || currentNumCoinsUsed < memo[start][total]) {
                memo[start][total] = currentNumCoinsUsed;
            }
        }

        return memo[start][total];
    }

    public int[][] coinChangeBottomUp(int[] coins, int total) {
        int row = coins.length + 1;
        int column = total + 1;
        int[][] coinChangeMatrix = new int[row][column];

        for (int j = 1; j < column; j++) {
            coinChangeMatrix[0][j] = Integer.MAX_VALUE;
        }

        for (int i = 0; i < row; i++) {
            coinChangeMatrix[i][0] = 0;
        }

        for (int i = 1; i < row; i++) {
            for (int j = 1; j < column; j++) {
                if (coins[i-1] > j) {
                    coinChangeMatrix[i][j] = coinChangeMatrix[i-1][j];
                } else if (coins[i-1] == j) {
                    coinChangeMatrix[i][j] = 1;
                } else {
                    int value = (coinChangeMatrix[i][j - coins[i-1]] == Integer.MAX_VALUE) ? Integer.MAX_VALUE : coinChangeMatrix[i][j - coins[i-1]] + 1;
                    coinChangeMatrix[i][j] = Math.min(coinChangeMatrix[i-1][j], value);
                }
            }
        }
        return coinChangeMatrix;
    }


    public int[] coinChangeBottomUpResult(int[] coins, int total) {
        int row = coins.length;
        int column = total;

        int[] coinChangeResult = new int[coins.length];
        int coinChangeResultIndex = 0;

        int[][] coinChangeMatrix = coinChangeBottomUp(coins, total);

        while  (column != 0) {
            if(coinChangeMatrix[row][column] != coinChangeMatrix[row - 1][column]) {
                coinChangeResult[coinChangeResultIndex] = coins[row - 1];
                coinChangeResultIndex++;
                column = column - coins[row - 1];
            } else {
                row--;
            }


        }
        return ArraysUtil.removeZeros(coinChangeResult);
    }

    public CoinChangeOptimizationItem coinChangeBottomUpSpaceOptimization(int[] coins, int total) {
        int row = coins.length;
        int column = total + 1;

        int[] coinChangeArray = new int[column];
        coinChangeArray[0] = 0;

        int[] coinDenominationArray = new int[column];

        for (int j = 1; j < column; j++) {
            coinChangeArray[j] = Integer.MAX_VALUE;
        }

        for (int j = 0; j < column; j++) {
            coinDenominationArray[j] = -1;
        }

        for (int i = 0; i < row; i++) {
            for (int j = 1; j < column; j++) {
                if (coins[i] > j) {
                    continue;
                } else if (coins[i] == j) {
                    coinChangeArray[j] = 1;
                    coinDenominationArray[j] = i;
                } else {
                    int value = (coinChangeArray[j - coins[i]] == Integer.MAX_VALUE) ? Integer.MAX_VALUE : coinChangeArray[j - coins[i]] + 1;
                    if(value < coinChangeArray[j]) {
                        coinChangeArray[j] = value;
                        coinDenominationArray[j] = i;
                    }
                }
            }
        }

        CoinChangeOptimizationItem coinChangeItem
                = new CoinChangeOptimizationItem(coinChangeArray[total],
                    getCoinDenominationResult(coins, coinDenominationArray, total));
        return coinChangeItem;
    }

    private int[] getCoinDenominationResult(int[] coins, int[] coinDenominationArray, int total) {
        int[] coinDenominationResult = new int[coins.length];
        int index = 0;

        while (total != 0) {
            coinDenominationResult[index] = coins[coinDenominationArray[total]];
            index++;
            total = total - coins[coinDenominationArray[total]];
        }

        return ArraysUtil.removeZeros(coinDenominationResult);
    }

}
