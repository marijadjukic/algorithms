package algorithms.chapter.advanceddesign.dynamicprogramming;

import algorithms.chapter.datastructures.BinarySearchTree;

public class OptimalBST {

    public int optimalBSTRecursive(int[] keys, int[] frequency) {
        return optimalBSTRecursive(keys, frequency,0,keys.length);
    }

    private int optimalBSTRecursive(int[] keys, int[] frequency, int start, int end) {
        if(start==end){
            return 0;
        }
        if(end-start==1) {
            return frequency[start];
        }

        int startFrequency = 0;
        for(int i=start; i<end; i++) {
            startFrequency += frequency[i];
        }
        int optimalFrequency = Integer.MAX_VALUE;

        for(int i=start; i<end; i++){
            int currentFrequency = optimalBSTRecursive(keys,frequency,start,i) + optimalBSTRecursive(keys,frequency,i+1,end) + startFrequency;
            if(currentFrequency < optimalFrequency) {
                optimalFrequency = currentFrequency;
            }
        }
        return optimalFrequency;
    }


    public int optimalBSTMemoized(int[] keys, int[] frequency){
        Integer[][] memo = new Integer[keys.length+1][keys.length+1];
        return optimalBSTMemoizedRecursive(frequency, memo,0,keys.length);
    }

    private int optimalBSTMemoizedRecursive(int[] frequency, Integer[][] memo, int start, int end) {
        if(memo[start][end] != null) {
            return memo[start][end];
        }
        if(start==end){
            memo[start][end] = 0;
            return memo[start][end];
        }
        if(end-start==1) {
            memo[start][end] = frequency[start];
            return memo[start][end];
        }

        int startFrequency = 0;
        for(int i=start; i<end; i++) {
            startFrequency += frequency[i];
        }

        for(int i=start; i<end; i++){
            int optimalFrequency = optimalBSTMemoizedRecursive(frequency, memo, start, i) + optimalBSTMemoizedRecursive(frequency, memo,i+1,end) + startFrequency;
            if(memo[start][end] == null || optimalFrequency < memo[start][end]) {
                memo[start][end] = optimalFrequency;
            }
        }
        return memo[start][end];
    }

    public OptimalBSTItem[][] optimalBSTBottomUp(int[] keys, int[] frequency) {
        int n = keys.length;
        OptimalBSTItem[][] optimalBSTMatrix = new OptimalBSTItem[n][n];
        int[][] currentFrequencyMatrix = new int[n][n];

        for(int i=0; i<n; i++) {
            optimalBSTMatrix[i][i] = new OptimalBSTItem(frequency[i]);
            currentFrequencyMatrix[i][i] = frequency[i];
        }

        for(int l=2; l<=n; l++) {
            for(int i=0; i<n-l+1; i++){
                int j = i + l - 1;
                optimalBSTMatrix[i][j] = new OptimalBSTItem(Integer.MAX_VALUE);
                currentFrequencyMatrix[i][j] = currentFrequencyMatrix[i][j-1] + frequency[j];
                for(int k=i; k<=j; k++){
                    int optimalFrequency;
                    if(k==i) {
                        optimalFrequency = currentFrequencyMatrix[i][j] + optimalBSTMatrix[k+1][j].getOptimalFrequency();
                    } else if(k==j) {
                        optimalFrequency = currentFrequencyMatrix[i][j] + optimalBSTMatrix[i][k-1].getOptimalFrequency();
                    } else {
                        optimalFrequency = currentFrequencyMatrix[i][j] + optimalBSTMatrix[i][k-1].getOptimalFrequency() + optimalBSTMatrix[k+1][j].getOptimalFrequency();
                    }
                    if(optimalFrequency < optimalBSTMatrix[i][j].getOptimalFrequency()) {
                        optimalBSTMatrix[i][j] = new OptimalBSTItem(optimalFrequency,k);
                    }
                }
            }
        }
        return optimalBSTMatrix;
    }

    public BinarySearchTree<Integer> getOptimalBST(int[] keys, int[] frequency) {
        BinarySearchTree<Integer> optimalBST = new BinarySearchTree<>();
        OptimalBSTItem[][] optimalBSTMatrix = optimalBSTBottomUp(keys,frequency);

        insertOptimalBSTRecursive(optimalBSTMatrix, keys, optimalBST, 0,keys.length-1);

        return optimalBST;
    }

    private void insertOptimalBSTRecursive(OptimalBSTItem[][] optimalBSTMatrix, int[] keys, BinarySearchTree<Integer> optimalBST, int i, int j) {
        if(i==j) {
            optimalBST.insert(keys[i]);
        } else if(i<j){
            int parentIndex = optimalBSTMatrix[i][j].getParent();
            optimalBST.insert(keys[parentIndex]);

            insertOptimalBSTRecursive(optimalBSTMatrix, keys, optimalBST, i, parentIndex - 1);
            insertOptimalBSTRecursive(optimalBSTMatrix, keys, optimalBST, parentIndex + 1, j);
        }
    }

}
