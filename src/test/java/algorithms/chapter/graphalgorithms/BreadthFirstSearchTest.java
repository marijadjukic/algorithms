package algorithms.chapter.graphalgorithms;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.Before;
import org.junit.Test;

import java.util.*;

public class BreadthFirstSearchTest {

    private BreadthFirstSearch<String> breadthFirstSearch = new BreadthFirstSearch<>();
    private Vertex<String> vertexS = new Vertex<>("s");
    private Vertex<String> vertexR = new Vertex<>("r");
    private Vertex<String> vertexW = new Vertex<>("w");
    private Vertex<String> vertexT = new Vertex<>("t");
    private Vertex<String> vertexX = new Vertex<>("x");
    private Vertex<String> vertexU = new Vertex<>("u");
    private Vertex<String> vertexV = new Vertex<>("v");
    private Vertex<String> vertexY = new Vertex<>("y");

    private Graph<String> graph;

    @Before
    public void setUp() {

        Map<Vertex<String>, Vertex<String>[]> graphMap = new HashMap<>();
        graphMap.put(vertexS, new Vertex[]{vertexR, vertexW});
        graphMap.put(vertexR, new Vertex[]{vertexV});
        graphMap.put(vertexW, new Vertex[]{vertexT, vertexX});
        graphMap.put(vertexT, new Vertex[]{vertexU, vertexX});
        graphMap.put(vertexX, new Vertex[]{vertexU, vertexY});
        graphMap.put(vertexU, new Vertex[]{vertexR, vertexY});
        graphMap.put(vertexV, new Vertex[]{});
        graphMap.put(vertexY, new Vertex[]{});

        graph = new Graph<>(graphMap);
    }

    @Test
    public void testBreathFirstSearch() {
        breadthFirstSearch.breathFirstSearch(graph, vertexS);
        String shortestPath = breadthFirstSearch.printPath(graph, vertexS, vertexT);
        assertThat(shortestPath, is("s-w-t"));
    }

}
