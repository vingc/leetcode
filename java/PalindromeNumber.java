/**
 * Created by vingc on 2017/4/5.
 * ref: https://leetcode.com/problems/palindrome-number/#/description
 * num: 9
 * Determine whether an integer is a palindrome. Do this without extra space.
 */
public class PalindromeNumber {
    static class Solution {
        public boolean isPalindrome(int x) {

            if( 0 <= x && 9 >= x )
                return true;

            if( x < 0 )
                return false;

            String src = Integer.toString( x );

            StringBuffer strb = new StringBuffer( src );

            String dst = strb.reverse().toString();

            int i;
            int len = src.length();

            for( i = 0; i < len; i++ ) {
                if( src.charAt(i) != dst.charAt(i) )
                    return false;
            }

            return true;
        }
    }

    public static void main( String[] args ) {

        Solution sol = new Solution();

        System.out.println( sol.isPalindrome( 23342 ) );
    }
}
