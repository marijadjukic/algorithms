package algorithms.chapter.graphalgorithms;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.Before;
import org.junit.Test;

public class FloydWarshallTest {

    private FloydWarshall floydWarshall = new FloydWarshall();
    private GraphMatrix graph = new GraphMatrix(5);
    private GraphMatrix graph2 = new GraphMatrix(4);

    @Before
    public void setUp() {
        this.initGraph();
        this.initGraph2();
    }

    @Test
    public void testFloydWarshallRecursive() {
        int[][] allPairsShortestPaths = floydWarshall.floydWarshallRecursive(graph);

        assertThat(allPairsShortestPaths[0][2], is(-3));
        assertThat(allPairsShortestPaths[4][0], is(8));
    }

    @Test
    public void testFloydWarshallBottomUp() {
        int[][] allPairsShortestPathsWeights = floydWarshall.floydWarshallBottomUp(graph);
        Integer[][] allPairsShortestPaths = floydWarshall.floydWarshallAllPathsMatrix(graph);
        String shortestPathFrom0to2 = graph.printAllPairsShortestPaths(allPairsShortestPaths, 0, 2);

        assertThat(allPairsShortestPathsWeights[0][2], is(-3));
        assertThat(allPairsShortestPathsWeights[4][0], is(8));
        assertThat(shortestPathFrom0to2, is("0-4-3-2"));
    }

    @Test
    public void testTransitiveClosure() {
        int[][] transitiveClosureMatrix = floydWarshall.transitiveClosure(graph2);

        assertThat(transitiveClosureMatrix[0][3], is(0));
        assertThat(transitiveClosureMatrix[2][2], is(1));
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

    private void initGraph2() {
        graph2.addEdge(1, 3, 0);
        graph2.addEdge(1, 2, 0);
        graph2.addEdge(2, 1, 0);
        graph2.addEdge(3, 2, 0);
        graph2.addEdge(3, 0, 0);
    }
}
