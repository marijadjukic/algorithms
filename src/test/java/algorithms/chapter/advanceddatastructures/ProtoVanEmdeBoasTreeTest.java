package algorithms.chapter.advanceddatastructures;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.Test;

import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

public class ProtoVanEmdeBoasTreeTest {

    private static final Set<Integer> set = new TreeSet<>(Arrays.asList(2,3,4,5,7,14,15));
    private ProtoVanEmdeBoasTree protoVEBTree = initProtoVEBTree(set);

    @Test
    public void testProtoVanEmdeBoasStructure() {
        assertThat(protoVEBTree.getUniverseSize(), is(16));
        assertThat(protoVEBTree.getCluster().length, is(4));
        assertThat(protoVEBTree.getCluster()[1].getCluster()[0].getElements()[0], is(1));
    }

    @Test
    public void testIsMember() {
        assertTrue(protoVEBTree.isMember(7));
        assertFalse(protoVEBTree.isMember(9));
    }

    @Test
    public void testMinimum() {
        assertThat(protoVEBTree.minimum(), is(2));
    }

    @Test
    public void testSuccessor() {
        assertThat(protoVEBTree.successor(7), is(14));
    }

    @Test
    public void testInsert() {
        protoVEBTree.insert(10);
        assertThat(protoVEBTree.successor(7), is(10));
        assertThat(protoVEBTree.size(), is(8));
    }

    private ProtoVanEmdeBoasTree initProtoVEBTree(Set<Integer> set) {
        ProtoVanEmdeBoasTree protoVanEmdeBoasTree = new ProtoVanEmdeBoasTree(set);

        ProtoVanEmdeBoas rootSummary = getFirstLevelProtoVEBStructure(new Integer[]{1,1}, new Integer[]{1,1}, new Integer[]{0,1});
        ProtoVanEmdeBoas rootCluster0 = getFirstLevelProtoVEBStructure(new Integer[]{0,1}, new Integer[]{0,0}, new Integer[]{1,1});
        ProtoVanEmdeBoas rootCluster1 = getFirstLevelProtoVEBStructure(new Integer[]{1,1}, new Integer[]{1,1}, new Integer[]{0,1});
        ProtoVanEmdeBoas rootCluster2 = getFirstLevelProtoVEBStructure(new Integer[]{0,0}, new Integer[]{0,0}, new Integer[]{0,0});
        ProtoVanEmdeBoas rootCluster3 = getFirstLevelProtoVEBStructure(new Integer[]{0,1}, new Integer[]{0,0}, new Integer[]{1,1});

        ProtoVanEmdeBoas root = protoVanEmdeBoasTree.getRoot();
        root.setSummary(rootSummary);
        root.setCluster(new ProtoVanEmdeBoas[]{rootCluster0, rootCluster1, rootCluster2, rootCluster3});

        return protoVanEmdeBoasTree;
    }

    private ProtoVanEmdeBoas getFirstLevelProtoVEBStructure(Integer[] summary, Integer[] cluster1, Integer[] cluster2) {
        ProtoVanEmdeBoas clusterSummary = new ProtoVanEmdeBoas(2, summary);
        ProtoVanEmdeBoas clusterElements1 = new ProtoVanEmdeBoas(2, cluster1);
        ProtoVanEmdeBoas clusterElements2 = new ProtoVanEmdeBoas(2, cluster2);
        ProtoVanEmdeBoas[] clusterArray = new ProtoVanEmdeBoas[]{clusterElements1, clusterElements2};
        return new ProtoVanEmdeBoas(4, clusterSummary, clusterArray);
    }

}
