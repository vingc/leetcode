/**
 * Created by vingc on 2017/4/20.
 * ref: https://leetcode.com/problems/divide-two-integers/#/description
 * num; 29
 Divide two integers without using multiplication, division and mod operator.

 If it is overflow, return MAX_INT.

 */
public class DivideTwoIntegers {
    static class Solution {
        public int divide(int dividend, int divisor) {
            long quotient = 0;
            long remain = dividend;
            long subtractor = divisor;
            int minus = 0;
            long multi = 0;


            if( divisor == 0 ) {
                return -1;
            }

            if( remain < 0 ) {
                minus = 1;
                remain = Math.abs(remain);
            }

            if( subtractor < 0 ) {
                minus = minus ^ 1;
                subtractor = Math.abs(subtractor);
            }

            int power = 0;
            while( remain > subtractor ) {
                power++;
                subtractor = subtractor << 1;
            }

            while( power >= 0 && remain > 0 ) {
                if( remain < subtractor ) {
                    power--;
                    subtractor = subtractor >> 1;
                }
                else {
                    multi = 0;
                    while( remain >= subtractor ) {
                        multi++;
                        remain -= subtractor;
                    }

                    quotient += multi<<power;
                }
            }

            if( minus == 1 && quotient != 0 ) {

                if( quotient > Integer.MAX_VALUE ) {
                    return Integer.MIN_VALUE;
                }
                quotient = (~quotient + 1)|0x80000000; //negative
            }
            else if( quotient >= Integer.MAX_VALUE ) {
                return Integer.MAX_VALUE;
            }
            return (int)quotient;
        }
    }

    public static void main( String[] args ) {
        Solution sol = new Solution();
        System.out.println( sol.divide( -10, 11 ) );
    }
}
