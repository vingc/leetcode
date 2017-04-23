import java.util.Arrays;

/**
 * Created by vingc on 2017/4/23.
 * ref: https://leetcode.com/problems/search-for-a-range/#/description
 * num: 34
 Given an array of integers sorted in ascending order, find the starting and ending position of a given target value.

 Your algorithm's runtime complexity must be in the order of O(log n).

 If the target is not found in the array, return [-1, -1].

 For example,
 Given [5, 7, 7, 8, 8, 10] and target value 8,
 return [3, 4].
 */
public class SearchForARange {
    static class Solution {

        private int[] bsearch( int[] nums, int target, int begin, int end ) {

            //can't find target.
            if (begin > end) {
                return new int[]{-1,-1};
            }

            //target no exist in current range.
            if (target < nums[begin] || target > nums[end]) {
                return new int[]{-1,-1};
            }

            int mid = (begin + end) / 2;

            if (target == nums[mid]) {
                int first = mid;
                int last = mid;

                //find the starting pos
                for( int i = mid-1; i >= begin; i-- ) {
                    if( nums[i] == target ) {
                        first = i;
                    }
                    else {
                        break;
                    }
                }

                //find the ending pos
                for( int i = mid+1; i <= end; i++ ) {
                    if( nums[i] == target ) {
                        last = i;
                    }
                    else {
                        break;
                    }
                }
                return new int[]{first,last};
            } else if (target > nums[mid]) {
                return bsearch(nums, target, mid + 1, end);
            } else {
                return bsearch(nums, target, begin, mid - 1);
            }
        }

        public int[] searchRange(int[] nums, int target) {
            if( nums == null || nums.length < 1 ) {
                return new int[]{-1,-1};
            }

            return bsearch( nums, target, 0, nums.length-1 );
        }
    }

    public static void main( String[] args ) {
        Solution sol = new Solution();
        int[] nums = {2,5,6,7,7,8,8,8,9};
        System.out.println( Arrays.toString( sol.searchRange(nums,8) ) );
    }
}
