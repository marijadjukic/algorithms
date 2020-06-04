package algorithms.chapter.graphalgorithms;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.Before;
import org.junit.Test;

public class JohnsonAllPairsShortestPathsTest {

    private JohnsonAllPairsShortestPaths johnson = new JohnsonAllPairsShortestPaths();

    private Vertex<Integer> one = new Vertex<>(1);
    private Vertex<Integer> two = new Vertex<>(2);
    private Vertex<Integer> three = new Vertex<>(3);
    private Vertex<Integer> four = new Vertex<>(4);
    private Vertex<Integer> five = new Vertex<>(5);

    private Graph<Integer> graph = new Graph<>();

    @Before
    public void setUp() {
        initGraph();
    }

    @Test
    public void testJohnsonAllPairsShortestPaths() {
        Integer[][] allPairsShortestPaths = johnson.johnson(graph);
        Integer twoFourShortestPathWeight = allPairsShortestPaths[1][3];
        assertThat(graph.getEdgeWeight(one, three), is(13));
        assertThat(twoFourShortestPathWeight, is(1));
    }

    private void initGraph() {
        graph.add(one, two, 3);
        graph.add(one, three, 8);
        graph.add(one, five, -4);
        graph.add(two, four, 1);
        graph.add(two, five, 7);
        graph.add(three, two, 4);
        graph.add(four, three, -5);
        graph.add(four, one, 2);
        graph.add(five, four, 6);
    }


}
