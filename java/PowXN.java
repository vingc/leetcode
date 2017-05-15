/**
 * Created by vingc on 2017/5/15.
 * ref: https://leetcode.com/problems/powx-n/#/description
 * num: 50
 Implement pow(x, n).
 */
public class PowXN {
    static class Solution {
        public double myPow(double x, int n) {
            double ret = x;
            if( n == 0 ) {
                return 1;
            }

            if( x == 0 ) {
                return 0;
            }

            if( n == Integer.MIN_VALUE ) {
                n = Integer.MAX_VALUE;
                x = 1/x;
                return x*myPow(x,n);
            }
            else if( n < 0 ) {
                n = -n;
                x = 1/x;
            }

            return n%2==0 ? myPow(x*x,n/2) : x * myPow(x*x,n/2);
        }

        //iterative one
        double myPow2(double x, int n) {
            if(n==0) return 1;
            if(n<0) {
                n = -n;
                x = 1/x;
            }
            double ans = 1;
            while(n>0){
                if((n&1) != 0 ) ans *= x;
                x *= x;
                n >>= 1;
            }
            return ans;
        }

        //double myPow
        double myPow3(double x, int n) {
            if(n==0) return 1;

            double t = myPow(x,n/2);

            if(n%2 != 0) return n<0 ? 1/x*t*t : x*t*t;
            else return t*t;
        }

        //nest myPow
        double myPow4(double x, int n) {
            if(n<0) return 1/x * myPow(1/x, -(n+1));
            if(n==0) return 1;
            if(n==2) return x*x;
            if(n%2==0) return myPow( myPow(x, n/2), 2);
            else return x*myPow( myPow(x, n/2), 2);
        }
    }

    public static void main( String[] args ) {
        Solution sol = new Solution();
        System.out.println( sol.myPow( 0, -1 ) );
        System.out.println( sol.myPow( 1, -1 ) );
        System.out.println( sol.myPow( 2, 0 ) );
        System.out.println( sol.myPow( 8.12, Integer.MIN_VALUE ) );
    }
}
