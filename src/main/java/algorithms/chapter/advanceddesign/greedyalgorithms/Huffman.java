package algorithms.chapter.advanceddesign.greedyalgorithms;

import algorithms.chapter.sorting.PriorityQueue;

public class Huffman {

    public HuffmanNode huffmanCode(HuffmanNode[] charFrequency) {
        int n = charFrequency.length;
        PriorityQueue<HuffmanNode> q = new PriorityQueue<>(charFrequency, true);

        for (int i=0; i<n; i++) {
            HuffmanNode z = new HuffmanNode();
            HuffmanNode x = q.heapExtractMin();
            HuffmanNode y = q.heapExtractMin();
            z.setLeft(x);
            z.setRight(y);
            z.setFrequency(x.getFrequency() + y.getFrequency());
            q.minHeapInsert(z);
        }
        return q.heapExtractMin();
    }

}
