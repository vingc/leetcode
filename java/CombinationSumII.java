import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by vingc on 2017/4/30.
 * ref: https://leetcode.com/problems/combination-sum-ii/#/description
 * num: 40
 Given a collection of candidate numbers (C) and a target number (T), find all unique combinations in C where the candidate numbers sums to T.

 Each number in C may only be used once in the combination.

 Note:
 All numbers (including target) will be positive integers.
 The solution set must not contain duplicate combinations.
 For example, given candidate set [10, 1, 2, 7, 6, 1, 5] and target 8,
 A solution set is:
 [
 [1, 7],
 [1, 2, 5],
 [2, 6],
 [1, 1, 6]
 ]
 */
public class CombinationSumII {
    static class Solution {
        private List<List<Integer>> totalList;
        private int[] set;
        private int k;

        public List<List<Integer>> combinationSum2(int[] candidates, int target) {
            totalList = new ArrayList<List<Integer>>();
            set = new int[target];
            k=0;

            Arrays.sort( candidates ); //ASC

            for( int i = 0; i < candidates.length; i++ ) {
                if( i == 0 || candidates[i] != candidates[i-1] ) { //skip duplicates
                    if (candidates[i] <= target) {
                        set[k++] = candidates[i];
                        combination(candidates, target - candidates[i], i + 1 );
                        k--;
                    } else {
                        break; //ASC, don't need to check the next bigger number.
                    }
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
                if( i == begin || candidates[i] != candidates[i-1] ) { // skip duplicates
                    if (candidates[i] <= target) {
                        set[k++] = candidates[i];
                        combination(candidates, target - candidates[i], i + 1);
                        k--;
                    } else {
                        break; //ASC, don't need to check the next bigger number.
                    }
                }
            }
        }
    }

    public static void main( String[] args ) {
        Solution sol = new Solution();
        int[] nums = {2,3,6,7};//target 7
        int[] nums2 = {10, 1, 2, 7, 6, 1, 5}; //target 8
        int[] nums3 = {2,2,2}; //target 4
        List<List<Integer>> list = sol.combinationSum2( nums3, 4 );
        for( List<Integer> subList: list ) {
            System.out.println( subList.toString() );
        }
        //System.out.println( sol.combinationSum( nums, 7 ) );
    }
}
