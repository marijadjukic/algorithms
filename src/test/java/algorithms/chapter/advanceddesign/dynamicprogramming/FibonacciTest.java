package algorithms.chapter.advanceddesign.dynamicprogramming;

import org.junit.Test;

import java.text.MessageFormat;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class FibonacciTest {

    private Fibonacci fib = new Fibonacci();

    @Test
    public void testFibonacci() {
        assertThat(fib.fibonacci(0), is(0));
        assertThat(fib.fibonacci(1), is(1));
        assertThat(fib.fibonacci(2), is(1));
        assertThat(fib.fibonacci(3), is(2));
        assertThat(fib.fibonacci(4), is(3));
        assertThat(fib.fibonacci(5), is(5));
        assertThat(fib.fibonacci(6), is(8));

        long start = System.currentTimeMillis();
        int fibNumber = fib.fibonacci(40);
        long end = System.currentTimeMillis();
        System.out.println(MessageFormat.format(
                "RECURSIVE -> 40th fibonacci number is {0}. Time: {1}",
                fibNumber,end-start));
    }

    @Test
    public void testMemoizedFibonacci() {
        assertThat(fib.memoizedFibonacci(0), is(0));
        assertThat(fib.memoizedFibonacci(1), is(1));
        assertThat(fib.memoizedFibonacci(2), is(1));
        assertThat(fib.memoizedFibonacci(3), is(2));
        assertThat(fib.memoizedFibonacci(4), is(3));
        assertThat(fib.memoizedFibonacci(5), is(5));
        assertThat(fib.memoizedFibonacci(6), is(8));

        long start = System.currentTimeMillis();
        int fibNumber = fib.memoizedFibonacci(40);
        long end = System.currentTimeMillis();
        System.out.println(MessageFormat.format(
                "MEMOIZED -> 40th fibonacci number is {0}. Time: {1}",
                fibNumber,end-start));
    }

    @Test
    public void testBottomUpFibonacci() {
        assertThat(fib.bottomUpFibonacci(0), is(0));
        assertThat(fib.bottomUpFibonacci(1), is(1));
        assertThat(fib.bottomUpFibonacci(2), is(1));
        assertThat(fib.bottomUpFibonacci(3), is(2));
        assertThat(fib.bottomUpFibonacci(4), is(3));
        assertThat(fib.bottomUpFibonacci(5), is(5));
        assertThat(fib.bottomUpFibonacci(6), is(8));

        long start = System.currentTimeMillis();
        int fibNumber = fib.bottomUpFibonacci(40);
        long end = System.currentTimeMillis();
        System.out.println(MessageFormat.format(
                "BOTTOM-UP -> 40th fibonacci number is {0}. Time: {1}",
                fibNumber,end-start));
    }


}
