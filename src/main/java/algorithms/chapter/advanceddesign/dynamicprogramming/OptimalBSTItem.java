package algorithms.chapter.advanceddesign.dynamicprogramming;

public class OptimalBSTItem {

    private int optimalFrequency;

    private int parent;

    public OptimalBSTItem(int optimalFrequency) {
        this.optimalFrequency = optimalFrequency;
        this.parent = -1;
    }

    public OptimalBSTItem(int optimalFrequency, int parent) {
        this.optimalFrequency = optimalFrequency;
        this.parent = parent;
    }

    public int getOptimalFrequency() {
        return optimalFrequency;
    }

    public void setOptimalFrequency(int optimalFrequency) {
        this.optimalFrequency = optimalFrequency;
    }

    public int getParent() {
        return parent;
    }

    public void setParent(int parent) {
        this.parent = parent;
    }
}
