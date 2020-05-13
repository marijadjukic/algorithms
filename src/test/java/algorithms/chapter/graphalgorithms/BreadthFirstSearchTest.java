package algorithms.chapter.graphalgorithms;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.Before;
import org.junit.Test;

import java.util.*;

public class BreadthFirstSearchTest {

    private Vertex<String> vertexS = new Vertex<>("s");
    private Vertex<String> vertexR = new Vertex<>("r");
    private Vertex<String> vertexW = new Vertex<>("w");
    private Vertex<String> vertexT = new Vertex<>("t");
    private Vertex<String> vertexX = new Vertex<>("x");
    private Vertex<String> vertexU = new Vertex<>("u");
    private Vertex<String> vertexV = new Vertex<>("v");
    private Vertex<String> vertexY = new Vertex<>("y");

    private Graph<String> graph = new Graph<>();

    @Before
    public void setUp() {
        graph.add(vertexS, Arrays.asList(vertexR, vertexW));
        graph.add(vertexR, vertexV);
        graph.add(vertexW, Arrays.asList(vertexT, vertexX));
        graph.add(vertexT, Arrays.asList(vertexU, vertexX));
        graph.add(vertexX, Arrays.asList(vertexU, vertexY));
        graph.add(vertexU, Arrays.asList(vertexR, vertexY));
        graph.add(vertexV);
        graph.add(vertexY);
    }

    @Test
    public void testBreathFirstSearch() {
        graph.breathFirstSearch(vertexS);
        String shortestPath = graph.getPath(vertexS, vertexT);
        assertThat(shortestPath, is("s-w-t"));
    }

}
