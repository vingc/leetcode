import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by vingc on 2017/4/30.
 * ref: https://leetcode.com/problems/combination-sum/#/description
 * num: 39
 Given a set of candidate numbers (C) (without duplicates) and a target number (T), find all unique combinations in C where the candidate numbers sums to T.

 The same repeated number may be chosen from C unlimited number of times.

 Note:
 All numbers (including target) will be positive integers.
 The solution set must not contain duplicate combinations.
 For example, given candidate set [2, 3, 6, 7] and target 7,
 A solution set is:
 [
 [7],
 [2, 2, 3]
 ]
 */
public class CombinationSum {
    static class Solution {
        private List<List<Integer>> totalList;
        private int[] set;
        private int k;

        public List<List<Integer>> combinationSum(int[] candidates, int target) {
            totalList = new ArrayList<List<Integer>>();
            set = new int[target];
            k=0;

            Arrays.sort( candidates ); //ASC

            for( int i = 0; i < candidates.length; i++ ) {
                if( candidates[i] <= target ) {
                    set[k++] = candidates[i];
                    combination( candidates, target - candidates[i], i );
                    k--;
                }
                else {
                    break; //ASC, don't need to check the next bigger number.
                }
            }


            return totalList;

        }

        private void combination( int[] candidates, int target, int begin ) {
            if( target == 0 ) {
                //find a combination.
                //if( false == totalList.contains( subList ) )
                List<Integer> subList = new ArrayList<Integer>();
                for( int i = 0; i < k; i++ ) {
                    subList.add( set[i] );
                }
                //new ArrayList<>(Arrays.asList(set));
                totalList.add( subList );
                return;
            }
            else if( target < 0 ) {
                return;
            }

            //find a combination in which sum of numbers is equal to target.
            for( int i = begin; i < candidates.length; i++ ) {
                if( candidates[i] <= target ) {
                    set[k++] = candidates[i];
                    combination( candidates, target - candidates[i], i );
                    k--;
                }
                else {
                    break; //ASC, don't need to check the next bigger number.
                }
            }
        }
    }

    public static void main( String[] args ) {
        Solution sol = new Solution();
        int[] nums = {2,3,6,7};
        List<List<Integer>> list = sol.combinationSum( nums, 7 );
        for( List<Integer> subList: list ) {
            System.out.println( subList.toString() );
        }
        //System.out.println( sol.combinationSum( nums, 7 ) );
    }
}
