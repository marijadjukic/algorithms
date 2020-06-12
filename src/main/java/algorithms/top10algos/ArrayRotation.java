package algorithms.top10algos;

public class ArrayRotation {

    /**
     * Rotates an array of n elements to the right by provided number of steps.
     * @param array array to rotate
     * @param stepNum number of steps
     * @return rotated array
     */
    public int[] rotateArray(int[] array, int stepNum) {
        int n = array.length;
        int[] rotatedArray = new int[n];

        for (int i = 0; i < n; i ++) {
            if(i < stepNum) {
                rotatedArray[i] = array[n - stepNum + i];
            } else {
                rotatedArray[i] = array[i - stepNum];
            }
        }
        return rotatedArray;
    }

    public int[] rotateArrayFast(int[] array, int stepNum) {
        int n = array.length;
        stepNum = stepNum > n ? stepNum - n : stepNum;
        int m = n - stepNum;

        // reverse first part of an array
        this.reverse(array, 0, m-1);
        // reverse second part of an array
        this.reverse(array, m, n-1);
        // reverse whole array
        this.reverse(array, 0, n-1);

        return array;
    }

    private void reverse(int[] array, int left, int right) {
        while(left < right) {
            int tmp = array[left];
            array[left] = array[right];
            array[right] = tmp;
            left++;
            right--;
        }
    }

}
