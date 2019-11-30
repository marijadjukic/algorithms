package algorithms.leetcode.divideandconquer;

import java.util.Arrays;

public class MajorityElement {

    /**
     * Boyer–Moore majority vote algorithm with linear running time and memory space O(1)
     * @param nums array of integers
     * @return majority element (element that appears at least n/2 times)
     */
    public int majorityElement(int[] nums) {
        int n = nums.length;
        int majority = nums[0];
        int counter = 0;

        for (int i=0; i<n; i++){
            if(counter == 0) {
                majority = nums[i];
                counter = 1;
            } else if (majority == nums[i]) {
                counter++;
            } else {
                counter--;
            }
        }
        return majority;
    }
}
