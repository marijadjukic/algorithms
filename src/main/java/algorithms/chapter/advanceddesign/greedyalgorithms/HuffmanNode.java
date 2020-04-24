package algorithms.chapter.advanceddesign.greedyalgorithms;

public class HuffmanNode implements Comparable<HuffmanNode> {

    private Character character;

    private Integer frequency;

    private HuffmanNode left;

    private HuffmanNode right;

    public HuffmanNode() {
    }

    public HuffmanNode(Character character, Integer frequency) {
        this.character = character;
        this.frequency = frequency;
    }

    public Character getCharacter() {
        return character;
    }

    public void setCharacter(Character character) {
        this.character = character;
    }

    public Integer getFrequency() {
        return frequency;
    }

    public void setFrequency(Integer frequency) {
        this.frequency = frequency;
    }

    public HuffmanNode getLeft() {
        return left;
    }

    public void setLeft(HuffmanNode left) {
        this.left = left;
    }

    public HuffmanNode getRight() {
        return right;
    }

    public void setRight(HuffmanNode right) {
        this.right = right;
    }

    @Override
    public int compareTo(HuffmanNode o) {
        return this.frequency.compareTo(o.getFrequency());
    }
}
