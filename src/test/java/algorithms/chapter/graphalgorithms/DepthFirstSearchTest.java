package algorithms.chapter.graphalgorithms;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

import org.junit.Before;
import org.junit.Test;

import java.util.*;

public class DepthFirstSearchTest {

    private Vertex<String> vertexS = new Vertex<>("s");
    private Vertex<String> vertexZ = new Vertex<>("z");
    private Vertex<String> vertexW = new Vertex<>("w");
    private Vertex<String> vertexT = new Vertex<>("t");
    private Vertex<String> vertexX = new Vertex<>("x");
    private Vertex<String> vertexU = new Vertex<>("u");
    private Vertex<String> vertexV = new Vertex<>("v");
    private Vertex<String> vertexY = new Vertex<>("y");

    private Graph<String> graph = new Graph<>();

    @Before
    public void setUp() {
        graph.add(vertexS, Arrays.asList(vertexZ, vertexW));
        graph.add(vertexZ, Arrays.asList(vertexY, vertexW));
        graph.add(vertexY, vertexX);
        graph.add(vertexX, vertexZ);
        graph.add(vertexW, vertexX);
        graph.add(vertexV, Arrays.asList(vertexW, vertexS));
        graph.add(vertexT, Arrays.asList(vertexU, vertexV));
        graph.add(vertexU, Arrays.asList(vertexT, vertexV));
    }

    @Test
    public void testDepthFirstSearch() {
        graph.depthFirstSearch();
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

    @Test
    public void testDepthFirstSearchFromVertex() {
        graph.depthFirstSearch(vertexZ);
        String path = graph.getPath(vertexZ, vertexX);
        assertNotNull(path);
    }

}
