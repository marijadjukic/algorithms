package algorithms.chapter.advanceddatastructures;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class GraphComponentsTest {

    private GraphComponents<String> graphComponents = new GraphComponents<>();

    private List<String> vertices = Arrays.asList("a", "b", "c", "d", "e", "f", "g", "h", "i", "j");
    private List<Edge<String>> edges = Arrays.asList(
            new Edge<>("b","d"),
            new Edge<>("e", "g"),
            new Edge<>("a", "c"),
            new Edge<>("h", "i"),
            new Edge<>("a", "b"),
            new Edge<>("e", "f"),
            new Edge<>("b", "c")
    );

    private Graph<String> graph = new Graph<>(vertices, edges);

    @Test
    public void testConnectedComponents() {
        graphComponents.connectedComponents(graph);
        assertThat(graphComponents.getDisjointSet().findSet("c"), is(new SetNode<>("d")));
        assertThat(graphComponents.getDisjointSet().findSet("e"), is(new SetNode<>("g")));
        assertThat(graphComponents.getDisjointSet().findSet("i"), is(new SetNode<>("i")));
    }

    @Test
    public void testIsSameComponent() {
        graphComponents.connectedComponents(graph);
        assertTrue(graphComponents.isSameComponent("e", "g"));
        assertFalse(graphComponents.isSameComponent("b", "h"));
    }

}
