package algorithms.chapter.graphalgorithms;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class StronglyConnectedComponentsTest {

    private Vertex<String> aVertex = new Vertex<>("a");
    private Vertex<String> bVertex = new Vertex<>("b");
    private Vertex<String> cVertex = new Vertex<>("c");
    private Vertex<String> dVertex = new Vertex<>("d");
    private Vertex<String> eVertex = new Vertex<>("e");
    private Vertex<String> fVertex = new Vertex<>("f");
    private Vertex<String> gVertex = new Vertex<>("g");
    private Vertex<String> hVertex = new Vertex<>("h");

    private Graph<String> graph = new Graph<>();
    private StronglyConnectedComponents<String> scc = new StronglyConnectedComponents<>();

    @Before
    public void setUp() {
        graph.add(aVertex, bVertex);
        graph.add(bVertex, eVertex);
        graph.add(eVertex, aVertex);
        graph.add(eVertex, fVertex);
        graph.add(fVertex, gVertex);
        graph.add(gVertex, fVertex);
        graph.add(cVertex, dVertex);
        graph.add(dVertex, cVertex);
        graph.add(cVertex, gVertex);
        graph.add(dVertex, hVertex);
        graph.add(gVertex, hVertex);
        graph.add(hVertex, hVertex);
    }

    @Test
    public void testStronglyConnectedComponents() {
        List<String> components = scc.getStronglyConnectedComponents(graph);
        assertThat(components.size(), is(4));
        assertThat(components, containsInAnyOrder("a-e-b", "f-g", "c-d", "h"));
    }


}
