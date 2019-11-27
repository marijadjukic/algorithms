package algorithms.chapter.one;

public class Sorting {

    public static void main(String[] args) {
        Sorting sort = new Sorting();
        int[] arrToSort = new int[]{4,2,5,3,6,1,5};
        
        int[] sortedArrayAsc = sort.insertionSortAsc(new int[]{4,2,5,3,6,1,-5});
        int[] sortedArrayDesc = sort.insertionSortDesc(new int[]{4,2,5,3,6,1,-5});

        printArray(sortedArrayAsc);
        System.out.println();
        printArray(sortedArrayDesc);
        System.out.println();
        printArray(sort.selectionSort(arrToSort));
        
    }

    public int[] insertionSortAsc(int[] arrayToSort) {
        for(int i = 1; i<arrayToSort.length; i++){
            int key = arrayToSort[i];
            int j = i - 1;
            while(j>=0 && arrayToSort[j]>key){
                arrayToSort[j+1] = arrayToSort[j];
                j = j - 1;
            }
            arrayToSort[j+1] = key;
        }

        return arrayToSort;
    }

    public int[] insertionSortDesc(int[] arrayToSort) {
        for(int i = 1; i<arrayToSort.length; i++){
            int key = arrayToSort[i];
            int j = i - 1;
            while(j>=0 && arrayToSort[j]<key){
                arrayToSort[j+1] = arrayToSort[j];
                j = j - 1;
            }
            arrayToSort[j+1] = key;
        }
        return arrayToSort;
    }

    public <T> Integer linearSearch(T value, T[] array) {
        for (int i = 0; i < array.length; i++) {
            if(array[i] == value) {
                return i;
            }
        }
        return null;
    }

    public int[] addTwoBinaries(int[] arr1, int[] arr2) {
        int[] sum = new int[arr1.length+1];
        int i = arr1.length-1;
        while(i>-1) {
            sum[i+1]=sum[i+1] + arr1[i] + arr2[i];
            if(sum[i+1] == 2) {
                sum[i+1] = 0;
                sum[i] = 1;
            }
            i--;
        }
        return sum;
    }

    public int[] selectionSort(int[] arr) {
        for(int i = 0; i<arr.length;i++) {
            int min = arr[i];
            int tmp = i;
            for(int j = i+1; j<arr.length; j++) {
                if(arr[j] < min) {
                    min = arr[j];
                    tmp = j;
                }
            }
            arr[tmp] = arr[i];
            arr[i] = min;
        }
        return arr;
    }

    public int[] mergeSort(int[] array, int p, int r) {
        if(p < r) {
            int q = (p + r)/2;
            mergeSort(array, p, q);
            mergeSort(array, q+1, r);
            merge(array, p, q, r);  
        }
        return array;
    }

    private int[] merge(int[] arr, int p, int q, int r) {
        int[] left = new int[q-p+1];
        int[] right = new int[r-q];
        for(int i = 0; i < left.length; i ++) {
            left[i] = arr[p+i];
        }
        for(int j = 0; j < right.length; j++) {
            right[j] = arr[q+j+1];
        }
        int i = 0, j = 0;
        int k = p;
        while(i < left.length && j < right.length){
            if(left[i] <= right[j]) {
                arr[k] = left[i];
                i++;
            } else {
                arr[k] = right[j];
                j++;
            }
            k++;
        }
        while(i < left.length) {
            arr[k] = left[i];
            i++;
            k++;
        }
        while(j < right.length) {
            arr[k] = right[j];
            j++;
            k++;
        }
        return arr;
    }

    public Integer binarySearch(int value, int[] array) {
        int start = 0;
        int mid = array.length/2;
        int end = array.length;
        while(start<end) {
            if(value < array[mid]) {
                end = mid;
                mid = (start + end)/2; 
            } else if (value > array[mid]) {
                start = mid + 1;
                mid = ((end - mid + 1) + end)/2;
            } else {
                return mid;
            }
        }
        return null;
    }

    public int[] bubbleSort(int[] array) {
        for(int i = 0; i<array.length-1; i++){
            for(int j = array.length-1; j>i; j--) {
                if(array[j] < array[j-1]){
                    int tmp = array[j-1];
                    array[j-1] = array[j];
                    array[j] = tmp;
                }
            }
        }
        return array;
    }

    private static void printArray(int[] array) {
        for(int element : array) {
            System.out.print(element + ", ");
        }
    }

}