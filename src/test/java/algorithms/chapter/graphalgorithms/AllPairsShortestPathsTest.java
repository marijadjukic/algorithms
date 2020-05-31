package algorithms.chapter.graphalgorithms;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.Before;
import org.junit.Test;

public class AllPairsShortestPathsTest {

    private AllPairsShortestPaths apsp = new AllPairsShortestPaths();
    private GraphMatrix graph = new GraphMatrix(5);

    @Before
    public void setUp() {
        this.initGraph();
    }

    @Test
    public void testSlowAllPairsShortestPaths() {
        int[][] allPairsShortestPaths = apsp.slowAllPairsShortestPaths(graph);

        assertThat(allPairsShortestPaths[0][2], is(-3));
        assertThat(allPairsShortestPaths[4][0], is(8));

    }

    private void initGraph() {
        graph.addEdge(0, 1, 3);
        graph.addEdge(0, 2, 8);
        graph.addEdge(0, 4, -4);
        graph.addEdge(1, 4, 7);
        graph.addEdge(1, 3, 1);
        graph.addEdge(2, 1, 4);
        graph.addEdge(3, 2, -5);
        graph.addEdge(3, 0, 2);
        graph.addEdge(4, 3, 6);
    }

}
