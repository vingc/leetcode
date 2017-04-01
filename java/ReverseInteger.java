/**
 * Created by vingc on 2017/4/2.
 * ref: https://leetcode.com/problems/reverse-integer/#/description
 * num: 7
 *
 * Reverse digits of an integer.
 * Example1: x = 123, return 321
 * Example2: x = -123, return -321
 */
public class ReverseInteger {
    static class Solution {
        public int reverse(int x) {

            if( x == 0 )
                return x;

            String src = Integer.toString( x );

            //strip the '-'
            if( x < 0 )
                src = src.substring( 1, src.length() );

            StringBuffer srcBuff = new StringBuffer( src );
            String dst = srcBuff.reverse().toString();

            long dstDec = Long.parseLong( dst );

            if( x < 0 ) {
                if( dstDec > (long)Integer.MAX_VALUE + 1 )
                    return 0;//overflow, less than -2^31

                return -1 * (int)dstDec;
            }
            else {
                if( dstDec > Integer.MAX_VALUE )
                    return 0; //overflow, more than 2^31 - 1;

                return (int)dstDec;
            }

        }
        public int reverse2(int x) {

            if( x == 0 )
                return 0;

            long dst;
            int len = 0;
            int ret;
            int[] digital = new int[10]; //32 bit,10 is the max length of decimal

            ret = x;
            while( ret != 0 ) {
                digital[len++] = ret % 10;
                ret = ret / 10;
            }

            dst = 0;
            int i = 0;
            while( i < len ) {
                dst = digital[i] + dst * 10;
                i++;
            }

            if( dst < 0  && dst < Integer.MIN_VALUE ) {
                return 0;
            }
            else if ( dst > 0 && dst > Integer.MAX_VALUE ) {
                return 0;
            }

            /*
            long rev= 0;
            while( x != 0){
                rev= rev*10 + x % 10;
                x= x/10;
                if( rev > Integer.MAX_VALUE || rev < Integer.MIN_VALUE)
                    return 0;
            }
            */
            return (int)dst;
        }
    }

    public static void main( String[] args ) {
        Solution sol = new Solution();

        System.out.println( 123 + "   " + sol.reverse2( 123 ) );
    }
}
