package algorithms.chapter.advanceddesign.dynamicprogramming;

import java.util.Arrays;

public class RodCutting {

    public int cutRodRecursive(int[] profit, int rodLength) {
        if(rodLength == 0){
            return 0;
        }
        int q = 0;
        for(int i = 1; i<=profit.length && rodLength-i>=0; i++){
            q = Math.max(q, profit[i-1] + cutRodRecursive(profit,rodLength-i));
        }
        return q;
    }

    public int memoizedCutRod(int[] profit, int rodLength) {
        Integer[] memo = new Integer[rodLength+1];
        return memoizedCutRodRecursive(profit,rodLength, memo);
    }

    private int memoizedCutRodRecursive(int[] profit, int rodLength, Integer[] memo){
        if(memo[rodLength]!=null) {
            return memo[rodLength];
        }
        int q = 0;
        if(rodLength == 0){
            memo[0] = 0;
        } else {
            for(int i = 1; i<=profit.length && rodLength-i>=0; i++){
                q = Math.max(q, profit[i-1] + memoizedCutRodRecursive(profit,rodLength-i, memo));
            }
        }
        memo[rodLength] = q;
        return q;
    }

    public int bottomUpCutRod(int[] profit, int rodLength){
        int[] revenue = new int[rodLength+1];
        revenue[0] = 0;
        for(int i = 1; i<=profit.length; i++) {
            int q;
            for(int j = i; j <= rodLength; j++) {
                q = Math.max(revenue[j], profit[i-1] + revenue[j-i]);
                revenue[j] = q;
            }
        }
        return revenue[rodLength];
    }

    public int[] extendedBottomUpCutRod(int[] profit, int rodLength) {
        int[][] revenue = new int[profit.length+1][rodLength+1];
        int[] optimalPieceSizes = new int[rodLength];
        for(int i = 0; i<=rodLength; i++) {
            revenue[0][i] = i;
        }
        for(int i = 1; i<=profit.length; i++) {
            for(int j = 0; j <= rodLength; j++) {
                if(j>=i) {
                    revenue[i][j] = Math.max(revenue[i - 1][j], profit[i-1] + revenue[i][j - i]);
                } else {
                    revenue[i][j] = revenue[i-1][j];
                }
            }
        }

        int k = 0;
        int row = profit.length;
        int column = rodLength;
        while(row!=0) {
            if(revenue[row][column] != revenue[row-1][column]){
                column = column-row;
                optimalPieceSizes[k] = row;
                k++;
            } else {
                row--;
            }
        }
        return removeZeros(optimalPieceSizes);
    }

    private int[] removeZeros(int[] array) {
        Arrays.sort(array);
        int index = -1;
        while((index = Arrays.binarySearch(array, 0)) > -1){
            int[] newArr = new int[array.length-index-1];
            System.arraycopy(array, index+1, newArr, 0, newArr.length);
            array = newArr;
        }
        return array;
    }

}
