/**
 * Created by vingc on 2017/4/10.
 * ref: https://leetcode.com/problems/roman-to-integer/#/description
 * num: 13
 */
public class RomanToInteger {
    static class Solution {
        public int romanToInt(String s) {
            int[] roman = new int[128];

            roman['I'] = 1;
            roman['V'] = 5;
            roman['X'] = 10;
            roman['L'] = 50;
            roman['C'] = 100;
            roman['D'] = 500;
            roman['M'] = 1000;

            s = s.toUpperCase();

            int i;
            int len = s.length();
            char last = 0;
            char cur = 0;
            int sum = 0;

            for( i = len-1; i >= 0; i-- ) {
                cur = s.charAt( i );
                if( roman[cur] >= roman[last] ) {
                    sum += roman[cur];
                }
                else {
                    sum -= roman[cur];
                }
                last = cur;
            }

            return sum;
        }
    }

    public static void main( String[] args ) {
        Solution sol = new Solution();

        System.out.println( sol.romanToInt( "CXCIX" ) );//199
    }
}
