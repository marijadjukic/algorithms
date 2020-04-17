package algorithms.chapter.advanceddesign.dynamicprogramming;

public class MinJumpsItem {

    private int minNumOfJumps;

    private int[] jumpsArray;

    public MinJumpsItem(int minNumOfJumps, int[] jumpsArray) {
        this.minNumOfJumps = minNumOfJumps;
        this.jumpsArray = jumpsArray;
    }

    public int getMinNumOfJumps() {
        return minNumOfJumps;
    }

    public void setMinNumOfJumps(int minNumOfJumps) {
        this.minNumOfJumps = minNumOfJumps;
    }

    public int[] getJumpsArray() {
        return jumpsArray;
    }

    public void setJumpsArray(int[] jumpsArray) {
        this.jumpsArray = jumpsArray;
    }
}
