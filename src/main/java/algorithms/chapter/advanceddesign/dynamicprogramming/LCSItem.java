package algorithms.chapter.advanceddesign.dynamicprogramming;

public class LCSItem {

    private int longestCommonSubsequenceLength;

    private LCSDirection direction;

    public LCSItem(int longestCommonSubsequenceLength){
        this.longestCommonSubsequenceLength = longestCommonSubsequenceLength;
    }

    public LCSItem(int longestCommonSubsequenceLength, LCSDirection direction){
        this.longestCommonSubsequenceLength = longestCommonSubsequenceLength;
        this.direction = direction;
    }

    public int getLongestCommonSubsequenceLength() {
        return longestCommonSubsequenceLength;
    }

    public void setLongestCommonSubsequenceLength(int longestCommonSubsequenceLength) {
        this.longestCommonSubsequenceLength = longestCommonSubsequenceLength;
    }

    public LCSDirection getDirection() {
        return direction;
    }

    public void setDirection(LCSDirection direction) {
        this.direction = direction;
    }
}
