import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by vingc on 2017/4/11.
 * ref: https://leetcode.com/problems/3sum/#/description
 * num: 15
 *
 * Given an array S of n integers, are there elements a, b, c in S such that a + b + c = 0? Find all unique triplets in the array which gives the sum of zero.
 * Note: The solution set must not contain duplicate triplets.
 * For example, given array S = [-1, 0, 1, 2, -1, -4],
 * A solution set is:
 *  [
 *  [-1, 0, 1],
 *  [-1, -1, 2]
 * ]
 *
 */

public class ThressSum {
    static class Solution {
        public List<List<Integer>> threeSum(int[] nums) {

            List<List<Integer>> longList = new ArrayList<List<Integer>>();
            if( nums.length < 3 ) {
                return longList;
            }

            Arrays.sort( nums ); //ASC

            int i,j,k;

            for( i = 0; i < nums.length - 2; i++ ) {
                //skip the duplicate a[i];
                if( i > 0 && nums[i] == nums[i-1] ) {
                    continue;
                }

                j = i+1;
                k = nums.length - 1;
                while( j < k ) {
                    if( nums[i] + nums[j] + nums[k] == 0 ) {
                        //get
                        List<Integer> list = new ArrayList<>();
                        list.add( nums[i] );
                        list.add( nums[j] );
                        list.add( nums[k] );
                        longList.add( list );

                        //move
                        j++;
                        while( nums[j-1] == nums[j] && j < k )
                            j++; //skip the duplicate nums[j]

                        k--;
                        while( nums[k] == nums[k+1] && j < k )
                            k--; //skip the duplicate nums[k]
                }
                    else if( nums[i] + nums[j] + nums[k] <= 0 ) {
                        j++; //let nums[j] be bigger
                        while( nums[j-1] == nums[j] && j < k )
                            j++; //skip the duplicate nums[j]

                    }
                    else {
                        k--; //let nums[k] be smaller
                        while( nums[k] == nums[k+1] && j < k )
                            k--; //skip the duplicate nums[k]
                    }
                }
            }

            return longList;
        }
    }

    public static void main( String[] args ) {
        Solution sol = new Solution();
        //int[] nums = {-1, 0, 1, 2, -1, -4};
        int[] nums = {-2,0,0,2,2};
        List<List<Integer>> ll = sol.threeSum( nums );

        System.out.print( "[" );
        for( int i = 0; i < ll.size(); i++ ) {
            System.out.println( );
            List<Integer> li = ll.get( i );
            System.out.print(li);
        }
        System.out.print( "\n]" );
    }
}
