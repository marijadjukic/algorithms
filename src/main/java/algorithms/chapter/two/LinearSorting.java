package algorithms.chapter.two;

import java.util.LinkedList;
import java.util.List;

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

    /**
     * Procedure assumes that all elements are d-digits
     * and uses counting sort as intermediate stable sort.
     * @param array array to sort
     * @return sorted array
     */
    public int[] radixSort(int[] array) {
        int d = 1; // number of digits
        int quotient = array[0]/10;
        //calculates number of digits
        while(quotient > 0){
            quotient = quotient / 10;
            d++;
        }
        int n = array.length;
        for(int i=0; i<d; i++) {
            array = radixStableSorting(array, i, n);
        }
        return array;
    }

    /**
     * Returns array sorted on d-digits position using counting sort implementation
     * @param array array to sort
     * @param d number of digits
     * @param n array length
     * @return
     */
    private int[] radixStableSorting(int[] array, int d, int n) {
        int[] sorted = new int[n];
        int[] i_digit_arr = new int[n];

        for(int j=0; j<n; j++) {
            int digit = array[j] % (int)Math.pow(10,d+1);
            i_digit_arr[j] = digit;
        }

        int max = findMax(i_digit_arr);
        int[] counter = new int[max+1];

        for(int j=0; j<n; j++) {
            counter[i_digit_arr[j]]++;
        }

        for(int j=1; j<max+1; j++) {
            counter[j] += counter[j-1];
        }

        for(int j=n-1; j>=0; j--) {
            sorted[counter[i_digit_arr[j]]-1] = array[j];
            counter[i_digit_arr[j]]--;
        }
        return sorted;
    }

    /**
     * Sorts array using bucket sorting implementation with insertion sort
     * and assumes that all elements are in range from 0 to 1
     * @param array array to sort
     * @return sorted array
     */
    public double[] bucketSort(double[] array) {
        int n = array.length;
        List<Double>[] buckets = new LinkedList[n];

        for(int i=0; i<n; i++){
            buckets[i] = new LinkedList<>();
        }

        for(int i=0; i<n; i++) {
            int index = (int) (n*array[i]);
            buckets[index].add(array[i]);;
        }

        for(int i=0; i<n; i++) {
            if(buckets[i].size()>1){
                insertionSort(buckets[i]);
            }
        }
        return concatenateBucketsToArray(buckets,n);
    }

    private void insertionSort(List<Double> bucket) {
        for(int i = 1; i<bucket.size(); i++){
            double key = bucket.get(i);
            int j = i - 1;
            while(j>=0 && bucket.get(j)>key){
                bucket.set(j+1, bucket.get(j));
                j = j - 1;
            }
            bucket.set(j+1, key);
        }
    }

    private double[] concatenateBucketsToArray(List<Double>[] buckets, int n) {
        double[] sortedArray = new double[n];
        int k=0;

        for(int i=0; i<n; i++){
            if(buckets[i].size()>1){
                for(int j=0; j<buckets[i].size(); j++) {
                    sortedArray[k] = buckets[i].get(j);
                    k++;
                }
            } else if(buckets[i].size()==1){
                sortedArray[k] = buckets[i].get(0);
                k++;
            }
        }
        return sortedArray;
    }

}
