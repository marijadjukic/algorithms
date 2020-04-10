package algorithms.chapter.advanceddesign.dynamicprogramming;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class KnapsackProblemTest {

    private static final int[] itemValues = new int[]{60,100,120};
    private static final int[] itemWeights = new int[]{10,20,30};
    private static final int SACK_WEIGHT = 50;

    private static final int[] itemValues2 = new int[]{4, 2, 1, 2, 10};
    private static final int[] itemWeights2 = new int[]{12, 2, 1, 1, 4};
    private static final int SACK_WEIGHT_2 = 15;

    private static final int[] itemValues3 = new int[]{1,4,5,7};
    private static final int[] itemWeights3 = new int[]{1,3,4,5};
    private static final int SACK_WEIGHT_3 = 7;

    private KnapsackProblem kp = new KnapsackProblem();

    @Test
    public void testKnapsackProblemRecursive() {
        assertThat(kp.knapsackProblemRecursive(itemValues, itemWeights, SACK_WEIGHT), is(220));
        assertThat(kp.knapsackProblemRecursive(itemValues2, itemWeights2, SACK_WEIGHT_2), is(15));
        assertThat(kp.knapsackProblemRecursive(itemValues3, itemWeights3, SACK_WEIGHT_3), is(9));
    }

    @Test
    public void testMemoizedKnapsackProblem() {
        assertThat(kp.memoizedKnapsackProblem(itemValues, itemWeights, SACK_WEIGHT), is(220));
        assertThat(kp.memoizedKnapsackProblem(itemValues2, itemWeights2, SACK_WEIGHT_2), is(15));
        assertThat(kp.memoizedKnapsackProblem(itemValues3, itemWeights3, SACK_WEIGHT_3), is(9));
    }

    @Test
    public void testKnapsackProblemBottomUp() {
        KnapsackProblemResultItem result1 = kp.knapsackProblemBottomUpResult(itemValues, itemWeights, SACK_WEIGHT);
        assertArrayEquals(new int[]{120,100}, result1.getItemValuesResult());
        assertArrayEquals(new int[]{30,20}, result1.getItemWeightsResult());
        assertThat(result1.getMaxItemValues(), is(220));

        KnapsackProblemResultItem result2 = kp.knapsackProblemBottomUpResult(itemValues2, itemWeights2, SACK_WEIGHT_2);
        assertArrayEquals(new int[]{10,2,1,2}, result2.getItemValuesResult());
        assertArrayEquals(new int[]{4,1,1,2}, result2.getItemWeightsResult());
        assertThat(result2.getMaxItemValues(), is(15));

        KnapsackProblemResultItem result3 = kp.knapsackProblemBottomUpResult(itemValues3, itemWeights3, SACK_WEIGHT_3);
        assertArrayEquals(new int[]{5,4}, result3.getItemValuesResult());
        assertArrayEquals(new int[]{4,3}, result3.getItemWeightsResult());
        assertThat(result3.getMaxItemValues(), is(9));
    }

}
