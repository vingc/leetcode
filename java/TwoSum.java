/**
 * Created by vingc on 2017/3/30.
 * ref: https://leetcode.com/problems/two-sum/#/description
 * num: 1
 */

import java.io.*;
import java.util.*;

class Solution {
    public int[] twoSum(int[] nums, int target) {
        int i = 0, j = nums.length - 1;

        //int[] ret = new int[2];
        Map<Integer,Integer> maps = new HashMap<Integer,Integer>();

        for( i = 0; i < nums.length; i++ )
        {
            if( maps.containsKey( target - nums[i] ) )
            {
                //ret[1] = i;
                //ret[0] = maps.get( target - nums[i] );
                return new int[]{ maps.get( target - nums[i] ), i };
            }

            maps.put( nums[i], i );
        }

        return null;

    }
}

public class TwoSum {

    public static void main( String[] args ) {

        int[] arr = new int[]{3,2,4};
        int[] ret;

        Solution sol = new Solution();

        ret = sol.twoSum( arr, 6 );
        if( ret != null )
            System.out.println( Arrays.toString( ret ) );
        else
            System.out.println( "Don't content two proper integers" );

    }
}
