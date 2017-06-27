/**
 * Created by vingc on 2017/6/27.
 * ref: https://leetcode.com/problems/set-matrix-zeroes/#/description
 * num: 73
 Given a m x n matrix, if an element is 0, set its entire row and column to 0. Do it in place.
 */
public class SetMatrixZeroes {
    static class Solution {
        /* 1: use n+m space, scan the matrix to find which row or column need to be set to zero, mark it in n+m space,
              then set the row or column marked in the first phase
           2: use constant space, scan the matrix to find which row or column need to be set to zero, mark it in the first row
              or first column, use extra space to mark the first row or first column, then set the row or column marked
        */
        public void setZeroes(int[][] matrix) {

            int col0 = 1;
            int row0 = 1;

            int m = matrix.length;
            int n = matrix[0].length;
            for( int i = 0; i < m; i++ ) {
                if( matrix[i][0] == 0 ) col0 = 0;
            }

            for( int j = 0; j < n; j++ ) {
                if( matrix[0][j] == 0 ) row0 = 0;
            }

            for( int i = 1; i < m; i++ ) {
                for( int j = 1; j < n; j++ ) {
                    if( matrix[i][j] == 0 ) {
                        matrix[i][0] = 0;
                        matrix[0][j] = 0;
                    }
                }
            }


            for( int i = m-1; i >= 1; i-- ) {
                for( int j = n-1; j >= 1; j-- ) {
                    if( matrix[i][0] == 0 || matrix[0][j] == 0 ) {
                        matrix[i][j] = 0;
                    }
                }
            }

            if( row0 == 0 )
                for( int j = 0; j < n; j++ ) {
                    matrix[0][j] = 0;
                }
            if( col0 == 0 )
                for( int i = 0; i < m; i++ ) {
                    matrix[i][0] = 0;
                }
        }
    }

    public static void main( String[] args ) {
        Solution sol = new Solution();
        int[][] matrix = new int[][]{ {0,1,1},{1,1,1},{1,1,1} };
        sol.setZeroes( matrix );
        for( int i = 0; i < matrix.length; i++ ) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(matrix[i][j] + ",");
            }
            System.out.println();
        }
    }
}
