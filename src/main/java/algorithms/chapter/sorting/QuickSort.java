package algorithms.chapter.sorting;

public class QuickSort {

    public int[] quickSort(int[] array) {
        quickSortRecursive(array,0,array.length-1);
        return array;
    }

    public int[] randomizedQuickSort(int[] array) {
        randomizedQuickSortRecursive(array, 0, array.length-1);
        return array;
    }

    public int[] hoareQuickSort(int[] array) {
        hoareQuickSortRecursive(array, 0, array.length-1);
        return array;
    }

    private void quickSortRecursive(int[] array, int p, int r) {
        if(p<r){
            int q = partition(array, p, r);
            quickSortRecursive(array,p,q-1);
            quickSortRecursive(array,q+1,r);
        }
    }

    private void randomizedQuickSortRecursive(int[] array, int p, int r) {
        if(p<r){
            int q = randomizedPartition(array, p, r);
            randomizedQuickSortRecursive(array,p,q-1);
            randomizedQuickSortRecursive(array,q+1,r);
        }
    }

    private void hoareQuickSortRecursive(int[] array, int p, int r) {
        if(p<r) {
            int q = hoarePartition(array, p, r);
            hoareQuickSortRecursive(array, p, q);
            hoareQuickSortRecursive(array, q+1, r);
        }
    }

    private int partition(int[] array, int p, int r) {
        int pivot = array[r];
        int i = p-1;
        for(int j=p; j<r; j++) {
            if(array[j]<=pivot){
                i++;
                swap(array,i,j);
            }
        }
        swap(array,i+1,r);
        return i+1;
    }

    private int randomizedPartition(int[] array, int p, int r) {
        int randomPivot = p + (int) Math.random() * ((r-p) +1);
        swap(array,randomPivot,r);
        return partition(array,p,r);
    }

    private int hoarePartition(int[] array, int p, int r) {
        int pivot = array[p];
        int i = p - 1;
        int j = r + 1;
        while(true){
            do {
                j--;
            } while (array[j] > pivot);
            do {
                i++;
            } while (array[i] < pivot);
            if(i < j) {
                swap(array,i,j);
            } else return j;
        }

    }

    private void swap(int[] array, int i, int j) {
        int tmp = array[j];
        array[j] = array[i];
        array[i] = tmp;
    }

}
