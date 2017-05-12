import javafx.collections.transformation.TransformationList;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vingc on 2017/5/12.
 * ref: https://leetcode.com/problems/permutations-ii/#/description
 * num: 47
 Given a collection of numbers that might contain duplicates, return all possible unique permutations.

 For example,
 [1,1,2] have the following unique permutations:
 [
 [1,1,2],
 [1,2,1],
 [2,1,1]
 ]
 */
public class Permutations2 {
    static class Solution {
        public List<List<Integer>> permuteUnique(int[] nums) {
            List<List<Integer>> totalList = new ArrayList<List<Integer>>();
            List<Integer> tmp = new ArrayList<Integer>();

            if( nums.length == 0 ) {
                return totalList;
            }

            tmp.add( nums[0] );
            totalList.add( tmp );

            for( int i = 1; i < nums.length; i++ ) {
                //add i'th number into sublist, can be every place from 0 to i;
                List<List<Integer>> newTotalList = new ArrayList<List<Integer>>();
                for( int j = 0; j <= i; j++ ) {
                    //j indicate the place to add. for all subList add i'th number at j
                    for( List<Integer> subList: totalList ) {
                        List<Integer> newSubList = new ArrayList<Integer>( subList );
                        newSubList.add( j, nums[i] );
                        if( false == newTotalList.contains( newSubList ) )
                            newTotalList.add( newSubList );
                    }
                }

                totalList = newTotalList;
            }

            return totalList;
        }
    }

    public static void main( String[] args ) {
        Solution sol = new Solution();
        int[] nums = {1,1,3};
        List<List<Integer>> list = sol.permuteUnique( nums );
        for( List<Integer> sub: list ) {
            System.out.println( sub.toString() );
        }
    }
}
