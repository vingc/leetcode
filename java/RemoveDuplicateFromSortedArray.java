import java.util.Arrays;

/**
 * Created by vingc on 2017/4/19.
 * ref: https://leetcode.com/problems/remove-duplicates-from-sorted-array/#/description
 * num: 26

 Given a sorted array, remove the duplicates in place such that each element appear only once and return the new length.

 Do not allocate extra space for another array, you must do this in place with constant memory.

 For example,
 Given input array nums = [1,1,2],

 Your function should return length = 2, with the first two elements of nums being 1 and 2 respectively.
 It doesn't matter what you leave beyond the new length.

 */
public class RemoveDuplicateFromSortedArray {
    static class Solution {
        public int removeDuplicates(int[] nums) {

            if( nums.length < 2 ) {
                return nums.length;
            }

            int dup = 0;
            int i,j,k;
            int first; //the first occurrence
            int last;  //the last occurrence
            int save = 0;
            dup = nums[nums.length-1];
            first = nums.length-1;
            last = first;

            //traverse from end to begin, if find duplicate number, collapse to one number.
            for( i = nums.length - 2; i >=0; i-- ) {
                if( nums[i] == dup ) {
                    //find duplicate
                    last = i;
                    if( i == 0 ) {
                        //copy the tail from first to last place.
                        for( j = last, k = first; k < nums.length; j++, k++ ) {
                            nums[j] = nums[k];
                        }
                        save += first - last; // store the numbers be replaced.
                    }
                }
                else {
                    if( first != last ) {
                        //copy the tail from first to last place.
                        for( j = last, k = first; k < nums.length; j++, k++ ) {
                            nums[j] = nums[k];
                        }
                        save += first - last; // store the numbers be replaced.
                    }

                    //update index
                    dup = nums[i];
                    first = i;
                    last = first;
                }
            }



            System.out.println(Arrays.toString( nums ) );
            return nums.length - save;
        }
    }

    public static void main( String[] args ) {
        Solution sol = new Solution();
        int[] nums = {1,1,2,2,3,4,5,5,6,7,8,9};
        System.out.println( sol.removeDuplicates( nums ) );
    }
}
