package algorithms.chapter.sorting;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class LinearSelectionTest {

    private static final int[] array = new int[]{3,2,9,0,7,5,4,8,6,1};

    private LinearSelection ls = new LinearSelection();

    @Test
    public void testRandomizedSelect() {
        assertThat(ls.randomizedSelect(array, 3), is(2));
        assertThat(ls.randomizedSelect(array,1), is(0));
        assertThat(ls.randomizedSelect(array, 10), is(9));
    }

}
