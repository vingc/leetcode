import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by vingc on 2017/5/12.
 * ref: https://leetcode.com/problems/permutations/#/description
 * num: 46
 Given a collection of distinct numbers, return all possible permutations.

 For example,
 [1,2,3] have the following permutations:
 [
 [1,2,3],
 [1,3,2],
 [2,1,3],
 [2,3,1],
 [3,1,2],
 [3,2,1]
 ]
 */
public class Permutations {
    static class Solution {
        private List<List<Integer>> totalList;
        public List<List<Integer>> permute(int[] nums) {
            totalList = new ArrayList<List<Integer>>();
            Integer[] perm = new Integer[nums.length];

            if( nums.length > 0 )
                permuteProcess( nums, 0, perm );

            return totalList;



        }

        private void permuteProcess( int[] nums, int idx, Integer[] perm ) {
            if( idx == nums.length - 1 ) {
                perm[idx] = nums[idx];
                totalList.add( new ArrayList<Integer>( Arrays.asList(perm) ) );
                return;
            }

            //fix idx'th number, this number can be anyone between idx to nums.length-1;
            for( int i = idx; i < nums.length; i++ ) {
                perm[idx] = nums[i];
                swap( nums, idx, i );
                permuteProcess( Arrays.copyOf(nums,nums.length), idx+1, perm );
            }
        }

        private void swap( int[] nums, int a, int b ) {
            int tmp = nums[a];
            nums[a] = nums[b];
            nums[b] = tmp;
        }


        public List<List<Integer>> permute2(int[] nums) {
            totalList = new ArrayList<List<Integer>>();

            int i = 0;
            while( i < nums.length ) {
                List<Integer> subList = new ArrayList<Integer>();
                subList.add( nums[i] );
                totalList.add( subList );
                i++;
            }

            i = 0;
            while( i++ < nums.length - 1 ) {
                int size = totalList.size();
                int j = 0;
                while( j++ < size ) {
                    List<Integer> subList = totalList.remove(0);
                    for( int k = 0; k < nums.length; k++ ) {
                        if( false == subList.contains( nums[k] ) ) {
                            subList.add( nums[k] );
                            totalList.add( new ArrayList<Integer>( subList ) );
                            subList.remove( subList.size()-1 );
                        }
                    }
                }
            }

            return totalList;
        }
    }

    public static void main( String[] args ) {
        Solution sol = new Solution();
        int[] nums = {1,2,3};
        List<List<Integer>> list = sol.permute2( nums );
        for( List<Integer> sub: list ) {
            System.out.println( sub.toString() );
        }
    }
}
