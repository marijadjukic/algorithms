package algorithms.chapter.graphalgorithms;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

import org.junit.Before;
import org.junit.Test;

import java.util.*;

public class DepthFirstSearchTest {

    private DepthFirstSearch<String> depthFirstSearch = new DepthFirstSearch<>();

    private Vertex<String> vertexS = new Vertex<>("s");
    private Vertex<String> vertexZ = new Vertex<>("z");
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
        graphMap.put(vertexS, new Vertex[]{vertexZ, vertexW});
        graphMap.put(vertexZ, new Vertex[]{vertexY, vertexW});
        graphMap.put(vertexY, new Vertex[]{vertexX});
        graphMap.put(vertexX, new Vertex[]{vertexZ});
        graphMap.put(vertexW, new Vertex[]{vertexX});
        graphMap.put(vertexV, new Vertex[]{vertexW, vertexS});
        graphMap.put(vertexT, new Vertex[]{vertexU, vertexV});
        graphMap.put(vertexU, new Vertex[]{vertexT, vertexV});

        graph = new Graph<>(graphMap);
    }

    @Test
    public void testDepthFirstSearch() {
        depthFirstSearch.depthFirstSearch(graph);
        int lastFinishingTime = 0;
        for (Vertex<String> vertex : graph.getGraphMap().keySet()) {
            if(vertex.getFinishingTime() > lastFinishingTime) {
                lastFinishingTime = vertex.getFinishingTime();
            }
            assertThat(vertex.getDiscoveryTime(), greaterThan(0));
            assertThat(vertex.getFinishingTime(), greaterThan(0));
            assertThat(vertex.getDiscoveryTime(), lessThanOrEqualTo(vertex.getFinishingTime()));
        }
        assertThat(lastFinishingTime, is(16));
    }

}
