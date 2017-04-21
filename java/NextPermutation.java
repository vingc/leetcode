import java.util.Arrays;

/**
 * Created by vingc on 2017/4/21.
 * ref: https://leetcode.com/problems/next-permutation/#/description
 * ref2: http://blog.csdn.net/jeasn168/article/details/39047685
 * num: 31
 implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.

 If such arrangement is not possible, it must rearrange it as the lowest possible order (ie, sorted in ascending order).

 The replacement must be in-place, do not allocate extra memory.

 Here are some examples. Inputs are in the left-hand column and its corresponding outputs are in the right-hand column.
 1,2,3 → 1,3,2
 3,2,1 → 1,2,3
 1,1,5 → 1,5,1
 */
public class NextPermutation {
    static class Solution {
        public void nextPermutation(int[] nums) {
            int i,j,k;

            int len = nums.length;

            if( len <= 1 ) {
                return;
            }

            //1. if the last two elements is ASC, then swap them to get the next permutation.
            if( nums[len-1] > nums[len-2] ) {
                swap( nums, len-1, len-2 );
                return;
            }

            //2. else if the last s elements is DESC, then find the lowest k in last s which is bigger than len - s -1 element,
            // then swap k and len-s-1, then reverse the last s elements, to get the next permutation.

            i = len - 3;
            while( i >= 0 && nums[i] >= nums[i+1] ) i--;

            if( i == -1 ) {
                //all the array is DESC, then reverse whole,
                reverseArray( nums, 0 );
                return;
            }

            j = i;
            while( j < nums.length-1 && nums[j+1] > nums[i] ) j++;

            //now, nums[j] > nums[i] and nums[j+1] < nums[i], swap them;
            swap( nums, i, j );

            //reverse the last s elements which are DESC.
            reverseArray( nums, i+1 );

            return;
        }

        private void reverseArray( int[] nums, int begin ) {

            for( int i = begin,j = nums.length-1;  i < j; i++,j-- ) {
                swap( nums, i, j );
            }
        }

        private void swap( int[] nums, int i, int j ) {
            if( i < 0 || i >= nums.length || j < 0 || j >= nums.length ) {
                return;
            }

            int tmp = nums[i];
            nums[i] = nums[j];
            nums[j] = tmp;
            return;
        }


        public void nextLessPermutation(int[] nums) {
            int i,j,k;

            int len = nums.length;

            if( len <= 1 ) {
                return;
            }

            //1. if the last two elements is DESC, then swap them to get the next less permutation.
            if( nums[len-1] < nums[len-2] ) {
                swap( nums, len-1, len-2 );
                return;
            }

            //2. else if the last s elements is ASC, then find the biggest k in last s which is less than len - s -1 element,
            // then swap k and len-s-1, then reverse the last s elements, to get the next less permutation.

            i = len - 3;
            while( i >= 0 && nums[i] <= nums[i+1] ) i--;

            if( i == -1 ) {
                //3. if all the array is ASC, then reverse whole,
                reverseArray( nums, 0 );
                return;
            }

            j = i;
            while( j < nums.length-1 && nums[j+1] < nums[i] ) j++;

            //now, nums[j] < nums[i] and nums[j+1] > nums[i], swap them;
            swap( nums, i, j );

            //reverse the last s elements which are DESC.
            reverseArray( nums, i+1 );

            return;
        }
    }

    public static void main( String[] args ) {
        Solution sol = new Solution();
        int[] nums = {6,4,2,3};
        sol.nextLessPermutation( nums );
        System.out.println(Arrays.toString( nums ) );
    }
}
