package algorithms.chapter.advanceddesign.greedyalgorithms;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class HuffmanTest {

    private static final HuffmanNode[] charFrequency = new HuffmanNode[]{
            new HuffmanNode('f',5),
            new HuffmanNode('e',9),
            new HuffmanNode('c',12),
            new HuffmanNode('b',13),
            new HuffmanNode('d',16),
            new HuffmanNode('a',45)};

    private Huffman h = new Huffman();

    @Test
    public void testHuffmanCode() {
        assertThat(h.huffmanCode(charFrequency).getFrequency(), is(100));
        assertThat(h.huffmanCode(charFrequency).getFrequency(), is(100));
    }

}
