package algorithms.chapter.advanceddesign.greedyalgorithms;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class FractionalKnapsackProblemTest {

    private static final int[] itemValues = new int[] {60, 100, 120};
    private static final int[] itemWeights = new int[] {10, 20, 30};
    private static final int SACK_WEIGHT = 50;

    private FractionalKnapsackProblem fkp = new FractionalKnapsackProblem();

    @Test
    public void testFractionalKnapsackGreedy() {
        assertThat(fkp.fractionalKnapsackGreedy(itemValues, itemWeights, SACK_WEIGHT), is(240));
    }

}
