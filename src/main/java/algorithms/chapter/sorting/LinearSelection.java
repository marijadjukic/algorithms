package algorithms.chapter.sorting;

public class LinearSelection {

    public int randomizedSelect(int[] array, int i) {
        return randomizedSelectRecursive(array, 0, array.length-1, i);
    }

    private int randomizedSelectRecursive(int[] array, int p, int r, int i) {
        if(p==r) {
            return array[p];
        }
        int q = randomizedPartition(array,p,r);
        int k = q - p + 1;

        if (i==k) {
            return array[q];
        } else if(i<k) {
            return randomizedSelectRecursive(array,p,q-1,i);
        } else {
            return randomizedSelectRecursive(array,q+1,r,i-k);
        }
    }

    private int randomizedPartition(int[] array, int p, int r) {
        int randomPivot = p + (int) Math.random() * ((r-p) +1);
        swap(array,randomPivot,r);
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
