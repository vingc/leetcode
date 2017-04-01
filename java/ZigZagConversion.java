import java.util.Arrays;

/**
 * Created by vingc on 2017/4/2.
 * ret: https://leetcode.com/problems/zigzag-conversion/#/description
 * num: 6
 * examples:
 * if row = 2 and str = "ABC",
 * then: A C
 *       B
 * if row = 4 and str = "ABCDEFGHIJ"
 * then:
 * A    G
 * B E  H
 * C  F I
 * D    j
 */
public class ZigZagConversion {
    static class Solution {
        private int i = -1;
        private int j = 0;
        private int step = 0;
        private int route = 0; //0 vertical, 1 slash


        private void getNextPos( int numRows ) {
            if( route == 0 ) {
                //route is vertical

                if( step >= numRows )
                {

                    if( numRows == 2 ) {
                        //special, no slash route
                        i = 0;
                        j++;
                        step = 1;
                        return;
                    }
                    // route of vertical has reached the bottom,then change route to slash
                    route = 1;
                    step = 1;

                    //the top postion on slash
                    j++;
                    i = 1;
                    return;
                }
                else {
                    i++;
                    step++;
                    return;
                }
            }
            else {
                //route is slash

                if( step >= numRows - 2 ) {
                    //change the route to vertical
                    route = 0;
                    step = 1;
                    i = 0;
                    j++;
                    return;
                }
                else {
                    i++;
                    j++;
                    step++;
                    return;
                }
            }

        }

        public String convert(String s, int numRows) {
            i = -1;
            j = 0;
            step = 0;
            route = 0;
            int len = s.length();

            if( numRows < 2 || len == 0 ) {
                return s;
            }

            int column = ( numRows - 1 ) * ( len/(2*numRows-2) + 1 );
            char[][] conv = new char[numRows][column];
            int k = 0;

            for( k = 0; k < len; k++ )
            {
                getNextPos( numRows );
                conv[i][j] = s.charAt( k );
            }

            char[] ret = new char[len];
            k = 0;

            //read char row by row.
            for( i = 0; i < numRows; i++ )
                for( j = 0; j < column; j++ )
                {
                    if( conv[i][j] != 0 ) {
                        ret[k++] = conv[i][j];
                    }
                }

            return new String( ret );
        }

    }

    public static void main( String[] args ) {
        Solution sol = new Solution();
        String str = "ABV";
        int row = 2;

        System.out.println( sol.convert( str, row ) );
    }
}
