/**
 * Created by vingc on 2017/5/3.
 * ref: https://leetcode.com/problems/first-missing-positive/#/description
 * num: 41
 Given an unsorted integer array, find the first missing positive integer.

 For example,
 Given [1,2,0] return 3,
 and [3,4,-1,1] return 2.

 Your algorithm should run in O(n) time and uses constant space.
 */
public class FirstMissingPositive {
    static class Solution {
        public int firstMissingPositive(int[] nums) {

            if( nums.length == 0 ) {
                return 1;
            }



            //1. move 1 onto zero index of array, other bigger num move to num - 1 index of array.
            int i = 0;
            int idx;
            while( i < nums.length ) {
                if (nums[i] <= 0) {
                    i++; //check next; ignore the negative num or zero
                    continue;
                } else if (nums[i] > nums.length) {
                    i++; //check next; ignore bigger than the length.
                    continue;
                }

                idx = nums[i] - 1;
                if (idx == i) {
                    i++;//check next; don't need change.
                    continue;
                }

                if (nums[idx] != nums[i]) {
                    //swap num at i and idx
                    int tmp = nums[i];
                    nums[i] = nums[idx];
                    nums[idx] = tmp;
                    //check new num at i again;
                } else {
                    //duplicate num,
                    nums[i] = -1;
                    i++;
                }
            }

            //2. find the missing positive num; if pos is negative num, mean that miss positive num in this pos.
            for( i = 0; i < nums.length; i++ ) {
                if( nums[i] != i+1 ) {
                    //find the first negative in nums or zero or bigger than length.
                    break;
                }
            }


            return i+1;
        }
    }

    public static void main( String[] args ) {
        Solution sol = new Solution();
        int[] nums = {-1,4,2,3};
        System.out.println( sol.firstMissingPositive( nums ) );
    }
}
