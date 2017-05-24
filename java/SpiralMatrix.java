import java.util.ArrayList;
import java.util.List;

/**
 * Created by vingc on 2017/5/24.
 * ref: https://leetcode.com/problems/spiral-matrix/#/description
 * num: 54
 Given a matrix of m x n elements (m rows, n columns), return all elements of the matrix in spiral order.

 For example,
 Given the following matrix:

 [
 [ 1, 2, 3 ],
 [ 4, 5, 6 ],
 [ 7, 8, 9 ]
 ]
 You should return [1,2,3,6,9,8,7,4,5].
 */
public class SpiralMatrix {
    static class Solution {
        public List<Integer> spiralOrder(int[][] matrix) {
            List<Integer> list = new ArrayList<>();

            if ( matrix == null || matrix.length == 0 ) {
                return list;
            }

            int left = 0, right = matrix[0].length-1;
            int top = 0, bottom = matrix.length-1;
            while( top < bottom && left < right ) {
                //top
                for( int i = left; i < right; i++ ) {
                    list.add( matrix[top][i] );
                }

                //right
                for( int i = top; i < bottom; i++ ) {
                    list.add( matrix[i][right] );
                }

                //bottom
                for( int i = right; i > left; i-- ) {
                    list.add( matrix[bottom][i] );
                }

                //left
                for( int i = bottom; i > top; i-- ) {
                    list.add( matrix[i][left] );
                }

                top++;
                right--;
                bottom--;
                left++;
            }

            if( left == right ) {
                //right
                for( int i = top; i <= bottom; i++ ) {
                    list.add( matrix[i][right] );
                }
            }
            else if( top == bottom ) {
                //top
                for( int i = left; i <= right; i++ ) {
                    list.add( matrix[top][i] );
                }
            }

            return list;
        }
    }

    public static void main( String[] args ) {
        Solution sol = new Solution();
        int[][] nums = { {1,2,3}, {4,5,6}, {7,8,9} };

        List<Integer> list = sol.spiralOrder( nums );
        System.out.println( list.toString() );
    }
}
