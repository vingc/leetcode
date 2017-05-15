import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by vingc on 2017/5/15.
 * ref: https://leetcode.com/problems/n-queens/#/description
 * num: 51
 The n-queens puzzle is the problem of placing n queens on an n×n chessboard such that no two queens attack each other.
 Given an integer n, return all distinct solutions to the n-queens puzzle.

 Each solution contains a distinct board configuration of the n-queens' placement, where 'Q' and '.' both indicate a queen and an empty space respectively.

 For example,
 There exist two distinct solutions to the 4-queens puzzle:

 [
 [".Q..",  // Solution 1
 "...Q",
 "Q...",
 "..Q."],

 ["..Q.",  // Solution 2
 "Q...",
 "...Q",
 ".Q.."]
 ]
 */
public class NQueens {
    static class Solution {
        private List<List<String>> totalList;

        public List<List<String>> solveNQueens(int n) {

            totalList = new ArrayList<>();

            if( n == 0 ) {
                return totalList;
            }

            if( n == 1 ) {
                List<String> subList = new ArrayList<>();
                subList.add("Q");
                totalList.add(subList);
                return totalList;
            }

            char[][] board = new char[n][n];
            for( int i = 0; i < n; i++ ) {
                Arrays.fill( board[i], '.' );
            }

            solve( board, 0 );
            return totalList;
        }


        private void solve( char[][] board, int row ) {
            //first in first line
            for( int i = 0; i < board.length; i++ ) {
                if( isAvailPos( board, row, i ) ) {
                    board[row][i] = 'Q';
                    if( row == board.length - 1 ) {
                        //find the last position, save
                        List<String> list = new ArrayList<>();
                        for( int j = 0; j < board.length; j++ ) {
                            String str = new String( board[j] );
                            list.add( str );
                        }
                        totalList.add( list );

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
        List<List<String>> list = sol.solveNQueens( 4 );
        for( List<String> sub: list ) {
            System.out.println( sub.toString() );
            System.out.println("-----------");
        }
    }
}
