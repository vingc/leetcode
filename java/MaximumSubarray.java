/**
 * Created by vingc on 2017/5/24.
 * ref: https://leetcode.com/problems/maximum-subarray/#/description
 * num: 53
 Find the contiguous subarray within an array (containing at least one number) which has the largest sum.

 For example, given the array [-2,1,-3,4,-1,2,1,-5,4],
 the contiguous subarray [4,-1,2,1] has the largest sum = 6.
 */
public class MaximumSubarray {
    static class Solution {
        public int maxSubArray(int[] nums) {
            int max = Integer.MIN_VALUE;
            int sum = 0;
            for( int i = 0; i < nums.length; i++ ) {
                if( sum < 0 ) {
                    sum = nums[i];
                }
                else {
                    sum += nums[i];
                }

                if( sum > max ) {
                    max = sum;
                }

            }

            return max;
        }
    }

    public static void main( String[] args ) {
        Solution sol = new Solution();
        int[] nums = {-2,1,-3,4,-1,2,1,-5,4};
        System.out.println( sol.maxSubArray( nums ) );
    }
}
