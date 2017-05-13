/**
 * Created by vingc on 2017/5/13.
 * ref: https://leetcode.com/problems/rotate-image/#/description
 * num: 48
 You are given an n x n 2D matrix representing an image.

 Rotate the image by 90 degrees (clockwise).

 Follow up:
 Could you do this in-place?
 */
public class RotateImage {
    static class Solution {
        public void rotate(int[][] matrix) {

            //swich the x and y
            for( int i = 0; i < matrix.length; i++ )
                for( int j = 0; j < i; j++ )  {
                    int tmp = matrix[i][j];
                    matrix[i][j] = matrix[j][i];
                    matrix[j][i] = tmp;
                }

            //reverse every 1D matrix
            for( int i = 0; i < matrix.length; i++ )
                for( int j = 0 ,k = matrix.length-1; j < k; j++, k-- ) {
                    int tmp = matrix[i][j];
                    matrix[i][j] = matrix[i][k];
                    matrix[i][k] = tmp;
                }
        }
    }

    public static void main( String[] args ) {
        Solution sol = new Solution();
        int[][] nums = {{1,2,3},{4,5,6},{7,8,9}};
        sol.rotate( nums );

        for( int i = 0; i < nums.length; i++ ) {
            for (int j = 0; j < nums.length; j++) {
                System.out.print(nums[i][j] + ",");
            }
            System.out.println();
        }
    }
}
