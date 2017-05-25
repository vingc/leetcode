/**
 * Created by vingc on 2017/5/25.
 * ref: https://leetcode.com/problems/jump-game/#/description
 * num: 55
 Given an array of non-negative integers, you are initially positioned at the first index of the array.

 Each element in the array represents your maximum jump length at that position.

 Determine if you are able to reach the last index.

 For example:
 A = [2,3,1,1,4], return true.

 A = [3,2,1,0,4], return false.
 */
public class JumpGame {
    static class Solution {
        public boolean canJump(int[] nums) {
            int newlevelEnd = 0;
            int lastLevelEnd = 0; //the last index last step can reach

            if( nums == null || nums.length <= 1 ) {
                return true;
            }

            int i = 0;
            while( i <= lastLevelEnd ) {
                //traverse last level, to get the furthest position next level can reach.
                while( i <= lastLevelEnd ) {
                    newlevelEnd = Math.max( newlevelEnd, nums[i] + i );
                    if( newlevelEnd >= nums.length - 1 ) {
                        return true;
                    }
                    i++;
                }
                lastLevelEnd = newlevelEnd;

            }

            return false;
        }
    }

    public static void main( String[] args ) {
        Solution sol = new Solution();
        int[] nums = {2,3,1,1,4};
        System.out.println( sol.canJump( nums ) );
        nums = new int[]{3,2,1,0,4};
        System.out.println( sol.canJump( nums ) );
    }
}
