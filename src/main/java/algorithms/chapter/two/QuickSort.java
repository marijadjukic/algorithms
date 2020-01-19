package algorithms.chapter.two;

public class QuickSort {

    public int[] quickSort(int[] array) {
        quickSortRecursive(array,0,array.length-1);
        return array;
    }

    private void quickSortRecursive(int[] array, int p, int r) {
        if(p<r){
            int q = partition(array, p, r);
            quickSortRecursive(array,p,q-1);
            quickSortRecursive(array,q+1,r);
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

    private void swap(int[] array, int i, int j) {
        int tmp = array[j];
        array[j] = array[i];
        array[i] = tmp;
    }

}
