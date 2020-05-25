package algorithms.chapter.graphalgorithms;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import algorithms.chapter.advanceddatastructures.Edge;
import algorithms.chapter.advanceddatastructures.Graph;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class SingleSourceShortestPathsTest {

    private SingleSourceShortestPaths<String> sssp = new SingleSourceShortestPaths<>();

    private Vertex<String> s = new Vertex<>("s");
    private Vertex<String> t = new Vertex<>("t");
    private Vertex<String> x = new Vertex<>("x");
    private Vertex<String> y = new Vertex<>("y");
    private Vertex<String> z = new Vertex<>("z");
    private List<Vertex<String>> vertices = Arrays.asList(s, t, x, y, z);
    private List<Edge<Vertex<String>>> edges = Arrays.asList(
            new Edge<>(s, t, 6),
            new Edge<>(s, y, 7),
            new Edge<>(t, x, 5),
            new Edge<>(x, t, -2),
            new Edge<>(t, y, 8),
            new Edge<>(t, z, -4),
            new Edge<>(y, x, -3),
            new Edge<>(y, z, 9),
            new Edge<>(z, x, 7),
            new Edge<>(z, s, 2)
    );
    private Graph<Vertex<String>> graph = new Graph<>(vertices, edges);

    @Test
    public void testBellmanFord() {
        assertTrue(sssp.bellmanFord(graph, s));
        assertThat(t.getDistance(), is(2));
        assertThat(t.getParent(), is(x));
    }

}
