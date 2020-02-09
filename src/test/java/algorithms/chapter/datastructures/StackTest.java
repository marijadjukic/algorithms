package algorithms.chapter.datastructures;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class StackTest {

    private static int[] array = new int[]{15,6,2,9};
    private Stack stack = new Stack(array, 7);
    private Stack emptyStack = new Stack();

    @Test
    public void testEmptyStack() {
        assertTrue(emptyStack.stackEmpty());
        assertFalse(stack.stackEmpty());
    }

    @Test
    public void testPush(){
        stack.push(17);
        stack.push(3);
        assertThat(stack.size(), is(6));
    }

    @Test
    public void testPop() {
        int element = stack.pop();
        assertThat(element, is(9));
        assertThat(stack.size(),is(3));
    }

    @Test(expected = Error.class)
    public void testOverflow() {
        stack.push(17);
        stack.push(3);
        stack.push(1);
        assertThat(stack.size(),is(7));

        stack.push(8);

    }

    @Test(expected = Error.class)
    public void testStackUnderflow() {
        emptyStack.pop();
    }

}
