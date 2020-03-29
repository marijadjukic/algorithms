package algorithms.chapter.advanceddesign.dynamicprogramming;

public class LongestCommonSubsequence {

    public int longestCommonSubsequenceRecursive(String X, String Y) {
        int m = X.length();
        int n = Y.length();

        if(m==0 || n==0) {
            return 0;
        }
        int k = Math.min(m,n);
        int longestCommonSubsequenceLength = 0;
        for(int i=0; i<k; i++) {
            int current = longestCommonSubsequenceLength;
            if(X.charAt(i)==Y.charAt(i)) {
                longestCommonSubsequenceLength = longestCommonSubsequenceRecursive(X.substring(i+1),Y.substring(i+1)) + 1;
            } else {
                longestCommonSubsequenceLength = Math.max(
                        longestCommonSubsequenceRecursive(X.substring(i+1),Y),
                        longestCommonSubsequenceRecursive(X,Y.substring(i+1))
                );
            }
            longestCommonSubsequenceLength = Math.max(longestCommonSubsequenceLength,current);
        }
        return longestCommonSubsequenceLength;
    }

    public int memoizedLongestCommonSubsequence(String X, String Y) {
        Integer[][] memo = new Integer[X.length()][Y.length()];
        return memoizedLongestCommonSubsequenceRecursive(X,Y,memo);
    }

    private int memoizedLongestCommonSubsequenceRecursive(String X, String Y, Integer[][] memo) {
        int m = X.length();
        int n = Y.length();
        if(m>0 && n>0 && memo[m-1][n-1]!=null) {
            return memo[m-1][n-1];
        }
        if(m==0 || n==0) {
            return 0;
        } else {
            int k = Math.min(m, n);
            int longestCommonSubsequenceLength = 0;
            for (int i = 0; i < k; i++) {
                int current = longestCommonSubsequenceLength;
                if (X.charAt(i) == Y.charAt(i)) {
                    longestCommonSubsequenceLength = memoizedLongestCommonSubsequenceRecursive(X.substring(i + 1), Y.substring(i + 1),memo) + 1;
                } else {
                    longestCommonSubsequenceLength = Math.max(
                            longestCommonSubsequenceRecursive(X.substring(i+1),Y),
                            longestCommonSubsequenceRecursive(X,Y.substring(i+1))
                    );
                }
                longestCommonSubsequenceLength = Math.max(longestCommonSubsequenceLength, current);
                memo[X.length()-1][Y.length()-1] = longestCommonSubsequenceLength;

            }
        }
        return memo[m-1][n-1];
    }

    public LCSItem[][] longestCommonSubsequenceBottomUp(String X, String Y) {
        int m = X.length()+1;
        int n = Y.length()+1;

        LCSItem[][] lcsItemMatrix = new LCSItem[m][n];

        for(int i=0; i<m; i++){
            lcsItemMatrix[i][0] = new LCSItem(0);
        }

        for(int j=0; j<n; j++){
            lcsItemMatrix[0][j] = new LCSItem(0);
        }

        for(int i=1; i<m; i++) {
            for(int j=1; j<n; j++) {
                if(X.charAt(i-1)==Y.charAt(j-1)) {
                    lcsItemMatrix[i][j] = new LCSItem(lcsItemMatrix[i-1][j-1].getLongestCommonSubsequenceLength() + 1, LCSDirection.LEFT_UP);
                } else if(lcsItemMatrix[i-1][j].getLongestCommonSubsequenceLength()>=lcsItemMatrix[i][j-1].getLongestCommonSubsequenceLength()){
                    lcsItemMatrix[i][j] = new LCSItem(lcsItemMatrix[i-1][j].getLongestCommonSubsequenceLength(), LCSDirection.UP);
                } else {
                    lcsItemMatrix[i][j] = new LCSItem(lcsItemMatrix[i][j-1].getLongestCommonSubsequenceLength(), LCSDirection.LEFT);
                }
            }
        }

        return lcsItemMatrix;
    }

    public String getLongestCommonSubsequenceResult(String X, String Y) {
        LCSItem[][] lcsItemMatrix = longestCommonSubsequenceBottomUp(X,Y);
        StringBuilder result = new StringBuilder();
        return getLongestCommonSubsequenceResultRecursive(lcsItemMatrix, X, X.length(), Y.length(), result).toString();
    }

    private StringBuilder getLongestCommonSubsequenceResultRecursive(LCSItem[][] lcsItemMatrix, String X, int i, int j, StringBuilder result) {
        if(i==0 || j==0) {
            return result;
        }
        if(lcsItemMatrix[i][j].getDirection().equals(LCSDirection.LEFT_UP)){
            getLongestCommonSubsequenceResultRecursive(lcsItemMatrix,X,i-1,j-1,result);
            result.append(X.charAt(i-1));
        } else if(lcsItemMatrix[i][j].getDirection().equals(LCSDirection.UP)){
            getLongestCommonSubsequenceResultRecursive(lcsItemMatrix,X,i-1,j,result);
        } else {
            getLongestCommonSubsequenceResultRecursive(lcsItemMatrix,X,i,j-1,result);
        }
        return result;
    }

}
