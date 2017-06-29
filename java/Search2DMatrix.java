/**
 * Created by vingc on 2017/6/29.
 * ref: https://leetcode.com/problems/search-a-2d-matrix/#/description
 * num: 74
 Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:

 Integers in each row are sorted from left to right.
 The first integer of each row is greater than the last integer of the previous row.
 For example,

 Consider the following matrix:

 [
 [1,   3,  5,  7],
 [10, 11, 16, 20],
 [23, 30, 34, 50]
 ]
 Given target = 3, return true.
 */
public class Search2DMatrix {
    static class Solution {
        public boolean searchMatrix(int[][] matrix, int target) {
            if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
                return false;
            }

            int m = matrix.length;
            int n = matrix[0].length;

            int i = 0;
            int j = n - 1;

            while (i < m && j >= 0) {
                if (target == matrix[i][j]) {
                    return true;
                }

                if (target < matrix[i][j]) {
                    j--;
                } else {
                    i++;
                }
            }

            return false;
        }


        //binarySearch
        public boolean searchMatrix2(int[][] matrix, int target) {
            if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
                return false;
            }

            int m = matrix.length;
            int n = matrix[0].length;

            int begin = 0;
            int end = n*m - 1;

            while( begin <= end ) {
                int mid = (begin+end)/2;
                int value = matrix[mid/n][mid%n];
                if( target == value ) {
                    return true;
                }

                if( target < value ) {
                    end = mid-1;
                }
                else {
                    begin = mid+1;
                }
            }

            return false;

        }
    }

    public static void main( String[] args ) {
        Solution sol = new Solution();
        int[][] matrix = { {1,2,3,4},{5,6,7,8},{9,10,11,12} };
        System.out.println( sol.searchMatrix2( matrix, 18 ) );
    }
}
