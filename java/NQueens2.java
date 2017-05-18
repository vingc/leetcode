import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by vingc on 2017/5/18.
 * ref: https://leetcode.com/problems/n-queens-ii/#/description
 * num: 52
 Follow up for N-Queens problem.

 Now, instead outputting board configurations, return the total number of distinct solutions.
 */
public class NQueens2 {
    static class Solution {
        private int total;
        public int totalNQueens(int n) {

            if( n == 0 ) {
                return 0;
            }

            if( n == 1 ) {
                return 1;
            }

            total = 0;

            char[][] board = new char[n][n];
            for( int i = 0; i < n; i++ ) {
                Arrays.fill( board[i], '.' );
            }

            solve( board, 0 );
            return total;
        }


        private void solve( char[][] board, int row ) {
            //first in first line
            for( int i = 0; i < board.length; i++ ) {
                if( isAvailPos( board, row, i ) ) {
                    board[row][i] = 'Q';
                    if( row == board.length - 1 ) {
                        //find the last position, save
                        total++;

                        board[row][i] = '.';
                        return;
                    }

                    solve( board, row+1 );
                    board[row][i] = '.';
                }
            }
        }


        private boolean isAvailPos( char[][] board, int x, int y ) {

            //find bottom of slash /, x+y is constant
            int bottomX = ( x + y <= board.length - 1 ) ? x+y : board.length-1;
            int bottomY = ( x + y <= board.length - 1 ) ? 0 : (x+y - board.length + 1);

            //find top of slash \,
            int topX;
            int topY;
            if( y > x ) {
                topX = 0;
                topY = y - x;
            }
            else {
                topX = x-y;
                topY = 0;
            }

            for( int i = 0; i < board.length; i++ ) {
                //check row
                if( board[x][i] == 'Q' ) {
                    return false;
                }

                //check column
                if( board[i][y] == 'Q' ) {
                    return false;
                }

                //check slash /
                int x1 = bottomX - i;
                int y1 = bottomY + i;
                if( x1 >= 0 && y1 <= board.length - 1 && board[x1][y1] == 'Q' ) {
                    return false;
                }

                //check slash \
                int x2 = topX + i;
                int y2 = topY + i;
                if( x2 <= board.length - 1 && y2 <= board.length - 1 && board[x2][y2] == 'Q' ) {
                    return false;
                }
            }

            return true;
        }
    }

    public static void main( String[] args ) {
        Solution sol = new Solution();
        System.out.println(sol.totalNQueens(8));
    }
}
