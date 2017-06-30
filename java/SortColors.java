/**
 * Created by vingc on 2017/6/30.
 * ref: https://leetcode.com/problems/sort-colors/#/description
 * num: 75
 Given an array with n objects colored red, white or blue, sort them so that objects of the same color are adjacent, with the colors in the order red, white and blue.

 Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.

 Note:
 You are not suppose to use the library's sort function for this problem.
 */
public class SortColors {
    /* 3-way quikSort: http://www.geeksforgeeks.org/3-way-quicksort-dutch-national-flag/ */
    static class Solution {
        public void sortColors(int[] nums) {
            if( nums == null || nums.length <= 1 ) {
                return;
            }

            int m = nums.length;

            int high = m-1;
            int low = 0;
            int mid = 0;
            int pivot = 1; // white

            while( mid <= high ) {

                //move red to left
                if( nums[mid] < pivot ) {
                    swap( nums, low, mid );
                    low++;
                    mid++;
                }
                else if( nums[mid] == pivot ) {
                    mid++;
                    //left white,do noting
                }
                else {
                    //move blue to right
                    swap( nums, high, mid );
                    high--;
                    //check new value of mid
                }
            }

        }

        private void swap( int[] nums, int i, int j ) {
            int tmp = nums[i];
            nums[i] = nums[j];
            nums[j] = tmp;
        }
    }

    public static void main( String[] args ) {
        Solution sol = new Solution();
        int[] nums = {1,0,2,0,1,1,0,2,1};
        sol.sortColors( nums );
        for( int i: nums ) {
            System.out.print( i + "," );
        }
    }
}
