package algorithms.chapter.advanceddesign.dynamicprogramming;

public class CoinChangeOptimizationItem {

    private int totalNumOfCoins;

    private int[] coinsDenomination;

    public CoinChangeOptimizationItem(int totalNumOfCoins, int[] coinsDenomination) {
        this.totalNumOfCoins = totalNumOfCoins;
        this.coinsDenomination = coinsDenomination;
    }

    public int getTotalNumOfCoins() {
        return totalNumOfCoins;
    }

    public void setTotalNumOfCoins(int totalNumOfCoins) {
        this.totalNumOfCoins = totalNumOfCoins;
    }

    public int[] getCoinsDenomination() {
        return coinsDenomination;
    }

    public void setCoinsDenomination(int[] coinsDenomination) {
        this.coinsDenomination = coinsDenomination;
    }
}
