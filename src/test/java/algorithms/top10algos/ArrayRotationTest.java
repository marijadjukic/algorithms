package algorithms.top10algos;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class ArrayRotationTest {

    private static final int[] ARRAY = new int[] {1,2,3,4,5,6,7};
    private ArrayRotation ar = new ArrayRotation();

    @Test
    public void testArrayRotation() {
        int[] rotatedArray = ar.rotateArray(ARRAY, 3);

        assertThat(rotatedArray, is(new int[]{5,6,7,1,2,3,4}));
    }

    @Test
    public void testArrayRotationFast() {
        int[] rotatedArray = ar.rotateArrayFast(ARRAY, 3);

        assertThat(rotatedArray, is(new int[]{5,6,7,1,2,3,4}));
    }

}
