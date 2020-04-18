package algorithms.chapter.advanceddesign.greedyalgorithms;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class ActivitySelectionProblemTest {

    private static final int[] start = new int[]{1, 3, 0, 5, 3, 5, 6, 8, 8, 2, 12};
    private static final int[] finish = new int[]{4, 5, 6, 7, 9, 9, 10, 11, 12, 14, 16};
    private static final int[] compatibleActivities = new int[]{0, 3, 7, 10};

    private ActivitySelectionProblem asp = new ActivitySelectionProblem();

    @Test
    public void testActivitySelectionRecursive() {
        assertTrue(asp.activitySelectionRecursive(start, finish).containsAll(Arrays.asList(compatibleActivities)));
    }

    @Test
    public void testActivitySelectionGreedy() {
        assertTrue(asp.activitySelectionGreedy(start, finish).containsAll(Arrays.asList(compatibleActivities)));
    }

}
