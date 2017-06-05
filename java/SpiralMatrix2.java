import java.util.Arrays;

/**
 * Created by vingc on 2017/6/5.
 * ref: https://leetcode.com/problems/spiral-matrix-ii/#/description
 * num: 59
 Given an integer n, generate a square matrix filled with elements from 1 to n2 in spiral order.

 For example,
 Given n = 3,

 You should return the following matrix:
 [
 [ 1, 2, 3 ],
 [ 8, 9, 4 ],
 [ 7, 6, 5 ]
 ]
 */
public class SpiralMatrix2 {
    static class Solution {
        public int[][] generateMatrix( int n ) {
            if ( n < 0 ) {
                return null;
            }


            int[][] matrix = new int[n][n];
            int left = 0, right = n - 1;
            int up = 0, down = n - 1;
            int num = 0;

            while( left < right && up < down ) {
                //up row
                for( int i = left; i < right; i++ ) {
                    matrix[up][i] = ++num;
                }

                //right column
                for( int i = up; i < down; i++ ) {
                    matrix[i][right] = ++num;
                }

                //down row
                for( int i = right; i > left; i-- ) {
                    matrix[down][i] = ++num;
                }

                //left column
                for( int i = down; i > up; i-- ) {
                    matrix[i][left] = ++num;
                }

                left++;
                right--;
                up++;
                down--;
            }

            if( left == right ) {
                //column
                for( int i = up; i <= down; i++ ) {
                    matrix[i][left] = ++num;
                }
            }

            //n*n don't need to check up == down

            return matrix;
        }
    }

    public static void main( String[] args ) {
        Solution sol = new Solution();
        int[][] ret = sol.generateMatrix( 5 );
        for( int i = 0; i < ret.length; i++ ) {
            for( int j = 0; j < ret.length; j++ ) {
                System.out.print( ret[i][j] + "," );
            }
            System.out.println();
        }
    }
}
