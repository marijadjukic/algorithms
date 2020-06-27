package algorithms.top10algos;

public class KMPSubstringSearch {

    public int findSubstring(String text, String pattern) {
        int n = text.length();
        int m = pattern.length();

        if (m == 0) {
            return 0;
        }

        if (m > n) {
            return -1;
        }

        int[] patternArray = getPatternArray(pattern);
        int i = 0;
        int j = 0;

        int index = -1;

        while (i < n) {
            if(text.charAt(i) == pattern.charAt(j)) {
                if(j == m - 1) {
                    return index;
                }
                i++;
                j++;
            } else {
                if(j==0 && text.charAt(i) != pattern.charAt(j)) {
                    i++;
                } else {
                    j = patternArray[j - 1];
                    index = i - j;
                }
            }
        }
        return index;
    }


    // time O(n), space O(n)
    public int[] getPatternArray(String pattern) {
        int n = pattern.length();
        int[] array = new int[n];
        array[0] = 0;

        int i = 1;
        int j = 0;
        while(i < n) {
            if(pattern.charAt(i) == pattern.charAt(j)) {
                array[i] = j + 1;
                i++;
                j++;
            } else {
                if(j == 0) {
                    array[i] = j;
                    i++;
                } else {
                    j = array[j - 1];
                }
            }
        }
        return array;
    }

}
