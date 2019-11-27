package algorithms.chapter.one;

public class MaxSubarrayBean {
    
    private int lowIndex;

    private int highIndex;

    private int maxSum;

    public MaxSubarrayBean(int lowIndex, int highIndex, int maxSum) {
        this.lowIndex = lowIndex;
        this.highIndex = highIndex;
        this.maxSum = maxSum;
    }

    public int getLowIndex() {
        return lowIndex;
    }

    public int getHightIndex() {
        return highIndex;
    }

    public int getMaxSum() {
        return maxSum;
    }

    @Override
    public boolean equals(Object o) {
        if(o instanceof MaxSubarrayBean) {
            MaxSubarrayBean o2 = (MaxSubarrayBean)o;
            return this.lowIndex==o2.lowIndex && this.highIndex==o2.highIndex && this.maxSum==o2.maxSum;
        }
        return false;
    }

    @Override
    public String toString() {
        return "[low-index=" + this.lowIndex + ", high-index=" + this.highIndex + ", max-sum=" + this.maxSum + "]";
    }

}