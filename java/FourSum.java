import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by vingc on 2017/4/17.
 * ref: https://leetcode.com/problems/4sum/#/description
 * num: 18
 */

/*
* Given an array S of n integers, are there elements a, b, c, and d in S such that a + b + c + d = target?
* Find all unique quadruplets in the array which gives the sum of target.
* Note: The solution set must not contain duplicate quadruplets.
* For example, given array S = [1, 0, -1, 0, -2, 2], and target = 0.
A solution set is:
[
  [-1,  0, 0, 1],
  [-2, -1, 1, 2],
  [-2,  0, 0, 2]
]
* */

public class FourSum {
    static class Solution {
        public List<List<Integer>> fourSum(int[] nums, int target) {

            Arrays.sort( nums );//ASC

            int i,j,k,l; //index of quadruplet
            int sum;
            List<List<Integer>> list = new ArrayList<>();

            for( i = 0; i < nums.length - 3; i++ ) {
                //skip the duplicate nums[i]
                if( i > 0 && nums[i] == nums[i-1] )
                    continue;

                for( j = i + 1; j < nums.length - 2; j++ ) {

                    //skip the duplicate nums[j]
                    if( j > i + 1 && nums[j] == nums[j-1] )
                        continue;

                    k = j + 1;
                    l = nums.length - 1;
                    sum = target - nums[i] - nums[j];
                    while( k < l ) {
                        if( nums[k] + nums[l] == sum ) {
                            //find a quadruplet;
                            list.add( Arrays.asList( nums[i], nums[j], nums[k], nums[l] ) );

                            //move
                            while( k < l && nums[k] == nums[k+1] ) k++;
                            while( k < l && nums[l] == nums[l-1] ) l--;
                            k++;
                            l--;
                        }
                        else if( nums[k] + nums[l] < sum ) {
                            //increase nums[k]
                            while( k < l && nums[k] == nums[k+1] ) k++;
                            k++;
                        }
                        else {
                            //decrease nums[l]
                            while( k < l && nums[l] == nums[l-1] ) l--;
                            l--;
                        }

                    }
                }
            }

            return list;
        }
    }

    public static void main( String[] args ) {
        Solution sol = new Solution();
        int[] nums = {1, 0, -1, 0, -2, 2};
        System.out.println( sol.fourSum( nums, 0 ) );
    }
}
