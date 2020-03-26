package algorithms.chapter.advanceddesign.dynamicprogramming;

public class MatrixChainItem {

    int multiplicationCost;

    int optimalCostIndex;

    public MatrixChainItem(int multiplicationCost) {
        this.multiplicationCost = multiplicationCost;
        this.optimalCostIndex = 0;
    }

    public int getMultiplicationCost() {
        return multiplicationCost;
    }

    public void setMultiplicationCost(int multiplicationCost) {
        this.multiplicationCost = multiplicationCost;
    }

    public int getOptimalCostIndex() {
        return optimalCostIndex;
    }

    public void setOptimalCostIndex(int optimalCostIndex) {
        this.optimalCostIndex = optimalCostIndex;
    }
}
