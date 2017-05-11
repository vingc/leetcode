import java.util.Arrays;

/**
 * Created by vingc on 2017/5/11.
 * ref: https://leetcode.com/problems/jump-game-ii/#/description
 * num: 45
 Given an array of non-negative integers, you are initially positioned at the first index of the array.

 Each element in the array represents your maximum jump length at that position.

 Your goal is to reach the last index in the minimum number of jumps.

 For example:
 Given array A = [2,3,1,1,4]

 The minimum number of jumps to reach the last index is 2. (Jump 1 step from index 0 to 1, then 3 steps to the last index.)

 Note:
 You can assume that you can always reach the last index.
 */
public class JumpGame2 {
    static class Solution {

        private int jumpGreedy( int[] nums, int begin, int[] step ) {
            if( nums.length - 1 <= begin + nums[begin] ) {
                //last jump
                return 1;
            }

            int min = nums.length;
            int tmp;
            for( int i = nums[begin]; i > 0; i-- ) {
                if( step[begin+i] != -1 ) {
                    tmp = step[begin+i];
                }
                else {
                    tmp = jumpGreedy(nums, begin + i, step );
                }

                if( tmp < min ) {
                    min = tmp;
                }
            }

            step[begin] = 1 + min;
            return 1 + min;
        }

        public int jump(int[] nums) {

            if( nums.length <= 1 ) {
                return 0;
            }

            int[] step = new int[nums.length];
            Arrays.fill( step, -1 );

            return jumpGreedy( nums, 0, step );
        }

        //O(n^2)
        public int jump2(int[] nums) {

            if( nums.length <= 1 ) {
                return 0;
            }

            int[] step = new int[nums.length];
            Arrays.fill( step, -1 );

            for( int i = nums.length - 1; i >= 0; i-- ) {
                if( nums[i] == 0 ) {
                    step[i] = nums.length;
                    continue;
                }

                if( nums[i] + i >= nums.length - 1 ) {
                    step[i] = 1;
                    continue;
                }

                //get the minimum number of jumps from i
                int min = nums.length;
                for( int j = i+1, k = 0; k < nums[i]; k++,j++ ) {
                    if( step[j] < min ) {
                        min = step[j];
                    }
                }
                if( min == nums.length ) {
                    step[i] = min;
                }
                else {
                    step[i] = 1 + min;
                }
            }

            return step[0];
        }

        //O(n)
        public int jump3(int[] nums) {

            if( nums.length <= 1 ) {
                return 0;
            }

            /* 2 3 2 1 4 5
            2    --- 0
            3 2  --- 1
            1 4  --- 2
            5    --- 3
            nodes can be reached in the minimum number of level - step        */

            int i = 0;
            int level = 0;
            int levelEnd = 0;
            int nextLevelEnd = 0;
/*
            while( levelEnd - i + 1 > 0 ) { //current level have nodes
                while( i <= levelEnd ) {
                    nextLevelEnd = nextLevelEnd > nums[i]+i ? nextLevelEnd : nums[i]+i; //get the farthest nodes can be reached in next level.
                    if( nextLevelEnd >= nums.length - 1 ) {
                        return level + 1;
                    }
                    i++;
                }
                level++;
                levelEnd = nextLevelEnd;
            }
*/
            while( i <= levelEnd ) { //current level have nodes

                nextLevelEnd = nextLevelEnd > nums[i]+i ? nextLevelEnd : nums[i]+i; //get the farthest nodes can be reached in next level.
                if( nextLevelEnd >= nums.length - 1 ) {
                    return level + 1;
                }

                i++;
                if( i > levelEnd ) {
                    level++;
                    levelEnd = nextLevelEnd;
                }
            }
            return 0;
        }
    }

    public static void main( String[] args ) {
        Solution sol = new Solution();
        int[] nums = {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
        System.out.println( sol.jump3( nums ) );
        System.out.println( nums.length );
    }
}
