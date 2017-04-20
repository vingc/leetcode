import java.util.Arrays;

/**
 * Created by vingc on 2017/4/20.
 * ref: https://leetcode.com/problems/remove-element/#/description
 * num: 27
 Given an array and a value, remove all instances of that value in place and return the new length.

 Do not allocate extra space for another array, you must do this in place with constant memory.

 The order of elements can be changed. It doesn't matter what you leave beyond the new length.

 Example:
 Given input array nums = [3,2,2,3], val = 3

 Your function should return length = 2, with the first two elements of nums being 2.
 */
public class RemoveElementOfArray {
    static class Solution {
        public int removeElement(int[] nums, int val) {

            int id = 0;
            for( int i: nums ) {
                if( i != val ) {
                    nums[id++] = i;
                }
            }

            System.out.println(Arrays.toString( nums ) );
            return id;
        }
    }

    public static void main( String[] args ) {
        Solution sol = new Solution();
        int[] nums = {1,1,2,2};
        System.out.println( sol.removeElement( nums, 1 ) );
    }
}