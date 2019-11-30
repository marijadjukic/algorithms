package algorithms.leetcode.divideandconquer;

public class MaximumSubarray {

    public int maxSubArray(int[] nums) {
        return findMaximumSubarray(nums,0,nums.length-1);
    }

    private int findMaximumSubarray(int[] nums, int low, int high) {

        if (low==high) {
            return nums[low];
        } else{
            int mid = (low + high - 1)/2;
            int leftMaxSubArray = findMaximumSubarray(nums, low, mid);
            int rightMaxSubArray = findMaximumSubarray(nums,mid+1, high);
            int crossingMaxSubArray = findMaxCrossingSubarray(nums, low, mid, high);

            if (leftMaxSubArray >= rightMaxSubArray && leftMaxSubArray >= crossingMaxSubArray) {
                return leftMaxSubArray;
            } else if (rightMaxSubArray >= leftMaxSubArray && rightMaxSubArray >= crossingMaxSubArray) {
                return rightMaxSubArray;
            } else {
                return crossingMaxSubArray;
            }
        }

    }

    private int findMaxCrossingSubarray(int[] nums, int low, int mid, int high) {
        int leftSum = Integer.MIN_VALUE;
        int rightSum = Integer.MIN_VALUE;

        int sum = 0;
        for(int i=mid; i>=low; i--) {
            sum = sum + nums[i];
            if(sum > leftSum){
                leftSum = sum;
            }
        }

        sum = 0;
        for(int j=mid+1; j<=high; j++) {
            sum = sum + nums[j];
            if(sum > rightSum) {
                rightSum = sum;
            }
        }

        return leftSum + rightSum;
    }
}
