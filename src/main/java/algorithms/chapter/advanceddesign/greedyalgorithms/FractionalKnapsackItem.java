package algorithms.chapter.advanceddesign.greedyalgorithms;

public class FractionalKnapsackItem {

    private int itemValue;

    private int itemWeight;

    private int fraction;

    public FractionalKnapsackItem(int itemValue, int itemWeight, int fraction) {
        this.itemValue = itemValue;
        this.itemWeight = itemWeight;
        this.fraction = fraction;
    }

    public int getItemValue() {
        return itemValue;
    }

    public void setItemValue(int itemValue) {
        this.itemValue = itemValue;
    }

    public int getItemWeight() {
        return itemWeight;
    }

    public void setItemWeight(int itemWeight) {
        this.itemWeight = itemWeight;
    }

    public int getFraction() {
        return fraction;
    }

    public void setFraction(int fraction) {
        this.fraction = fraction;
    }

}
