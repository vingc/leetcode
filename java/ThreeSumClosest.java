import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by vingc on 2017/4/13.
 * ref; https://leetcode.com/problems/3sum-closest/#/description
 * num: 16
 * Given an array S of n integers, find three integers in S such that the sum is closest to a given number, target.
 * Return the sum of the three integers. You may assume that each input would have exactly one solution.
 * For example, given array S = {-1 2 1 -4}, and target = 1.
 * The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
 */
public class ThreeSumClosest {
    static class Solution {
        public int threeSumClosest(int[] nums, int target) {
            int i,j,k;
            int minDiff,curDiff;
            int ret = 0;
            int end = 0;

            if( nums.length < 3 ) {
                return 0;
            }

            Arrays.sort( nums );
           // System.out.println( Arrays.toString( nums ) );
            minDiff = Integer.MAX_VALUE;
            for( i = 0; i < nums.length - 2; i++ ) {
                j = i+1;
                k = nums.length-1;
                while( j < k ) {
                    curDiff = target - nums[j] - nums[k] - nums[i];
                    if( Math.abs( curDiff ) < minDiff ) {
                        //current subarray is closer to target than last subarray.
                        ret = nums[i] + nums[j] + nums[k];
                        minDiff = Math.abs( curDiff );
                        //System.out.println( nums[i] + "," + nums[j] + "," + nums[k] );

                        if( curDiff == 0 ) {
                            end = 1;
                            break;
                        }
                    }


                    if( curDiff < 0 ) {
                        k--; //decrease the sum;
                    }
                    else {
                        j++; //increase the sum;
                    }
                }

                if( end == 1 ) {
                    break;
                }
            }

            return ret;
        }
    }

    public static void main( String[] args ) {
        Solution sol = new Solution();
        int[] nums = {-1, 2, 1, -4};
        int[] nums2 = {0,0,0};
        System.out.println( sol.threeSumClosest( nums2, 1 ) );
    }
}
