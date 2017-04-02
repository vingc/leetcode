/**
 * Created by vingc on 2017/4/2.
 * ref: https://leetcode.com/problems/string-to-integer-atoi/#/description
 * num: 8
 * implement atoi
 */
public class StringToInteger {
    static class Solution {
        public int myAtoi(String str) {

            //int src = Integer.parseInt( str );
            int len = str.length();

            if( len == 0 )
                return 0;

            long dst = 0;
            int i = 0;


            //skip the space or tab
            while( str.charAt( i ) == ' ' || str.charAt( i ) == '\b' )
                i++;

            if( i >= len )
                return 0;

            //check the plus or minus
            int sign = 1;
            if( str.charAt( i ) == '-' ) {
                sign = -1;
                i++;
            }
            else if ( str.charAt( i ) == '+' ) {
                sign = 1;
                i++;
            }

            if( i >= len )
                return 0;

            //convertion
            char ch = str.charAt( i++ );
            while( ch >= '0' && ch <= '9' ) {

                dst = dst * 10 + sign * ( ch - '0' );

                if( dst > Integer.MAX_VALUE ) {
                    dst = Integer.MAX_VALUE;
                    break;
                }
                else if( dst < Integer.MIN_VALUE ) {
                    dst = Integer.MIN_VALUE;
                    break;
                }

                if( i >= len )
                    break;
                ch = str.charAt( i++ );
            }


            return (int)dst;
        }
    }

    public static void main( String[] args ) {
        Solution sol = new Solution();
        System.out.println( "123 -> " + sol.myAtoi( "+123" ) );
        System.out.println( "-123 -> " + sol.myAtoi( "-123" ) );
        System.out.println( "0 -> " + sol.myAtoi( "0" ) );
        System.out.println( "0123 -> " + sol.myAtoi( "  0123" ) );
        System.out.println( "0123q -> " + sol.myAtoi( " 0123q" ) );
    }
}
