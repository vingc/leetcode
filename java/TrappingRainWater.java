/**
 * Created by vingc on 2017/5/3.
 * ref: https://leetcode.com/problems/trapping-rain-water/#/description
 * num: 42
 Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it is able to trap after raining.

 For example,
 Given [0,1,0,2,1,0,1,3,2,1,2,1], return 6.
 */
public class TrappingRainWater {
    static class Solution {
        public int trap(int[] height) {

            int left = -1,right = 0,bottom = 0; // water pool consist of left edge and right edge and bottom.
            int high,length;//hight and length of pool
            int max = 0; // the units of water can be tripped;

            for( int i = 0; i < height.length; i++ ) {
                if( height[i] <= 0 ) {
                    continue;
                }

                //find a left edge
                left = height[i];
                bottom = 0;//init the bottom.
                right = 0;//init the right
                high = 0;
                length = 0;

                //fint the right and bottom;
                for( int j = i+1; j < height.length; j++ ) {

                    if( height[j] < left && height[j] > right ) {
                        //update the bottom and right
                        bottom = right; // bottom must be the second highest edge.
                        right = height[j];

                        length = j-i-1;
                        high = right - bottom;
                        max += length * high;
                    }
                    else if( height[j] >= left ) {
                        //find the highest valid right, higher than left edge have no meaning.
                        bottom = right;
                        right = height[j];

                        length = j-i-1;
                        high = left - bottom;
                        max += length * high;
                        break;

                    }

                }


            }

            return max;

        }
    }

    public static void main( String[] args ) {
        Solution sol = new Solution();
        int[] nums = {4,2,0,3,2,5};
        System.out.println( sol.trap( nums ) );
    }
}
