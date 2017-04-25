/**
 * Created by vingc on 2017/4/25.
 * ref: https://leetcode.com/problems/search-insert-position/#/description
 * num: 35
 Given a sorted array and a target value, return the index if the target is found. If not, return the index where it would be if it were inserted in order.

 You may assume no duplicates in the array.

 Here are few examples.
 [1,3,5,6], 5 → 2
 [1,3,5,6], 2 → 1
 [1,3,5,6], 7 → 4
 [1,3,5,6], 0 → 0
 */
public class SearchInsertPosition {
    static class Solution {
        private int bsearch( int[] nums, int target, int begin, int end ) {
            /* has checked the edge cases in searchInsert()
            //target less than the nums[0]
            if( begin < 0 || end < 0 ) {
                return 0;
            }

            //target bigger than the nums[nums.length-1]
            if( begin >= nums.length || end >= nums.length ) {
                return nums.length - 1;
            }

            //can't find the target, and target bigger than end
            if( begin > end ) {
                return begin;
            }
            */

            int mid = (begin + end) / 2;
            if( nums[mid] == target ) {
                return mid;
            }

            if( target < nums[mid] ) {
                if( begin == mid ) {
                    //target bigger than begin-1, and less than begin
                    return begin;
                }

                return bsearch( nums, target, begin, mid-1 );
            }
            else {
                if( mid == end ) {
                    //begin = end = mid, target bigger than end,
                    return end+1;
                }

                return bsearch( nums, target, mid+1, end );
            }
        }

        public int searchInsert(int[] nums, int target) {
/*
            //check edge cases
            if( target <= nums[0] ) {
                return 0;
            }
            else if( target == nums[nums.length-1] ) {
                return nums.length - 1;
            }
            else if( target > nums[nums.length-1]  ) {
                return nums.length;
            }*/

            return bsearch( nums, target, 0, nums.length-1 );
        }
    }

    public static void main( String[] args ) {
        Solution sol = new Solution();
        int[] nums = {1,3,5,6};
        System.out.println( sol.searchInsert( nums, 7 ) );
    }
}
