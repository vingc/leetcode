/**
 * Created by vingc on 2017/6/16.
 * ref: https://leetcode.com/problems/climbing-stairs/#/description
 * num: 70
 You are climbing a stair case. It takes n steps to reach to the top.

 Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?

 Note: Given n will be a positive integer.
 */
public class ClimbingStairs {
    static class Solution {
        public int climbStairs(int n) {
            int[] step = new int[n];
            if( n <= 2 ) {
                return n;
            }

            step[0] = 1; //1 step to top
            step[1] = 2; //2 steps to top

            for( int i = 2; i < n; i++ ) {
                step[i] = step[i-1] + step[i-2];
            }

            return step[n-1];
        }
    }

    public static void main( String[] args ) {
        Solution sol = new Solution();
        System.out.println( sol.climbStairs(3) );
    }
}
