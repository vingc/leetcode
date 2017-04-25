import java.util.Arrays;

/**
 * Created by vingc on 2017/4/25.
 * ref: https://leetcode.com/problems/valid-sudoku/#/description
 * num: 36
 Determine if a Sudoku is valid, according to: Sudoku Puzzles - The Rules.

 The Sudoku board could be partially filled, where empty cells are filled with the character '.'.
 A valid Sudoku board (partially filled) is not necessarily solvable. Only the filled cells need to be validated.
 */
public class ValidSudoku {
    static class Solution {
        public boolean isValidSudoku(char[][] board) {

            if( board == null ) {
                return false;
            }

            if( board.length != 9 ) {
                return false;
            }

            if( board[0].length != 9 ) {
                return false;
            }

            //check the first rule and second rule
            //each column or row must have numbers 1-9 occuring just once
            int[] numsR = new int[9]; //row,map
            int[] numsC = new int[9]; //column,map
            int[][] numsB = new int[9][9];//sub boxes,map

            int i,j,value,boxIdx;

            for( i = 0; i < numsB.length; i++ ) {

                Arrays.fill(numsB[i], 0 ); //don't have be filled by number
            }

            for( i = 0; i < 9; i++ ) {

                Arrays.fill(numsR, 0 ); //don't have be filled by number
                Arrays.fill(numsC, 0 ); //don't have be filled by number
                for( j = 0; j < 9; j++ ) {
                    //i row, j column
                    if( board[i][j] != '.' ) {
                        value = board[i][j] - '1';
                        if( value < 0 || value > 8 ) {
                            return false;
                        }
                        if( numsR[value] !=0 ) {
                            //duplicate in same row
                            return false;
                        }

                        numsR[value] = 1; //fill one row,index by char
                    }


                    //j row, i column
                    if( board[j][i] != '.' ) {
                        value = board[j][i] - '1';
                        if( value < 0 || value > 8 ) {
                            return false;
                        }
                        if( numsC[value] != 0 ) {
                            //duplicate in same column
                            return false;
                        }

                        numsC[value] = 1; //fill one column, index by char

                        //check box
                        boxIdx = (j/3)*3+(i/3);
                        if( numsB[boxIdx][value] != 0 ) {
                            //duplicate in same subbox
                            return false;
                        }
                        numsB[boxIdx][value] = 1; //fill one box, index by char
                    }
                }
            }

            return true;
        }
        public boolean isValidSudoku2(char[][] board) {
            int [] vset = new int [9];
            int [] hset = new int [9];
            int [] bckt = new int [9];
            int idx = 0;
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    if (board[i][j] != '.') {
                        idx = 1 << (board[i][j] - '0') ;

                        if ((hset[i] & idx) > 0 ||
                            (vset[j] & idx) > 0 ||
                            (bckt[(i / 3) * 3 + j / 3] & idx) > 0)
                            return false;

                        hset[i] |= idx;
                        vset[j] |= idx;
                        bckt[(i / 3) * 3 + j / 3] |= idx;
                    }
                }
            }
            return true;
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

        System.out.println( sol.isValidSudoku( board ) );
    }
}
