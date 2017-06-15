/**
 * Created by vingc on 2017/6/15.
 * ref: https://leetcode.com/problems/sqrtx/#/description
 * num: 69
 Implement int sqrt(int x).

 Compute and return the square root of x.
 */
public class Sqrtx {
    static class Solution {
        public int mySqrt(int x) {
            if( x == 0 || x == 1 ) {
                return x;
            }

            int low = 1, up = x;
            int mid;
            if( up > 65535 ) {
                up = 65535;
            }

            while( low <= up ) {
                mid = low + ( up - low ) / 2;
                long mul = (long)mid * mid;
                if( mul > x ) {
                    up = mid - 1;
                }
                else if( mul < x ) {
                    low = mid + 1;
                }
                else {
                    return mid;
                }
            }

            return up;


        }

        public int mySqrt2( int x ) {
            long r = x;
            while (r*r > x)
                r = (r + x/r) / 2;
            return (int) r;
        }
    }

    public static void main( String[] args ) {
        Solution sol = new Solution();
        System.out.println( sol.mySqrt(2147395599) );
    }
}
