/**
 * Created by vingc on 2017/4/22.
 * ref: https://leetcode.com/problems/search-in-rotated-sorted-array/#/description
 * num: 33
 Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.

 (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).

 You are given a target value to search. If found in the array return its index, otherwise return -1.

 You may assume no duplicate exists in the array.
 */
public class SearchInRotatedSortedArray {
    static class Solution {
        private int bsearch(int[] nums, int target, int begin, int end) {
            //can't find target.
            if (begin > end) {
                return -1;
            }

            //target no exist in current range.
            if (target < nums[begin] || target > nums[end]) {
                return -1;
            }

            int mid = (begin + end) / 2;

            if (target == nums[mid]) {
                return mid;
            } else if (target > nums[mid]) {
                return bsearch(nums, target, mid + 1, end);
            } else {
                return bsearch(nums, target, begin, mid - 1);
            }
        }

        public int search(int[] nums, int target) {
            // 1. find the pivot
            // 2. if target bigger than first element, then binary search in left subarray before pivot
            //    else binary search in right subarray before pivot.

            if (nums == null || nums.length <= 0) {
                return -1;
            }

            int pivot = 0;
            int i;
            for (i = 0; i < nums.length - 1; i++) {
                if (nums[i] > nums[i + 1]) {
                    pivot = i+1;
                    break;
                }
            }

            if (pivot == 0) {
                return bsearch(nums, target, 0, nums.length - 1);
            }

            if (target >= nums[0]) {
                return bsearch(nums, target, 0, pivot-1 );
            } else {
                return bsearch(nums, target, pivot, nums.length - 1);
            }
        }
    }

    public static void main( String[] args ) {
        int[] nums = {4, 5, 6, 7, 0, 1, 2};
        Solution sol = new Solution();
        System.out.println( sol.search( nums, 0 ) );
    }
}
