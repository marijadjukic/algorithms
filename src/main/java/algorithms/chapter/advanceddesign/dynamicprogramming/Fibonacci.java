package algorithms.chapter.advanceddesign.dynamicprogramming;

import java.util.HashMap;
import java.util.Map;

public class Fibonacci {

    private Map<Integer, Integer> memo = new HashMap<>();
    private int[] fib = new int[2];

    public int fibonacci(int n) {
        int f;
        if(n==0) {
            f = 0;
        } else if(n<=2) {
            f = 1;
        } else {
            f = fibonacci(n-1) + fibonacci(n-2);
        }
        return f;
    }

    public int memoizedFibonacci(int n) {
        if(memo.keySet().contains(n)){
            return memo.get(n);
        }
        int f;
        if(n==0) {
            f = 0;
        } else if(n<=2) {
            f = 1;
        } else {
            f = memoizedFibonacci(n-1) + memoizedFibonacci(n-2);
        }
        memo.put(n,f);
        return f;
    }

    public int bottomUpFibonacci(int n) {
        int f;
        for(int k=0; k<=n; k++) {
            if(k==0) {
                f = 0;
            } else if(k<=2) {
                f = 1;
            } else {
                f = fib[1] + fib[0];
            }
            int prev = fib[1];
            fib[0] = prev;
            fib[1] = f;
        }
        return fib[1];
    }

}
