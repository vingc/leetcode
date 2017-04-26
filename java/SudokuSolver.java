import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Created by vingc on 2017/4/25.
 * ref: https://leetcode.com/problems/sudoku-solver/#/description
 * num: 37
 Write a program to solve a Sudoku puzzle by filling the empty cells.

 Empty cells are indicated by the character '.'.

 You may assume that there will be only one unique solution.
 */
public class SudokuSolver {
    static class Solution {
        /*
        * 1. use a list to store the valid numbers for particular Position.
        * 2. initialize the valid numbers 1-9
        * 3. traverse the board to reset the list, if we find a 6 in one Position A.
        *    then we delete 6 from all its adjacent positions unfilled.
        *    and delete the list of Position A.
        *    if list of one Position unfilled become null, then we can't find a solution.
        * 5. use minHeap store the positions unfilled, the key is the list length of Position.
        * 6. get the top root of minHeap, fill it with one proper number N in its list,
        *    which make all its adjacent positions unfilled have at least one valid numbers except N.
        *    then delete the number N from its adjacent positions' lists.
        *    fix the minHeap.
        *    repeat this step until no Position in minHeap.
        * */
        public void solveSudoku(char[][] board) {

            int i,j,k;
            Position[] heap = new Position[81]; //minHeap
            Position[][] pos = new Position[9][9]; //i&j --> positon
            k = 0;
            //
            //Queue<Position> quque = new PriorityQueue<>();

            //initial pos
            for( i = 0; i < 9; i++ ) {
                for( j = 0;j < 9; j++ ) {
                    pos[i][j] = new Position( i, j, board[i][j] );
                }
            }

            //initial heap
            for( i = 0; i < 9; i++ )
            for( j = 0; j < 9; j++ ) {
                if( board[i][j] == '.' ) {
                    heap[k++] = pos[i][j];
                }
                else {
                    //delete current number from adjacent positions' unused numbers.
                    deleteNumber( pos[i][j], pos, board[i][j] - '0' );
                }
            }

            if( false == solve( pos, heap, k, 0 ) ) {
                System.out.println( "don't find solution" );

                return;
            }

            for( i = 0; i < 9; i++ )
            for( j = 0; j < 9; j++ ) {
                if( board[i][j] == '.' ) {
                    board[i][j] = pos[i][j].ch;
                }
            }
        }

        private boolean solve( Position[][] pos, Position[] heap, int max, int idx ) {

            //check have been finished?
            if( idx >= max ) {
                return true;
            }

            Position top = heap[idx];

            //check if top has possible numbers
            if( top.cnt <= 0 ) {
                return false;
            }

            Position[][] copy = new Position[9][9];
            for( int i = 0; i < 9; i++ )
             for( int j = 0; j < 9; j++ ){
                copy[i][j] = (Position)(pos[i][j].clone());
            }

            for( int v = 1; v < 10; v++ ) {
                if( top.numSet[v-1] == 1 ) {

                    top.ch = (char)(v + '0');
                    deleteNumber( top, pos, v );

                    //solve next
                    if( solve( pos, heap, max, idx+1 ) ) {
                        return true;
                    }

                    //can't solve the problem, use next possible number,first restore the pos state
                    for( int i = 0; i < 9; i++ )
                    for( int j = 0; j < 9; j++ ){
                        pos[i][j] = (Position)(copy[i][j].clone());
                    }
                }
            }
            return false;
        }

        //v is 1-9
        private void deleteNumber( Position cur, Position[][] pos, int v ) {

            int i = cur.x;
            int j = cur.y;
            int boxXBase = i - i%3;
            int boxYBase = j - j%3;

            int m = 0;
            v--;
            for( m = 0; m < 9; m++ ) {
                //row
                if( m != j && pos[i][m].ch == '.' && pos[i][m].numSet[v] == 1  ) {
                    pos[i][m].numSet[v] = 0;
                    pos[i][m].cnt--;
                }

                //column
                if( m != i && pos[m][j].ch == '.' && pos[m][j].numSet[v] == 1  ) {
                    pos[m][j].numSet[v] = 0;
                    pos[m][j].cnt--;
                }

                //box
                int boxX = boxXBase + m/3;
                int boxY = boxYBase + m%3;
                if( ( boxX != i && boxY != j ) && pos[boxX][boxY].ch == '.' && pos[boxX][boxY].numSet[v] == 1 ) {
                    pos[boxX][boxY].numSet[v] = 0;
                    pos[boxX][boxY].cnt--;
                }
            }

        }

