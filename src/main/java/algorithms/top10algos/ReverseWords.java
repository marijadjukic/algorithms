package algorithms.top10algos;

public class ReverseWords {

    public String reverseWords(String s) {
        String[] words = s.split(" ");
        int end = words.length - 1;
        for (int i = 0; i < words.length/2; i++) {
            String tmp = words[i];
            words[i] = words[end - i];
            words[end - i] = tmp;
        }
        return String.join(" ", words);
    }

    public char[] reverseStringAsCharArray(char[] stringAsChar) {
        int stringLength = stringAsChar.length;
        int i = 0;
        //reverse each word in array except last word
        for (int j = 0; j < stringLength; j++) {
            if(stringAsChar[j] == ' ') {
                reverse(stringAsChar, i, j-1);
                i = j + 1;
            }
        }

        // reverse last word
        reverse(stringAsChar, i, stringLength - 1);

        // reverse whole string
        reverse(stringAsChar, 0, stringLength - 1);

        return stringAsChar;
    }

    private void reverse(char[] array, int left, int right) {
        while(left < right) {
            char tmp = array[left];
            array[left] = array[right];
            array[right] = tmp;
            left++;
            right--;
        }
    }

}
