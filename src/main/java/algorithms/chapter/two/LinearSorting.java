package algorithms.chapter.two;

public class LinearSorting {

    public int[] countingSort(int[] array) {
        int n = array.length;
        int[] sorted = new int[n];

        int max = findMax(array);
        int[] counter = new int[max+1];

        //counter[i] will contain the number of elements equal to i
        for(int i=0; i<n; i++) {
            counter[array[i]]++;
        }

        //counter[i] will contain the number of elements less than or equal to i
        for(int i=1; i<max+1; i++) {
            counter[i] += counter[i-1];
        }

        for(int i=n-1; i>=0; i--) {
            sorted[counter[array[i]]-1] = array[i];
            counter[array[i]]--;
        }

        return sorted;
    }

    private int findMax(int[] array) {
        int max = array[0];
        for(int i=1; i<array.length; i++) {
            if(array[i]>max) {
                max = array[i];
            }
        }
        return max;
    }

}
