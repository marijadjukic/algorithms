package algorithms.chapter.advanceddesign.dynamicprogramming;

import java.util.Arrays;

public class MinJumpsProblem {

    public int minJumpsRecursive(int[] jumps) {
        return minJumpsRecursive(jumps, 0, jumps[0]);
    }

    private int minJumpsRecursive(int[] jumps, int start, int end) {
        if(start == jumps.length-1) {
            return 0;
        }

        if(jumps[start] == 0) {
            return Integer.MAX_VALUE;
        }

        int minNumJumps = Integer.MAX_VALUE;

        for (int i = 1; i <= end; i++) {
            if(start + i < jumps.length) {
                int recurrence = minJumpsRecursive(jumps, start + i, jumps[start + i]);
                int current = (recurrence == Integer.MAX_VALUE) ? Integer.MAX_VALUE : 1 + recurrence;

                if (current < minNumJumps) {
                    minNumJumps = current;
                }
            }
        }

        return minNumJumps;
    }

    public int memoizedMinJumps(int[] jumps) {
        Integer[] memo = new Integer[jumps.length];
        return memoizedMinJumpsRecursive(memo, jumps, 0, jumps[0]);
    }

    private int memoizedMinJumpsRecursive(Integer[] memo, int[] jumps, int start, int end) {
        if(memo[start] != null) {
            return memo[start];
        }
        if(start == jumps.length-1) {
            memo[start] = 0;
            return memo[start];
        }

        if(jumps[start] == 0) {
            memo[start] = Integer.MAX_VALUE;
            return memo[start];
        }

        for (int i = 1; i <= end; i++) {
            if(start + i < jumps.length) {
                int recurrence = memoizedMinJumpsRecursive(memo, jumps, start + i, jumps[start + i]);
                int current = (recurrence == Integer.MAX_VALUE) ? Integer.MAX_VALUE : 1 + recurrence;

                if (memo[start] == null || current < memo[start]) {
                    memo[start] = current;
                }
            }
        }
        return memo[start];
    }

    public MinJumpsItem minJumpsBottomUp(int[] jumps) {
        int n = jumps.length;

        int[] minNumJumpsArray = new int[n];

        int[] jumpsElements = new int[n];
        jumpsElements[0] = -1;

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if(j + jumps[j] >= i) {
                    if (minNumJumpsArray[i] != 0) {
                        if(minNumJumpsArray[j] + 1 < minNumJumpsArray[i]){
                            minNumJumpsArray[i] = minNumJumpsArray[j] + 1;
                            jumpsElements[i] = j;
                        }
                    } else {
                        minNumJumpsArray[i] = minNumJumpsArray[j] + 1;
                        jumpsElements[i] = j;
                    }
                }
            }
        }

        MinJumpsItem minJumpsItem = new MinJumpsItem(minNumJumpsArray[n-1],
                getJumpsResult(jumps,jumpsElements));

        return minJumpsItem;
    }

    private int[] getJumpsResult(int[] jumps, int[] jumpsElements) {
        int n = jumps.length;
        int[] jumpResult = new int[n];

        int i = n - 1;
        jumpResult[i] = i;

        int index = i-1;
        int counter = 1;

        while(i != 0) {
            i = jumpsElements[i];
            jumpResult[index] = i;
            index--;
            counter++;
        }

        return Arrays.copyOfRange(jumpResult, n - counter, n);
    }

}