        public void solveSudoku2(char[][] board) {
            dfs( board, 0 );
        }

        private boolean dfs( char[][] board, int pos ) {
            if( pos >= 81 ) {
                return true;
            }

            int x = pos / 9; //row
            int y = pos % 9; //column

            if( board[x][y] != '.' ) {
                return dfs( board, pos+1 );
            }

            for( int i = 1; i < 10; i ++ ) {
                if( isNumAvailable( board, x, y, i ) ) {
                    board[x][y] = (char)(i+'0');
                    //recursive
                    if( dfs( board, pos+1 ) ) {
                        return true;
                    }

                    //restore, and try next number
                    board[x][y] = '.';
                }
            }

            //all number is unavailable
            return false;
        }

        //check if the number i has been used in row/column/box
        private boolean isNumAvailable( char[][] board, int x, int y, int i ) {
            if( x < 0 || x >= 9 || y < 0 || y >= 9 || i < 1 || i > 9 ) {
                return false;
            }

            int boxXBase = x - x%3;
            int boxYBase = y - y%3;
            char ch = (char)(i+'0');
            for( int j = 0; j < 9; j++ ) {

                //row
                if( board[x][j] == ch  ) {
                    return false;
                }

                //column
                if( board[j][y] == ch  ) {
                    return false;
                }

                //box
                int boxX = boxXBase + j/3;
                int boxY = boxYBase + j%3;
                if( board[boxX][boxY] == ch ) {
                    return false;
                }
            }

            return true;
        }

    }

    static class Position implements Cloneable {
        int x; //row
        int y; //column
        char ch; //value

        //valid numbers unused in row & column & box
        int[] numSet;
        int cnt;

        public Position( int x, int y, char ch ) {
            this.x = x;
            this.y = y;
            this.ch = ch;

            numSet = new int[9];
            if( ch == '.' ) {
                cnt = 9;
                Arrays.fill( numSet, 1 );
            }
            else {
                cnt = 0;
                Arrays.fill( numSet, 0 );
            }
        }
        public Object clone() {
            Position pos = null;
            try{
                pos = (Position)super.clone();
            }catch(CloneNotSupportedException e) {
                e.printStackTrace();
            }
            return pos;
        }

    }

    public static void main( String[] args ) {

        Solution sol = new Solution();
        char[][] board = {  {'.','8','7','6','5','4','3','2','1'},
                            {'1','.','.','.','.','.','.','.','.'},
                            {'2','.','.','.','.','.','.','.','.'},
                            {'3','.','.','.','.','.','.','.','.'},
                            {'4','.','.','.','.','.','.','.','.'},
                            {'5','.','.','.','.','.','.','.','.'},
                            {'6','.','.','.','.','.','.','.','.'},
                            {'7','.','.','.','.','.','.','.','.'},
                            {'1','.','.','.','.','.','.','.','.'} };
        char[][] bo = new char[9][];

        {
            bo[0] = "..9748...".toCharArray();
            bo[1] = "7........".toCharArray();
            bo[2] = ".2.1.9...".toCharArray();
            bo[3] = "..7...24.".toCharArray();
            bo[4] = ".64.1.59.".toCharArray();
            bo[5] = ".98...3..".toCharArray();
            bo[6] = "...8.3.2.".toCharArray();
            bo[7] = "........6".toCharArray();
            bo[8] = "...2759..".toCharArray();
        }

        //sol.solveSudoku( bo ); // undone, need to debug
        sol.solveSudoku2( bo );
        for( int i = 0; i < 9; i++ ) {
            System.out.println( new String( bo[i] ) );
        }
    }
}
