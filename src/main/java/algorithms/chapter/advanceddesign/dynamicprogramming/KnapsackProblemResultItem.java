package algorithms.chapter.advanceddesign.dynamicprogramming;

public class KnapsackProblemResultItem {

    private int[] itemValuesResult;

    private int[] itemWeightsResult;

    private int maxItemValues;

    public int[] getItemValuesResult() {
        return itemValuesResult;
    }

    public void setItemValuesResult(int[] itemValuesResult) {
        this.itemValuesResult = itemValuesResult;
    }

    public int[] getItemWeightsResult() {
        return itemWeightsResult;
    }

    public void setItemWeightsResult(int[] itemWeightsResult) {
        this.itemWeightsResult = itemWeightsResult;
    }

    public int getMaxItemValues() {
        return maxItemValues;
    }

    public void setMaxItemValues(int maxItemValues) {
        this.maxItemValues = maxItemValues;
    }

}
