package algorithms.chapter.graphalgorithms;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.Before;
import org.junit.Test;

import java.util.Set;

public class SingleSourceShortestPathsTest {

    private SingleSourceShortestPaths<String> sssp = new SingleSourceShortestPaths<>();

    private Vertex<String> s = new Vertex<>("s");
    private Vertex<String> t = new Vertex<>("t");
    private Vertex<String> x = new Vertex<>("x");
    private Vertex<String> y = new Vertex<>("y");
    private Vertex<String> z = new Vertex<>("z");
    private Vertex<String> r = new Vertex<>("r");

    private Graph<String> belmanFordGraph = new Graph<>();
    private Graph<String> dagGraph = new Graph<>();
    private Graph<String> dijkstraGraph = new Graph<>();

    @Before
    public void setUp() {
        initBelmanFordGraph();
        initDagGraph();
        initDijkstraGraph();
    }

    @Test
    public void testBellmanFord() {
        assertTrue(sssp.bellmanFord(belmanFordGraph, s));
        assertThat(t.getDistance(), is(2));
        assertThat(t.getParent(), is(x));
        assertThat(belmanFordGraph.getPath(s, t), is("s-y-x-t"));
    }

    @Test
    public void testDagShortestPaths() {
        sssp.dagShortestPaths(dagGraph, s);
        assertThat(x.getDistance(), is(6));
        assertThat(x.getParent(), is(s));
        assertThat(dagGraph.getPath(s, y), is("s-x-y"));
    }

    @Test
    public void testDijkstra() {
       Set<Vertex<String>> set = sssp.dijkstra(dijkstraGraph, s);
       assertThat(x.getDistance(), is(9));
       assertThat(x.getParent(), is(t));
       assertThat(dijkstraGraph.getPath(s, z), is("s-y-z"));
    }

    private void initBelmanFordGraph() {
        belmanFordGraph.add(s, t, 6);
        belmanFordGraph.add(s, y, 7);
        belmanFordGraph.add(t, x, 5);
        belmanFordGraph.add(x, t, -2);
        belmanFordGraph.add(t, y, 8);
        belmanFordGraph.add(t, z, -4);
        belmanFordGraph.add(y, x, -3);
        belmanFordGraph.add(y, z, 9);
        belmanFordGraph.add(z, x, 7);
        belmanFordGraph.add(z, s, 2);
    }

    private void initDagGraph() {
        dagGraph.add(r, s, 5);
        dagGraph.add(r, t, 3);
        dagGraph.add(s, t, 2);
        dagGraph.add(s, x, 6);
        dagGraph.add(t, x, 7);
        dagGraph.add(t, y, 4);
        dagGraph.add(t, z, 2);
        dagGraph.add(x, y, -1);
        dagGraph.add(x, z, 1);
        dagGraph.add(y, z, -2);
        dagGraph.add(z);
    }

    private void initDijkstraGraph() {
        dijkstraGraph.add(s, t, 10);
        dijkstraGraph.add(t, y, 2);
        dijkstraGraph.add(y, t, 3);
        dijkstraGraph.add(s, y, 5);
        dijkstraGraph.add(t, x, 1);
        dijkstraGraph.add(y, x, 9);
        dijkstraGraph.add(y, z, 2);
        dijkstraGraph.add(x, z, 4);
        dijkstraGraph.add(z, x, 6);
        dijkstraGraph.add(z, s, 7);
    }

}
