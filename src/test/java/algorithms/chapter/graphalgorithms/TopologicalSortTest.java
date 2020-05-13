package algorithms.chapter.graphalgorithms;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class TopologicalSortTest {

    private Vertex<String> undershorts = new Vertex<>("undershorts");
    private Vertex<String> pants = new Vertex<>("pants");
    private Vertex<String> belt = new Vertex<>("belt");
    private Vertex<String> shirt = new Vertex<>("shirt");
    private Vertex<String> tie = new Vertex<>("tie");
    private Vertex<String> jacket = new Vertex<>("jacket");
    private Vertex<String> socks = new Vertex<>("socks");
    private Vertex<String> shoes = new Vertex<>("shoes");
    private Vertex<String> watch = new Vertex<>("watch");

    private Graph<String> graph = new Graph<>();
    private TopologicalSort<String> ts = new TopologicalSort<>();

    @Before
    public void setUp() {
        graph.add(undershorts, Arrays.asList(pants, shoes));
        graph.add(socks, shoes);
        graph.add(watch);
        graph.add(pants, Arrays.asList(belt, shoes));
        graph.add(belt, jacket);
        graph.add(shirt, Arrays.asList(belt, tie));
        graph.add(tie, jacket);
        graph.add(jacket);
        graph.add(shoes);
    }

    @Test
    public void testTopologicalSort() {
        LinkedList<Vertex<String >> list = ts.topologicalSort(graph);
        assertThat(list.getFirst().getFinishingTime(), is(18));
        assertThat(list.getLast().getFinishingTime(), is(4));
    }

}
